/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.solution;
import java.util.ArrayList;
import immortuos.utils.*;

/**
 *
 * @author Felix
 */
public class Subject {
    ArrayList<Survivor> observers;
    ArrayList<Event> eventsRecieved;
    public Subject() {
        observers = new ArrayList<Survivor>();
        eventsRecieved = new ArrayList<Event>();
    }
    
    public void register(Survivor s) {
        observers.add(s);
        eventsRecieved.forEach((event) -> {
            s.notify(event);
        });
    }
    
    public void onEvent(Event event) {
        eventsRecieved.add(event);
        observers.forEach((survivor) -> {
            survivor.notify(event);
        });
    }
}
