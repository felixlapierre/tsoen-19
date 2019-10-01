/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

public class Point {
    public int x;
    public int y;
    
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
            return this.x == p.x
                    && this.y == p.y;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
