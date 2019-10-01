package immortuos.solution;

import immortuos.utils.Survivor;
import immortuos.utils.Point;

/**
 * The main application for the solution. Write your code here.
 */
public class Application {

    Subject waterSubject;

    /**
     * Create a new application. You must not change this constructor's
     * signature.
     */
    public Application() {
        // You may write code here.
        waterSubject = new Subject();
    }

    /**
     * Called when a new survivor must be added to the system. You must not
     * change this method's signature.
     *
     * @param survivor The survivor to be added.
     * @param type The type of this survivor.
     */
    public void registerSurvivor(Survivor survivor, String type) {
        // Write your code here.
        waterSubject.register(survivor);
        survivor.notify("registered", survivor.getLocation());
    }

    /**
     * Called when an event occurs in the area tracked by the system. You must
     * not change this method's signature.
     *
     * @param eventType The type of the event.
     * @param eventLocation The location at which the event occurred.
     */
    public void onEvent(String eventType, Point eventLocation) {
        // Write your code here.
        switch (eventType) {
            case "water":
                waterSubject.onEvent(eventType, eventLocation);
                break;
            default:
                throw new RuntimeException("Unknown event " + eventType);
        }
    }
}
