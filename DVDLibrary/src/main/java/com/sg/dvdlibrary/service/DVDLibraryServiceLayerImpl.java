/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    DVDLibraryDao dao;
    private DVDLibraryAuditDao auditDao;

    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao, DVDLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addDVD(DVD title) throws DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException, DVDLibraryPersistenceException, DVDLibraryDaoException {

        // First check to see if there is alreay a title 
        // associated with the given title's id
        // If so, we're all done here - 
        // throw a DVDLibraryDuplicateIdException
        if (dao.getDvdByTitle(title.getTitle()) != null) {
            throw new DVDLibraryDuplicateTitleException(
                    "ERROR: Could not create title.  DVD Id "
                    + title.getTitle()
                    + " already exists");
        }

        validateDVDData(title);
        //dao.createDvd(title.getTitle(), title);

        // The title was successfully created, now write to the audit log
        //auditDao.writeAuditEntry("DVD " + title.getDVDId() + " CREATED.");
    }

    @Override
    public List<DVD> listDVDs() throws DVDLibraryDaoException {
        return dao.getAllDvds();
    }

//    @Override
//    public DVD editDVD(String title) throws DVDLibraryDaoException {
//        return dao.editDvdByTitle(title);
//}

@Override
    public DVD viewDVD(String title) throws DVDLibraryDaoException {
    return dao.getDvdByTitle(title);
    }

    @Override
        public DVD removeDVD(String title) throws DVDLibraryDaoException {
        DVD removeDVD = dao.removeDvdByTitle(title);
        //Write to audit log
        //auditDao.writeAuditEntry("DVD " + titleId + " REMOVED.");
        return dao.removeDvdByTitle(title);
    }

    private void validateDVDData(DVD title) throws DVDLibraryDataValidationException {

        if (title.getTitle() == null
                || title.getTitle().trim().length() == 0
                || title.getReleaseDate() == null
                || title.getReleaseDate().trim().length() == 0
                || title.getMpaaRating() == null
                || title.getMpaaRating().trim().length() == 0
                || title.getDirectorName() == null
                || title.getDirectorName().trim().length() == 0
                || title.getMovieStudio() == null
                || title.getMovieStudio().trim().length() == 0
                || title.getUserRating() == null
                || title.getUserRating().trim().length() == 0) {

            throw new DVDLibraryDataValidationException(
                    "ERROR: All fields are required.");

    }
}
}
