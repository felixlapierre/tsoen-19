/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.solution;

import immortuos.utils.Point;
import immortuos.utils.Survivor;

/**
 * Restrict the distance of a notification to a survivor. This class follows the
 * Decorator design pattern.
 *
 * @author Felix
 */
public class DistanceRestriction implements Survivor {

    private Survivor wrapped;
    private final double distance;

    public DistanceRestriction(Survivor survivor, double distance) {
        wrapped = survivor;
        this.distance = distance;
    }

    public Point getLocation() {
        return wrapped.getLocation();
    }

    public void notify(String message, Point point) {
        if (Distance.get(wrapped.getLocation(), point) <= distance) {
            wrapped.notify(message, point);
        }
    }
}
