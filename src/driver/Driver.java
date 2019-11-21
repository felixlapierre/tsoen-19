/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package driver;

import immortuos.utils.Config;
import java.util.Properties;

/**
 * @author Felix
 */
public class Driver {
    public static void main(String[] args) {
        Properties p = Config.get();
        System.out.println(p.getProperty("property"));
    }
}
