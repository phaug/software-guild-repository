/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
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
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    BigDecimal userMoney = BigDecimal.ZERO;

    public VendingMachineServiceLayerImpl(VendingMachineDao myDao, VendingMachineAuditDao myAuditDao) {
        this.dao = myDao;
        this.auditDao = myAuditDao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        return dao.getItem(itemName);
    }

    public void updateQuantity(Item itemName) throws VendingMachinePersistenceException {
        itemName.setItemInventory(itemName.getItemInventory() - 1);
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
}
