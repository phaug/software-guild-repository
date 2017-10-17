/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoStubImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImplTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
//        service = new VendingMachineServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        service
                = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
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
     * Test of getAllItems method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAllItems() throws Exception {
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetItem() throws Exception {
    }

    /**
     * Test of updateQuantity method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testUpdateQuantity() throws Exception {

    }

    /**
     * Test of depositMoney method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testDepositMoney() {
    }

    /**
     * Test of getAmountInMachine method, of class
     * VendingMachineServiceLayerImpl.
     */
    @Test
    public void testGetAmountInMachine() {
    }

    /**
     * Test of purchase method, of class VendingMachineServiceLayerImpl.
     */
    @Test
    public void testPurchaseInsufficientFunds() throws Exception {
        Item item = new Item("Snickers");
        service.depositMoney(BigDecimal.ONE);
        item.setItemCost(BigDecimal.TEN);
        try {
            service.purchase(item);
            fail("Check for error");
        } catch (VendingMachineInsufficientFundsException ex) {

        }

    }

    @Test
    public void testPurchaseInventory() throws Exception {
        Item item = new Item("Snickers");
        service.depositMoney(BigDecimal.ONE);
        item.setItemCost(BigDecimal.ONE);
        item.setItemInventory(0);
        try {
            service.purchase(item);
            fail("Check error");
        } catch (VendingMachineNoItemInventoryException ex) {

        }
    }

    @Test
    public void testsufficientFunds() throws Exception {
        Item item = null; 
        try {
         item = service.getItem("Snickers");
        } catch (NullPointerException ex){
            fail("error");
        }
        service.depositMoney(BigDecimal.TEN);
        try {
            service.purchase(item);
        } catch (VendingMachineInsufficientFundsException ex) {
            fail("Check for error");

        }
    }
}
