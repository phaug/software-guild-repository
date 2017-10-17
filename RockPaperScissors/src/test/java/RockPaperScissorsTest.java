/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patri
 */
public class RockPaperScissorsTest {

    public RockPaperScissorsTest() {
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
     * Test of main method, of class RockPaperScissors.
     */
    @Test
    public void testMain() {
    }

    /**
     * Test of validateResults method, of class RockPaperScissors.
     */
    @Test
    public void testValidateResults() {

        int user = 1;
        int computer = 1;

        assertEquals("tie", RockPaperScissors.validateResults(user, computer));

        user = 1;
        computer = 2;

        assertEquals("computer", RockPaperScissors.validateResults(user, computer));

        user = 1;
        computer = 3;

        assertEquals("user", RockPaperScissors.validateResults(user, computer));

        user = 2;
        computer = 3;

        assertEquals("computer", RockPaperScissors.validateResults(user, computer));

        user = 2;
        computer = 1;

        assertEquals("user", RockPaperScissors.validateResults(user, computer));

        user = 3;
        computer = 1;

        assertEquals("computer", RockPaperScissors.validateResults(user, computer));

        user = 3;
        computer = 2;

        assertEquals("user", RockPaperScissors.validateResults(user, computer));
    }

}
