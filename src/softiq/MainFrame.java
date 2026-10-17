/**
 * The main application window. Uses a CardLayout to switch in between.
 * the welcome screen,
 * Topic selection screen,
 * Quiz screen, and
 * Results screen.
 */

package softiq;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends Jframe {
    public static final String CARD_WELCOME = "welcome";
    public static final String CARD_TOPICS = "topics";
    public static final String CARD_QUIZ = "quiz";
    public static final String CARD_RESULT = "result";
    public static final String CARD_HISTORY = "history";

    private final CardLayout cardLayout;
    private final JPanel container;
    private final TopicPanel topicPanel;
    private final HistoryPanel historyPanel;

    private String playerName = "Guest";

    public MainFrame() {
        setTitle("SoftIQ - Test Your Software Development Knowledge");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 680);
        setMinimumSize(new Dimension(700, 640));
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        WelcomePanel welcomePanel = new WelcomePanel(this);
        topicPanel = new TopicPanel(this);
        historyPanel = new HistoryPanel(this);

        container.add(welcomePanel, CARD_WELCOME);
        container.add(topicPanel, CARD_TOPICS);
        container.add(historyPanel, CARD_HISTORY);

        add(container);
        cardLayout.show(container, CARD_WELCOME);
    }

    public String getPlayerName() {
        return playerName;
    }

    /** Called by the Start Quiz button: asks who is playing, then shows the topics. */
    public void beginQuiz() {
        Object input = JOptionPane.showInputDialog(
                this,
                "Enter your name so your results can be saved to your history:",
                "Player Name",
                JOptionPane.QUESTION_MESSAGE,
                null, null, playerName);
        if (input == null) {
            return; // user cancelled
        }
        String name = input.toString().trim();
        if (name.isEmpty()) {
            name = "Guest";
        }
        if (name.length() > 30) {
            name = name.substring(0, 30);
        }
        playerName = name;
        topicPanel.setPlayerName(playerName);
        showTopics();
    }

    public void showWelcome() {
        cardLayout.show(container, CARD_WELCOME);
    }

    public void showTopics() {
        cardLayout.show(container, CARD_TOPICS);
    }

    public void showHistory() {
        historyPanel.reload(playerName);
        cardLayout.show(container, CARD_HISTORY);
    }

    /** Starts a fresh quiz for the given topic. */
    public void startQuiz(String topic) {
        QuizPanel quizPanel;
        try {
            quizPanel = new QuizPanel(this, topic);
        } catch (DatabaseException e) {
            showDatabaseError(e);
            return;
        }
        quizPanel.setName(CARD_QUIZ);
        removeCardIfPresent(CARD_QUIZ);
        container.add(quizPanel, CARD_QUIZ);
        cardLayout.show(container, CARD_QUIZ);
    }

    /** Saves the finished quiz to the database, then shows the results screen. */
    public void showResult(String topic, int score, int total) {
        double percentage = Grading.percentage(score, total);
        String grade = Grading.letterFor(percentage);
        try {
            QuizRepository.saveAttempt(playerName, topic, score, total, percentage, grade);
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this,
                    "Your result could not be saved to the history:\n" + e.getMessage(),
                    "Save failed", JOptionPane.WARNING_MESSAGE);
        }

        ResultPanel resultPanel = new ResultPanel(this, topic, score, total);
        resultPanel.setName(CARD_RESULT);
        removeCardIfPresent(CARD_RESULT);
        container.add(resultPanel, CARD_RESULT);
        cardLayout.show(container, CARD_RESULT);
    }

    public void showDatabaseError(DatabaseException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Database error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void removeCardIfPresent(String name) {
        for (Component c : container.getComponents()) {
            if (name.equals(c.getName())) {
                container.remove(c);
                return;
            }
        }
    }

    public void exitApp() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit SoftIQ?",
                "Exit SoftIQ",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
