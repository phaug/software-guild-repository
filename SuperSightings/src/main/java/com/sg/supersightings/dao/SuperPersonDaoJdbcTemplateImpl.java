/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
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
    
    private static final String SQL_UPDATE_PERSON
            = "update superperson set superName = ?, description = ?, sideId = ?, "
        + "powerId = ? where superpersonId = ?";
    
    private static final String SQL_SELECT_SUPERPERSON
            = "select * from superperson where superpersonId = ?";
    
    private static final String SQL_SELECT_POWER_FOR_SUPERPERSON
            ="select power.powerId from superperson s " 
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
           

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPerson(SuperPerson person) {
        jdbcTemplate.update(SQL_INSERT_PERSON,
                person.getSuperName(),
                person.getDescription(),
                person.getSide(),
                person.getPower());
        
        int personId =
                jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        person.setPersonId(personId);
    }

    @Override
    public void deletePerson(int superPersonId) {
        jdbcTemplate.update(SQL_DELETE_PERSON, superPersonId);
        
    }

    @Override
    public void updatedPerson(SuperPerson updatePerson) {
        jdbcTemplate.update(SQL_UPDATE_PERSON, 
                updatePerson.getSuperName(),
                updatePerson.getDescription(),
                updatePerson.getSide(),
                updatePerson.getPower(),
                updatePerson.getPersonId());
    }
    
    private Power findPowerforPerson(int superPersonId) {
        return null;
    }

    @Override
    public SuperPerson getPersonbyId(int id) {
        try {
            SuperPerson superPerson = jdbcTemplate.queryForObject(SQL_SELECT_SUPERPERSON, 
                    new PersonMapper(),
                    id);
            
            superPerson.setPower(findPowerforPerson(id));
            return superPerson;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllPersons() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPERPERSONS,
                new PersonMapper());
    }

    @Override
    public List<SuperPerson> getPersonByLocationId(int locationId) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_LOCATIONID, 
                new PersonMapper(),
                locationId);
    }

    @Override
    public List<SuperPerson> getPersonsByOrganizationId(int orgId) {
        return jdbcTemplate.query(SQL_SELECT_PERSONS_BY_ORGANIZATIONID, 
                new PersonMapper(),
                orgId);
    }

}
