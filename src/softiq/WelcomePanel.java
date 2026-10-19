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
    private static final String CREATOR_NAME = "Samukelo.dev";

    public WelcomePanel(MainFrame frame){
        setLayout(new GridBagLayout());
        setBackground(UiTheme.BACKGROUND);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UiTheme.SURFACE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiTheme.BORDER, 1),
                BorderFactory.createEmptyBorder(38, 48, 34, 48)));

        JLabel titleLabel = new JLabel("SoftIQ");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 54));
        titleLabel.setForeground(UiTheme.ACCENT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Test Your ICT soft skill Knowledge");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 19));
        subtitleLabel.setForeground(UiTheme.TEXT);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(8));
        card.add(subtitleLabel);

        JLabel descLabel = new JLabel("<html><div style='text-align:center;'>Explore Java, PHP, HTML, CSS, Python, Networking, and Databases &amp; SQL.<br>" +
                "Pick a topic, answer the questions, and get your final grade!</div></html>");
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descLabel.setForeground(UiTheme.MUTED);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(18));
        card.add(descLabel);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        buttons.setOpaque(false);
        JButton startButton = createButton("Start Quiz", UiTheme.ACCENT, UiTheme.ACCENT_HOVER);
        startButton.addActionListener(e -> frame.beginQuiz());
        JButton exitButton = createButton("Exit", UiTheme.SURFACE_RAISED, UiTheme.BORDER);
        exitButton.addActionListener(e -> frame.exitApp());
        buttons.add(startButton);
        buttons.add(exitButton);
        card.add(Box.createVerticalStrut(28));
        card.add(buttons);

        JLabel creatorLabel = new JLabel("Created by " + CREATOR_NAME);
        creatorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        creatorLabel.setForeground(UiTheme.MUTED);
        creatorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(24));
        card.add(creatorLabel);

        add(card);
    }

    private JButton createButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        UiTheme.styleButton(button, bgColor, hoverColor);
        button.setPreferredSize(new Dimension(170, 44));
        return button;
    }
}
