package com.sg.supersightings.controller;

import com.sg.supersightings.dao.SightingDao;
import com.sg.supersightings.model.Sighting;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    SightingDao sDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        List<Sighting> sightingList =sDao.findLastTenSightings();
        
        model.addAttribute("sightingList", sightingList );
        
        return "index";
    }
    
    
}
