package softiq;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseManager {
    private static final Path DATABASE_PATH = Paths.get(
            System.getProperty("user.home"), ".softiq", "softiq.db");

    private DatabaseManager() {
    }

    public static String getDatabasePath() {
        return DATABASE_PATH.toAbsolutePath().toString();
    }

    public static Connection getConnection() throws DatabaseException {
        Connection connection = null;
        try {
            Files.createDirectories(DATABASE_PATH.getParent());
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + DATABASE_PATH.toAbsolutePath());
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS quiz_attempts ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "player_name TEXT NOT NULL, "
                        + "topic TEXT NOT NULL, "
                        + "score INTEGER NOT NULL, "
                        + "total INTEGER NOT NULL CHECK (total > 0), "
                        + "percentage REAL NOT NULL, "
                        + "grade TEXT NOT NULL, "
                        + "taken_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP)");
            }
            return connection;
        } catch (IOException | SQLException e) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeException) {
                    e.addSuppressed(closeException);
                }
            }
            throw new DatabaseException("Could not open the SoftIQ database at "
                    + getDatabasePath(), e);
        }
    }
}
