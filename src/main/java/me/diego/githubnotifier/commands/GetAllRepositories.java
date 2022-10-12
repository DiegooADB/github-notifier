package me.diego.githubnotifier.commands;

import me.diego.githubnotifier.entity.DiscordGuildUrl;
import me.diego.githubnotifier.repository.DiscordGuildRepository;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.util.List;

public class GetAllRepositories extends Command {

    public GetAllRepositories() {
        this.setCommandName("fodase");
        this.setDescription("fodase");
    }

    @Override
    public void slashHandle(SlashCommandInteractionEvent event) {
        String guildId = event.getGuild().getId();
        List<DiscordGuildUrl> allGuilds = DiscordGuildRepository.getInstance().findAllRepositoriesByGuild(guildId);

        EmbedBuilder embedFound = createEmbedFound(allGuilds);

        event.replyEmbeds(embedFound.build()).queue();
    }

    private EmbedBuilder createEmbedFound(List<DiscordGuildUrl> discordGuildUrls) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Repositories");
        eb.setColor(new Color(255, 0, 54));

        eb.setDescription("All repositories set for this guild");
        eb.addBlankField(true);

        discordGuildUrls.forEach(repository -> {
            eb.addField("Repository", repository.getRepositoryUrl(), false);
        });

        return eb;
    }
}
