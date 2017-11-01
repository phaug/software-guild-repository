/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.model.Power;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class PowerController {

    PowerDao dao;

    @Inject
    PowerController(PowerDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/addPower", method = RequestMethod.POST)
    public String addPower(HttpServletRequest request) {

        Power power = new Power();
        power.setPowerName(request.getParameter("power"));
        dao.addPower(power);
        

        return "redirect:displayPowersPage";
    }

    @RequestMapping(value = "/displayPowersPage", method = RequestMethod.GET)
    public String displayPowersPage(Model model) {
        // Get all the Persons from the DAO
        List<Power> powersList = dao.getAllPowers();

        model.addAttribute("powersList", powersList);

        return "powers";
    }

    @RequestMapping(value = "/displayPowerDetails", method = RequestMethod.GET)
    public String displayPowerDetails(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);

        Power power = dao.getPowerbyId(powerId);

        model.addAttribute("power", power);

        return "powerDetails";
    }

    @RequestMapping(value = "/deletePower", method = RequestMethod.GET)
    public String deletePower(HttpServletRequest request) {
        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);
        dao.deletePower(powerId);
        return "redirect:displayPowersPage";
    }

    @RequestMapping(value = "/displayEditPowerForm", method = RequestMethod.GET)
    public String displayPowerForm(HttpServletRequest request, Model model) {
        String powerIdParameter = request.getParameter("powerId");
        int powerId = Integer.parseInt(powerIdParameter);
        Power power = dao.getPowerbyId(powerId);
        model.addAttribute("power", power);
        return "editPowerForm";
    }

    @RequestMapping(value = "/editPower", method = RequestMethod.POST)
    public String editPower(@Valid @ModelAttribute("power") Power power, BindingResult result) {

    if (result.hasErrors()) {
        return "editPowerForm";
    }
        dao.updatePower(power);

        return "redirect:displayPowersPage";
    }

}
