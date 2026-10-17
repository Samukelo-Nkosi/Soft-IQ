/**
 * this section will display the user's final score,
 * percentage, and letter grade
 * after completing a quiz
 */


package softiq;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    public ResultPanel(MainFrame frame, String topic, int score, int total) {
        setLayout(new GridBagLayout());
        setBackground(new Color(24, 28, 40));

        double percentage = Grading.percentage(score, total);
        String grade = Grading.letterFor(percentage);
        Color gradeColor = gradeColor(grade);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel headingLabel = new JLabel("Quiz Complete!");
        headingLabel.setFont(new Font("SansSerif", Font.BOLD, 32));
        headingLabel.setForeground(Color.WHITE);
        gbc.gridy = 0;
        add(headingLabel, gbc);

        JLabel topicLabel = new JLabel("Topic: " + topic);
        topicLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topicLabel.setForeground(new Color(88, 166, 255));
        gbc.gridy = 1;
        add(topicLabel, gbc);

        JLabel gradeLabel = new JLabel(grade);
        gradeLabel.setFont(new Font("SansSerif", Font.BOLD, 90));
        gradeLabel.setForeground(gradeColor);
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(gradeLabel, gbc);

        JLabel scoreLabel = new JLabel(String.format("Score: %d / %d  (%.1f%%)", score, total, percentage));
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        scoreLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(scoreLabel, gbc);

        JLabel remarkLabel = new JLabel(remarkFor(grade));
        remarkLabel.setFont(new Font("SansSerif", Font.ITALIC, 15));
        remarkLabel.setForeground(new Color(200, 200, 200));
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 10, 30, 10);
        add(remarkLabel, gbc);

        JButton retryButton = createButton("Choose Another Topic", new Color(46, 160, 67));
        retryButton.addActionListener(e -> frame.showTopics());
        gbc.gridy = 5;
        gbc.insets = new Insets(8, 10, 8, 10);
        add(retryButton, gbc);

        JButton retakeButton = createButton("Retake This Topic", new Color(70, 100, 200));
        retakeButton.addActionListener(e -> frame.startQuiz(topic));
        gbc.gridy = 6;
        add(retakeButton, gbc);

        JButton historyButton = createButton("View History", new Color(120, 90, 180));
        historyButton.addActionListener(e -> frame.showHistory());
        gbc.gridy = 7;
        add(historyButton, gbc);

        JButton exitButton = createButton("Exit", new Color(200, 60, 60));
        exitButton.addActionListener(e -> frame.exitApp());
        gbc.gridy = 8;
        add(exitButton, gbc);
    }

    private Color gradeColor(String grade) {
        switch (grade) {
            case "A": return new Color(46, 204, 113);
            case "B": return new Color(88, 166, 255);
            case "C": return new Color(241, 196, 15);
            case "D": return new Color(230, 126, 34);
            default: return new Color(231, 76, 60);
        }
    }

    private String remarkFor(String grade) {
        switch (grade) {
            case "A": return "Outstanding! You know your stuff.";
            case "B": return "Great job! Just a little more polish.";
            case "C": return "Not bad , a bit more practice will help.";
            case "D": return "You're getting there. Review the basics and try again.";
            default: return "Keep studying , you'll get it next time!";
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(240, 42));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
}
