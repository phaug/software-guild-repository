/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.model.Money;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class VendingController {

    int itemId;
    String purchase;
    String returnChange;
    String changeMessage;
    double userMoney;

    @Inject
    private VendingMachineServiceLayer service;

    //initial load page
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayItems(HttpServletRequest request, Model m) throws VendingMachinePersistenceException {
        List<Item> items = service.getAllItems();
        m.addAttribute("items", items);
        m.addAttribute("depositMoney", service.getAmountInMachine());
        m.addAttribute("itemId", itemId);
        m.addAttribute("purchase", purchase);
        m.addAttribute("returnChange", returnChange);

        return "index";
    }

    @RequestMapping(value = "/displayItems", method = RequestMethod.POST)
    public String displayItemSelection(HttpServletRequest request) throws VendingMachinePersistenceException {
        String itemIdParameter = request.getParameter("itemSelection");
        itemId = Integer.parseInt(itemIdParameter);

        return "redirect:/";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String displayDeposit(HttpServletRequest request) {
        String addMoney = request.getParameter("moneyAdded");

        if (addMoney.equals("1.00")) {
            service.depositMoney(new BigDecimal("1.00"));
        }

        if (addMoney.equals("0.25")) {
            service.depositMoney(new BigDecimal("0.25"));
        }

        if (addMoney.equals("0.10")) {
            service.depositMoney(new BigDecimal("0.10"));
        }

        if (addMoney.equals("0.05")) {
            service.depositMoney(new BigDecimal("0.05"));
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public String purchase(HttpServletRequest request, Model m) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
        Item itemPurchased = service.getItem(itemId);
        BigDecimal moneyInput = new BigDecimal(userMoney);
        m.addAttribute("messages", "Thanks!");
        try {
            Change change = service.purchase(itemPurchased);
            m.addAttribute("returnChange", "Quarters = " + change.getQuarters()
                    + "Dimes = " + change.getDimes()
                    + "Nickels = " + change.getNickels()
                    + "Pennies = " + change.getPennies());

        } catch (VendingMachineInsufficientFundsException | VendingMachineNoItemInventoryException ex) {
            m.addAttribute("messages", ex.getMessage());
        }
        
        m.addAttribute("items", service.getAllItems());
        return "index";

    }

    @RequestMapping(value = "/returnChange", method = RequestMethod.POST)
    public String returnChange(HttpServletRequest request, Model m) throws VendingMachinePersistenceException {
        returnChange = request.getParameter("returnChange");
        Change change = service.returnMoney();
        m.addAttribute("returnChange", "Dollars = " + change.getOneDollarBills()
                + "Quarters = " + change.getQuarters() + ""
                + "Dimes = " + change.getDimes()+ ""
                + "Nickels = " + change.getNickels()+ ""
                + "Pennies = " + change.getPennies());
        m.addAttribute("items", service.getAllItems());
        return "index";
    }

}
