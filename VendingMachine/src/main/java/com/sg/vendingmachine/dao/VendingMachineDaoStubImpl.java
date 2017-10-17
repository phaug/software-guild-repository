/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patri
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    Item item;

    List<Item> allItems = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        item = new Item("Snickers");
        //item.getItemName("Snickers");
        item.setItemCost(new BigDecimal(0.75));
        item.setItemInventory(8);
        allItems.add(item);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return allItems;
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        if(itemName.equals(item.getItemName())){
            return item;
        }else{
            return null;
        }
    }

    @Override
    public void updateQuantity(Item updateItem) throws VendingMachinePersistenceException {
        allItems.get(0).setItemInventory(item.getItemInventory() -1);
    }

}
