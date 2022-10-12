package me.diego.githubnotifier.config;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class ConfigLoader {
    private Map<String, Object> config;

    public void init() {
        try {
            String resourcePath = this.getClass().getClassLoader().getResource("config.yaml").getPath();
            InputStream inputStream = new FileInputStream(resourcePath);

            Yaml config = new Yaml();
            this.config = config.load(inputStream);
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String string) {
        return (String) this.config.get(string);
    }
    
    private static ConfigLoader instance;
    public static ConfigLoader getInstance() {
        if ( instance == null ) instance = new ConfigLoader();
        return instance;
    }
}
