/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class OrderDaoStubImpl implements OrderDao {

    Order order;
    HashMap<String, HashMap<Long, Order>> orders = new HashMap<>();

    public OrderDaoStubImpl() {

        order = new Order(1);
        order.setTotalLaborCost(new BigDecimal(2.5));
        order.setTotalMaterialCost(new BigDecimal(4.5));
        order.setTotalTax(new BigDecimal(6.5));
        order.setTotalCost(new BigDecimal(0));
        order.setOrderDate("092217");
        order.setFlooringArea(Double.NaN);
        order.setCustomerName("p");
        order.setLabCostPerSqFt(BigDecimal.ZERO);
        order.setMatCostPerSqFt(BigDecimal.ZERO);
        order.setState("MI");
        order.setTaxRate(Double.MAX_VALUE);
        order.setProductType("wood");

        if (!orders.containsKey(order.getOrderDate())) {
            HashMap<Long, Order> orderMap = new HashMap<>();
            orderMap.put(1l, order);
            orders.put(order.getOrderDate(), orderMap);
        } else {
            orders.get(order.getOrderDate()).put(1l, order);
        }
    }

    @Override
    public List<Order> getAllOrders(String OrderDate) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order order) throws OrderPersistenceException {
        if (!orders.containsKey(order.getOrderDate())) {
            orders.put(order.getOrderDate(), new HashMap<Long, Order>());
        }
        long id = 0;
        for (long l : orders.get(order.getOrderDate()).keySet()) {
            if (l > id) {
                id = l;
            }
        }
        id++;
        order.setOrderNumber(id);
        orders.get(order.getOrderDate()).put(id, order);
        return order;
    }

    @Override
    public Order removeOrder(Order removeOrder) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveOrder() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateOrder(Order updateOrder) throws OrderPersistenceException {
        if (orders.containsKey(updateOrder.getOrderDate())) {
            orders.get(updateOrder.getOrderDate()).put(updateOrder.getOrderNumber(), updateOrder);
        }
    }

    @Override
    public Order getOrder(String OrderDate, long orderNumber) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadFile(String orderDate) throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
