/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao.test;

import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.dao.SuperPersonDao;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.SuperPerson;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author apprentice
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:test-applicationContext.xml"})
public class PowerTest {

    @Autowired
    PowerDao dao;
    SuperPersonDao spDao;

    public PowerTest() {
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

        spDao = ctx.getBean("SuperPersonDao", SuperPersonDao.class);
        dao = ctx.getBean("PowerDao", PowerDao.class);

        List<SuperPerson> persons = spDao.getAllPersons();
        for (SuperPerson currentPerson : persons) {
            spDao.deletePerson(currentPerson.getPersonId());
        }

        List<Power> power = dao.getAllPowers();
        for (Power currentPower : power) {
            dao.deletePower(currentPower.getPowerId());

        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addPower() {
        Power power = new Power();
        power.setPowerName("flight");

        dao.addPower(power);

        Power fromDao = dao.getPowerbyId(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void deletePower() {
        Power power = new Power();
        power.setPowerName("flight");

        dao.addPower(power);

        Power fromDao = dao.getPowerbyId(power.getPowerId());
        assertEquals(fromDao, power);
        dao.deletePower(power.getPowerId());
        assertNull(dao.getPowerbyId(power.getPowerId()));
    }

    @Test
    public void updatePower() {
        Power power = new Power();
        power.setPowerName("flight");

        dao.addPower(power);

        power.setPowerName("invisibility");
        dao.updatePower(power);
        Power fromDao = dao.getPowerbyId(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void getAllPowers() {
        Power power = new Power();
        power.setPowerName("flight");
        dao.addPower(power);
        Power power2 = new Power();
        power.setPowerName("strength");
        dao.addPower(power2);
        List<Power> pList = dao.getAllPowers();
        assertEquals(pList.size(), 2);
    }

}
