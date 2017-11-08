/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Sighting;
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
public class LocationDaoJdbcTemplateImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_LOCATION
            = "insert into location (locationName, description, address, latitude, longitude) "
            + "value (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationId = ?";

    private static final String DELETE_LOCATION_FROM_SIGHTING
            = "delete from sighting where locationId = ?";

    private static final String DELETE_LOCATION_FROM_ORGANIZATION
            = "delete from organization where locationId = ?";

    private static final String SQL_DELETE_SUPERPERSONSIGHTING
            = "delete from superpersonsighting where sightingId = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set locationName = ?, description = ?, address = ?, "
            + "latitude = ?, longitude = ? where locationId = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";

    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATIONID
            = "select s.* from sighting s "
            + "inner join location on s.locationId = location.locationId "
            + "where location.locationId = ? ";

    private static final String SQL_SELECT_ORGANIZATIONS_BY_LOCATIONID
            = "select o.* from organization o "
            + "inner join location on o.locationId = location.locationId "
            + "where location.locationId = ? ";

    private static final String SQL_DELETE_SUPERPERSONORGANIZATION
            = "delete from superpersonorganization where organizationId = ?";

    private static final String SQL_LOCATION_BY_PERSONID
            = "select l.* from location l "
            + "	inner join sighting on l.locationId = sighting.locationId "
            + "    inner join superpersonsighting on superpersonsighting.sightingId = sighting.sightingId "
            + "    inner join superperson on superperson.superPersonId = superpersonsighting.superpersonId "
            + "    where superperson.superPersonId = ?";

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setDate(rs.getDate("date").toLocalDate());
            s.setSightingId(rs.getInt("sightingId"));
            return s;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Location addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());

        int locationId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        location.setLocationId(locationId);
        return location;

    }

    @Override
    public void deleteLocation(int locationId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATIONID,
                new SightingMapper(),
                locationId);
        for (Sighting s : sightingList) {
            jdbcTemplate.update(SQL_DELETE_SUPERPERSONSIGHTING, s.getSightingId());
        }
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_LOCATIONID,
                new OrganizationMapper(),
                locationId);
        for (Organization o : orgList) {
            jdbcTemplate.update(SQL_DELETE_SUPERPERSONORGANIZATION, o.getOrganizationId());
        }
        jdbcTemplate.update(DELETE_LOCATION_FROM_SIGHTING, locationId);
        jdbcTemplate.update(DELETE_LOCATION_FROM_ORGANIZATION, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location updateLocation) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                updateLocation.getLocationName(),
                updateLocation.getDescription(),
                updateLocation.getAddress(),
                updateLocation.getLatitude(),
                updateLocation.getLongitude(),
                updateLocation.getLocationId());
    }

    @Override
    public Location getLocationbyId(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION,
                    new LocationMapper(),
                    id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS,
                new LocationMapper());
    }

    @Override
    public List<Location> getLocationbyPersonId(int personId) {
        List<Location> locationList
                = jdbcTemplate.query(SQL_LOCATION_BY_PERSONID,
                        new LocationMapper(),
                        personId);

        return locationList;

    }

}
