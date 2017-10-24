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
public class SightingTest {

    SuperPersonDao spDao;
    LocationDao lDao;
    SightingDao sDao;
    PowerDao pDao;

    public SightingTest() {

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
        List<SuperPerson> persons = spDao.getAllPersons();
        for (SuperPerson currentPerson : persons) {
            spDao.deletePerson(currentPerson.getPersonId());
        }

        List<Location> location = lDao.getAllLocations();
        for (Location currentLocation : location) {
            lDao.deleteLocation(currentLocation.getLocationId());
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
    public void addSighting() {
        Location loc = new Location();
        loc.setLocationName("Green");
        loc.setDescription("?");
        loc.setAddress("51 St");
        loc.setLatitude(BigDecimal.ONE);
        loc.setLongitude(BigDecimal.ONE);
        lDao.addLocation(loc);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        SuperPerson sp = new SuperPerson();
        sp.setSuperName("george");
        sp.setDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sDao.addSighting(sighting);

        Sighting fromDao = sDao.getSightingbyId(sighting.getSightingId());
        assertEquals(fromDao, sighting);
    }

    @Test
    public void DeleteSighting() {

        Location loc = new Location();
        loc.setLocationName("Green");
        loc.setDescription("?");
        loc.setAddress("51 St");
        loc.setLatitude(BigDecimal.ONE);
        loc.setLongitude(BigDecimal.ONE);
        lDao.addLocation(loc);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        SuperPerson sp = new SuperPerson();
        sp.setSuperName("george");
        sp.setDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sDao.addSighting(sighting);

        Sighting fromDao = sDao.getSightingbyId(sighting.getSightingId());
        assertEquals(fromDao, sighting);
        sDao.deleteSighting(sighting.getSightingId());
        assertNull(sDao.getSightingbyId(sighting.getSightingId()));
    }

}
