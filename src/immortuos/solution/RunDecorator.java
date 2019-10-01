/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.solution;
import immortuos.utils.*;

/**
 *
 * @author Felix
 */
public class RunDecorator implements Survivor {
    Survivor wrapped;
    double runDistance;
    
    public RunDecorator(double runDistance, Survivor s) {
        wrapped = s;
        this.runDistance = runDistance;
    }

    @Override
    public void notify(String message, Point location) {
        Point newLocation = RunLocation.get(wrapped.getLocation(), location, runDistance);
        wrapped.notify("run", newLocation);
    }

    @Override
    public Point getLocation() {
        return wrapped.getLocation();
    }
    
}
