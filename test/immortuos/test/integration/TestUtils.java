/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.test.integration;

import immortuos.solution.Application;
import immortuos.utils.Event;
import immortuos.utils.Point;
import static org.junit.Assert.*;

/**
 *
 * @author Felix
 */
public class TestUtils {
    private Application app;
    
    public TestUtils(Application app) {
        this.app = app;
    }

    public static void AssertHasEvent(FakeSurvivor survivor, Event event) {
        if (!HasEvent(survivor, event)) {
            fail("Survivor at " + survivor.getLocation() + " did not recieve " + event);
        }
    }
    
    public static void AssertDoesNotHaveEvent(FakeSurvivor survivor, Event event) {
        if(HasEvent(survivor, event)) {
            fail("Survivor at " + survivor.getLocation() + " should not have recieved " + event);
        }
    }
    
    public static void AssertDoesNotHaveEvent(FakeSurvivor survivor, String eventType) {
        if(HasEvent(survivor, eventType)) {
            fail("Survivor at " + survivor.getLocation() + " should not have recieved any " + eventType + " events");
        }
    }

    private static boolean HasEvent(FakeSurvivor survivor, Event event) {
        for (int i = 0; i < survivor.events.size(); i++) {
            if (survivor.events.get(i).equals(event)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean HasEvent(FakeSurvivor survivor, String eventType) {
        for(int i = 0; i < survivor.events.size(); i++) {
            if(survivor.events.get(i).getType().equalsIgnoreCase(eventType)) {
                return true;
            }
        }
        return false;
    }
    
    public FakeSurvivor createAndRegisterSurvivor(String type, Point location) {
        FakeSurvivor s = new FakeSurvivor(location, type);
        app.registerSurvivor(s);
        return s;
    }
}
