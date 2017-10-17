/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class VendingMachineDaoDbImpl implements VendingMachineDao {
    
    private static final String SQL_SELECT_ITEM
            = "select * from items where item_id = ?";
    
    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from items";
    
    private static final String SQL_UPDATE_QUANTITY
            = "update quantity";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
            

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new ItemMapper());
    }

    @Override
    public Item getItem(int itemId) throws VendingMachinePersistenceException {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, 
                    new ItemMapper(), itemId);
            
        } catch (EmptyResultDataAccessException ex) {
            
            return null;
        }
    }

    @Override
    public void updateQuantity(Item updateItem) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_UPDATE_QUANTITY, 
                updateItem.getItemInventory(),
                updateItem.getItemId());
    }
    
    private static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item(rs.getInt("item_id"));
            item.setItemName(rs.getString("item_name"));
            item.setItemCost(rs.getBigDecimal("item_price"));
            item.setItemInventory(rs.getInt("item_inventory"));
            return item;
        }
        
    }
    
}
