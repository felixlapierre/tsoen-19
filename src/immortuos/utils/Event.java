/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

/**
 *
 * @author Felix
 */
public class Event {

    private final String type;
    private final Point location;

    public Event(String type, Point location) {
        this.type = type;
        this.location = new Point(location);
    }

    public String getType() {
        return type;
    }

    public Point getLocation() {
        return new Point(location);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            return e.type.equalsIgnoreCase(type)
                    && e.location.equals(location);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Event " + type + " at " + location;
    }
}
