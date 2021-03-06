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
public class SuperPersonDaoJdbcTemplateImpl implements SuperPersonDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_PERSON
            = "insert into superperson (superName, description, sideId, powerId) "
            + "value (?, ?, ?, ?)";

    private static final String SQL_DELETE_PERSON
            = "delete from superperson where superpersonId = ?";

    private static final String SQL_DELETE_SUPERPERSONORGANIZATION
            = "delete from superpersonorganization where superPersonId = ?";

    private static final String SQL_DELETE_SUPERPERSONSIGHTING
            = "delete from superpersonsighting where superPersonId = ?";

    private static final String SQL_UPDATE_PERSON
            = "update superperson set superName = ?, description = ?, sideId = ?, "
            + "powerId = ? where superpersonId = ?";

    private static final String SQL_SELECT_SUPERPERSON
            = "select * from superperson where superpersonId = ?";

    private static final String SQL_INSERT_SUPERPERSONORGANIZATION
            = "insert into superPersonOrganization (superPersonId, organizationId) values(?, ?)";

    private static final String SQL_INSERT_SUPERPERSONSIGHTING
            = "insert into superpersonsighting (superPersonId, sightingId) values(?, ?)";

    private static final String SQL_SELECT_POWER_FOR_SUPERPERSON
            = "select power.powerId from superperson s "
            + "inner join power on s.powerId = power.powerId "
            + " where power.powerId = ?";

    private static final String SQL_SELECT_ALL_SUPERPERSONS
            = "select * from superperson";

    private static final String SQL_SELECT_PERSON_BY_LOCATIONID
            = "select p.* from superperson p "
            + "inner join superpersonsighting on p.superpersonId = superpersonsighting.superpersonId "
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

    private static final class PersonMapper implements RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setSuperName(rs.getString("superName"));
            sp.setDescription(rs.getString("description"));
            sp.setSide(rs.getInt("side"));
            sp.setPersonId(rs.getInt("personId"));
            return sp;
        }
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationId(rs.getInt("organizationId"));
            o.setOrgName(rs.getString("orgName"));
            o.setOrgDescription(rs.getString("description"));
            o.setPhoneNum(rs.getString("phone"));
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
    

        private void insertSuperPersonOrganization(SuperPerson person) {
            final int personId = person.getPersonId();
            final List<Organization> orgs = person.getOrganization();

            // Update the books_authors bridge table with an entry for 
            // each author of this book
            for (Organization currentOrg : orgs) {
                jdbcTemplate.update(SQL_INSERT_SUPERPERSONORGANIZATION,
                        personId,
                        currentOrg.getOrganizationId());
            }
        }

        private void insertSuperPersonSighting(SuperPerson person) {
            final int personId = person.getPersonId();
            final List<Sighting> sighting = person.getSighting();

            // Update the books_authors bridge table with an entry for 
            // each author of this book
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

        private Power findPowerforPerson(SuperPerson person) {
            return null;
        }

        private List<SuperPerson>
                associateOrganizationandPowerwithPersons(List<SuperPerson> personList) {
            // set the complete list of org ids for persons
            for (SuperPerson currentPerson : personList) {
                // add orgs to current persons
                currentPerson.setOrganization(findOrganizationsForPerson(currentPerson));
                // add the sighting to current persons
                currentPerson.getSighting(findSightingsForPerson(currentPerson));
                // add the power to current persons
                currentPerson.setPower(findPowerforPerson(currentPerson));
            }
            return personList;
        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        public void addPerson(SuperPerson person
        ) {
            jdbcTemplate.update(SQL_INSERT_PERSON,
                    person.getSuperName(),
                    person.getDescription(),
                    person.getSide(),
                    person.getPower());
            person.setPersonId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                    Integer.class));

            insertSuperPersonOrganization(person);
            insertSuperPersonSighting(person);

        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        public void deletePerson(int superPersonId
        ) {
            jdbcTemplate.update(SQL_DELETE_SUPERPERSONORGANIZATION);
            jdbcTemplate.update(SQL_DELETE_PERSON, superPersonId);

        }

        @Override
        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        public void updatedPerson(SuperPerson updatePerson
        ) {
            jdbcTemplate.update(SQL_UPDATE_PERSON,
                    updatePerson.getSuperName(),
                    updatePerson.getDescription(),
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

                superPerson.setPower(findPowerforPerson(superPerson));
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

    }
