/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.controller;

import com.sg.flooring.dao.OrderPersistenceException;
import com.sg.flooring.dao.ProductPersistenceException;
import com.sg.flooring.dao.StatePersistenceException;
import com.sg.flooring.dto.Order;
import com.sg.flooring.service.OrderDataValidationException;
import com.sg.flooring.service.OrderServicer;
import com.sg.flooring.ui.FlooringView;
import com.sg.flooring.ui.UserIO;
import com.sg.flooring.ui.UserIOConsoleImpl;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    private FlooringView view;
    private OrderServicer service;
    private UserIO io = new UserIOConsoleImpl();

    public FlooringController(FlooringView view, OrderServicer service) {
        this.view = view;
        this.service = service;

    }

    public void run() throws OrderDataValidationException {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
            try {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        getAllOrdersByDate();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveOrders();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        displayUnknownCommandBanner();
                }

                
                
            } catch (OrderPersistenceException | StatePersistenceException e) {
                e.printStackTrace();
                view.displayErrorMessage(e.getMessage());
            } catch (InputMismatchException ime) {
                displayUnknownCommandBanner();

            }
        }
        displayQuitBanner();
    }

    private int getMenuSelection() throws OrderPersistenceException {
        return view.printAndGetMenuSelection();

    }

    private void getAllOrdersByDate() throws OrderPersistenceException {
        view.displayAllOrdersInquiryBanner();
        String getDate = view.getOrderDate();
        List<Order> ordersByDate = service.getAllOrders(getDate);
        view.displayListOfOrders(ordersByDate);

    }

    private void addOrder() throws OrderPersistenceException {
        view.displayAddOrderBanner();
        boolean errors = true;
            Order newOrder = view.getOrderInfo();
            if(newOrder == null){
                throw new OrderPersistenceException("Malformed input data, please try again.");
            }
            try {
                newOrder = service.addOrder(newOrder);
                view.displayOrder(newOrder);
                errors = false;
            } catch (OrderDataValidationException | StatePersistenceException | ProductPersistenceException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
    }

    private void editOrder() throws OrderPersistenceException, OrderDataValidationException, StatePersistenceException{
        view.displayEditOrderBanner();
        String getOriginalOrderDate = view.getOrderDate();
        Long getOrderNumber = view.getOrderNumber();
        Order editOrder = service.getOrder(getOriginalOrderDate, getOrderNumber);
        if (editOrder != null) {
            Order newOrderInfo = view.setOrderInfo(editOrder);

            service.editOrder(newOrderInfo);
        }
    }

    private void removeOrder() throws OrderPersistenceException {
        view.displayRemoveOrderBanner();
        String getOriginalOrderDate = view.getOrderDate();
        Long getOrderNumber = view.getOrderNumber();
        Order removeOrder = service.getOrder(getOriginalOrderDate, getOrderNumber);
        //view.confirmOrderRemoval(removeOrder);
        if (view.confirmOrderRemoval(removeOrder)) {
            service.removeOrder(removeOrder);
        }

    }

    private void saveOrders() throws OrderPersistenceException {
        view.displaySaveOrdersBanner();
        service.saveOrders();
    }

    private void displayQuitBanner() {
        view.displayQuitBanner();
    }

    private void displayUnknownCommandBanner() {
        view.displayUnknownCommandBanner();
    }
}
