/**
 * This panel will display questions one at a
 * time for the selected topic, tracks the users
 * score, and lets them return to topic selection.
 */


package softiq;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizPanel extends JPanel {

    private final MainFrame frame;
    private final String topic;
    private final List<Question> questions;

    private int currentIndex = 0;
    private int score = 0;
    private int selectedOption = -1;

    private final JLabel progressLabel;
    private final JLabel questionLabel;
    private final JPanel optionsPanel;
    private final ButtonGroup buttonGroup;
    private final JButton nextButton;
    private final JLabel feedbackLabel;

    public QuizPanel(MainFrame frame, String topic) {
        this.frame = frame;
        this.topic = topic;
        this.questions = Questionbank.getQuestions(topic);

        setLayout(new BorderLayout());
        setBackground(new Color(24, 28, 40));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Top bar: back button + progress
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);

        JButton backButton = new JButton("\u2190 Back to Topics");
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> confirmBackToTopics());
        topBar.add(backButton, BorderLayout.WEST);

        progressLabel = new JLabel("", SwingConstants.RIGHT);
        progressLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        progressLabel.setForeground(Color.LIGHT_GRAY);
        topBar.add(progressLabel, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // ---- Center: question + options ----
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel topicLabel = new JLabel(topic);
        topicLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        topicLabel.setForeground(new Color(88, 166, 255));
        topicLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(topicLabel);
        centerPanel.add(Box.createVerticalStrut(8));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(questionLabel);
        centerPanel.add(Box.createVerticalStrut(20));

        optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(optionsPanel);
        buttonGroup = new ButtonGroup();

        centerPanel.add(Box.createVerticalStrut(15));
        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        feedbackLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(feedbackLabel);

        add(centerPanel, BorderLayout.CENTER);

        // ---- Bottom: next/submit button ----
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        nextButton = new JButton("Submit Answer");
        nextButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(46, 160, 67));
        nextButton.setFocusPainted(false);
        nextButton.setPreferredSize(new Dimension(160, 40));
        nextButton.addActionListener(e -> handleNextOrSubmit());
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion();
    }

    private void confirmBackToTopics() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Leaving now will discard your current quiz progress. Continue?",
                "Return to Topics",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (choice == JOptionPane.YES_OPTION) {
            frame.showTopics();
        }
    }

    private void loadQuestion() {
        if (questions.isEmpty()) {
            questionLabel.setText("No questions available for this topic.");
            nextButton.setEnabled(false);
            return;
        }

        Question q = questions.get(currentIndex);
        progressLabel.setText("Question " + (currentIndex + 1) + " of " + questions.size()
                + "   |   Score: " + score);
        questionLabel.setText("<html>" + (currentIndex + 1) + ". " + q.getQuestionText() + "</html>");

        optionsPanel.removeAll();
        buttonGroup.clearSelection();
        // Remove old buttons from the group
        java.util.Enumeration<AbstractButton> old = buttonGroup.getElements();
        while (old.hasMoreElements()) {
            buttonGroup.remove(old.nextElement());
        }

        String[] options = q.getOptions();
        for (int i = 0; i < options.length; i++) {
            JRadioButton radio = new JRadioButton(options[i]);
            radio.setFont(new Font("SansSerif", Font.PLAIN, 15));
            radio.setForeground(Color.WHITE);
            radio.setOpaque(false);
            radio.setAlignmentX(Component.LEFT_ALIGNMENT);
            radio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            final int idx = i;
            radio.addActionListener(e -> selectedOption = idx);
            buttonGroup.add(radio);
            optionsPanel.add(radio);
            optionsPanel.add(Box.createVerticalStrut(6));
        }

        selectedOption = -1;
        feedbackLabel.setText(" ");
        nextButton.setText(currentIndex == questions.size() - 1 ? "Submit & Finish" : "Submit Answer");
        nextButton.setEnabled(true);
        setOptionsEnabled(true);

        optionsPanel.revalidate();
        optionsPanel.repaint();
    }

    private void setOptionsEnabled(boolean enabled) {
        for (Component c : optionsPanel.getComponents()) {
            c.setEnabled(enabled);
        }
    }

    private void handleNextOrSubmit() {
        // Two-phase button: first click submits/reveals answer, second click advances.
        if (nextButton.getText().startsWith("Submit")) {
            if (selectedOption == -1) {
                JOptionPane.showMessageDialog(this, "Please select an answer before submitting.",
                        "No Answer Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Question q = questions.get(currentIndex);
            setOptionsEnabled(false);
            if (q.isCorrect(selectedOption)) {
                score++;
                feedbackLabel.setForeground(new Color(46, 160, 67));
                feedbackLabel.setText("\u2713 Correct!");
            } else {
                feedbackLabel.setForeground(new Color(220, 80, 80));
                feedbackLabel.setText("\u2717 Incorrect. Correct answer: " + q.getOptions()[q.getCorrectIndex()]);
            }
            progressLabel.setText("Question " + (currentIndex + 1) + " of " + questions.size()
                    + "   |   Score: " + score);
            nextButton.setText(currentIndex == questions.size() - 1 ? "Finish Quiz" : "Next Question");
        } else {
            currentIndex++;
            if (currentIndex >= questions.size()) {
                frame.showResult(topic, score, questions.size());
            } else {
                loadQuestion();
            }
        }
    }
}
