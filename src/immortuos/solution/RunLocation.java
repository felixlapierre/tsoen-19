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
public class RunLocation {
    public static Point get(Point survivorLoc, Point eventLoc, double runDistance) {
        double deltaX = survivorLoc.x - eventLoc.x;
        double deltaY = survivorLoc.y - eventLoc.y;
        double magnitude = Distance.get(survivorLoc, eventLoc);
        deltaX = deltaX / magnitude * runDistance;
        deltaY = deltaY / magnitude * runDistance;
        return new Point(survivorLoc.x + deltaX, survivorLoc.y  + deltaY);
    }
}
