/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryAuditDaoStubImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class DVDLibraryServiceLayerTest {
//    // wire the Service Layer with stub implementations of the Dao and
//    // Audit Dao
//
//    DVDLibraryDao dao = new DVDLibraryDaoStubImpl();
//    DVDLibraryAuditDao auditDao = new DVDLibraryAuditDaoStubImpl();
//
//    service  = new DVDLibraryServiceLayerImpl(dao, auditDao);

    ApplicationContext ctx
            = new ClassPathXmlApplicationContext("applicationContext.xml");
    DVDLibraryServiceLayer service  = ctx.getBean("serviceLayer", DVDLibraryServiceLayer.class);

    public DVDLibraryServiceLayerTest() {
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
     * Test of addDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testAddDVD() throws Exception {
    }

    /**
     * Test of listDVDs method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testListDVDs() throws Exception {
    }

    /**
     * Test of viewDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testViewDVD() throws Exception {
    }

    /**
     * Test of removeDVD method, of class DVDLibraryServiceLayer.
     */
    @Test
    public void testRemoveDVD() throws Exception {
    }

    public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

        public void addDVD(DVD title) throws DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException, DVDLibraryPersistenceException, DVDLibraryDaoException {
        }

        public List<DVD> listDVDs() throws DVDLibraryDaoException {
            return null;
        }

        public DVD viewDVD(String title) throws DVDLibraryDaoException {
            return null;
        }

        public DVD removeDVD(String title) throws DVDLibraryDaoException {
            return null;
        }
    }

}
