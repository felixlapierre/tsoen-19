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
 *
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

        TestUtils.AssertHasEvent(someSurvivor, new Event("registered", new Point(5, 5)));
    }

    @Test
    public void testUnknownEvent() {
        Application app = new Application();
        Event unknownEvent = new Event("cookies", new Point(5, 5));

        try {
            app.onEvent(unknownEvent);
            fail("No exception was thrown when event \"cookies\" was sent");
        } catch (Exception e) {
        }
    }

    /**
     * Feature 2: Water sources
     */
    @Test
    public void testWaterFound() {
        Point someLoc = new Point(5, 5);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", someLoc);

        Event waterEvent = new Event("water", new Point(7, 8));

        testApp.onEvent(waterEvent);

        TestUtils.AssertHasEvent(someSurvivor, waterEvent);
    }

    @Test
    public void testWaterTooFar() {
        Point someLoc = new Point(5, 5);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", someLoc);

        Event waterEvent = new Event("water", new Point(15, 6));
        testApp.onEvent(waterEvent);

        utils.AssertDoesNotHaveEvent(someSurvivor, waterEvent);
    }

    // Water edge cases
    @Test
    public void testWaterTwoSurvivorsClose() {
        Point survivorLoc1 = new Point(5, 5);
        Point survivorLoc2 = new Point(10, 7);
        Event waterEvent = new Event("water", new Point(8, 6));

        FakeSurvivor someSurvivorOne = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor someSurvivorTwo = utils.createAndRegisterSurvivor("citizen", survivorLoc2);

        testApp.onEvent(waterEvent);

        utils.AssertHasEvent(someSurvivorOne, waterEvent);
        utils.AssertHasEvent(someSurvivorTwo, waterEvent);
    }

    @Test
    public void testWaterThreeSurvivorTypes() {
        Point survivorLoc1 = new Point(5, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point survivorLoc3 = new Point(6, 10);
        Event waterEvent = new Event("water", new Point(8, 6));

        FakeSurvivor someSurvivorOne = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor someSurvivorTwo = utils.createAndRegisterSurvivor("soldier", survivorLoc2);
        FakeSurvivor someSurvivorThree = utils.createAndRegisterSurvivor("merchant", survivorLoc3);

        testApp.onEvent(waterEvent);

        utils.AssertHasEvent(someSurvivorOne, waterEvent);
        utils.AssertHasEvent(someSurvivorTwo, waterEvent);
        utils.AssertHasEvent(someSurvivorThree, waterEvent);
    }

    @Test
    public void testWaterExactlyCloseEnough() {
        Point survivorLoc = new Point(5, 5);
        Event waterEvent = new Event("water", new Point(10, 5));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(waterEvent);

        utils.AssertHasEvent(someSurvivor, waterEvent);
    }

    @Test
    public void testWaterExactlyTooFar() {
        Point survivorLoc = new Point(5, 5);
        Event waterEvent = new Event("water", new Point(10, 6));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(waterEvent);

        utils.AssertDoesNotHaveEvent(someSurvivor, waterEvent);
    }

    /**
     * Feature 3: Trading spots
     */
    @Test
    public void testTradeCloseToCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(5, 8));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(tradeEvent);

        utils.AssertHasEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testTradeFarFromCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(6, 8));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(tradeEvent);

        utils.AssertDoesNotHaveEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testTradeCloseToMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(5, 10));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        testApp.onEvent(tradeEvent);

        utils.AssertHasEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testTradeFarFromMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(6, 10));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        testApp.onEvent(tradeEvent);

        utils.AssertDoesNotHaveEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testTradeDoesNotNotifySoldier() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(5, 6));

        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("soldier", survivorLoc);

        testApp.onEvent(tradeEvent);

        utils.AssertDoesNotHaveEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testTradeThreeSurvivorTypes() {
        Point survivorLoc1 = new Point(6, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point survivorLoc3 = new Point(6, 10);
        Event tradeEvent = new Event("trade", new Point(8, 6));

        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc2);
        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc3);

        testApp.onEvent(tradeEvent);

        utils.AssertHasEvent(citizen, tradeEvent);
        utils.AssertDoesNotHaveEvent(soldier, tradeEvent);
        utils.AssertHasEvent(merchant, tradeEvent);
    }

    /**
     * Feature 4: Zombie spotted
     */
    @Test
    public void testZombieCloseSoldier() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(12, 5));

        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc);

        testApp.onEvent(zombieEvent);

        utils.AssertHasEvent(soldier, zombieEvent);
    }

    @Test
    public void testZombieFarSoldier() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(12, 6));

        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc);

        testApp.onEvent(zombieEvent);

        utils.AssertDoesNotHaveEvent(soldier, zombieEvent);
    }

    @Test
    public void testZombieCloseCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(9, 5));
        Event runEvent = new Event("run", new Point(4, 5));

        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(zombieEvent);

        utils.AssertHasEvent(citizen, runEvent);
    }

    @Test
    public void testZombieFarCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(9, 4));

        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(zombieEvent);

        utils.AssertDoesNotHaveEvent(citizen, "run");
    }

    @Test
    public void testZombieCloseMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(1, 5));
        Event runEvent = new Event("run", new Point(6, 5));

        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        testApp.onEvent(zombieEvent);

        utils.AssertHasEvent(merchant, runEvent);
    }

    @Test
    public void testZombieFarMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(1, 6));

        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        testApp.onEvent(zombieEvent);
        utils.AssertDoesNotHaveEvent(merchant, "zombie");
    }

    @Test
    public void testZombie45Degree() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(3, 3));
        Event runEvent = new Event("run", new Point(5.707, 5.707));

        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(zombieEvent);
        utils.AssertHasEvent(citizen, runEvent);
    }

    @Test
    public void testZombieDiagonal() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(3, 4));
        Event runEvent = new Event("run", new Point(5.894, 5.447));

        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        testApp.onEvent(zombieEvent);
        utils.AssertHasEvent(citizen, runEvent);
    }

    /**
     * Feature 5: Notify on arrival
     */
    @Test
    public void testArrivalWaterFound() {
        Point someLoc = new Point(5, 5);

        Event waterEvent = new Event("water", new Point(7, 8));

        testApp.onEvent(waterEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", someLoc);

        TestUtils.AssertHasEvent(someSurvivor, waterEvent);
    }

    @Test
    public void testArrivalWaterTooFar() {
        Point someLoc = new Point(5, 5);

        Event waterEvent = new Event("water", new Point(15, 6));
        testApp.onEvent(waterEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", someLoc);

        utils.AssertDoesNotHaveEvent(someSurvivor, waterEvent);
    }

    @Test
    public void testArrivalWaterTwoSurvivorsClose() {
        Point survivorLoc1 = new Point(5, 5);
        Point survivorLoc2 = new Point(10, 7);
        Event waterEvent = new Event("water", new Point(8, 6));

        testApp.onEvent(waterEvent);

        FakeSurvivor someSurvivorOne = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor someSurvivorTwo = utils.createAndRegisterSurvivor("citizen", survivorLoc2);

        utils.AssertHasEvent(someSurvivorOne, waterEvent);
        utils.AssertHasEvent(someSurvivorTwo, waterEvent);
    }

    @Test
    public void testArrivalWaterThreeSurvivorTypes() {
        Point survivorLoc1 = new Point(5, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point survivorLoc3 = new Point(6, 10);
        Event waterEvent = new Event("water", new Point(8, 6));

        testApp.onEvent(waterEvent);

        FakeSurvivor someSurvivorOne = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor someSurvivorTwo = utils.createAndRegisterSurvivor("soldier", survivorLoc2);
        FakeSurvivor someSurvivorThree = utils.createAndRegisterSurvivor("merchant", survivorLoc3);

        utils.AssertHasEvent(someSurvivorOne, waterEvent);
        utils.AssertHasEvent(someSurvivorTwo, waterEvent);
        utils.AssertHasEvent(someSurvivorThree, waterEvent);
    }

    @Test
    public void testArrivalWaterExactlyCloseEnough() {
        Point survivorLoc = new Point(5, 5);
        Event waterEvent = new Event("water", new Point(10, 5));

        testApp.onEvent(waterEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertHasEvent(someSurvivor, waterEvent);
    }

    @Test
    public void testArrivalWaterExactlyTooFar() {
        Point survivorLoc = new Point(5, 5);
        Event waterEvent = new Event("water", new Point(10, 6));

        testApp.onEvent(waterEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertDoesNotHaveEvent(someSurvivor, waterEvent);
    }

    /**
     * Feature 3: Trading spots
     */
    @Test
    public void testArrivalTradeCloseToCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(5, 8));

        testApp.onEvent(tradeEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertHasEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testArrivalTradeFarFromCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(6, 8));

        testApp.onEvent(tradeEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertDoesNotHaveEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testArrivalTradeCloseToMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(5, 10));

        testApp.onEvent(tradeEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        utils.AssertHasEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testArrivalTradeFarFromMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(6, 10));

        testApp.onEvent(tradeEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        utils.AssertDoesNotHaveEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testArrivalTradeDoesNotNotifySoldier() {
        Point survivorLoc = new Point(5, 5);
        Event tradeEvent = new Event("trade", new Point(5, 6));

        testApp.onEvent(tradeEvent);
        FakeSurvivor someSurvivor = utils.createAndRegisterSurvivor("soldier", survivorLoc);

        utils.AssertDoesNotHaveEvent(someSurvivor, tradeEvent);
    }

    @Test
    public void testArrivalTradeThreeSurvivorTypes() {
        Point survivorLoc1 = new Point(6, 5);
        Point survivorLoc2 = new Point(10, 7);
        Point survivorLoc3 = new Point(6, 10);
        Event tradeEvent = new Event("trade", new Point(8, 6));

        testApp.onEvent(tradeEvent);

        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc1);
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc2);
        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc3);

        utils.AssertHasEvent(citizen, tradeEvent);
        utils.AssertDoesNotHaveEvent(soldier, tradeEvent);
        utils.AssertHasEvent(merchant, tradeEvent);
    }

    /**
     * Feature 4: Zombie spotted
     */
    @Test
    public void testArrivalZombieCloseSoldier() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(12, 5));

        testApp.onEvent(zombieEvent);
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc);

        utils.AssertHasEvent(soldier, zombieEvent);
    }

    @Test
    public void testArrivalZombieFarSoldier() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(12, 6));

        testApp.onEvent(zombieEvent);
        FakeSurvivor soldier = utils.createAndRegisterSurvivor("soldier", survivorLoc);

        utils.AssertDoesNotHaveEvent(soldier, zombieEvent);
    }

    @Test
    public void testArrivalZombieCloseCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(9, 5));
        Event runEvent = new Event("run", new Point(4, 5));

        testApp.onEvent(zombieEvent);
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertHasEvent(citizen, runEvent);
    }

    @Test
    public void testArrivalZombieFarCitizen() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(9, 4));

        testApp.onEvent(zombieEvent);
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertDoesNotHaveEvent(citizen, "run");
    }

    @Test
    public void testArrivalZombieCloseMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(1, 5));
        Event runEvent = new Event("run", new Point(6, 5));

        testApp.onEvent(zombieEvent);
        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        utils.AssertHasEvent(merchant, runEvent);
    }

    @Test
    public void testArrivalZombieFarMerchant() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(1, 6));

        testApp.onEvent(zombieEvent);
        FakeSurvivor merchant = utils.createAndRegisterSurvivor("merchant", survivorLoc);

        utils.AssertDoesNotHaveEvent(merchant, "zombie");
    }

    @Test
    public void testArrivalZombie45Degree() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(3, 3));
        Event runEvent = new Event("run", new Point(5.707, 5.707));

        testApp.onEvent(zombieEvent);
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertHasEvent(citizen, runEvent);
    }

    @Test
    public void testArrivalZombieDiagonal() {
        Point survivorLoc = new Point(5, 5);
        Event zombieEvent = new Event("zombie", new Point(3, 4));
        Event runEvent = new Event("run", new Point(5.894, 5.447));

        testApp.onEvent(zombieEvent);
        FakeSurvivor citizen = utils.createAndRegisterSurvivor("citizen", survivorLoc);

        utils.AssertHasEvent(citizen, runEvent);
    }
}
