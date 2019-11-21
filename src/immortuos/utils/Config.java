/*
 *  Team Software Engineering Tryout
 *  SCS Concordia 
 */
package immortuos.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Helper class to load configuration from the application.properties file.
 * @author Felix
 */
public class Config {

    private static Config instance;

    public static Properties get() {
        if (instance == null) {
            instance = new Config();
            instance.load();
        }
        return instance.props;
    }

    private Properties props;

    private void load() {
        try(InputStream input = new FileInputStream("config.properties")) {
            props = new Properties();
            props.load(input);
            input.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
