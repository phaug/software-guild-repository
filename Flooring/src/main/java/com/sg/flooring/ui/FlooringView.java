/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import com.sg.flooring.dto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO myIo) {
        this.io = myIo;
    }

    public int printAndGetMenuSelection() {
        io.print("Flooring Program");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 6);
    }

    public void displayAllOrdersInquiryBanner() {
        io.print("=========Display Orders===========");
    }

    public void displayOrderByDate(List<Order> order) {
        io.print("Below is a list of the orders for " + order);

    }

    public void displayErrorMessage(String message) {
        io.print("ERROR");
        io.print(message);
    }

    public Order getOrderInfo() {

        String customerName = io.readString("Please enter name.");
        String customerState = io.readString("What is the State where you live? Please use abbreviation");
        String floorArea = io.readString("What's the floor area in square feet?");
        try{
            Double doubleVariable = Double.parseDouble(floorArea);
        }catch (NumberFormatException e){
            return null;
        }
        
        String productMaterial = io.readString("What material are you looking to use?"
                + "\n carpet | cost per square foot = $2.25 | labor cost per square foot = $2.10"
                + "\n laminate | cost per square foot = $1.75 | labor cost per square foot = $2.10"
                + "\n tile | cost per square foot = $3.50 | labor cost per square foot = $4.15"
                + "\n wood | cost per square foot = $5.15 | labor cost per square foot = $4.75");
        Order currentOrder = new Order();
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(customerState.toUpperCase());
        currentOrder.setFlooringArea(Double.parseDouble(floorArea));
        currentOrder.setProductType(productMaterial.toLowerCase());
        return currentOrder;
    }

    public void displayOrder(Order order) {
        io.print("Your order number is " + order.getOrderNumber()
                + "\n Your order date is " + order.getOrderDate() + "."
                + "\n You listed " + order.getCustomerName() + " as the customer name."
                + "\n You listed " + order.getState() + " as your home state."
                + "\n Your flooring area is " + order.getFlooringArea() + " square feet."
                + "\n You selected " + order.getProductType() + " as the flooring material."
                + "\n The total labor cost is $" + order.getTotalLaborCost()
                + "\n The total material cost is $" + order.getTotalMaterialCost()
                + "\n The total tax is $" + order.getTotalTax()
                + "\n The total cost for everything is $" + order.getTotalCost());

    }

    public String getOrderDate() {
        return io.readString("Please enter the date of your original order.");
    }

    public void displayAddOrderBanner() {
        io.print("=== Please place your order ===");
    }

    public void displayNewOrderSuccessBanner() {
        io.readString(
                "Your order is successfully created.  Please hit enter to continue");
    }

    public void displayEditOrderBanner() {
        io.print("Edit Order");
    }

    public Long getOrderNumber() {
        return io.readLong("Please enter your order number");
    }

    public Order setOrderInfo(Order order) {

        String editName = io.readString("Currently you have " + order.getCustomerName()
                + " listed as your name, would you like to change it?"
                + "\n If so please enter new value, otherwise leave blank and hit enter.");
        if (!editName.isEmpty()) {
            order.setCustomerName(editName);
        }
        String editState = io.readString("Currently, you have " + order.getState()
                + " listed as your home state, would you like to change it?"
                + "\n If so please enter new value, otherwise leave blank and hit enter.");
        if (!editState.isEmpty()) {
            order.setState(editState);
        }
        String editFloorArea = io.readString("Currently, you have " + order.getFlooringArea()
                + " listed as your flooring area size, would you like to change it?"
                + "\n If so please enter new value, otherwise leave blank and hit enter.");
        if (!editFloorArea.isEmpty()) {
            order.setFlooringArea(Double.parseDouble(editFloorArea));
        }
        String editProductMaterial = io.readString("Currently, you have " + order.getProductType()
                + " listed as the flooring material, would you like to change it?"
                + "\n If so please enter new value, otherwise leave blank and hit enter.");
        if (!editProductMaterial.isEmpty()) {
            order.setProductType(editState);
        }
        return order;
    }

    public void displayRemoveOrderBanner() {
        io.print("Remove an order");
    }

    public boolean confirmOrderRemoval(Order order) {
        String removeOrder = io.readString("Are you sure you want to remove this order? Enter y or n.");

        return removeOrder.equals("y");
    }

    public void displaySaveOrdersBanner() {
        io.print("Order(s) saved");
    }

    public void displayListOfOrders(List<Order> orders) {
        for (Order o : orders) {
            io.print("Order Number: " + o.getOrderNumber()
                    + " |Customer Name: " + o.getCustomerName()
                    + " |Flooring Area: " + o.getFlooringArea()
                    + " |Product Type: " + o.getProductType());
        }
    }

    public void displayQuitBanner() {
        io.print("Good Bye.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }
}
