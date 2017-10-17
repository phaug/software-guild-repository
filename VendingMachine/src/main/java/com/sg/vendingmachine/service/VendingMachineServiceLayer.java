/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Money;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author patri
 */
public interface VendingMachineServiceLayer {
    
    public List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    public Item getItem(String itemName) throws VendingMachinePersistenceException;
            
    public void depositMoney(BigDecimal amount);
    
    public BigDecimal getAmountInMachine();
    
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
