package softiq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class QuizRepository {
    private QuizRepository() {
    }

    public static List<Question> getQuestions(String topic) {
        return Questionbank.getQuestions(topic);
    }

    public static void saveAttempt(String playerName, String topic, int score, int total,
                                   double percentage, String grade) throws DatabaseException {
        String sql = "INSERT INTO quiz_attempts "
                + "(player_name, topic, score, total, percentage, grade) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            statement.setString(2, topic);
            statement.setInt(3, score);
            statement.setInt(4, total);
            statement.setDouble(5, percentage);
            statement.setString(6, grade);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Could not save the quiz attempt.", e);
        }
    }

    public static List<QuizAttempt> getHistory(String playerName) throws DatabaseException {
        boolean filterByPlayer = playerName != null;
        String sql = "SELECT id, player_name, topic, score, total, percentage, grade, taken_at "
                + "FROM quiz_attempts "
                + (filterByPlayer ? "WHERE player_name = ? " : "")
                + "ORDER BY id DESC";
        List<QuizAttempt> attempts = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (filterByPlayer) {
                statement.setString(1, playerName);
            }
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    attempts.add(new QuizAttempt(
                            results.getInt("id"),
                            results.getString("player_name"),
                            results.getString("topic"),
                            results.getInt("score"),
                            results.getInt("total"),
                            results.getDouble("percentage"),
                            results.getString("grade"),
                            results.getString("taken_at")));
                }
            }
            return attempts;
        } catch (SQLException e) {
            throw new DatabaseException("Could not load quiz history.", e);
        }
    }

    public static void clearHistory() throws DatabaseException {
        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM quiz_attempts");
        } catch (SQLException e) {
            throw new DatabaseException("Could not clear quiz history.", e);
        }
    }
}
