/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.test.integration;
import immortuos.utils.Point;
import immortuos.utils.Survivor;
import immortuos.utils.Event;

import java.util.ArrayList;

/**
 * A fake class for the Survivor to be used for integration testing.
 * @author Felix
 */
public class FakeSurvivor implements Survivor {
    private Point location;
    public ArrayList<Event> events;
    
    /**
     * Create a new Survivor
     * @param location The location of the survivor.
     */
    public FakeSurvivor(Point location) {
        this.location = new Point(location.x, location.y);
        this.events = new ArrayList<Event>();
    }
    
    public void notify(Event event) {
        events.add(event);
    }
    
    public Point getLocation() {
        return new Point(this.location.x, this.location.y);
    }
}