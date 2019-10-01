/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

public class Point {
    public double x;
    public double y;
    
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
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Point) {
            Point p = (Point)o;
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
