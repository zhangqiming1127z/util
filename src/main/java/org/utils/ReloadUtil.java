package org.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
public class ReloadUtil {
    public static PropertiesConfiguration configuration = null;
    static {
        try {
            String path = ReloadUtil.class.getClassLoader().getResource("config.properties").getPath();
            configuration = new PropertiesConfiguration(path);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
