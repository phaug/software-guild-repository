/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.model.Organization;
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
public class OrganizationController {

    OrganizationDao dao;

    @Inject
    public OrganizationController(OrganizationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage() {
        return "Organizations";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {
        Organization organization = new Organization();
        organization.setOrgName(request.getParameter("organizationName"));
        organization.setDescription(request.getParameter("description"));
        organization.setLocation(request.getParameter("location"));
        organization.setPhone(request.getParameter("phone"));

        dao.addOrganization(organization);

        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        // Get all the Contacts from the DAO
        List<Organization> orgList = dao.getAllOrganizations();

        // Put the List of Contacts on the Model
        model.addAttribute("orgList", orgList);

        // Return the logical name of our View component
        return "organizations";
    }

}
