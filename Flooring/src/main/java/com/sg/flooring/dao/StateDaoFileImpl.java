/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import static com.sg.flooring.dao.ProductDaoFileImpl.DELIMITER;
import static com.sg.flooring.dao.ProductDaoFileImpl.PRODUCT_FILE;
import com.sg.flooring.dto.Product;
import com.sg.flooring.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StateDaoFileImpl implements StateDao {

    private Map<String, Double> stateTaxes = new HashMap<>();
    public static final String STATE_FILE = "state.txt";
    public static final String DELIMITER = "::";

    @Override
    public void loadFile() throws StatePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(STATE_FILE)));
        } catch (FileNotFoundException e) {
            throw new StatePersistenceException("Could not load prodcut data into memory.", e);
        }

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            stateTaxes.put(currentTokens[0], Double.parseDouble(currentTokens[1]));
        }

        scanner.close();
    }
    
    
    @Override
    public Double getState(String stateName) throws StatePersistenceException{
        loadFile();
        return stateTaxes.get(stateName);
    }

    @Override
    public Map <String, Double> taxPercentage () throws StatePersistenceException {
        loadFile();
        return stateTaxes;
    }

}
