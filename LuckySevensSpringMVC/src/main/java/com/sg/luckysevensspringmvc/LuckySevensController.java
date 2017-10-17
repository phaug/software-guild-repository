/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.luckysevensspringmvc;

import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class LuckySevensController {

    @RequestMapping(value = "/luckySevenRoll",
            method = RequestMethod.POST)
    public String factorNumber(HttpServletRequest request,
            Map<String, Object> model) {

        Random r = new Random();
        int totalRolls, maxRolls, maxCash, dice1, dice2;
        totalRolls = 1;
        maxRolls = 0;

        String input = request.getParameter("userMoney");
        int userMoney = Integer.parseInt(input);

        maxCash = userMoney;

        while (userMoney
                > 0) {

            dice1 = r.nextInt(6) + 1;
            dice2 = r.nextInt(6) + 1;
            if (dice1 + dice2 == 7) {
                userMoney += 4;
            } else {
                userMoney -= 1;
            }

            if (userMoney > maxCash) {
                maxCash = userMoney;
                maxRolls = totalRolls;
            }
            totalRolls++;
        }

        model.put("totalRolls", totalRolls);
        model.put("maxCash", maxCash);
        model.put("userMoney", userMoney);
        model.put("maxRolls", maxRolls);
        return "results";
    }
}
