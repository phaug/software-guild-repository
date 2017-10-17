/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface DVDLibraryServiceLayer {

    void addDVD(DVD title) throws DVDLibraryDuplicateTitleException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException,
            DVDLibraryDaoException;

    List<DVD> listDVDs() throws 
            DVDLibraryDaoException;

    DVD viewDVD(String title) throws 
            DVDLibraryDaoException;

    DVD removeDVD(String title) throws
            DVDLibraryDaoException;

}
