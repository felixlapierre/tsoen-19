/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.test.integration;
import immortuos.utils.Point;
import immortuos.utils.Survivor;

import java.util.ArrayList;

/**
 * A fake class for the Survivor to be used for integration testing.
 * @author Felix
 */
public class FakeSurvivor implements Survivor {
    private Point location;
    public ArrayList<String> events;
    public ArrayList<Point> eventLocations;
    
    /**
     * Create a new Survivor
     * @param location The location of the survivor.
     */
    public FakeSurvivor(Point location) {
        this.location = new Point(location.x, location.y);
        this.events = new ArrayList<String>();
        this.eventLocations = new ArrayList<Point>();
    }
    
    public void notify(String type, Point location) {
        events.add(type);
        eventLocations.add(new Point(location));
    }
    
    public Point getLocation() {
        return new Point(this.location.x, this.location.y);
    }
}
