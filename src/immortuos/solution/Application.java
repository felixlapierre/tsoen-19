package immortuos.solution;

import immortuos.utils.Survivor;
import immortuos.utils.Point;
import immortuos.utils.Event;

/**
 * The main application for the solution. Write your code here.
 */
public class Application {

    Subject waterSubject;
    Subject tradeSubject;
    Subject zombieSubject;

    /**
     * Create a new application. You must not change this constructor's
     * signature.
     */
    public Application() {
        // You may write code here.
        waterSubject = new Subject();
        tradeSubject = new Subject();
        zombieSubject = new Subject();
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
        switch (type) {
            case "citizen":
                tradeSubject.register(new DistanceRestriction(3.0, survivor));
                zombieSubject.register(new DistanceRestriction(4.0, new RunDecorator(1.0, survivor)));
                break;
            case "merchant":
                tradeSubject.register(new DistanceRestriction(5.0, survivor));
                zombieSubject.register(new DistanceRestriction(4.0, new RunDecorator(1.0, survivor)));
                break;
            case "soldier":
                zombieSubject.register(new DistanceRestriction(7.0, survivor));
                break;
        }
        waterSubject.register(new DistanceRestriction(5.0, survivor));

        survivor.notify(new Event("registered", survivor.getLocation()));
    }

    /**
     * Called when an event occurs in the area tracked by the system. You must
     * not change this method's signature.
     *
     * @param eventType The type of the event.
     * @param eventLocation The location at which the event occurred.
     */
    public void onEvent(Event event) {
        // Write your code here.
        switch (event.getType()) {
            case "water":
                waterSubject.onEvent(event);
                break;
            case "trade":
                tradeSubject.onEvent(event);
                break;
            case "zombie":
                zombieSubject.onEvent(event);
                break;
            default:
                throw new RuntimeException("Unknown event " + event.getType());
        }
    }
}
