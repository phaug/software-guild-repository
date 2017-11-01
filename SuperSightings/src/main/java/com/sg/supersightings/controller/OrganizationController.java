/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
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
public class OrganizationController {

    OrganizationDao dao;
    LocationDao lDao;

    @Inject
    public OrganizationController(OrganizationDao dao, LocationDao lDao) {
        this.dao = dao;
        this.lDao = lDao;
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {
        Organization organization = new Organization();
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = lDao.getLocationbyId(locationId);
        organization.setOrgName(request.getParameter("organizationName"));
        organization.setDescription(request.getParameter("description"));
        organization.setLocation(location);
        organization.setPhone(request.getParameter("phone"));

        dao.addOrganization(organization);

        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String displayOrganizationsPage(Model model) {
        List<Organization> orgList = dao.getAllOrganizations();
        List<Location> locationList = lDao.getAllLocations();

        model.addAttribute("locationList", locationList);

        model.addAttribute("orgList", orgList);
        return "organizations";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = dao.getOrganizationbyId(organizationId);

        model.addAttribute("organization", organization);

        return "organizationDetails";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        dao.deleteOrganization(organizationId);
        return "redirect:displayOrganizationsPage";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        List<Location> locationList = lDao.getAllLocations();
        model.addAttribute("locationList", locationList);
        Organization organization = dao.getOrganizationbyId(organizationId);
        model.addAttribute("organization", organization);
        return "editOrganizationForm";
    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result, Integer locationId) {

        if (result.hasErrors()) {
            return "editOrganizationForm";
        }
        organization.setLocation(lDao.getLocationbyId(locationId));
        dao.updateOrganization(organization);
        return "redirect:displayOrganizationsPage";
    }

}
