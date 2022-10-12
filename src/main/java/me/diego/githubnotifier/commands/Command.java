package me.diego.githubnotifier.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    public abstract void slashHandle(SlashCommandInteractionEvent event);
    private String commandName;
    private String description;
    private final List<OptionData> options = new ArrayList<>();

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }



    public List<OptionData> getOptions() {
        return options;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
