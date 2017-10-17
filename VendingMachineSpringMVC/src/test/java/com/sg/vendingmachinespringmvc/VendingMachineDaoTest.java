/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
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
    public void setUp() throws VendingMachinePersistenceException {

    } 
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(9, dao.getAllItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        Item itemFromDao = dao.getItem(6);

        Item itemTest = new Item(6);
        itemTest.setItemName("Doritos");
        itemTest.setItemCost(new BigDecimal("1.25"));
        itemTest.setItemInventory(5);

        assertEquals(itemTest, itemFromDao);

    }

    /**
     * Test of updateItem method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateItem() throws Exception {
        Item itemFromDao = dao.getItem(4);

        int inventory = itemFromDao.getItemInventory();
        itemFromDao.setItemInventory(inventory - 1);
        dao.updateQuantity(itemFromDao);
        itemFromDao = dao.getItem(4);

        assertEquals(itemFromDao.getItemInventory(), inventory - 1);

    }

}
