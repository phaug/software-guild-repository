/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

/**
 *
 * @author apprentice
 */
public class OrderDataValidationException extends Exception {

    public OrderDataValidationException(String message) {

        super(message);
    }

    OrderDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
