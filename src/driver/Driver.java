package driver;

import immortuos.utils.Config;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */

/**
 * @author Felix
 */
public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        Properties p = Config.get();
        System.out.println(p.getProperty("whereami"));
    }
}
