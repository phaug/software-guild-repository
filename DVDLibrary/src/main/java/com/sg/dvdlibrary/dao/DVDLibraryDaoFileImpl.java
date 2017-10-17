/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author patri
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "::";

    public DVDLibraryDaoFileImpl() throws DVDLibraryDaoException {
        loadDvdList();
    }

    @Override
    public DVD createDvd(DVD dvd)
            throws DVDLibraryDaoException {
        if (dvds.containsKey(dvd.getTitle())) {
            throw new DVDLibraryDaoException("dvd already exists with that name");
        }
        dvds.put(dvd.getTitle(), dvd);
        writeList();
        return dvd;
    }

    @Override
    public List<DVD> getAllDvds()
            throws DVDLibraryDaoException {
        loadDvdList();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDvdByTitle(String title)
            throws DVDLibraryDaoException {
        loadDvdList();
        return dvds.get(title);
    }

    @Override
    public DVD removeDvdByTitle(String title)
            throws DVDLibraryDaoException {
        DVD removeDvd = dvds.remove(title);
        writeList();
        return removeDvd;
    }

    @Override
    public void save(DVD toEdit)
            throws DVDLibraryDaoException {

        if (toEdit == null) {
            System.out.println("This DVD does not exist");
        }
        writeList();
        loadDvdList();

    }

    @Override
    public List<DVD> searchDvdByTitle(String title)
            throws DVDLibraryDaoException {

        List<DVD> matches = new ArrayList<>();

        for (DVD dvd : dvds.values()) {
            if (dvd.getTitle().startsWith(title)) {
                matches.add(dvd);
            }
        }

        return matches;
    }

    private Map<String, DVD> dvds = new HashMap<>();

    private void loadDvdList() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load DVD data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        //
        // __________________________________________________________________________
        // |            |    | |             |            |                         |
        // |Donnie Darko|2001|R|Richard Kelly|Flower Films|Psychological mind melter|
        // |            |    | |             |            |                         |
        // --------------------------------------------------------------------------
        //  [0]          [1] [2] [3]          [4]          [5] 
        String[] currentTokens;
        // Go through DVD_FILE line by line, decoding each line into a 
        // DVD object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new DVD object and put it into the map of DVDs
            // 
            DVD currentTitle = new DVD(currentTokens[0]);
            // Set the remaining vlaues on currentTitle manually
            currentTitle.setReleaseDate(currentTokens[1]);
            currentTitle.setMpaaRating(currentTokens[2]);
            currentTitle.setDirectorName(currentTokens[3]);
            currentTitle.setMovieStudio(currentTokens[4]);
            currentTitle.setUserRating(currentTokens[5]);

            // Put currentStudent into the map using DVD title as the key
            dvds.put(currentTitle.getTitle(), currentTitle);
        }
        // close scanner
        scanner.close();
    }

    private String getDefaultSaveValue(String input) {
        if (null == input || input.length() == 0) {
            return "Not Set";
        }

        return input;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE. See loadRoster
     * for file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeList() throws DVDLibraryDaoException {
        // We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        // Write out the DVD objects to the DVD file.
        //
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList) {
            // write the Student object to the file
            out.println(getDefaultSaveValue(currentDvd.getTitle()) + DELIMITER
                    + getDefaultSaveValue(currentDvd.getReleaseDate()) + DELIMITER
                    + getDefaultSaveValue(currentDvd.getMpaaRating()) + DELIMITER
                    + getDefaultSaveValue(currentDvd.getDirectorName()) + DELIMITER
                    + getDefaultSaveValue(currentDvd.getMovieStudio()) + DELIMITER
                    + getDefaultSaveValue(currentDvd.getUserRating()));
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public DVD editDvdByTitle(String title) throws DVDLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
