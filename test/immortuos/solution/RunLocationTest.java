/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.solution;

import immortuos.utils.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Felix
 */
public class RunLocationTest {
    
    public RunLocationTest() {
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
     * Test of get method, of class RunLocation.
     */
    @Test
    public void testGet() {
        Point survivorLoc = new Point(5, 5);
        Point eventLoc = new Point(5, 3);
        double runDistance = 1.0;
        Point expResult = new Point(5, 6);
        Point result = RunLocation.get(survivorLoc, eventLoc, runDistance);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetRight() {
        Point survivorLoc = new Point(5, 5);
        Point eventLoc = new Point(9, 5);
        double runDistance = 1.0;
        Point expResult = new Point(4, 5);
        Point result = RunLocation.get(survivorLoc, eventLoc, runDistance);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetDiagonal() {
        Point survivorLoc = new Point(5, 5);
        Point eventLoc = new Point(1, 1);
        double runDistance = 1.0;
        Point expResult = new Point(5.707, 5.707);
        Point result = RunLocation.get(survivorLoc, eventLoc, runDistance);
        assertEquals(expResult, result);
    }
    
}
