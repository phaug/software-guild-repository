/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
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
public class PowerDaoJdbcTemplateImpl implements PowerDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_INSERT_POWER
            = "insert into power (powName) "
            + "value (?)";

    private static final String SQL_DELETE_POWER
            = "delete from power where powerId = ?";

    private static final String SQL_UPDATE_POWER
            = "update power set powName = ? where powerId = ?";

    private static final String SQL_SELECT_POWER
            = "select * from power where powerId = ?";

    private static final String SQL_SELECT_ALL_POWERS
            = "select * from power";

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power p = new Power();
            p.setPowerName(rs.getString("powName"));
            p.setPowerId(rs.getInt("powerId"));
            return p;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getPowerName());

        int powerId
                = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        power.setPowerId(powerId);
    }

    @Override
    public void deletePower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    @Override
    public void updatePower(Power updatePower) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                updatePower.getPowerName(),
                updatePower.getPowerId());

    }

    @Override
    public Power getPowerbyId(int id) {
         try {
            return jdbcTemplate.queryForObject(SQL_SELECT_POWER, 
                                               new PowerMapper(), 
                                               id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_POWERS,
                new PowerMapper());
    }

}
