package me.diego.githubnotifier.commands;

import me.diego.githubnotifier.entity.DiscordGuildUrl;
import me.diego.githubnotifier.repository.DiscordGuildRepository;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

public class TestCommand extends Command{
    public TestCommand() {
        this.setCommandName("test");
        this.setDescription("test");
    }

    @Override
    public void slashHandle(SlashCommandInteractionEvent event) {
        List<DiscordGuildUrl> allGuilds = DiscordGuildRepository.getInstance().findAllGuilds();

        event.reply("fodase " + allGuilds.get(0).getRepositoryUrl()).queue();
    }

}
