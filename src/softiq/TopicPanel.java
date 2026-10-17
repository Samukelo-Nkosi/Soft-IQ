package softiq;

import javax.swing.*;
import java.awt.*;

/**
 * this section lets the user choose which section do they want to
 * be quizzed on
 */

public class TopicPanel extends JPanel {

    public TopicPanel(MainFrame frame){
        setLayout(new BorderLayout());
        setBackground(new Color(24, 28, 40));

        JLabel heading = new JLabel("Choose a topic", SwingConstants.CENTER);
        heading.setFont(new Font("SansSerif", Font.BOLD, 30));
        heading.setForeground(Color.WHITE);
        heading.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(heading, BorderLayout.NORTH);

        JPanel gridPanel =new JPanel(new GridLayout(0, 2, 20, 20));
        gridPanel.setBackground(new Color(24,28, 40));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        for (String topic : QuestionBank.getTopics()) {
            JButton topicButton = new JButton("<html><center>" + topic + "<br>"
                    + "<font size=2>" + QuestionBank.getQuestions(topic).size() + " questions</font></center></html>");
            topicButton.setFont(new Font("SansSerif", Font.BOLD, 16));
            topicButton.setForeground(Color.WHITE);
            topicButton.setBackground(new Color(45, 55, 80));
            topicButton.setFocusPainted(false);
            topicButton.setPreferredSize(new Dimension(200, 90));
            topicButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            topicButton.addActionListener(e -> frame.startQuiz(topic));
            gridPanel.add(topicButton);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
    }
}
