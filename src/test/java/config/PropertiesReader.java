package config;

import java.io.*;
import java.util.Properties;

import static junit.framework.TestCase.fail;

public class PropertiesReader {

    private Properties properties = new Properties();

    public PropertiesReader() {
        loadProperties("data/general.properties");
    }

    private void loadProperties(String propsLocation) {
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propsLocation);
            properties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't find properties file at location: " + propsLocation, ex);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
