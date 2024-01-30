package io.loopcamp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    // Properties object to store configuration data
    private static Properties configFile;

    static {
        try {
            // Read configuration properties from the file
            FileInputStream fileInputStream = new FileInputStream("configuration.properties");

            // Initialize the Properties object
            configFile = new Properties();

            // Load properties from the file into the Properties object
            configFile.load(fileInputStream);

            // Close the input stream after loading properties
            fileInputStream.close();
        } catch (IOException e) {
            // Handle exception if properties file loading fails
            System.out.println("Failed to load properties file!");
            e.printStackTrace();
        }
    }

    /**
     * Get the value of a property based on the provided key.
     *
     * @param key the key of the property
     * @return the value of the property
     */
    public static String getProperty(String key) {
        return configFile.getProperty(key);
    }

}
