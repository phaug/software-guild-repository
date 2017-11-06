/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.dao.SightingDao;
import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperPerson;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
public class SightingsController {

    @Autowired
    SightingDao dao;
    @Autowired
    LocationDao lDao;
    @Autowired
    SuperPersonDao spDao;

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(
            @RequestParam("date") String dateString,
            @RequestParam("locationId") Integer locationId,
            @RequestParam("personId") List<Integer> personId) {

        Sighting sighting = new Sighting();
        Location location = lDao.getLocationbyId(locationId);
        sighting.setDate(LocalDate.parse(dateString));
        sighting.setLocation(location);
        List<SuperPerson> spList = new ArrayList<>();
        for (Integer superList : personId) {
            spList.add(spDao.getPersonbyId(superList));
        }
        sighting.setSuperPerson(spList);

        dao.addSighting(sighting);

        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model model) {
        // Get all the Sightings from the DAO
        List<Sighting> sightingList = dao.getAllSightings();
        List<Location> locationList = lDao.getAllLocations();
        List<SuperPerson> superPersonList = spDao.getAllPersons();

        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superPersonList", superPersonList);

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
        List<SuperPerson> superPersonList = spDao.getAllPersons();
        model.addAttribute("superPersonList", superPersonList);
        List<Location> locationList = lDao.getAllLocations();
        model.addAttribute("locationList", locationList);

        Sighting sighting = dao.getSightingbyId(sightingId);
        model.addAttribute("sighting", sighting);
        model.addAttribute("sightingId", sightingId);
        List<Integer> selectedPeople = new ArrayList<>();
        for (SuperPerson sp : sighting.getSuperPerson()) {
            selectedPeople.add(sp.getPersonId());
        }
        model.addAttribute("selectedPeople", selectedPeople);
        return "editSightingForm";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(
            @RequestParam("date") String date,
            @RequestParam("locationId") Integer locationId,
            @RequestParam("superPersonId") List<Integer> personId,
            @RequestParam("sightingId") Integer sightingId) {

        Sighting sighting = new Sighting();
        Location location = lDao.getLocationbyId(locationId);
        sighting.setDate(LocalDate.parse(date));
        sighting.setLocation(location);
        sighting.setSightingId(sightingId);
        List<SuperPerson> spList = new ArrayList<>();
        for (Integer superList : personId) {
            spList.add(spDao.getPersonbyId(superList));
        }
        sighting.setSuperPerson(spList);
        dao.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }
}
