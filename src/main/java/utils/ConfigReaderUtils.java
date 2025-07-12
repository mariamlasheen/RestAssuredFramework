package utils;

import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtils {
    private static final Properties properties = new Properties();

     static {
        try (FileInputStream file = new FileInputStream("src/main/resources/environment.properties")) {
            properties.load(file);
        } catch (IOException e) {
            System.err.println("Failed to load environment.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
