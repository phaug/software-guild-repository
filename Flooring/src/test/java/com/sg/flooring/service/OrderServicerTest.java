/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.OrderDao;
import com.sg.flooring.dao.OrderDaoFileImpl;
import com.sg.flooring.dao.OrderPersistenceException;
import com.sg.flooring.dao.ProductDao;
import com.sg.flooring.dao.ProductDaoFileImpl;
import com.sg.flooring.dao.ProductPersistenceException;
import com.sg.flooring.dao.StateDao;
import com.sg.flooring.dao.StateDaoFileImpl;
import com.sg.flooring.dao.StatePersistenceException;
import com.sg.flooring.dto.Order;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class OrderServicerTest {

    private OrderServicer service;
    private OrderDao orderDao;
    private ProductDao prodDao;
    private StateDao stateDao;

//    ApplicationContext ctx
//            = new ClassPathXmlApplicationContext("applicationContext.xml");
//    OrderServicer service = ctx.getBean("serviceLayer", OrderServicer.class);
    public OrderServicerTest() {
    }

    @Before
    public void setUpClass() throws ProductPersistenceException, StatePersistenceException {

        orderDao = new OrderDaoFileImpl("test");
        prodDao = new ProductDaoFileImpl();
        prodDao.loadFile();
        stateDao = new StateDaoFileImpl();
        stateDao.loadFile();
        service = new OrderServicer(orderDao, stateDao, prodDao);
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
     * Test of addOrder method, of class OrderServicer.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order orderA = new Order(1);
        orderA.setOrderDate("092217");
        orderA.setCustomerName("customerA");
        orderA.setState("MI");
        orderA.setFlooringArea(50.00);
        orderA.setProductType("wood");
        service.addOrder(orderA);

        Order result = service.getOrder("092217", 1);
        assertEquals("customerA", result.getCustomerName());

    }

    /**
     * Test of editOrder method, of class OrderServicer.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order orderA = new Order(1);
        orderA.setOrderDate("092217");
        orderA.setCustomerName("customerA");
        orderA.setState("MI");
        orderA.setFlooringArea(50.00);
        orderA.setProductType("wood");

        service.addOrder(orderA);
        Order fromService = service.getOrder("092217", 1);
        //change something on the order

        fromService.setCustomerName("customerB");
        fromService.setState("OH");
        fromService.setFlooringArea(40.00);
        fromService.setProductType("tile");
        service.editOrder(fromService);

        Order orderResult = service.getOrder("092217", 1);
        assertEquals("customerB", orderResult.getCustomerName());
    }

    /**
     * Test of getOrder method, of class OrderServicer.
     */
    @Test
    public void testGetOrder() throws Exception {
    }

    /**
     * Test of confirmOrder method, of class OrderServicer.
     */
    @Test
    public void testConfirmOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class OrderServicer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order orderA = new Order(1);
        orderA.setOrderDate("09222017");
        orderA.setCustomerName("customerA");
        orderA.setState("MI");
        orderA.setFlooringArea(50.00);
        orderA.setProductType("wood");
        service.addOrder(orderA);
        service.removeOrder(orderA);
    }

    /**
     * Test of saveOrders method, of class OrderServicer.
     */
    @Test
    public void testSaveOrders() throws Exception {
    }

    /**
     * Test of getAllOrders method, of class OrderServicer.
     */
    @Test
    public void testGetAllOrders() {
        Order orderA = new Order();
        orderA.setOrderDate("09222017");
        orderA.setCustomerName("customerA");
        orderA.setState("MI");
        orderA.setFlooringArea(50.00);
        orderA.setProductType("wood");
        try {
            service.addOrder(orderA);
        } catch (OrderDataValidationException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OrderPersistenceException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductPersistenceException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StatePersistenceException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Order orderB = new Order();
        orderB.setOrderDate("09222017");
        orderB.setCustomerName("customerB");
        orderB.setState("MI");
        orderB.setFlooringArea(60.00);
        orderB.setProductType("tile");

        try {
            service.addOrder(orderB);
        } catch (OrderDataValidationException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OrderPersistenceException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProductPersistenceException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (StatePersistenceException ex) {
            Logger.getLogger(OrderServicerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        service.getAllOrders("09222017");
    }

}
