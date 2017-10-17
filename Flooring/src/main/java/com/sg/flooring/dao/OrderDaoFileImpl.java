/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class OrderDaoFileImpl implements OrderDao {

    private HashMap<String, HashMap<Long, Order>> orders = new HashMap<>();
    public static final String DELIMITER = "::";
    private static final String ORDER_DIRECTORY = "./OrderFiles/Orders_";
    private boolean shouldWrite = false;

    public OrderDaoFileImpl(String mode) {
        shouldWrite = mode.equals("Production");
    }

    @Override
    public void loadFile(String orderDate) throws OrderPersistenceException {
        File file = new File(ORDER_DIRECTORY + orderDate + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new OrderPersistenceException("Could not load order data into memory.", ex);
            }
        }
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException("Could not load order data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            HashMap<Long, Order> currentOrders = new HashMap<>();

            Order currentOrder = new Order(Long.parseLong(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setFlooringArea(Double.parseDouble(currentTokens[3]));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setTaxRate(Double.parseDouble(currentTokens[5]));
            currentOrder.setMatCostPerSqFt(new BigDecimal(currentTokens[6]));
            currentOrder.setLabCostPerSqFt(new BigDecimal(currentTokens[7]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[8]));
            currentOrder.setTotalMaterialCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalLaborCost(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));
            currentOrder.setOrderDate(currentTokens[12]);

            currentOrders.put(currentOrder.getOrderNumber(), currentOrder);
            if (!orders.containsKey(currentOrder.getOrderDate())) {
                orders.put(currentOrder.getOrderDate(), currentOrders);
            } else {
                orders.get(currentOrder.getOrderDate()).put(currentOrder.getOrderNumber(), currentOrder);
            }

        }
    }

    private void writeOrder() throws OrderPersistenceException {
        if (shouldWrite) {

            for (String date : orders.keySet()) {

                PrintWriter out;

                try {
                    out = new PrintWriter(new FileWriter(ORDER_DIRECTORY + date + ".txt"));
                } catch (IOException e) {
                    throw new OrderPersistenceException("Order not found");
                }
                for (Order order : orders.get(date).values()) {
                    out.println(
                            order.getOrderNumber() + DELIMITER
                            + order.getCustomerName() + DELIMITER
                            + order.getState() + DELIMITER
                            + order.getFlooringArea() + DELIMITER
                            + order.getProductType() + DELIMITER
                            + order.getTaxRate() + DELIMITER
                            + order.getMatCostPerSqFt() + DELIMITER
                            + order.getLabCostPerSqFt() + DELIMITER
                            + order.getTotalTax() + DELIMITER
                            + order.getTotalMaterialCost() + DELIMITER
                            + order.getTotalLaborCost() + DELIMITER
                            + order.getTotalCost() + DELIMITER
                            + order.getOrderDate());
                    out.flush();
                }
            }
        }
        else{
            System.out.println("Not in Production Mode, not writing to disk");
        }
    }

    @Override
    public Order addOrder(Order order) throws OrderPersistenceException {
        loadFile(order.getOrderDate());
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
    public Order removeOrder(Order order) throws OrderPersistenceException {
        loadFile(order.getOrderDate());
        if (orders.containsKey(order.getOrderDate())) {
            orders.get(order.getOrderDate()).remove(order.getOrderNumber());

        }
        writeOrder();
        return order;
    }

    @Override
    public void updateOrder(Order updateOrder) throws OrderPersistenceException {
        loadFile(updateOrder.getOrderDate());
        if (orders.containsKey(updateOrder.getOrderDate())) {
            orders.get(updateOrder.getOrderDate()).put(updateOrder.getOrderNumber(), updateOrder);
        }
        writeOrder();
    }

    @Override
    public List<Order> getAllOrders(String orderDate) throws OrderPersistenceException {
        if (orders.get(orderDate) == null) {
            loadFile(orderDate);
        }
        List<Order> listOfOrders = new ArrayList<>();
        loadFile(orderDate);
        if (orders.containsKey(orderDate)) {
            listOfOrders.addAll(orders.get(orderDate).values());
        }
        return listOfOrders;
    }

    @Override
    public void saveOrder() throws OrderPersistenceException {
        writeOrder();
    }

    @Override
    public Order getOrder(String orderDate, long orderNumber) throws OrderPersistenceException {
        Order o = null;
        List<Order> orders = getAllOrders(orderDate);
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                o = order;
            }
        }
        return o;
    }

}
