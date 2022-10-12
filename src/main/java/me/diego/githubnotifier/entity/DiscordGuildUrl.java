package me.diego.githubnotifier.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DiscordGuildUrl {
    public DiscordGuildUrl() {
    }

    public DiscordGuildUrl(long id, String guildId, String repositoryUrl) {
        this.id = id;
        this.guildId = guildId;
        this.repositoryUrl = repositoryUrl;
    }

    private long id;
    private String guildId;
    private String repositoryUrl;
}
