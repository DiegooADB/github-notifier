package me.diego.githubnotifier;

import me.diego.githubnotifier.config.ConfigLoader;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class GithubNotifier {
    public static JDA jda;

    private final ConfigLoader configLoader = ConfigLoader.getInstance();

    public GithubNotifier() {
        startConfig();

        String token = configLoader.getString("token");
        jda = JDABuilder.createDefault(token).build();
    }

    private void startConfig() {
        configLoader.init();
    }

    public static void main(String[] args) {
        new GithubNotifier();
    }
}
