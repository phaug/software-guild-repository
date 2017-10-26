/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.model.Location;
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
public class LocationsController {

    LocationDao dao;

    @Inject
    public LocationsController(LocationDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "Locations";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request) {
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setLatitude((BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);

        dao.addLocation(location);

        return "redirect/displayLocationsPage";
    }

    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String displayLocationsPage(Model model) {
        
        List<Location> locationList = dao.getAllLocations();

        
        model.addAttribute("locationList", locationList);

        
        return "locations";
    }
}
