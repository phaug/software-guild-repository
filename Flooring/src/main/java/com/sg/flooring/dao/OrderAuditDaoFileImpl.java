/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author apprentice
 */
public class OrderAuditDaoFileImpl implements OrderAuditDao {
    
    public static final String ORDER_FILE = "order.txt";

    @Override
    public void writeAuditEntry(String entry) throws OrderPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE, true));
        } catch (IOException e) {
            throw new OrderPersistenceException("Could not persist audit information.", e);
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }

}
