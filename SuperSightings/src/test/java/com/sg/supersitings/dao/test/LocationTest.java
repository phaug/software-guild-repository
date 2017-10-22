/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.sg.supersitings.dao.test;
//
//import com.sg.supersightings.dao.LocationDao;
//import com.sg.supersightings.dao.SightingDao;
//import com.sg.supersightings.dao.SuperPersonDao;
//import com.sg.supersightings.model.Location;
//import com.sg.supersightings.model.Sighting;
//import com.sg.supersightings.model.SuperPerson;
//import java.math.BigDecimal;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertEquals;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author apprentice
// */
//public class LocationTest {
//
//    LocationDao lDao;
//    SightingDao sDao;
//    SuperPersonDao spDao;
//
//    public LocationTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
//        lDao = ctx.getBean("LocationDao", LocationDao.class);
//        sDao = ctx.getBean("SightingDao", SightingDao.class);
//        spDao = ctx.getBean("SuperPersonDao", SuperPersonDao.class);
//
//        List<Location> location = lDao.getAllLocations();
//        for (Location currentLocation : location) {
//            lDao.deleteLocation(currentLocation.getLocationId());
//        }
//        List<Sighting> sightings = sDao.getAllSightings();
//        for (Sighting currentSighting : sightings) {
//            sDao.deleteSighting(currentSighting.getSightingId());
//        }
//        List<SuperPerson> persons = spDao.getAllPersons();
//        for (SuperPerson currentPerson : persons) {
//            spDao.deletePerson(currentPerson.getPersonId());
//        }
//
//    }
//
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void addLocation() {
//        Location location = new Location();
//        location.setLocName("Place");
//        location.setLocDescription("Where");
//        location.setAddress("123 address");
//        location.setLatitude(BigDecimal.ONE);
//        location.setLongitude(BigDecimal.ONE);
//        
//        lDao.addLocation(location);
//        
//        Location fromDao = lDao.getLocationbyId(location.getLocationId());
//        assertEquals(fromDao, location);
//    }
//}
