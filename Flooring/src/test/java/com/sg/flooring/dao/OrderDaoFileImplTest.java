/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class OrderDaoFileImplTest {

    private OrderDaoFileImpl dao = new OrderDaoFileImpl("Test");

    public OrderDaoFileImplTest() {

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
     * Test of addOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testAddOrder() throws Exception {

        Order orderA = new Order();
        orderA.setOrderDate("09222017");
        orderA.setCustomerName("customerA");
        orderA.setState("MI");
        orderA.setFlooringArea(400.00);
        orderA.setProductType("tile");
        Order orderB = new Order();
        orderB.setOrderDate("09202017");
        orderB.setCustomerName("customerB");
        orderB.setState("MI");
        orderB.setFlooringArea(40.00);
        orderB.setProductType("wood");
        dao.addOrder(orderA);
        dao.addOrder(orderB);
        dao.saveOrder();
    }

    /**
     * Test of removeOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

    @Test
    public void testUpdateOrder() throws Exception {

    }

    /**
     * Test of getAllOrders method, of class OrderDaoFileImpl.
     */
    @Test
    public void testGetAllOrders() throws Exception {

        Order orderA = new Order();
        orderA.setOrderDate("09222017");
        orderA.setCustomerName("customerA");
        orderA.setState("MI");
        orderA.setFlooringArea(400.00);
        orderA.setProductType("tile");
        dao.addOrder(orderA);

        Order orderB = new Order();
        orderB.setOrderDate("09222017");
        orderB.setCustomerName("customerB");
        orderB.setState("MI");
        orderB.setFlooringArea(40.00);
        orderB.setProductType("wood");
        dao.addOrder(orderB);

        //dao.getAllOrders("09222017");

        assertEquals(2, dao.getAllOrders("09222017").size());
    }

    /**
     * Test of saveOrder method, of class OrderDaoFileImpl.
     */
    @Test
    public void testSaveOrder() throws Exception {

        //Order order = dao.addOrder(order);
    }

    @Test
    public void testGetOrder() throws Exception {

        Order orderA = new Order();
        orderA.setOrderDate("09222017");
        orderA.setCustomerName("customerA");
        dao.addOrder(orderA);

        dao.getOrder("09222017", 1);

    }

}
