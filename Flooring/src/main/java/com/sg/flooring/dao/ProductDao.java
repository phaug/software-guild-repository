/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dto.Product;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public interface ProductDao {
      
   public Map<String, Product> getProducts()
            throws ProductPersistenceException;
    
   void loadFile() throws ProductPersistenceException;
   
   public Product getProduct(String productName)
        throws ProductPersistenceException;
}
