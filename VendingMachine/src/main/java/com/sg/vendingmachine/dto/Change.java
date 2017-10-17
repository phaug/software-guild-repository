/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author patri
 */
public class Change {

    private int fiveDollarBills;
    private int oneDollarBills;
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public Change(BigDecimal amount) {
        BigDecimal[] result = amount.divideAndRemainder(new BigDecimal(5));
        fiveDollarBills = result[0].intValue();
        BigDecimal leftOver = result[1];
        if (leftOver.compareTo(BigDecimal.ZERO) > 0) {
            result = leftOver.divideAndRemainder(new BigDecimal(1));
            oneDollarBills = result[0].intValue();
            leftOver = result[1];
            if (leftOver.compareTo(BigDecimal.ZERO) > 0) {
                result = leftOver.divideAndRemainder(new BigDecimal(.25));
                quarters = result[0].intValue();
                leftOver = result[1];
                if (leftOver.compareTo(BigDecimal.ZERO) > 0) {
                    result = leftOver.divideAndRemainder(new BigDecimal(.10));
                    dimes = result[0].intValue();
                    leftOver = result[1];
                    if (leftOver.compareTo(BigDecimal.ZERO) > 0) {
                        result = leftOver.divideAndRemainder(new BigDecimal(.05));
                        dimes = result[0].intValue();
                        leftOver = result[1];
                        if (leftOver.compareTo(BigDecimal.ZERO) > 0) {
                            result = leftOver.divideAndRemainder(new BigDecimal(.01));
                            pennies = result[0].intValue();
                            leftOver = result[1];

                        }
                    }

                }
            }
        }
    }

    public int getFiveDollarBills() {
        return fiveDollarBills;
    }

    public int getOneDollarBills() {
        return oneDollarBills;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

}
