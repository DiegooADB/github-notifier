package me.diego.githubnotifier;

import me.diego.githubnotifier.config.ConfigLoader;
import me.diego.githubnotifier.handler.SlashCommandsHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class GithubNotifier {
    //TODO inserir dados no banco de dados
    //TODO Criar uma verificação para saber se existe um banco de dados
    //TODO Criar comando para adicionar um repositorio a ser ouvido
    public static JDA jda;

    public GithubNotifier() throws InterruptedException {
        String token = System.getenv("TOKEN");
        jda = JDABuilder.createDefault(token).build();

        jda.awaitReady();
        onStart();
    }

    private void onStart() {
        ConfigLoader.getInstance().init();
        SlashCommandsHandler.getInstance().init();
        jda.addEventListener(SlashCommandsHandler.getInstance());
    }

    public static void main(String[] args) {
        try {
            new GithubNotifier();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
