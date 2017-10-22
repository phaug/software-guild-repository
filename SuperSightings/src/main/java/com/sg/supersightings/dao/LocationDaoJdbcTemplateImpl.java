/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    private static final String SQL_UPDATE_LOCATION
            = "update location set locName = ?, locDescription = ?, address = ?, "
            + "latitude = ?, longitude = ? where superpersonId = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";

    private static final String SQL_LOCATION_BY_PERSONID
            = "select l.* from location l "
            + "	inner join sighting on l.locationId = sighting.locationId "
            + "    inner join superpersonsighting on superpersonsighting.sightingId = sighting.sightingId "
            + "    inner join superperson on superperson.superPersonId = superpersonsighting.superpersonId "
            + "    where superperson.superPersonId = ?";
    
    private static final class LocationMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int i) throws SQLException {
        Location l = new Location();
        l.setLocName(rs.getString("locName"));
        l.setLocDescription(rs.getString("locDescription"));
        l.setAddress(rs.getString("address"));
        l.setLatitude(rs.getBigDecimal("latitude"));
        l.setLongitude(rs.getBigDecimal("longitude"));
        l.setLocationId(rs.getInt("locationId"));
        return l;
    }
}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocName(),
                location.getLocDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());
        
        int locationId =
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        location.setLocationId(locationId);
    }

    @Override
    public void deleteLocation(int locationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateLocation(Location updateLocation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Location getLocationbyId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, 
                new LocationMapper());      
    }

    @Override
    public List<Location> getLocationbyPersonId(int personId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
