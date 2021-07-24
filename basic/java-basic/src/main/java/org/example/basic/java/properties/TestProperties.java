package org.example.basic.java.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author anyuan
 * @since 2021-07-23 10:57
 */
public class TestProperties {

    public static void main(String[] args) throws IOException {

        final Properties properties = new Properties();
        properties.load(new FileInputStream(""));

    }
}
