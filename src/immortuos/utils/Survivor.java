/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

/**
 * A class representing a single survivor to be monitored by the application.
 * @author Felix
 */
public interface Survivor {
     
    /**
     * Notify the survivor that an event has occurred.
     * @param message The message indicating what event has occurred.
     * @param location The location associated with the event.
     */
    public void notify(Event event);
    
    public Point getLocation();
    
    public String getType();
}
