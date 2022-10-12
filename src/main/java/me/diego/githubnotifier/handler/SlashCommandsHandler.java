package me.diego.githubnotifier.handler;

import me.diego.githubnotifier.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Map;

public class SlashCommandsHandler extends ListenerAdapter {
    public void init() {
        CommandHandler.getInstance().registerCommands();
    }

    Map<String, Command> commands = CommandHandler.getInstance().getCommands();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (commands.containsKey(event.getCommandPath())) {
            Command command = this.commands.get(event.getCommandPath());

            command.slashHandle(event);
        };

    }

    private static SlashCommandsHandler instance;
    public static SlashCommandsHandler getInstance() {
        if (instance == null) instance = new SlashCommandsHandler();
        return instance;
    }
}
