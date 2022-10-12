import me.diego.githubnotifier.db.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindAll(conn);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString("guild_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement createPreparedStatementFindAll(Connection conn) throws SQLException {
        String sql = """
                SELECT * FROM `github-notifier`.guild;;
                """;

        return conn.prepareStatement(sql);
    }
}
