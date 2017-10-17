/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author apprentice
 */
public class Order {

    private long orderNumber;
    private String customerName;
    private String state;
    private Double flooringArea;
    private String productType;
    private Double taxRate;
    private BigDecimal matCostPerSqFt;
    private BigDecimal labCostPerSqFt;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalLaborCost;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private String orderDate;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {

        this.orderDate = orderDate;
    }


    public Order(){
        
    }

    public Order(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getFlooringArea() {
        return flooringArea;
    }

    public void setFlooringArea(Double flooringArea) {
        this.flooringArea = flooringArea;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getMatCostPerSqFt() {
        return matCostPerSqFt;
    }

    public void setMatCostPerSqFt(BigDecimal matCostPerSqFt) {
        this.matCostPerSqFt = matCostPerSqFt;
    }

    public BigDecimal getLabCostPerSqFt() {
        return labCostPerSqFt;
    }

    public void setLabCostPerSqFt(BigDecimal labCostPerSqFt) {
        this.labCostPerSqFt = labCostPerSqFt;
    }

    public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    @Override
public String toString() {
    return "Order Date: " + orderDate + " |Order Number: " + orderNumber + " |Customer Name" 
            + customerName + " |State: " + state + " |Flooring Area: " + flooringArea + " |Product Type " + productType;
}
    

}
