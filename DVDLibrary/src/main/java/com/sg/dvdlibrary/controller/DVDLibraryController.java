/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.service.DVDLibraryDataValidationException;
import com.sg.dvdlibrary.service.DVDLibraryDuplicateTitleException;
import com.sg.dvdlibrary.service.DVDLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author patri
 */
public class DVDLibraryController {

    
    DVDLibraryView view;
    DVDLibraryServiceLayer service;
    private UserIO io = new UserIOConsoleImpl();
    
        public DVDLibraryController(DVDLibraryServiceLayer service, DVDLibraryView view) {
        this.service = service;
        this.view = view;
        }

    public void run() throws DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException, DVDLibraryPersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        viewDvd();
                        break;
                    case 3:
                        addDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        searchDvd();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException, DVDLibraryPersistenceException, DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDvd = view.getNewDvdInfo();
        service.addDVD(newDvd);
        view.displayCreateSuccessBanner();
    }

    private void listDvds() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = service.listDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDvd() throws DVDLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getTitleChoice();
        DVD dvd = service.viewDVD(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DVDLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getTitleChoice();
        service.removeDVD(title);
        view.displayRemoveSuccessBanner();
    }

    private void editDvd() throws DVDLibraryDaoException {
        view.displayEditDvdBanner();
        String title = view.getTitleChoice();
        DVD toEdit = service.viewDVD(title);
        if(toEdit != null){
            DVD newDvdInfo = view.setDvdInfo(toEdit);
        toEdit.setTitle(newDvdInfo.getTitle());
        toEdit.setDirectorName(newDvdInfo.getDirectorName());
        toEdit.setMovieStudio(newDvdInfo.getMovieStudio());
        toEdit.setMpaaRating(newDvdInfo.getMpaaRating());
        toEdit.setReleaseDate(newDvdInfo.getReleaseDate());
        toEdit.setUserRating(newDvdInfo.getUserRating());
        service.viewDVD(title);
        view.displayEditSuccessBanner();
        }else {
            System.out.println("No such DVD");
        }
        
    }

    private void searchDvd() throws DVDLibraryDaoException {
        view.displaySearchDvdBanner();
        String title = view.getTitleChoice();
        List<DVD> searchResults = service.listDVDs();

        view.displayDVDList(searchResults);
        view.displaySearchDvdSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.service = service;
        this.view = view;
    }

}
