/**
 * now this is a landing screen of the application
 * displays the quiz name,
 * a short description,
 * the creator credit,
 * and start/exit buttons
 */


package softiq;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private static final String CREATOR_NAME = "Samukelo Nkosi";

    public WelcomePanel(MainFrame frame){
        setLayout(new GridBagLayout());
        setBackground(new Color(24, 28, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("SoftIQ");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 56));
        titleLabel.setForeground(new Color(88, 166, 255));
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Test Your Software Development Knowledge");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        add(subtitleLabel, gbc);

        JLabel descLabel = new JLabel("<html><div style='text-align:center;'>Covers Java, Networking, OOP Concepts, and Databases &amp; SQL.<br>" +
                "Pick a topic, answer the questions, and get your final grade!</div></html>");

        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(new Color(200, 200, 200));
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 30, 10);
        add(descLabel, gbc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        buttons.setOpaque(false);
        JButton startButton = createButton("Start Quiz", new Color(46, 160, 67));
        startButton.addActionListener(e -> frame.beginQuiz());
        JButton exitButton = createButton("Exit", new Color(200, 60, 60));
        exitButton.addActionListener(e -> frame.exitApp());
        buttons.add(startButton);
        buttons.add(exitButton);
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(buttons, gbc);
    }

    private JButton  createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(220, 45));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }
}
