/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.SuperPerson;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class SuperPersonController {

    @Autowired
    SuperPersonDao dao;
    @Autowired
    PowerDao pDao;
    @Autowired
    OrganizationDao oDao;

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(
            @RequestParam("organizationId") List<Integer> organizationId,
            @RequestParam("powerId") Integer powerId,
            @RequestParam("superName") String superName,
            @RequestParam("superDescription") String superDescription,
            @RequestParam("side") Integer side) {

        SuperPerson superPerson = new SuperPerson();
        Power power = pDao.getPowerbyId(powerId);
        superPerson.setSuperName(superName);
        superPerson.setSuperDescription(superDescription);
        superPerson.setPower(power);
        List<Organization> organizationList = new ArrayList<>();
        for (Integer orgList : organizationId) {
            organizationList.add(oDao.getOrganizationbyId(orgList));
        }
        superPerson.setOrganization(organizationList);
        superPerson.setSide(side);

        dao.addPerson(superPerson);

        return "redirect:displaySuperPersonsPage";
    }

    @RequestMapping(value = "/displaySuperPersonsPage", method = RequestMethod.GET)
    public String displaySuperPersonsPage(Model model) {
        // Get all the Persons from the DAO
        List<SuperPerson> personsList = dao.getAllPersons();
        List<Organization> organizationList = oDao.getAllOrganizations();
        List<Power> powerList = pDao.getAllPowers();

        model.addAttribute("powerList", powerList);
        model.addAttribute("organizationList", organizationList);
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

    @RequestMapping(value = "/displayEditSuperPersonForm", method = RequestMethod.GET)
    public String displayEditSuperPersonForm(HttpServletRequest request, Model model) {
        String superPersonIdParameter = request.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superPersonIdParameter);
        List<Organization> organizationList = oDao.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        List<Power> powerList = pDao.getAllPowers();
        model.addAttribute("powerList", powerList);

        SuperPerson superPerson = dao.getPersonbyId(superPersonId);
        model.addAttribute("superPerson", superPerson);
        model.addAttribute("superPersonId", superPersonId);
        List<Integer> selectedOrgs = new ArrayList<>();
        for (Organization org : superPerson.getOrganization()) {
            selectedOrgs.add(org.getOrganizationId());
        }
        model.addAttribute("selectedOrgs", selectedOrgs);
        return "editSuperPersonForm";
    }

    @RequestMapping(value = "/editSuperPerson", method = RequestMethod.POST)
    public String editSuperPerson(
            @RequestParam("organizationId") List<Integer> submitOrgIds,
            @RequestParam("powerId") Integer powerId,
            @RequestParam("superName") String superName,
            @RequestParam("superDescription") String superDescription,
            @RequestParam("side") Integer side,
            @RequestParam("personId") Integer personId) {

        SuperPerson sp = new SuperPerson();
        Power power = pDao.getPowerbyId(powerId);
        sp.setSuperName(superName);
        sp.setSuperDescription(superDescription);
        sp.setPersonId(personId);
        sp.setPower(power);
        sp.setSide(side);
        List<Organization> organizationList = new ArrayList<>();
        for (Integer orgList : submitOrgIds) {
            organizationList.add(oDao.getOrganizationbyId(orgList));
        }
        sp.setOrganization(organizationList);
        dao.updatedPerson(sp);
        return "redirect:displaySuperPersonsPage";
    }

}
