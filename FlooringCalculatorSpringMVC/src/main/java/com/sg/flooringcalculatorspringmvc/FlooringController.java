/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringcalculatorspringmvc;

import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class FlooringController {

    @RequestMapping(value = "/flooringParameters",
            method = RequestMethod.POST)
    public String factorNumber(HttpServletRequest request,
            Map<String, Object> model) {

        String widthInput = request.getParameter("flooringWidth");
        String lengthInput = request.getParameter("flooringLength");
        String costInput = request.getParameter("costPerSqFt");
        int flooringLength = Integer.parseInt(lengthInput);
        int flooringWidth = Integer.parseInt(widthInput);
        int costPerSqFt = Integer.parseInt(costInput);
        int flooringArea = flooringLength * flooringWidth;
        int installRate = flooringArea * 20;
        int laborCost = installRate * 86;
        int materialCost = flooringArea * costPerSqFt;
        int totalCost = laborCost + materialCost;

        request.setAttribute("flooringLength", flooringLength);
        request.setAttribute("flooringWidth", flooringWidth);
        request.setAttribute("costPerSqFt", costPerSqFt);
        request.setAttribute("flooringArea", flooringArea);
        request.setAttribute("installRate", installRate);
        request.setAttribute("laborCost", laborCost);
        request.setAttribute("materialCost", materialCost);
        request.setAttribute("totalCost", totalCost);
        return "result";
    }
}
