package conf;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private final Properties properties;

    public PropertiesReader(String path) {
        properties = new Properties();
        readProperties(path);
    }

    public Properties getProperties(){
        return new Properties(properties);
    }

    private void readProperties(String path){
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read properties", e);
        }
    }
}
