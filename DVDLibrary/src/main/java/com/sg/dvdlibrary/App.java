/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDaoFileImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryDuplicateTitleException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayerImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author patri
 */
public class App {

    public static void main(String[] args) throws DVDLibraryDaoException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException, DVDLibraryPersistenceException {
//        UserIO myIo = new UserIOConsoleImpl();
//        DVDLibraryView myView = new DVDLibraryView(myIo);
//        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
//        DVDLibraryAuditDao myAuditDao = new DVDLibraryAuditDaoFileImpl();
//        DVDLibraryServiceLayer myService = new DVDLibraryServiceLayerImpl(myDao, myAuditDao);
//        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
//        controller.run();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.run();
    }
}
