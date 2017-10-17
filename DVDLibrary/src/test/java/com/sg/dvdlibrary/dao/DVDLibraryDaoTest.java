/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class DVDLibraryDaoTest {
    
    public DVDLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createDvd method, of class DVDLibraryDao.
     */
    @Test
    public void testCreateDvd() throws Exception {
    }

    /**
     * Test of getAllDvds method, of class DVDLibraryDao.
     */
    @Test
    public void testGetAllDvds() throws Exception {
    }

    /**
     * Test of getDvdByTitle method, of class DVDLibraryDao.
     */
    @Test
    public void testGetDvdByTitle() throws Exception {
    }

    /**
     * Test of removeDvdByTitle method, of class DVDLibraryDao.
     */
    @Test
    public void testRemoveDvdByTitle() throws Exception {
    }

    /**
     * Test of searchDvdByTitle method, of class DVDLibraryDao.
     */
    @Test
    public void testSearchDvdByTitle() throws Exception {
    }

    /**
     * Test of save method, of class DVDLibraryDao.
     */
    @Test
    public void testSave() throws Exception {
    }

    public class DVDLibraryDaoImpl implements DVDLibraryDao {

        public DVD createDvd(DVD dvd) throws DVDLibraryDaoException {
            return null;
        }

        public List<DVD> getAllDvds() throws DVDLibraryDaoException {
            return null;
        }

        public DVD getDvdByTitle(String title) throws DVDLibraryDaoException {
            return null;
        }

        public DVD removeDvdByTitle(String title) throws DVDLibraryDaoException {
            return null;
        }

        public List<DVD> searchDvdByTitle(String title) throws DVDLibraryDaoException {
            return null;
        }

        public void save(DVD toEdit) throws DVDLibraryDaoException {
        }

        @Override
        public DVD editDvdByTitle(String title) throws DVDLibraryDaoException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
