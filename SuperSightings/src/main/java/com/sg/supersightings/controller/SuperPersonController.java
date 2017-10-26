/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.SuperPerson;
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
public class SuperPersonController {

    SuperPersonDao dao;

    @Inject
    public SuperPersonController(SuperPersonDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displaySuperPersonsPage", method = RequestMethod.GET)
    public String displaySuperPersonsPage() {
        return "SuperPersons";
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(HttpServletRequest request) {

        SuperPerson superPerson = new SuperPerson();
        superPerson.setSuperName(request.getParameter("superName"));
        superPerson.setDescription(request.getParameter("description"));
        superPerson.setPower(request.getParameter("power"));
        superPerson.setOrganization(request.getParameter("organization"));
        superPerson.setSide(request.getParameter("side"));

        dao.addPerson(superPerson);

        return "redirect:displaySuperPersonsPage";
    }

    @RequestMapping(value = "/displaySuperPersonsPage", method = RequestMethod.GET)
    public String displaySuperPersonsPage(Model model) {
        // Get all the Persons from the DAO
        List<SuperPerson> personsList = dao.getAllPersons();

        model.addAttribute("personsList", personsList);

        return "superPersons";
    }

    @RequestMapping(value = "/displaySuperPersonDetails", method = RequestMethod.GET)
    public String displaySuperPersonDetails(HttpServletRequest request, Model model) {
        String superPersonIdParameter = request.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superPersonIdParameter);

        SuperPerson superPerson = dao.getPersonbyId(superPersonId);

        model.addAttribute("superPerson", superPerson);

        return "superPersonDetails";
    }

    @RequestMapping(value = "/deleteSuperPerson", method = RequestMethod.GET)
    public String deleteSuperPerson(HttpServletRequest request) {
        String superPersonIdParameter = request.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superPersonIdParameter);
        dao.deletePerson(superPersonId);
        return "redirect:displaySuperPersonsPage";
    }

}
