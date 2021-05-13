package org.example.config;

import org.jcp.xml.dsig.internal.dom.Utils;

import java.io.*;
import java.util.Properties;

public class KafkaConfig {

    public static Properties getKafkaProperties()  {

        Properties properties = new Properties();
        String filePath = "/kafka.properties";

        File file = new File(filePath);

        try {
            InputStream is = file.exists() ? new FileInputStream(file) : Utils.class.getResourceAsStream(filePath);

            properties.load(is);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }



}
