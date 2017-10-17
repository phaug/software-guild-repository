/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author patri
 */
public class DVDLibraryView {

    private UserIO io;

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVD Collection");
        io.print("2. View a DVD");
        io.print("3. Add a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD's information");
        io.print("6. Search for DVD title");
        io.print("7. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 7);
    }

    public DVD getNewDvdInfo() {

        String title = io.readString("Please enter DVD title");
        String releaseDate = io.readString("Please enter the DVD release date");
        String mpaaRating = io.readString("Please enter MPAA rating (PG, PG-13, etc.");
        String directorName = io.readString("Please enter the director's name");
        String movieStudio = io.readString("Please enter the studio that made the movie");
        String userRating = io.readString("Please write your personal rating of the movie");
        DVD currentTitle = new DVD(title);
        currentTitle.setReleaseDate(releaseDate);
        currentTitle.setMpaaRating(mpaaRating);
        currentTitle.setDirectorName(directorName);
        currentTitle.setMovieStudio(movieStudio);
        currentTitle.setUserRating(userRating);
        return currentTitle;
    }

       public DVD setDvdInfo(DVD toEdit) {


       String releaseDate = io.readString("Please enter the DVD release date");
        String mpaaRating = io.readString("Please enter MPAA rating (PG, PG-13, etc.");
        String directorName = io.readString("Please enter the director's name");
        String movieStudio = io.readString("Please enter the studio that made the movie");
        String userRating = io.readString("Please write your personal rating of the movie");

        toEdit.setReleaseDate(releaseDate);
        toEdit.setMpaaRating(mpaaRating);
        toEdit.setDirectorName(directorName);
        toEdit.setMovieStudio(movieStudio);
        toEdit.setUserRating(userRating);
        return toEdit;
    }
    public void displayCreateDVDBanner() {
        io.print("=== Create new DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentTitle : dvdList) {
            io.print(currentTitle.getTitle() + ": "
                    + currentTitle.getReleaseDate() + " "
                    + currentTitle.getMpaaRating() + " "
                    + currentTitle.getDirectorName() + " "
                    + currentTitle.getMovieStudio() + " "
                    + currentTitle.getUserRating());
        }

        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the DVD title.");
    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getMovieStudio());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited. Please hit enter to continue.");
    }

    public void displaySearchDvdBanner() {
        io.print("=== Search for a DVD by title ===");
    }
    
    public void displaySearchDvdSuccessBanner(){
        io.print("=== DVD found ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}
