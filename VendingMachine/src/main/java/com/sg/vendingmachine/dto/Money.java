/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author patri
 */
public enum Money {
    FIVE_DOLLAR_BILL(500),
    ONE_DOLLAR_BILL(100),
    QUARTER(25),
    DIME(10),
    NICKEL(5),
    PENNY(1);
    
    private int value = 0;

    Money(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }

}
