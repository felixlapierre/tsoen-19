/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.test.integration;

import immortuos.utils.Point;
import static org.junit.Assert.*;

/**
 *
 * @author Felix
 */
public class TestUtils {

    public static void AssertHasEvent(FakeSurvivor survivor, String event, Point location) {
        if (!HasEvent(survivor, event, location)) {
            fail("Survivor at " + survivor.getLocation() + " did not recieve event " + event + " at " + location);
        }
    }
    
    public static void AssertDoesNotHaveEvent(FakeSurvivor survivor, String event, Point location) {
        if(HasEvent(survivor, event, location)) {
            fail("Survivor at " + survivor.getLocation() + " should not have recieved event " + event + " at " + location);
        }
    }

    private static boolean HasEvent(FakeSurvivor survivor, String event, Point location) {
        if (survivor.events.size() != survivor.eventLocations.size()) {
            throw new RuntimeException("FATAL: FakeSurvivor events and eventLocations did not match in size");
        }
        for (int i = 0; i < survivor.events.size(); i++) {
            if (survivor.events.get(i).equalsIgnoreCase(event)
                    && survivor.eventLocations.get(i).equals(location)) {
                return true;
            }
        }
        return false;
    }
}
