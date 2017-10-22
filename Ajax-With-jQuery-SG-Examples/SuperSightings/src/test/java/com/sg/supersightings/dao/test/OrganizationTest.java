/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao.test;

import com.sg.supersightings.dao.LocationDao;
import com.sg.supersightings.dao.OrganizationDao;
import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.SuperPerson;
import java.math.BigDecimal;
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
public class OrganizationTest {

    SuperPersonDao spDao;
    LocationDao lDao;
    OrganizationDao oDao;
    PowerDao pDao;

    public OrganizationTest() {
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

        oDao = ctx.getBean("OrganizationDao", OrganizationDao.class);
        spDao = ctx.getBean("SuperPersonDao", SuperPersonDao.class);
        lDao = ctx.getBean("LocationDao", LocationDao.class);
        pDao = ctx.getBean("PowerDao", PowerDao.class);

        List<Location> location = lDao.getAllLocations();
        for (Location currentLocation : location) {
            lDao.deleteLocation(currentLocation.getLocationId());
        }

        List<Organization> orgs = oDao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            oDao.deleteOrganization(currentOrg.getOrganizationId());
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
    public void addGetOrganization() {
        Location l = new Location();
        l.setLocName("Area 51");
        l.setLocDescription("?");
        l.setAddress("51 St");
        l.setLatitude(BigDecimal.ONE);
        l.setLongitude(BigDecimal.ONE);

        lDao.addLocation(l);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        SuperPerson sp = new SuperPerson();
        sp.setSuperName("george");
        sp.setDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);

        spDao.addPerson(sp);

        Organization org = new Organization();
        org.setOrgName("Avengers");
        org.setOrgDescription("Mordor");
        org.setPhoneNum("555-5555");
        org.setLocation(l);
        List<SuperPerson> persons = new ArrayList<>();
        persons.add(sp);
        org.setSuperPersons(persons);

        oDao.addOrganization(org);

        Organization fromDao = oDao.getOrganizationbyId(org.getOrganizationId());
        assertEquals(fromDao, org);
    }
    
    @Test
    public void deleteOrganization() {
        Location l = new Location();
        l.setLocName("Area 51");
        l.setLocDescription("?");
        l.setAddress("51 St");
        l.setLatitude(BigDecimal.ONE);
        l.setLongitude(BigDecimal.ONE);

        lDao.addLocation(l);

        Power p = new Power();
        p.setPowerName("flight");
        pDao.addPower(p);

        SuperPerson sp = new SuperPerson();
        sp.setSuperName("george");
        sp.setDescription("Yeah");
        sp.setSide(0);
        sp.setPower(p);

        spDao.addPerson(sp);

        Organization org = new Organization();
        org.setOrgName("Avengers");
        org.setOrgDescription("Mordor");
        org.setPhoneNum("555-5555");
        org.setLocation(l);
        List<SuperPerson> persons = new ArrayList<>();
        persons.add(sp);
        org.setSuperPersons(persons);

        oDao.addOrganization(org);

        Organization fromDao = oDao.getOrganizationbyId(org.getOrganizationId());
        assertEquals(fromDao, org);
        oDao.deleteOrganization(org.getOrganizationId());
        assertNull(oDao.getOrganizationbyId(org.getOrganizationId()));
    }

}
