/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patri
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(6, dao.getAllItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        Item itemFromDao = dao.getItem("Doritos");
        
        Item itemTest = new Item("Doritos");
        itemTest.setItemCost(new BigDecimal("1.25"));
        itemTest.setItemInventory(1);
        
        assertEquals(itemTest, itemFromDao);
        
    }

    /**
     * Test of updateItem method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateItem() throws Exception {
        Item itemFromDao = dao.getItem("Reeses");
        
        int inventory = itemFromDao.getItemInventory();
        itemFromDao.setItemInventory(inventory - 1);
        dao.updateQuantity(itemFromDao);
        itemFromDao = dao.getItem("Reeses");
 
        assertEquals(itemFromDao.getItemInventory(), inventory - 1);
        
    }
    
}
