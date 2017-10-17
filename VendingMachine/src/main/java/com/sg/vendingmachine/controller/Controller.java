/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patri
 */
public class Controller {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    UserIO io = new UserIOConsoleImpl();

    public Controller(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;

    }

    public void run() {
        boolean keepRunning = true;
        VendingMachineView.VendingMachineAction action;

        while (keepRunning) {
            action = view.getAction();

            if (action.equals(VendingMachineView.VendingMachineAction.SHOW_MENU)) {
                List<Item> allItems;
                try {
                    allItems = service.getAllItems();
                    view.displayMenuItems(allItems);
                } catch (VendingMachinePersistenceException ex) {
                    view.displayUnknownCommandBanner();
                }

            } else if (action.equals(VendingMachineView.VendingMachineAction.ADD_MONEY)) {

                try {
                    BigDecimal amount = view.collectMoney();
                    service.depositMoney(amount);
                    view.displayDollarsEnteredBanner(service.getAmountInMachine());
                } catch (NumberFormatException ex) {
                    view.displayErrorMessage("Need to enter a dollar amount.");
                }

            } else if (action.equals(VendingMachineView.VendingMachineAction.BUY_ITEM)) {
                List<Item> allItems;
                try {
                    allItems = service.getAllItems();
                    view.displayMenuItems(allItems);
                } catch (VendingMachinePersistenceException ex) {
                    view.displayUnknownCommandBanner();
                }
                boolean noEntry = true;
                Item itemPurchased = null;
                while (noEntry){
                    
                    String itemName = view.getItemFromUser();
                    

                    try {
                        itemPurchased = service.getItem(itemName);
                    } catch (VendingMachinePersistenceException ex) {
                        view.displayUnknownCommandBanner();
                    }
                    if (itemPurchased == null) {
                        view.displayErrorMessage("Must enter an item name");
                    }else{
                        noEntry = false;
                    }
                }
                try {
                    Change change = service.purchase(itemPurchased);
                    view.displayPurchaseSuccessBanner(change);
                } catch (VendingMachineInsufficientFundsException ex) {
                    view.displayInsufficientFundsBanner();
                } catch (VendingMachineNoItemInventoryException ex) {
                    view.displayOutofStockBanner();
                } catch (VendingMachinePersistenceException ex) {
                    view.displayUnknownCommandBanner();
                }

            } else if (action.equals(VendingMachineView.VendingMachineAction.EXIT)) {
                view.displayExitBanner();
                return;
            }
        }

    }
}
