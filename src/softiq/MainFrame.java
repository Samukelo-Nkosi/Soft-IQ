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
    public static final String CARD_RESULTS = "results";

    private final CardLayout cardLayout;
    private final JPanel container;
    private final TopicPanel topicPanel;

    public MainFrame(){
        setTitle("SoftIQ - By Samukelo.dev");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 580);
        setMinimumSize(new Dimension (650, 500));
        setLocationRelativeToNull(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        WelcomePanel welcomePanel = new WelcomePanel(this);
        topicPanel = new TopicPanel(this);

        container.add(welcomePanel, CARD_WELCOME);
        container.add(topicPanel, CARD_TOPICS);

        add(container);
        cardLayout.show(container, CARD_WELCOME);
    }

    public void showWelcome() {
        cardLayout.show(container, CARD_WELCOME);
    }

    public void showTopics(){
        cardLayout.show(container, CARD_TOPICS);
    }

    public void startQuiz(String topic) {
        QuizPanel quizPanel = new QuizPanel(this, topic);
        quizPanel.setName(CARD_QUIZ);

        removeCardIfPresent(CARD_QUIZ);
        container.add(quizPanel, CARD_QUIZ);
        cardLayout.show(container, CARD_QUIZ);
    }

    public void showResult(String topic, int score, int total){
        ResultPanel resultPanel = new ResultPanel(this, topic, score, total);
        resultPanel.setName(CARD_RESULTS);
        removeCardIfPresent(CARD_RESULTS);
        container.add(resultPanel, CARD_RESULTS);
        cardLayout.show(container, CARD_RESULTS);
    }

    private void removeCardIfPresent(String name){
        for (Component c : container.getComponents()) {
            if (name.equals(c.getName())) {
                container.remove(c);
                return;
            }
        }
    }

    public void exitApp(){
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit SoftIQ?",
                                                   "Exit SoftIQ",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }
}
