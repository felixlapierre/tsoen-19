/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

/**
 * A class representing a single survivor to be monitored by the application.
 * @author Felix
 */
public class Survivor {
    private Point location;
    
    /**
     * Create a new Survivor
     * @param location The location of the survivor.
     */
    public Survivor(Point location) {
        this.location = new Point(location.x, location.y);
    }
    
    /**
     * Notify the survivor that an event has occurred.
     * @param type The message indicating what event has occurred.
     * @param location The location associated with the event.
     */
    public void notify(String type, Point location) {
        
    }
    
    public int getX() {
        return this.location.x;
    }
    
    public int getY() {
        return this.location.y;
    }
}
