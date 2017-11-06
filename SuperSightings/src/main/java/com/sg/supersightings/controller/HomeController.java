package com.sg.supersightings.controller;

import com.sg.supersightings.dao.SightingDao;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    SightingDao sDao;

    @RequestMapping
    public String home(Model model){
        
        model.addAttribute("sightings", sDao.findLastTenSightings(LocalDate.now().minusDays(10)));
        
        return "index";
    }
    
    
}
