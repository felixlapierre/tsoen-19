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
    public void notify(Event event) {
        Point newLocation = RunLocation.get(wrapped.getLocation(), event.getLocation(), runDistance);
        wrapped.notify(new Event("run", newLocation));
    }

    @Override
    public Point getLocation() {
        return wrapped.getLocation();
    }
    
    public String getType() {
        return wrapped.getType();
    }
    
}
