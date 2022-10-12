package me.diego.githubnotifier.handler;

import me.diego.githubnotifier.GithubNotifier;
import me.diego.githubnotifier.commands.Command;
import me.diego.githubnotifier.config.ConfigLoader;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandHandler {

    private Map<String, Command> commands = new HashMap<>();

    public void registerCommands() {
        String guildId = ConfigLoader.getInstance().getString("dev.devGuild").orElseThrow(() -> new RuntimeException("Guild not found"));

        Guild testServer = GithubNotifier.jda.getGuildById(guildId);

        Reflections reflections = new Reflections(Command.class.getPackage().getName());
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> clas : classes) {
            try {
                Command command = clas.getDeclaredConstructor().newInstance();

                this.commands.put(command.getCommandName(), command);

                CommandCreateAction commandCreateAction = testServer.upsertCommand(command.getCommandName(), command.getDescription());
                if (command.getOptions().size() > 0) {
                    command.getOptions().forEach(data -> {
                        commandCreateAction.addOption(
                                data.getType(),
                                data.getName(),
                                data.getDescription(),
                                data.isRequired(),
                                data.isAutoComplete()
                        ).queue();
                    });
                }
                commandCreateAction.queue();

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    private static CommandHandler instance;
    public static CommandHandler getInstance() {
        if (instance == null) instance = new CommandHandler();
        return instance;
    }
}
