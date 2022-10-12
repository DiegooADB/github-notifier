package me.diego.githubnotifier.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class SetRepository extends Command{
    public SetRepository() {
        this.setCommandName("repository");
        this.setDescription("kkkkkkk fodase");
    }

    private final List<OptionData> options = new ArrayList<>();

    public List<OptionData> getOptions() {
        if(!options.isEmpty()) return options;
        options.add(new OptionData(OptionType.STRING,
                "url",
                "set a repository to be listened",
                true,
                false));
        return options;
    }

    @Override
    public void slashHandle(SlashCommandInteractionEvent event) {
        OptionMapping url = event.getOption("url");

        event.reply("fodase " + url.getAsString()).queue();
    }
}
