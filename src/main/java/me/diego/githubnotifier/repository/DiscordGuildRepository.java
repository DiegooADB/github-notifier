package me.diego.githubnotifier.repository;

import me.diego.githubnotifier.config.ConfigLoader;
import me.diego.githubnotifier.db.ConnectionFactory;
import me.diego.githubnotifier.entity.DiscordGuildUrl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscordGuildRepository {
    public List<DiscordGuildUrl> findAllGuilds() {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindAll(conn);
             ResultSet rs = ps.executeQuery()) {

            List<DiscordGuildUrl> discordGuildUrls = new ArrayList<>();

            while (rs.next()) {
                discordGuildUrls.add(DiscordGuildUrl.builder()
                        .id(rs.getLong("id"))
                        .guildId(rs.getString("guild_id"))
                        .repositoryUrl(rs.getString("repository_url"))
                        .build());
            }

            return discordGuildUrls;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DiscordGuildUrl> findAllRepositoriesByGuild(String guildId) {
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = createPreparedStatementFindRepositoriesByGuildId(conn, guildId);
            ResultSet rs = ps.executeQuery()) {

            List<DiscordGuildUrl> discordGuildUrls = new ArrayList<>();

            while (rs.next()) {
                discordGuildUrls.add(DiscordGuildUrl.builder()
                        .id(rs.getLong("id"))
                        .guildId(rs.getString("guild_id"))
                        .repositoryUrl(rs.getString("repository_url")).build());
            }
            return discordGuildUrls;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDiscordInDb(String guildId, String repositoryUrl) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementSave(conn, guildId, repositoryUrl);
        ) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement createPreparedStatementFindRepositoriesByGuildId(Connection conn, String guildId) throws SQLException {
        String sql = """
                SELECT * FROM `github-notifier`.guild WHERE guild_id = ?;
                """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, guildId);
        return ps;
    }

    private PreparedStatement createPreparedStatementSave(Connection conn, String guildId, String repositoryUrl) throws SQLException {
        String sql = """
                INSERT INTO `github-notifier`.`guild` (`guild_id`, `repository_url`) VALUES (?, ?);
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, guildId);
        ps.setString(2, repositoryUrl);

        return ps;
    }

    private static PreparedStatement createPreparedStatementFindAll(Connection conn) throws SQLException {
        String sql = """
                SELECT * FROM `github-notifier`.guild;;
                """;

        return conn.prepareStatement(sql);
    }

    private static DiscordGuildRepository instance;

    public static DiscordGuildRepository getInstance() {
        if (instance == null) instance = new DiscordGuildRepository();
        return instance;
    }
}
