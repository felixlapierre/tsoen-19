package immortuos.test.integration;

import immortuos.solution.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import immortuos.utils.*;

/**
 * Integration tests for the Immortuos application.
 * @author Felix
 */
public class ApplicationTest {
    
    public ApplicationTest() {
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

    @Test
    public void testRegisterSurvivor() {
        Application app = new Application();
        
        Point someLoc = new Point(5, 5);
        String someSurvivorType = "citizen";
        
        FakeSurvivor someSurvivor = new FakeSurvivor(someLoc);
        
        app.registerSurvivor(someSurvivor, someSurvivorType);
        
        assertEquals(1, someSurvivor.events.size());
        assertEquals("registered", someSurvivor.events.get(0));
        assertEquals(someLoc, someSurvivor.eventLocations.get(0));
    }
    
    @Test
    public void testUnknownEvent() {
        Application app = new Application();
        
        try {
            app.onEvent("cookies", new Point(5, 5));
            fail("No exception was thrown when event \"cookies\" was sent");
        } catch(Exception e) { }
    }
    
    @Test
    public void testWaterFound() {
        Application app = new Application();
        
        Point someLoc = new Point(5, 5);
        String someSurvivorType = "citizen";
        
        FakeSurvivor someSurvivor = new FakeSurvivor(someLoc);
        
        app.registerSurvivor(someSurvivor, someSurvivorType);
        
        Point waterLocation = new Point(7, 8);
        String waterEvent = "water";
        app.onEvent(waterEvent, waterLocation);

        TestUtils.AssertHasEvent(someSurvivor, waterEvent, waterLocation);
    }
    
    @Test
    public void testWaterTooFar() {
        Application app = new Application();
        
        Point someLoc = new Point(5, 5);
        String someSurvivorType = "citizen";
        
        FakeSurvivor someSurvivor = new FakeSurvivor(someLoc);
        
        app.registerSurvivor(someSurvivor, someSurvivorType);
        
        Point waterLocation = new Point(15, 6);
        String waterEvent = "water";
        app.onEvent(waterEvent, waterLocation);

        TestUtils.AssertDoesNotHaveEvent(someSurvivor, waterEvent, waterLocation);
    }
    
}
