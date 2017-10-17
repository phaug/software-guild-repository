/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
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

/**
 *
 * @author patri
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> inventory = new HashMap<>();

    public static final String INVENTORY_FILE = "Inventory.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {
        loadList();
        return new ArrayList<Item>(inventory.values());
    }

    @Override
    public Item getItem(String itemName)
            throws VendingMachinePersistenceException {
        loadList();
        return inventory.get(itemName);
    }

    @Override
    public void updateQuantity(Item updateItem)
            throws VendingMachinePersistenceException {
        loadList();
        inventory.remove(updateItem.getItemName());
        inventory.put(updateItem.getItemName(), updateItem);
        writeList();
    }

    private void loadList() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load roster data into memory.", e);
        } 

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemCost(new BigDecimal(currentTokens[1]));
            currentItem.setItemInventory(Integer.parseInt(currentTokens[2]));

            inventory.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private void writeList() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save student data.", e);
        }
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {

            out.println(currentItem.getItemName() + DELIMITER
                    + currentItem.getItemCost() + DELIMITER
                    + currentItem.getItemInventory());

            out.flush();
        }

        out.close();
    }
    
    

}
