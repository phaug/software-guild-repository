/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.OrderDao;
import com.sg.flooring.dao.OrderPersistenceException;
import com.sg.flooring.dao.ProductDao;
import com.sg.flooring.dao.ProductPersistenceException;
import com.sg.flooring.dao.StateDao;
import com.sg.flooring.dao.StatePersistenceException;
import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;

/**
 *
 * @author apprentice
 */
public class OrderServicer {

    OrderDao dao;
    StateDao stateDao;
    ProductDao productDao;

    LocalDate date = LocalDate.now();

    public OrderServicer(OrderDao dao, StateDao stateDao, ProductDao productDao) {
        this.dao = dao;
        this.stateDao = stateDao;
        this.productDao = productDao;
    }
    
    public void loadDaos() throws StatePersistenceException, ProductPersistenceException{
       stateDao.loadFile();
       productDao.loadFile();
//       dao.loadFile(orderDate);
    }

    public Order addOrder(Order order) throws OrderDataValidationException, OrderPersistenceException, ProductPersistenceException, StatePersistenceException {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyy");
        String formattedDate = date.format(formatter);
        order.setOrderDate(formattedDate);
        validateOrderData(order);
        boolean isValid = validStateAndProductData(order);
        if(!isValid){
            throw new OrderDataValidationException("Please enter valid product and State information");
        }
        order = orderCalculations(order);
        return dao.addOrder(order);
    }

    public void editOrder(Order editOrder) throws OrderDataValidationException, StatePersistenceException, OrderPersistenceException {

        try {
            editOrder = orderCalculations(editOrder);
        } catch (ProductPersistenceException ex) {
            Logger.getLogger(OrderServicer.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao.updateOrder(editOrder);

    }

    public Order getOrder(String orderDate, long orderNumber) throws OrderPersistenceException {
        return dao.getOrder(orderDate, orderNumber);

    }

    public Order confirmOrder(Order confirmOrder) throws OrderDataValidationException, StatePersistenceException, ProductPersistenceException {
        return orderCalculations(confirmOrder);
    }

    private Order orderCalculations(Order orderCalculations)
            throws OrderDataValidationException, StatePersistenceException, ProductPersistenceException {
        Map<String, Double> taxPercentage = stateDao.taxPercentage();
        Map<String, Product> getProducts = productDao.getProducts();
        orderCalculations.setLabCostPerSqFt(getProducts.get(orderCalculations.getProductType()).getLaborCostPerSquareFoot().setScale(2, RoundingMode.CEILING));
        orderCalculations.setMatCostPerSqFt(getProducts.get(orderCalculations.getProductType()).getCostPerSquareFoot().setScale(2, RoundingMode.CEILING));
        orderCalculations.setTotalLaborCost(new BigDecimal(orderCalculations.getFlooringArea() * orderCalculations.getLabCostPerSqFt().doubleValue()).setScale(2, RoundingMode.CEILING));
        orderCalculations.setTotalMaterialCost(new BigDecimal(orderCalculations.getFlooringArea() * orderCalculations.getMatCostPerSqFt().doubleValue()).setScale(2, RoundingMode.CEILING));
        orderCalculations.setTaxRate(taxPercentage.get(orderCalculations.getState()));
        orderCalculations.setTotalTax(new BigDecimal((orderCalculations.getTotalLaborCost().doubleValue() + orderCalculations.getTotalMaterialCost().doubleValue()) * orderCalculations.getTaxRate() / 100).setScale(2, RoundingMode.CEILING));
        orderCalculations.setTotalCost(orderCalculations.getTotalLaborCost().add(orderCalculations.getTotalMaterialCost().add(orderCalculations.getTotalTax().setScale(2, RoundingMode.CEILING))));

        return orderCalculations;

    }
    
    private boolean validStateAndProductData(Order order) throws StatePersistenceException, ProductPersistenceException{
        Double stateTax = stateDao.getState(order.getState());
        boolean isStateFound = true;
        Product productName = productDao.getProduct(order.getProductType());
        boolean isProductFound = true;
        if(productName == null){
            return false;
        }
        if(stateTax == null){
            return false;
        }
        return isStateFound && isProductFound;
    }

    private void validateOrderData(Order order) throws OrderDataValidationException {
        if (order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || order.getState() == null
                || order.getState().trim().length() == 0
                || order.getFlooringArea() == null
                || order.getFlooringArea() <= 0
                || order.getProductType() == null
                || order.getProductType().trim().length() == 0) {

            throw new OrderDataValidationException(
                    "ERROR: You entered invalid data in one of the fields, please make sure to write a name, state abbreviation, one of the listed material types, and an accurate sized flooring area.");
        }
    }

    public Order removeOrder(Order orderNumber) throws OrderPersistenceException {
        Order removeOrder = dao.removeOrder(orderNumber);
        return dao.removeOrder(removeOrder);
    }

    public void saveOrders() throws OrderPersistenceException {
        dao.saveOrder();
    }

    public List<Order> getAllOrders(String orderDate) {
        try {
            return dao.getAllOrders(orderDate);
        } catch (OrderPersistenceException ex) {
            return null;
        }
    }

}
