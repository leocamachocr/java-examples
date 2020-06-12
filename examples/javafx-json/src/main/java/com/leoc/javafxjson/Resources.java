package com.leoc.javafxjson;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Resources {
    public static final String BASE_PATH;

    static {
        //Identificamos en archivo ubicado en el directorio resources
        URL propertiesPath = Thread.currentThread().getContextClassLoader().getResource("app.properties");
        Properties appProps = new Properties();
        try {
            appProps.load(propertiesPath.openStream());
        } catch (IOException ignored) {
        }
        BASE_PATH = appProps.getProperty("base.path");


    }
}
