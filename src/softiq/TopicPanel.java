package softiq;

import javax.swing.*;
import java.awt.*;

/**
 * this section lets the user choose which section do they want to
 * be quizzed on
 */

public class TopicPanel extends JPanel {
    private final JLabel playerLabel;

    public TopicPanel(MainFrame frame){
        setLayout(new BorderLayout());
        setBackground(UiTheme.BACKGROUND);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UiTheme.BACKGROUND);
        JLabel heading = new JLabel("Choose a topic", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 30));
        heading.setForeground(UiTheme.TEXT);
        heading.setBorder(BorderFactory.createEmptyBorder(28, 10, 12, 10));
        header.add(heading, BorderLayout.CENTER);
        playerLabel = new JLabel("Player: Guest", SwingConstants.CENTER);
        playerLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        playerLabel.setForeground(UiTheme.ACCENT);
        playerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 18, 10));
        header.add(playerLabel, BorderLayout.SOUTH);
        add(header, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 18, 18));
        gridPanel.setBackground(UiTheme.BACKGROUND);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(12, 60, 20, 60));

        for (String topic : Questionbank.getTopics()) {
            JButton topicButton = new JButton("<html><center><b>" + topic + "</b><br>"
                    + "<font size=3>" + Questionbank.getQuestions(topic).size()
                    + " questions</font></center></html>");
            UiTheme.styleButton(topicButton, UiTheme.SURFACE, UiTheme.SURFACE_RAISED);
            topicButton.setFont(new Font("SansSerif", Font.BOLD, 17));
            topicButton.setPreferredSize(new Dimension(230, 100));
            topicButton.addActionListener(e -> frame.startQuiz(topic));
            gridPanel.add(topicButton);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(UiTheme.BACKGROUND);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(UiTheme.BACKGROUND);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 14, 10));
        JButton backButton = new JButton("Back to Home");
        UiTheme.styleButton(backButton, UiTheme.SURFACE_RAISED, UiTheme.BORDER);
        backButton.addActionListener(e -> frame.showWelcome());
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setPlayerName(String name) {
        playerLabel.setText("Player: " + name);
    }
}
