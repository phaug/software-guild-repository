/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.dao.SightingDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import java.time.LocalDate;
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
public class SightingsController {

    SightingDao dao;
    LocationDao lDao;

    @Inject
    public SightingsController(SightingDao dao) {
        this.dao = dao;
    }


    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest request) {

        Sighting sighting = new Sighting();
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = lDao.getLocationbyId(locationId);
//        sighting.setDate(LocalDate.MAX);
        sighting.setLocation(location);

        dao.addSighting(sighting);

        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displayContactsPage(Model model) {
        // Get all the Sightings from the DAO
        List<Sighting> sightingList = dao.getAllSightings();

        model.addAttribute("sightingList", sightingList);

        return "sightings";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = dao.getSightingbyId(sightingId);

        model.addAttribute("sighting", sighting);

        return "sightingDetails";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        dao.deleteSighting(sightingId);
        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = dao.getSightingbyId(sightingId);
        model.addAttribute("sighting", sighting);
        return "editSightingForm";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result) {

    if (result.hasErrors()) {
        return "editSightingForm";
    }
        dao.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }
}
