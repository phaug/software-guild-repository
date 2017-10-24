/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
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
public class SightingDaoJdbcTemplateImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (date, locationId) "
            + "value (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from superperson where superpersonId = ?";

    private static final String SQL_DELETE_SUPERPERSONSIGHTING
            = "delete from superpersonsighting where sightingId = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set date = ?, locationId = ?, "
            + "where sightingId = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where sightingId = ?";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";

    private static final String SQL_INSERT_SUPERPERSONSIGHTING
            = "insert into superpersonsighting (superPersonId, sightingId) values(?, ?)";

    private static final String SQL_SELECT_PERSONS_BY_SIGHTINGID
            = "select sp.* from superperson sp "
            + "inner join superpersonsighting on sp.superPersonId = superpersonsighting.superpersonId "
            + "inner join sighting on sighting.sightingId = superpersonsighting.sightingId "
            + "where sighting.sightingId = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTINGID
            = "select l.* from location l "
            + "inner join sighting on l.locationId = sighting.locationId "
            + "where sighting.sightingId = ?";

    private static final String SQL_SELECT_SIGHTING_BY_PERSONID
            = "select s.* from sighting s "
            + "inner join superpersonsighting on s.sightingId = superpersonsighting.sightingId "
            + "inner join superperson p on p.superPersonId = superpersonsighting.superpersonId "
            + "where p.superPersonId = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE
            = "select * from sighting "
            + "where date(`date`) = ?";

    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATIONID_AND_DATE
            = "select s.* from sighting s "
            + "inner join location on s.locationId = location.locationId "
            + "where location.locationId = ? "
            + "and date(`date`) = ?;";

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setDate(rs.getDate("date").toLocalDate());
            s.setSightingId(rs.getInt("sightingId"));
            return s;
        }
    }

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

    private void insertSuperPersonSightings(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<SuperPerson> persons = sighting.getSuperPerson();

        for (SuperPerson currentPerson : persons) {
            jdbcTemplate.update(SQL_INSERT_SUPERPERSONSIGHTING,
                    sightingId,
                    currentPerson.getPersonId());
        }
    }

    private List<SuperPerson> findPersonsforSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_PERSONS_BY_SIGHTINGID,
                new PersonMapper(),
                sighting.getSightingId());
    }

    private Location findLocationforSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTINGID,
                new LocationMapper(),
                sighting.getSightingId());
    }

    private List<Sighting>
            associateLocationandPersonsWithSighting(List<Sighting> sightingList) {
       
        for (Sighting currentSighting : sightingList) {
            
            currentSighting.setSuperPerson(findPersonsforSighting(currentSighting));
            
            currentSighting.setLocation(findLocationforSighting(currentSighting));
        }
        return sightingList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDate().toString(),
                sighting.getLocation().getLocationId());
        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));

        insertSuperPersonSightings(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SUPERPERSONSIGHTING, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting updateSighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                updateSighting.getDate(),
                updateSighting.getLocation().getLocationId(),
                updateSighting.getSightingId());
        jdbcTemplate.update(SQL_DELETE_SUPERPERSONSIGHTING, updateSighting);
        insertSuperPersonSightings(updateSighting);
    }

    @Override
    public Sighting getSightingbyId(int id) {
        try {
      
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    id);
            
            sighting.setSuperPerson(findPersonsforSighting(sighting));
            
            sighting.setLocation(findLocationforSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {

        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());

        return associateLocationandPersonsWithSighting(sightingList);
    }

    @Override
    public List<Sighting> getSightingsByPersonId(int personId) {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_PERSONID,
                        new SightingMapper(),
                        personId);

        return associateLocationandPersonsWithSighting(sightingList);
    }

    @Override
    public List<Sighting> getSightingbyLocationIdandDate(int locationId, int date) {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATIONID_AND_DATE,
                        new SightingMapper(),
                        date);

        return associateLocationandPersonsWithSighting(sightingList);
    }

    @Override
    public List<Sighting> getAllSightingsbyDate(int dateId) {
        List<Sighting> sightingList
                = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE,
                        new SightingMapper(),
                        dateId);

        return associateLocationandPersonsWithSighting(sightingList);
    }

}
