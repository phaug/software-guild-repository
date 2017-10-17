/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Order;
import com.sg.flooring.dto.Product;
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
public class ProductDaoFileImpl implements ProductDao {

    private Map<String, Product> products = new HashMap<>();
    public static final String PRODUCT_FILE = "product.txt";
    public static final String DELIMITER = "::";

    @Override
    public void loadFile() throws ProductPersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new ProductPersistenceException("Could not load product data into memory.", e);
        }
        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setProductType(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductType(), currentProduct);
        }

        scanner.close();
    }

    @Override
    public Product getProduct(String ProductName) throws ProductPersistenceException {
        loadFile();
        return products.get(ProductName);
    }

    @Override
    public Map<String, Product> getProducts() throws ProductPersistenceException {
        loadFile();
        return products;
    }

}
