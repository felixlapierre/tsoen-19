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
    private Application testApp;
    private TestUtils utils;
    
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
        testApp = new Application();
        utils = new TestUtils(testApp);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRegisterSurvivor() {
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", new Point(5, 5));
        
        assertEquals(1, someSurvivor.events.size());
        assertEquals("registered", someSurvivor.events.get(0));
        assertEquals(someSurvivor.getLocation(), someSurvivor.eventLocations.get(0));
    }
    
    @Test
    public void testUnknownEvent() {
        Application app = new Application();
        
        try {
            app.onEvent("cookies", new Point(5, 5));
            fail("No exception was thrown when event \"cookies\" was sent");
        } catch(Exception e) { }
    }
    
    /**
     * Feature 2: Water sources
     */
    
    @Test
    public void testWaterFound() {
        Point someLoc = new Point(5, 5);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", someLoc);
        
        Point waterLocation = new Point(7, 8);
        String waterEvent = "water";
        testApp.onEvent(waterEvent, waterLocation);

        TestUtils.AssertHasEvent(someSurvivor, waterEvent, waterLocation);
    }
    
    @Test
    public void testWaterTooFar() {
        Point someLoc = new Point(5, 5);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", someLoc);
        
        Point waterLocation = new Point(15, 6);
        String waterEvent = "water";
        testApp.onEvent(waterEvent, waterLocation);

        utils.AssertDoesNotHaveEvent(someSurvivor, waterEvent, waterLocation);
    }
    
    // Water edge cases
    @Test
    public void testWaterTwoSurvivorsClose() {
        Point survivorLoc1 = new Point(5, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point waterLoc = new Point(8, 6);
        
        FakeSurvivor someSurvivorOne = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor someSurvivorTwo = utils.createAndRegisterSurvivor("citizen", survivorLoc2);
        
        testApp.onEvent("water", waterLoc);
        
        utils.AssertHasEvent(someSurvivorOne, "water", waterLoc);
        utils.AssertHasEvent(someSurvivorTwo, "water", waterLoc);
    }
    
    @Test
    public void testWaterThreeSurvivorTypes() {
        Point survivorLoc1 = new Point(5, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point survivorLoc3 = new Point(6, 10);
        Point waterLoc = new Point(8, 6);
        
        FakeSurvivor someSurvivorOne = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor someSurvivorTwo = utils.createAndRegisterSurvivor("soldier", survivorLoc2);
        FakeSurvivor someSurvivorThree = utils.createAndRegisterSurvivor("merchant", survivorLoc3);
        
        testApp.onEvent("water", waterLoc);
        
        utils.AssertHasEvent(someSurvivorOne, "water", waterLoc);
        utils.AssertHasEvent(someSurvivorTwo, "water", waterLoc);
        utils.AssertHasEvent(someSurvivorThree, "water", waterLoc);
    }
    
    @Test
    public void testWaterExactlyCloseEnough() {
        Point survivorLoc = new Point(5, 5);
        Point waterLoc = new Point(10, 5);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);
        
        testApp.onEvent("water", waterLoc);
        
        utils.AssertHasEvent(someSurvivor, "water", waterLoc);
    }
    
    @Test
    public void testWaterExactlyTooFar() {
        Point survivorLoc = new Point(5, 5);
        Point waterLoc = new Point(10, 6);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);
        
        testApp.onEvent("water", waterLoc);
        
        utils.AssertDoesNotHaveEvent(someSurvivor, "water", waterLoc);
    }
    
    /**
     * Feature 3: Trading spots
     */
    @Test
    public void testTradeCloseToCitizen() {
        Point survivorLoc = new Point(5, 5);
        Point tradeLoc = new Point(5, 8);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);
        
        testApp.onEvent("trade", tradeLoc);
        
        utils.AssertHasEvent(someSurvivor, "trade", tradeLoc);
    }
    
    @Test
    public void testTradeFarFromCitizen() {
        Point survivorLoc = new Point(5, 5);
        Point tradeLoc = new Point(6, 8);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);
        
        testApp.onEvent("trade", tradeLoc);
        
        utils.AssertDoesNotHaveEvent(someSurvivor, "trade", tradeLoc);
    }
    
    @Test
    public void testTradeCloseToMerchant() {
        Point survivorLoc = new Point(5, 5);
        Point tradeLoc = new Point(5, 10);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("merchant", survivorLoc);
        
        testApp.onEvent("trade", tradeLoc);
        
        utils.AssertHasEvent(someSurvivor, "trade", tradeLoc);
    }
    
    @Test
    public void testTradeFarFromMerchant() {
        Point survivorLoc = new Point(5, 5);
        Point tradeLoc = new Point(6, 10);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("merchant", survivorLoc);
        
        testApp.onEvent("trade", tradeLoc);
        
        utils.AssertDoesNotHaveEvent(someSurvivor, "trade", tradeLoc);
    }
    
    @Test
    public void testTradeDoesNotNotifySoldier() {
        Point survivorLoc = new Point(5, 5);
        Point tradeLoc = new Point(5, 6);
        
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("soldier", survivorLoc);
        
        testApp.onEvent("trade", tradeLoc);
        
        utils.AssertDoesNotHaveEvent(someSurvivor, "trade", tradeLoc);
    }
    
    @Test
    public void testTradeThreeSurvivorTypes() {
        Point survivorLoc1 = new Point(6, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point survivorLoc3 = new Point(6, 10);
        Point tradeLoc = new Point(8, 6);
        
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc2);
        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc3);
        
        testApp.onEvent("trade", tradeLoc);
        
        utils.AssertHasEvent(citizen, "trade", tradeLoc);
        utils.AssertDoesNotHaveEvent(soldier, "trade", tradeLoc);
        utils.AssertHasEvent(merchant, "trade", tradeLoc);
    }
    
    /**
     * Feature 4: Zombie spotted
     */
    
    @Test
    public void testZombieCloseSoldier() {
        Point survivorLoc = new Point(5, 5);
        Point zombieLoc = new Point(12, 5);
        
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc);
        
        testApp.onEvent("zombie", zombieLoc);
        
        utils.AssertHasEvent(soldier, "zombie", zombieLoc);
    }
    
    @Test
    public void testZombieFarSoldier() {
        Point survivorLoc = new Point(5, 5);
        Point zombieLoc = new Point(12, 6);
        
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc);
        
        testApp.onEvent("zombie", zombieLoc);
        
        utils.AssertDoesNotHaveEvent(soldier, "zombie");
    }
    
    @Test
    public void testZombieCloseCitizen() {
        Point survivorLoc = new Point(5, 5);
        Point zombieLoc = new Point(9, 5);
        Point runLoc = new Point(4, 5);
        
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);
        
        testApp.onEvent("zombie", zombieLoc);
        
        utils.AssertHasEvent(citizen, "run", runLoc);
    }
    
    @Test
    public void testZombieFarCitizen() {
        Point survivorLoc = new Point(5, 5);
        Point zombieLoc = new Point(9, 4);
        
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);
        
        testApp.onEvent("zombie", zombieLoc);
        
        utils.AssertDoesNotHaveEvent(citizen, "run");
    }
    
    @Test
    public void testZombieCloseMerchant() {
        Point survivorLoc = new Point(5, 5);
        Point zombieLoc = new Point(1, 5);
        Point runLoc = new Point(6, 5);
        
        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc);
        
        testApp.onEvent("zombie", zombieLoc);
        
        utils.AssertHasEvent(merchant, "run", runLoc);
    }
    
    @Test
    public void testZombieFarMerchant() {
        
    }
}
