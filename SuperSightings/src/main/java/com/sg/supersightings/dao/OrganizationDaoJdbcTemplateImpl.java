/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class OrganizationDaoJdbcTemplateImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (orgName, description, phone, locationId) "
            + "value (?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationId = ?";

    private static final String SQL_DELETE_SUPERPERSONORGANIZATION
            = "delete from superpersonorganization where organizationId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set orgName = ?, description = ?, phone = ?, "
            + "locationId = ? where organizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organizationId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";

    private static final String SQL_INSERT_SUPERPERSONORGANIZATION
            = "insert into superPersonOrganization (organizationId, superPersonId) values(?, ?)";

    private static final String SQL_SELECT_ORGANIZATION_BY_PERSONID
            = "select o.* from organization o "
            + "inner join superpersonorganization on o.organizationId = superpersonorganization.organizationId "
            + "inner join superperson p on p.superPersonId = superpersonorganization.superPersonId "
            + "where p.superPersonId = ?";

    private static final String SQL_SELECT_PERSONS_BY_ORGANIZATIONID
            = "select p.* from superperson p "
            + "inner join superpersonorganization on p.superPersonId = superpersonorganization.superPersonId "
            + "inner join organization org on org.organizationId = superpersonorganization.organizationId "
            + "where org.organizationId = ?";

    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATIONID
            = "select l.* from location l "
            + "inner join organization on l.locationId = organization.locationId "
            + "where organization.organizationId = ?";

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationId(rs.getInt("organizationId"));
            o.setOrgName(rs.getString("orgName"));
            o.setDescription(rs.getString("description"));
            o.setPhone(rs.getString("phone"));
            return o;
        }
    }

    private static final class PersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setSuperName(rs.getString("superName"));
            sp.setDescription(rs.getString("superDescription"));
            sp.setSide(rs.getInt("side"));
            sp.setPersonId(rs.getInt("superPersonId"));
            return sp;
        }
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationName(rs.getString("locationName"));
            l.setDescription(rs.getString("description"));
            l.setAddress(rs.getString("address"));
            l.setLatitude(rs.getBigDecimal("latitude"));
            l.setLongitude(rs.getBigDecimal("longitude"));
            l.setLocationId(rs.getInt("locationId"));
            return l;
        }
    }

    private void insertSuperPersonOrganization(Organization organization) {
        final int organizationId = organization.getOrganizationId();
        final List<SuperPerson> sp = organization.getSuperPersons();
        // Update the SuperPersonOrganization bridge table with an entry for 
        //each person of org.
        for (SuperPerson currentPerson : sp) {
            jdbcTemplate.update(SQL_INSERT_SUPERPERSONORGANIZATION,
                    organizationId,
                    currentPerson.getPersonId());
        }

    }

    private List<SuperPerson> findPersonsForOrg(Organization org) {
        return jdbcTemplate.query(SQL_SELECT_PERSONS_BY_ORGANIZATIONID,
                new PersonMapper(),
                org.getOrganizationId());
    }

    private Location findLocationForOrganization(Organization org) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATIONID,
                new LocationMapper(),
                org.getOrganizationId());
    }

    private List<Organization>
            associateLocationsandPersonsWithOrgs(List<Organization> orgList) {
        // set the complete list of superperson ids for each org
        for (Organization currentOrg : orgList) {
            // add persons to current org
            currentOrg.setSuperPersons(findPersonsForOrg(currentOrg));
            // add the Publisher to current book
            currentOrg.setLocation(findLocationForOrganization(currentOrg));
        }
        return orgList;
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getOrgName(),
                organization.getDescription(),
                organization.getPhone(),
                organization.getLocation().getLocationId());
        organization.setOrganizationId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        insertSuperPersonOrganization(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        //delete bridgt relationship
        jdbcTemplate.update(SQL_DELETE_SUPERPERSONORGANIZATION, organizationId);
        //delete org
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization updateOrg) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                updateOrg.getOrgName(),
                updateOrg.getDescription(),
                updateOrg.getLocation().getLocationId(),
                updateOrg.getPhone(),
                updateOrg.getOrganizationId());
        // delete superpersonorganization relationships and then reset them
        jdbcTemplate.update(SQL_DELETE_SUPERPERSONORGANIZATION, updateOrg.getOrganizationId());
        insertSuperPersonOrganization(updateOrg);
    }

    @Override
    public Organization getOrganizationbyId(int orgId) {
        try {
            // get the properties from the books table
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(),
                    orgId);
            // get the Authors for this book and set list on the book
            org.setSuperPersons(findPersonsForOrg(org));
            // get the Publisher for this book
            org.setLocation(findLocationForOrganization(org));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        //set location and person(s) for each org
        return associateLocationsandPersonsWithOrgs(orgList);
    }

    @Override
    public List<Organization> getOrganizationsByPersonID(int personId) {
        List<Organization> orgList =
                jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_PERSONID, 
                        new OrganizationMapper(),
                        personId);
        
        return associateLocationsandPersonsWithOrgs(orgList);
    }

}
