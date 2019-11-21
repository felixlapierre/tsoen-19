/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package driver;

import immortuos.solution.Application;
import immortuos.utils.Config;
import immortuos.utils.Point;
import immortuos.utils.PrintSurvivor;
import java.util.Properties;

/**
 * Use this driver class to manually test your code.
 * @author Felix
 */
public class Driver {
    public static void main(String[] args) {
        // Example of how to use Config file
        Properties p = Config.get();
        System.out.println(p.getProperty("property"));
        
        // Application testing
        Application app = new Application();
        
        PrintSurvivor s = new PrintSurvivor("citizen", new Point(5, 5));
        
        app.registerSurvivor(s);
    }
}
