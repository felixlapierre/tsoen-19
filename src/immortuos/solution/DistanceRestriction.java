/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.solution;

import immortuos.utils.Point;
import immortuos.utils.Survivor;
import immortuos.utils.Event;

/**
 * Restrict the distance of a notification to a survivor. This class follows the
 * Decorator design pattern.
 *
 * @author Felix
 */
public class DistanceRestriction implements Survivor {

    private Survivor wrapped;
    private final double distance;

    public DistanceRestriction(double distance, Survivor survivor) {
        wrapped = survivor;
        this.distance = distance;
    }

    DistanceRestriction() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Point getLocation() {
        return wrapped.getLocation();
    }
    
    public String getType() {
        return wrapped.getType();
    }

    public void notify(Event event) {
        if (Distance.get(wrapped.getLocation(), event.getLocation()) <= distance) {
            wrapped.notify(event);
        }
    }
}
