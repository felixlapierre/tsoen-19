/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.solution;
import java.util.ArrayList;
import immortuos.utils.*;
import immortuos.solution.*;

/**
 *
 * @author Felix
 */
public class Subject {
    ArrayList<Survivor> observers;
    public Subject() {
        observers = new ArrayList<Survivor>();
    }
    
    public void register(Survivor s) {
        observers.add(s);
    }
    
    public void onEvent(String message, Point location) {
        observers.forEach((survivor) -> {
            double distance = Distance.get(survivor.getLocation(), location);
            if(distance <= 10.0) {
                survivor.notify(message, location);
            }
        });
    }
}
