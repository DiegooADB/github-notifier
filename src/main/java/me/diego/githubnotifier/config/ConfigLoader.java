package me.diego.githubnotifier.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

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

    public Optional<String> getString(String string) {
        String[] strings = string.split("\\.");

        Object singleString = this.getConfig().get(strings[0]);

        if (strings.length == 1)
            return Optional.of((String) singleString);

        Map<String, Object> beforeMap = (Map<String, Object>) singleString;

        for (int i = 1; i <= strings.length; i++) {

            if (beforeMap.containsKey(strings[strings.length - 1])) {
                return Optional.of((String) beforeMap.get(strings[i]));
            }

            if (beforeMap.containsValue(strings[i])) {
                return Optional.empty();
            }

            beforeMap = (Map<String, Object>) beforeMap.get(strings[i]);
        }
        return Optional.empty();
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    private static ConfigLoader instance;

    public static ConfigLoader getInstance() {
        if (instance == null) instance = new ConfigLoader();
        return instance;
    }
}
