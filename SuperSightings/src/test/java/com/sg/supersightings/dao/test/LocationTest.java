/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao.test;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.dao.SightingDao;
import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperPerson;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class LocationTest {

    LocationDao lDao;
    SightingDao sDao;
    SuperPersonDao spDao;
    PowerDao pDao;

    public LocationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        lDao = ctx.getBean("LocationDao", LocationDao.class);
        sDao = ctx.getBean("SightingDao", SightingDao.class);
        spDao = ctx.getBean("SuperPersonDao", SuperPersonDao.class);
        pDao = ctx.getBean("PowerDao", PowerDao.class);

        List<Sighting> sightings = sDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sDao.deleteSighting(currentSighting.getSightingId());
        }
        List<Location> location = lDao.getAllLocations();
        for (Location currentLocation : location) {
            lDao.deleteLocation(currentLocation.getLocationId());
        }
        List<SuperPerson> persons = spDao.getAllPersons();
        for (SuperPerson currentPerson : persons) {
            spDao.deletePerson(currentPerson.getPersonId());
        }

        List<Power> powers = pDao.getAllPowers();
        for (Power currentPower : powers) {
            pDao.deletePower(currentPower.getPowerId());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void addLocation() {
        Location location = new Location();
        location.setLocationName("Place");
        location.setDescription("Where");
        location.setAddress("123 address");
        location.setLatitude(BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);

        Location createdLocation = lDao.addLocation(location);

        Location fromDao = lDao.getLocationbyId(location.getLocationId());
        assertEquals(createdLocation, fromDao);
    }

    @Test
    public void deleteLocation() {

        Location location = new Location();
        location.setLocationName("Place");
        location.setDescription("Where");
        location.setAddress("123 address");
        location.setLatitude(BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);

        Location createdLocation = lDao.addLocation(location);

        Location fromDao = lDao.getLocationbyId(location.getLocationId());
        assertEquals(createdLocation, fromDao);
        lDao.deleteLocation(location.getLocationId());
        assertNull(lDao.getLocationbyId(location.getLocationId()));

    }

    @Test
    public void updatedLocation() {
        Location location = new Location();
        location.setLocationName("Place");
        location.setDescription("Where");
        location.setAddress("123 address");
        location.setLatitude(BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);

        Location createdLocation = lDao.addLocation(location);

        location.setLocationName("New Place");
        lDao.updateLocation(createdLocation);

        Location fromDao = lDao.getLocationbyId(location.getLocationId());
        assertEquals(createdLocation, fromDao);
    }

    @Test
    public void getAllLocations() {
        Location location = new Location();
        location.setLocationName("Place");
        location.setDescription("Where");
        location.setAddress("123 address");
        location.setLatitude(BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);
        Location addLocation = lDao.addLocation(location);

        Location l2 = new Location();
        l2.setLocationName("Grace");
        l2.setDescription("Other Where");
        l2.setAddress("543 address");
        l2.setLatitude(BigDecimal.ONE);
        l2.setLongitude(BigDecimal.ONE);
        Location add2Location = lDao.addLocation(l2);
        List<Location> list = lDao.getAllLocations();
        assertEquals(list.size(), 2);
    }

    @Test
    public void getLocationById() {
        Location location = new Location();
        location.setLocationName("Place");
        location.setDescription("Where");
        location.setAddress("123 address");
        location.setLatitude(BigDecimal.ONE);
        location.setLongitude(BigDecimal.ONE);
        Location addLocation = lDao.addLocation(location);

        Location fromDao = lDao.getLocationbyId(location.getLocationId());
        assertEquals(location, fromDao);

    }

    @Test
    public void getLocationbyPersonId() {
        Location loc = new Location();
        loc.setLocationName("Green");
        loc.setDescription("?");
        loc.setAddress("51 St");
        loc.setLatitude(BigDecimal.ONE);
        loc.setLongitude(BigDecimal.ONE);
        loc = lDao.addLocation(loc);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Name");
        person.setDescription("awesome");
        person.setSide(1);
        person.setPower(p);
        person = spDao.addPerson(person);

        List<SuperPerson> sp = new ArrayList<>();
        sp.add(person);

        lDao.getLocationbyId(loc.getLocationId());

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        sighting.setSuperPerson(sp);
        sDao.addSighting(sighting);
        
        List<Location> fromDao = lDao.getLocationbyPersonId(person.getPersonId());
        assertEquals(fromDao.size(), 1);
    }

}
