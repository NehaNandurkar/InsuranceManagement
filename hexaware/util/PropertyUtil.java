package com.hexaware.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    public static String getPropertyString() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Unable to find the file");
                return null;
            }
            properties.load(input);
            return properties.getProperty("connectionString");
        }
    }
}
