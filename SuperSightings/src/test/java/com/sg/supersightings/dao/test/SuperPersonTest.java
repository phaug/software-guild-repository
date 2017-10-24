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
public class SuperPersonTest {

    SuperPersonDao spDao;
    LocationDao lDao;
    OrganizationDao oDao;
    SightingDao sDao;
    PowerDao pDao;

    public SuperPersonTest() {
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
        oDao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        spDao = ctx.getBean("SuperPersonDao", SuperPersonDao.class);
        pDao = ctx.getBean("PowerDao", PowerDao.class);

        List<SuperPerson> persons = spDao.getAllPersons();
        for (SuperPerson currentPerson : persons) {
            spDao.deletePerson(currentPerson.getPersonId());
        }

        List<Organization> orgs = oDao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            oDao.deleteOrganization(currentOrg.getOrganizationId());
        }

        List<Location> location = lDao.getAllLocations();
        for (Location currentLocation : location) {
            lDao.deleteLocation(currentLocation.getLocationId());
        }
        List<Sighting> sightings = sDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sDao.deleteSighting(currentSighting.getSightingId());
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
    public void addPerson() {

        Location l = new Location();
        l.setLocationName("Area 51");
        l.setDescription("?");
        l.setAddress("51 St");
        l.setLatitude(BigDecimal.ONE);
        l.setLongitude(BigDecimal.ONE);
        lDao.addLocation(l);

        Organization org = new Organization();
        org.setOrgName("Avengers");
        org.setDescription("Mordor");
        org.setPhone("555-5555");
        org.setLocation(l);
        oDao.addOrganization(org);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        Location loc = new Location();
        loc.setLocationName("Green");
        loc.setDescription("?");
        loc.setAddress("51 St");
        loc.setLatitude(BigDecimal.ONE);
        loc.setLongitude(BigDecimal.ONE);
        lDao.addLocation(l);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        sDao.addSighting(sighting);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Name");
        person.setDescription("awesome");
        person.setSide(1);
        person.setPower(p);
        List<Organization> organization = new ArrayList<>();
        organization.add(org);
        List<Sighting> s = new ArrayList<>();
        s.add(sighting);

        spDao.addPerson(person);

        SuperPerson fromDao = spDao.getPersonbyId(person.getPersonId());
        assertEquals(fromDao, person);
    }

    @Test
    public void deletePerson() {
        Location l = new Location();
        l.setLocationName("Area 51");
        l.setDescription("?");
        l.setAddress("51 St");
        l.setLatitude(BigDecimal.ONE);
        l.setLongitude(BigDecimal.ONE);
        lDao.addLocation(l);

        Organization org = new Organization();
        org.setOrgName("Avengers");
        org.setDescription("Mordor");
        org.setPhone("555-5555");
        org.setLocation(l);
        oDao.addOrganization(org);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        Location loc = new Location();
        loc.setLocationName("Green");
        loc.setDescription("?");
        loc.setAddress("51 St");
        loc.setLatitude(BigDecimal.ONE);
        loc.setLongitude(BigDecimal.ONE);
        lDao.addLocation(l);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(loc);
        sDao.addSighting(sighting);

        SuperPerson person = new SuperPerson();
        person.setSuperName("Name");
        person.setDescription("awesome");
        person.setSide(1);
        person.setPower(p);
        List<Organization> organization = new ArrayList<>();
        organization.add(org);
        List<Sighting> s = new ArrayList<>();
        s.add(sighting);

        spDao.addPerson(person);

        SuperPerson fromDao = spDao.getPersonbyId(person.getPersonId());
        assertEquals(fromDao, person);
        spDao.deletePerson(person.getPersonId());
        assertNull(spDao.getPersonbyId(person.getPersonId()));
    }

}
