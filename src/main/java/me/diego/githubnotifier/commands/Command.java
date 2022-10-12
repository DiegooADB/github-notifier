package me.diego.githubnotifier.commands;

import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Command {
    public abstract void slashHandle(SlashCommandInteractionEvent event);
    private String commandName;
    private String description;
    private final List<OptionData> options = new ArrayList<>();
}
