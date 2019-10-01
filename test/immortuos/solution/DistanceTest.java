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
public class DistanceTest {
    
    public DistanceTest() {
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
     * Test of get method, of class Distance.
     */
    @Test
    public void testGet() {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(6, 9);
        double expResult = 5.0;
        double result = Distance.get(p1, p2);
        assertEquals(expResult, result, 0.0);
    }
    
}
