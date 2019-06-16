package com.intellias.mvp.hazard.model.util;

import java.util.ResourceBundle;

public class SQLConnectionManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQL");

    private SQLConnectionManager (){
    }

    /**
     * Method which is used to get value of the property obtained by the key
     *
     * @param key {@code key} used to find properties
     * @return returns the value of the property obtained by the key
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}


