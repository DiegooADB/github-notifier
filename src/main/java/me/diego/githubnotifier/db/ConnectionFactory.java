package me.diego.githubnotifier.db;

import me.diego.githubnotifier.config.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = ConfigLoader.getInstance().getString("db.url").orElseThrow(() -> new RuntimeException("url not found"));
        String username = ConfigLoader.getInstance().getString("db.username").orElseThrow(() -> new RuntimeException("username not found"));;
        String password = ConfigLoader.getInstance().getString("db.password").orElseThrow(() -> new RuntimeException("password not found"));

        return DriverManager.getConnection(url, username, password);
    }
}
