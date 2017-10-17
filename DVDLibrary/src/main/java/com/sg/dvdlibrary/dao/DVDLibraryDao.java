/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author patri
 */
public interface DVDLibraryDao {

    /**
     * Adds the given DVD to the lists and associates it with the given DVD
     * title. If there is already a DVD associated with that title it will
     * return that DVD object, otherwise returns null.
     *
     * @param title name for the DVD.
     * @param dvdTitle DVD added to the list.
     * @return the DVD object previously associated with the DVD title if it
     * exists, null otherwise
     */
    DVD createDvd(DVD dvd)
            throws DVDLibraryDaoException;

    /*Returns a String array containing the DVD titles of all DVDs in the
     * list.
     *
     * @return String array containing the titles of all the DVDS
     */
    List<DVD> getAllDvds()
            throws DVDLibraryDaoException;

    /**
     * Returns the DVD object associated with the given DVD title. Returns null
     * the DVD does not exist
     *
     * @param title of the DVD to retrieve
     * @return the DVD object associated with the given DVD title, null if the
     * DVD doesn't exist
     */
    DVD getDvdByTitle(String title)
            throws DVDLibraryDaoException;

    /**
     * Removes from the list the DVD associated with the DVD title. Returns the
     * DVD object that is being removed or null if there is no DVD associated
     * with the title
     *
     * @param title of DVD to be removed
     * @return DVD object that was removed or null if DVD was associated with
     * the DVD title
     */
    DVD removeDvdByTitle(String title)
            throws DVDLibraryDaoException;
   
    DVD editDvdByTitle(String title)
            throws DVDLibraryDaoException;
    /**
     * Searches through list of DVDs associated with the DVD title.
     * Returns the DVD searched or null if there is no DVD associated with the
     * title.
     * 
     * @param title of the DVD being searched
     * @return DVD object that has been searched or null if DVD doesn't exist
     * @throws DVDLibraryDaoException 
     */
    List<DVD> searchDvdByTitle(String title)
            throws DVDLibraryDaoException;

    
    
    /**
     * Edits a the information from of a DVD associated with the DVD title.  
     * Returns the DVD changes made or null if there is no DVD associated 
     * with the title.
     * 
     * 
     * @param toEdit 
     * @throws com.sg.dvdlibrary.dao.DVDLibraryDaoException
     */
    public void save(DVD toEdit) throws DVDLibraryDaoException;

}
