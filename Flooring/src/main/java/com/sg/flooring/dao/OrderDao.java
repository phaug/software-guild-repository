/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {

    List<Order> getAllOrders(String OrderDate)
            throws OrderPersistenceException;

    Order addOrder(Order order)
            throws OrderPersistenceException;

    Order removeOrder(Order removeOrder)
            throws OrderPersistenceException;

    void saveOrder()
            throws OrderPersistenceException;

    void updateOrder(Order updateOrder)
            throws OrderPersistenceException;

    Order getOrder(String OrderDate, long orderNumber)
            throws OrderPersistenceException;

    void loadFile(String orderDate) throws OrderPersistenceException;
            

}
