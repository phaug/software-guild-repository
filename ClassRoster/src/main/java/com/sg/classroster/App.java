/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster;

import Controller.ClassRosterController;
import DAO.ClassRosterAuditDao;
import DAO.ClassRosterAuditDaoFileImpl;
import DAO.ClassRosterDao;
import DAO.ClassRosterDaoFileImpl;
import Service.ClassRosterServiceLayer;
import Service.ClassRosterServiceLayerImpl;
import UI.ClassRosterView;
import UI.UserIO;
import UI.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alejandro
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        ClassRosterView myView = new ClassRosterView(myIo);
//        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//        ClassRosterController controller = new ClassRosterController(myService, myView);
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
