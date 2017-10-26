/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.SightingDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class SightingsController {
    
    SightingDao dao;
    
    @Inject
    public SightingsController(SightingDao dao) {
        this.dao = dao;
    }
    
        @RequestMapping(value="/displaySightingsPage", method=RequestMethod.GET)
    public String displaySearchPage() {
        return "Sightings";
    }

}
