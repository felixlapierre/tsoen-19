package immortuos.solution;

import immortuos.utils.Survivor;
import immortuos.utils.Event;
import java.util.HashMap;

/**
 * The main application for the solution. Write your code here.
 */
public class Application {

    HashMap<String, Subject> subjects;
    Subject waterSubject;
    Subject tradeSubject;
    Subject zombieSubject;

    /**
     * Create a new application. You must not change this constructor's
     * signature.
     */
    public Application() {
        // You may write code here.
        subjects = new HashMap<String, Subject>();

        waterSubject = new Subject();
        tradeSubject = new Subject();
        zombieSubject = new Subject();

        subjects.put("water", waterSubject);
        subjects.put("trade", tradeSubject);
        subjects.put("zombie", zombieSubject);
    }

    /**
     * Called when a new survivor must be added to the system. You must not
     * change this method's signature.
     *
     * @param survivor The survivor to be added.
     * @param type The type of this survivor.
     */
    public void registerSurvivor(Survivor survivor) {
        // Write your code here.
        switch (survivor.getType()) {
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
        if (subjects.containsKey(event.getType())) {
            subjects.get(event.getType()).onEvent(event);
        } else {
            throw new RuntimeException("Unknown event " + event.getType());
        }
    }
}
