/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface VendingMachineServiceLayer {
    
    public List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    public Item getItem(int itemId) throws VendingMachinePersistenceException;
            
    public void depositMoney(BigDecimal amount);
    
    public BigDecimal getAmountInMachine();
    
    public Change returnMoney();
    
    /**
     *
     * @param item
     * @return
     * @throws VendingMachineInsufficientFundsException
     * @throws VendingMachineNoItemInventoryException
     */
    public Change purchase(Item item) throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException, VendingMachinePersistenceException;
    
}
