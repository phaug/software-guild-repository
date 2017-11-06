/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao.test;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.dao.SightingDao;
import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
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
    OrganizationDao oDao;

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
        oDao = ctx.getBean("OrganizationDao", OrganizationDao.class);

        List<SuperPerson> persons = spDao.getAllPersons();
        for (SuperPerson currentPerson : persons) {
            spDao.deletePerson(currentPerson.getPersonId());
        }

        List<Sighting> sightings = sDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sDao.deleteSighting(currentSighting.getSightingId());
        }
        List<Organization> orgs = oDao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            oDao.deleteOrganization(currentOrg.getOrganizationId());
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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        Sighting fromDao = sDao.getSightingbyId(sighting.getSightingId());
        assertEquals(fromDao, sighting);
        sDao.deleteSighting(sighting.getSightingId());
        assertNull(sDao.getSightingbyId(sighting.getSightingId()));
    }

    @Test
    public void updateSighting() {

        Location loc = new Location();
        loc.setLocationName("Green");
        loc.setDescription("?");
        loc.setAddress("51 St");
        loc.setLatitude(BigDecimal.ONE);
        loc.setLongitude(BigDecimal.ONE);
        lDao.addLocation(loc);

        Location loc2 = new Location();
        loc2.setLocationName("Orange");
        loc2.setDescription("oh");
        loc2.setAddress("1st Ave");
        loc2.setLatitude(BigDecimal.ONE);
        loc2.setLongitude(BigDecimal.ONE);
        lDao.addLocation(loc2);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        SuperPerson sp = new SuperPerson();
        sp.setSuperName("george");
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        sighting.setLocation(loc2);
        sDao.updateSighting(sighting);

        Sighting fromDao = sDao.getSightingbyId(sighting.getSightingId());
        assertEquals(fromDao, sighting);

    }

    @Test
    public void getAllSightings() {

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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        Location loc2 = new Location();
        loc2.setLocationName("Blue");
        loc2.setDescription("Ug");
        loc2.setAddress("5 Ave");
        loc2.setLatitude(BigDecimal.ONE);
        loc2.setLongitude(BigDecimal.ONE);
        lDao.addLocation(loc2);

        Power p2 = new Power();
        p2.setPowerName("sight");
        pDao.addPower(p2);

        SuperPerson sp2 = new SuperPerson();
        sp2.setSuperName("Fred");
        sp2.setSuperDescription("Blah");
        sp2.setSide(0);
        sp2.setPower(p2);
        spDao.addPerson(sp2);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.now());
        sighting2.setLocation(loc2);
        List<SuperPerson> superpersons2 = new ArrayList<>();
        superpersons2.add(sp2);
        sighting2.setSuperPerson(superpersons2);
        sDao.addSighting(sighting2);

        List<Sighting> list = sDao.getAllSightings();
        assertEquals(list.size(), 2);
    }

    @Test
    public void getSightingById() {
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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        Sighting fromDao = sDao.getSightingbyId(sighting.getSightingId());
        assertEquals(fromDao, sighting);
    }

    @Test
    public void getSightingByPersonId() {
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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        List<Sighting> fromDao = sDao.getSightingsByPersonId(sp.getPersonId());
        assertEquals(fromDao.size(), 1);
    }

    @Test
    public void getSightingByLocationIdandDate() {
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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        List <Sighting> fromDao = sDao.getSightingbyLocationIdandDate(loc.getLocationId(), sighting.getDate());
        assertEquals(fromDao.size(), 1);

    }

    @Test
    public void getSightingByDate() {
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
        sp.setSuperDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);
        spDao.addPerson(sp);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        List<SuperPerson> superpersons = new ArrayList<>();
        superpersons.add(sp);
        sighting.setSuperPerson(superpersons);
        sDao.addSighting(sighting);

        List<Sighting> fromDao = sDao.getAllSightingsbyDate(sighting.getDate());
        assertEquals(1, fromDao.size());
    }

}
