/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author patri
 */
public class VendingMachineView {

    UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

//    public int printMenu(List<Item> allItems) {
//        io.print("Vending Machine");
//        this.displayMenuItems(allItems);
//        io.readInt("Please enter dollar amount you'd like to add to the machine.");
//        io.print("Exit");
//        return io.readInt("Please select from the choices.");
//    }
    public enum VendingMachineAction {
        SHOW_MENU,
        ADD_MONEY,
        BUY_ITEM,
        EXIT

    }

    public VendingMachineAction getAction() {
        int selection = io.readInt("What would you like to do?\n"
                + "1. Show Menu\n"
                + "2. Add Money\n"
                + "3.Buy Item\n"
                + "4.Exit\n",
                1, 4);
        return VendingMachineAction.values()[selection - 1];

    }

    public void displayDollarsEnteredBanner(BigDecimal amountDeposited) {

        if (amountDeposited == null || amountDeposited.equals(BigDecimal.ZERO)) {
            io.print("You did not enter any money, please add money to the machine.");
        } else {
            io.print("You have added  " + amountDeposited
                    + " to the machine, please make your selection");
        }
    }

    public BigDecimal collectMoney() {
        return io.readBigDecimal("Please enter dollar amount");

    }

    public String getItemFromUser() {
        return io.readString("What item would you like to purchase?"
                + " Please enter the title of the item.");

    }

    public void displayPurchaseSuccessBanner(Change change) {
        StringBuilder changeBuilder = new StringBuilder();

        changeBuilder
                .append("\nFive Dollar Bills: ").append(change.getFiveDollarBills())
                .append("\nOne Dollar Bills: ").append(change.getOneDollarBills())
                .append("\nQuarters: ").append(change.getQuarters())
                .append("\nDimes: ").append(change.getDimes())
                .append("\nNickels: ").append(change.getNickels())
                .append("\nPennies: ").append(change.getPennies());

        io.print(
                "Your change is \n " + changeBuilder
                + "." + " Please hit enter to continue");
    }

    public void displayInsufficientFundsBanner() {
        io.print("Insufficient funds, please choose from a different "
                + "selection or add more money to the machine.");
    }

    public void displayOutofStockBanner() {
        io.print("Sorry, this item is out of stock.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayMenuItems(List<Item> allItems) {
        for (Item currentItem : allItems) {
            io.print(currentItem.getItemName() + " $" + currentItem.getItemCost());
        }
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    public void displayNotItemNameErrorMessage (String errorMsg){
        io.print("Must enter the name of the item you with to purchase");
        io.print(errorMsg);
    }

}
