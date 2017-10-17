/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dto;

import java.math.BigDecimal;

/**
 *
 * @author apprentice
 */
public class State {
    
    private String stateName;
    private Double taxPercentage;

    public State(String stateName) {
        this.stateName = stateName;
       
    }

    public Double getTaxPercentage() {
        return taxPercentage;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setTaxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }
    
    
    
}
