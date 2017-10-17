/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Alejandro
 */
public class ClassRosterPersistenceException extends Exception {

    public ClassRosterPersistenceException(String message) {
        super(message);
    }

    public ClassRosterPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
