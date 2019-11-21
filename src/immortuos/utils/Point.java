/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

/**
 * Point is an immutable data object representing a point on a 2D plane.
 */
public class Point {
    private final double x;
    private final double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Point) {
            Point p = (Point)o;
            // Must be accurate to within 3 decimal places.
            return this.x < p.x + 0.001
                    && this.x > p.x - 0.001
                    && this.y < p.y + 0.001
                    && this.y > p.y - 0.001;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
