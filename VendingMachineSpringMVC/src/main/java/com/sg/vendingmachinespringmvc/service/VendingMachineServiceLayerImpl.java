/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    BigDecimal userMoney = BigDecimal.ZERO;

    public VendingMachineServiceLayerImpl(VendingMachineDao myDao) {
        this.dao = myDao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(int itemId) throws VendingMachinePersistenceException {
        return dao.getItem(itemId);
    }

    public void updateQuantity(Item itemId) throws VendingMachinePersistenceException {
        itemId.setItemInventory(itemId.getItemInventory() - 1);
    }

    @Override
    public void depositMoney(BigDecimal amount) {
        this.userMoney = this.userMoney.add(amount);
    }

    @Override
    public BigDecimal getAmountInMachine() {
        return userMoney;
    }

    @Override
    public Change purchase(Item item) throws VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException,
            VendingMachinePersistenceException {

        //validate state
        if (userMoney.compareTo(item.getItemCost()) < 0) {
            throw new VendingMachineInsufficientFundsException("Not enough money");
        } else if (item.getItemInventory() <= 0) {
            throw new VendingMachineNoItemInventoryException("Sorry, no soup for you.");
    
        } 
        
        item.setItemInventory(item.getItemInventory() - 1);
        dao.updateQuantity(item);
        userMoney = userMoney.subtract(item.getItemCost());
        Change change = new Change(userMoney);
        userMoney = BigDecimal.ZERO;
        return change;

    }
    @Override
    public Change returnMoney() {
        Change change = new Change (userMoney);
        userMoney = BigDecimal.ZERO;
        return change;
    }
}
