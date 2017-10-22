/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersitings.dao.test;

import com.sg.supersightings.dao.PowerDao;
import com.sg.supersightings.model.Power;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
    
    
    
}