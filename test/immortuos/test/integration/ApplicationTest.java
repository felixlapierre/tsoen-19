package immortuos.test.integration;

import immortuos.solution.Application;
import org.junit.Before;
import org.junit.Test;

import immortuos.utils.*;

/**
 * Integration tests for the Immortuos application.
 *
 * @author Felix
 */
public class ApplicationTest {

    private Application testApp;

    public ApplicationTest() {
    }

    @Before
    public void setUp() {
        testApp = new Application();
    }

    /**
     * Feature 1: Register survivor
     */
    @Test
    public void testRegisterSurvivor() {
        FakeSurvivor survivor = new FakeSurvivor("citizen", new Point(0, 0));
        
        Event expected = new Event("registered", new Point(0, 0));
        
        testApp.registerSurvivor(survivor);
        
        TestUtils.AssertHasEvent(survivor, expected);
    }

    /**
     * Feature 2: Water sources
     */
    @Test
    public void testWaterFound() {
        FakeSurvivor survivor = new FakeSurvivor("citizen", new Point(0, 0));
        testApp.registerSurvivor(survivor);
        
        Event waterEvent = new Event("water", new Point(5, 0));
        testApp.onEvent(waterEvent);

        TestUtils.AssertHasEvent(survivor, waterEvent);
    }
    
    /**
     * Feature 3
     */
    @Test
    public void testTradeCloseToMerchant() {
        FakeSurvivor survivor = new FakeSurvivor("merchant", new Point(0, 0));
        testApp.registerSurvivor(survivor);

        Event tradeEvent = new Event("trade", new Point(6, 0));
        testApp.onEvent(tradeEvent);

        TestUtils.AssertHasEvent(survivor, tradeEvent);
    }

    /**
     * Feature 4: Zombie spotted
     */
    @Test
    public void testZombieCloseSoldier() {
        FakeSurvivor soldier = new FakeSurvivor("soldier", new Point(0, 0));
        testApp.registerSurvivor(soldier);
        
        Event zombieEvent = new Event("zombie", new Point(7, 0));
        testApp.onEvent(zombieEvent);

        TestUtils.AssertHasEvent(soldier, zombieEvent);
    }

    @Test
    public void testZombieCloseCitizen() {
        FakeSurvivor citizen = new FakeSurvivor("citizen", new Point(1, 0));
        testApp.registerSurvivor(citizen);
        
        Event zombieEvent = new Event("zombie", new Point(0, 0));
        Event runEvent = new Event("run", new Point(2, 0));

        testApp.onEvent(zombieEvent);

        TestUtils.AssertHasEvent(citizen, runEvent);
    }

}
