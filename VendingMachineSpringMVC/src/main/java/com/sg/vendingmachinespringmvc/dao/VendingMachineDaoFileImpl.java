/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
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
 * @author apprentice
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<Integer, Item> inventory = new HashMap<>();

    public static final String INVENTORY_FILE = "C:\\Users\\apprentice\\Documents\\patrick-haug-work\\patrick-haug-individual-work\\VendingMachineSpringMVC\\Inventory.txt";
    public static final String DELIMITER = "::";

    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {
        loadList();
        return new ArrayList<Item>(inventory.values());
    }

    @Override
    public Item getItem(int itemId)
            throws VendingMachinePersistenceException {
        loadList();
        return inventory.get(itemId);
    }

    @Override
    public void updateQuantity(Item updateItem)
            throws VendingMachinePersistenceException {
        loadList();
        inventory.remove(updateItem.getItemId());
        inventory.put(updateItem.getItemId(), updateItem);
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

            Item currentItem = new Item(Integer.parseInt(currentTokens[0]));
            currentItem.setItemName((currentTokens[1]));
            currentItem.setItemCost(new BigDecimal(currentTokens[2]));
            currentItem.setItemInventory(Integer.parseInt(currentTokens[3]));

            inventory.put(currentItem.getItemId(), currentItem);
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
                    "Could not save item data.", e);
        }
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {

            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getItemCost() + DELIMITER
                    + currentItem.getItemInventory());

            out.flush();
        }

        out.close();
    }
    
}
