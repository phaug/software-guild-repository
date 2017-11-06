/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class SuperPersonDaoJdbcTemplateImpl implements SuperPersonDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_PERSON
            = "insert into superperson (superName, superDescription, side, powerId) "
            + "value (?, ?, ?, ?)";

    private static final String SQL_DELETE_PERSON
            = "delete from superperson where superPersonId = ?";

    private static final String SQL_DELETE_SUPERPERSONORGANIZATION
            = "delete from superpersonorganization where superPersonId = ?";

    private static final String SQL_DELETE_SUPERPERSONSIGHTING
            = "delete from superpersonsighting where superPersonId = ?";

    private static final String SQL_UPDATE_PERSON
            = "update superperson set superName = ?, superDescription = ?, side = ?, "
            + "powerId = ? where superPersonId = ?";

    private static final String SQL_SELECT_SUPERPERSON
            = "select * from superperson where superpersonId = ?";

    private static final String SQL_INSERT_SUPERPERSONORGANIZATION
            = "insert into superPersonOrganization (superPersonId, organizationId) values(?, ?)";

    private static final String SQL_INSERT_SUPERPERSONSIGHTING
            = "insert into superpersonsighting (superPersonId, sightingId) values(?, ?)";

    private static final String SQL_SELECT_ALL_SUPERPERSONS
            = "select * from superperson";

    private static final String SQL_SELECT_POWER_BY_PERSONID
            = "select p.* from power p "
            + "inner join superperson on p.powerId = superperson.powerId "
            + "where superperson.superPersonId = ?";

    private static final String SQL_SELECT_PERSON_BY_LOCATIONID
            = "select p.* from superperson p "
            + "inner join superpersonsighting on p.superPersonId = superpersonsighting.superPersonId "
            + "inner join sighting on sighting.sightingId = superpersonsighting.sightingId "
            + "inner join location on sighting.locationId = location.locationId "
            + "where location.locationId = ?";

    private static final String SQL_SELECT_PERSONS_BY_ORGANIZATIONID
            = "select p.* from superperson p "
            + "inner join superpersonorganization on p.superPersonId = superpersonorganization.superPersonId "
            + "inner join organization org on org.organizationId = superpersonorganization.organizationId "
            + "where org.organizationId = ?";
    private static final String SQL_SELECT_SIGHTING_BY_PERSONID
            = "select sight.* from sighting sight  "
            + "	inner join superpersonsighting on sight.sightingId = superpersonsighting.sightingId "
            + "    inner join superperson p on p.superPersonId = superpersonsighting.superpersonId "
            + "    where p.superPersonId = ?";

    private static final String SQL_SELECT_ORGANIZATION_BY_PERSONID
            = "select o.* from organization o "
            + "inner join superpersonorganization on o.organizationId = superpersonorganization.organizationId "
            + "inner join superperson p on p.superPersonId = superpersonorganization.superPersonId "
            + "where p.superPersonId = ?";

    private static final String SQL_SELECT_ORGID_BY_PERSONID
            = "select organizationId from superpersonorganization"
            + "where superpersonId = ?";

    private static final class PersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setSuperName(rs.getString("superName"));
            sp.setSuperDescription(rs.getString("superDescription"));
            sp.setSide(rs.getInt("side"));
            sp.setPersonId(rs.getInt("superPersonId"));
            return sp;
        }
    }

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

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sightingId"));
            s.setDate(rs.getDate("date").toLocalDate());
            return s;
        }
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerName(rs.getString("powName"));
            p.setPowerId(rs.getInt("powerId"));
            return p;
        }
    }

    private void insertSuperPersonOrganization(SuperPerson person) {
        final int personId = person.getPersonId();
        final List<Organization> orgs = person.getOrganization();

        for (Organization currentOrg : orgs) {
            jdbcTemplate.update(SQL_INSERT_SUPERPERSONORGANIZATION,
                    personId,
                    currentOrg.getOrganizationId());
        }
    }

    private void insertSuperPersonSighting(SuperPerson person) {
        final int personId = person.getPersonId();
        final List<Sighting> sighting = person.getSighting();

        for (Sighting currentSighting : sighting) {
            jdbcTemplate.update(SQL_INSERT_SUPERPERSONSIGHTING,
                    personId,
                    currentSighting.getSightingId());
        }
    }

    private List<Organization> findOrganizationsForPerson(SuperPerson person) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_PERSONID,
                new OrganizationMapper(),
                person.getPersonId());
    }

    private List<Sighting> findSightingsForPerson(SuperPerson person) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_PERSONID,
                new SightingMapper(),
                person.getPersonId());
    }

    private Power findPowerbyPersonId(SuperPerson person) {
        return jdbcTemplate.queryForObject(SQL_SELECT_POWER_BY_PERSONID,
                new PowerMapper(),
                person.getPersonId());
    }

    private List<SuperPerson>
            associateOrganizationandPowerwithPersons(List<SuperPerson> personList) {
        // set the complete list of org ids for persons
        for (SuperPerson currentPerson : personList) {
            // add orgs to current persons
            currentPerson.setOrganization(findOrganizationsForPerson(currentPerson));
            // add the sighting to current persons
            currentPerson.setSighting(findSightingsForPerson(currentPerson));
            // add the power to current persons
            currentPerson.setPower(findPowerbyPersonId(currentPerson));
        }
        return personList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SuperPerson addPerson(SuperPerson person
    ) {
        jdbcTemplate.update(SQL_INSERT_PERSON,
                person.getSuperName(),
                person.getSuperDescription(),
                person.getSide(),
                person.getPower().getPowerId());

        person.setPersonId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        insertSuperPersonOrganization(person);
        insertSuperPersonSighting(person);
        return person;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePerson(int superPersonId
    ) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSONSIGHTING, superPersonId);
        jdbcTemplate.update(SQL_DELETE_SUPERPERSONORGANIZATION, superPersonId);
        jdbcTemplate.update(SQL_DELETE_PERSON, superPersonId);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updatedPerson(SuperPerson updatePerson
    ) {
        jdbcTemplate.update(SQL_UPDATE_PERSON,
                updatePerson.getSuperName(),
                updatePerson.getSuperDescription(),
                updatePerson.getSide(),
                updatePerson.getPower().getPowerId(),
                updatePerson.getPersonId());

        jdbcTemplate.update(SQL_DELETE_SUPERPERSONORGANIZATION, updatePerson.getPersonId());
        insertSuperPersonOrganization(updatePerson);
    }

    @Override
    public SuperPerson getPersonbyId(int id
    ) {
        try {
            SuperPerson superPerson = jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON,
                    new PersonMapper(),
                    id);

            superPerson.setPower(findPowerbyPersonId(superPerson));
            superPerson.setOrganization(findOrganizationsForPerson(superPerson));
            return superPerson;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllPersons() {
        List<SuperPerson> personList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSONS,
                new PersonMapper());
        return associateOrganizationandPowerwithPersons(personList);
    }

    @Override
    public List<SuperPerson> getPersonByLocationId(int locationId
    ) {
        List<SuperPerson> personList = jdbcTemplate.query(SQL_SELECT_PERSON_BY_LOCATIONID,
                new PersonMapper(),
                locationId);
        return associateOrganizationandPowerwithPersons(personList);
    }

    @Override
    public List<SuperPerson> getPersonsByOrganizationId(int orgId
    ) {
        List<SuperPerson> personList = jdbcTemplate.query(SQL_SELECT_PERSONS_BY_ORGANIZATIONID,
                new PersonMapper(),
                orgId);
        return associateOrganizationandPowerwithPersons(personList);
    }
    
    @Override
    public Set<Integer> getOrgIdbyPersonId (int personId) {
         return new HashSet<>(jdbcTemplate.queryForList(SQL_SELECT_ORGID_BY_PERSONID, Integer.class));
         
    }

}
