


package softiq;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private static final Color BG = new Color(24, 28, 40);

    private final DefaultTableModel model;
    private final JCheckBox mineOnly;
    private final JLabel summaryLabel;
    private String currentPlayer = "Guest";

    public HistoryPanel(MainFrame frame) {
        setLayout(new BorderLayout(0, 10));
        setBackground(BG);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 15, 30));

        // ---- Top: heading, summary, filter ----
        JPanel north = new JPanel();
        north.setOpaque(false);
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        JLabel heading = new JLabel("Quiz History");
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(heading);
        north.add(Box.createVerticalStrut(6));

        summaryLabel = new JLabel(" ");
        summaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        summaryLabel.setForeground(new Color(88, 166, 255));
        summaryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        north.add(summaryLabel);

        mineOnly = new JCheckBox("Show only my attempts");
        mineOnly.setOpaque(false);
        mineOnly.setForeground(Color.LIGHT_GRAY);
        mineOnly.setAlignmentX(Component.LEFT_ALIGNMENT);
        mineOnly.addActionListener(e -> loadRows());
        north.add(mineOnly);
        add(north, BorderLayout.NORTH);

        // ---- Center: table ----
        String[] columns = {"#", "Player", "Topic", "Score", "Percent", "Grade", "Date"};
        model = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(26);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        table.setBackground(new Color(34, 40, 58));
        table.setForeground(Color.WHITE);
        table.setGridColor(new Color(60, 70, 95));
        table.setFillsViewportHeight(true);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int col : new int[]{0, 3, 4, 5}) {
            table.getColumnModel().getColumn(col).setCellRenderer(center);
        }
        int[] widths = {40, 120, 130, 70, 75, 55, 150};
        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(new Color(34, 40, 58));
        add(scroll, BorderLayout.CENTER);

        // ---- Bottom: buttons + database location ----
        JPanel south = new JPanel(new BorderLayout());
        south.setOpaque(false);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 4));
        buttons.setOpaque(false);
        JButton backButton = new JButton("Back to Home");
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> frame.showWelcome());
        JButton clearButton = new JButton("Clear History");
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> clearHistory(frame));
        buttons.add(backButton);
        buttons.add(clearButton);
        south.add(buttons, BorderLayout.CENTER);

        JLabel dbLabel = new JLabel("Database: " + DatabaseManager.getDatabasePath(),
                SwingConstants.CENTER);
        dbLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        dbLabel.setForeground(new Color(130, 130, 130));
        south.add(dbLabel, BorderLayout.SOUTH);
        add(south, BorderLayout.SOUTH);
    }

    /** Refreshes the table; called every time the screen is opened. */
    public void reload(String playerName) {
        currentPlayer = playerName;
        mineOnly.setText("Show only my attempts (" + playerName + ")");
        loadRows();
    }

    private void loadRows() {
        model.setRowCount(0);
        try {
            List<QuizAttempt> attempts =
                    QuizRepository.getHistory(mineOnly.isSelected() ? currentPlayer : null);
            double sum = 0;
            for (QuizAttempt a : attempts) {
                model.addRow(new Object[]{
                        a.getId(), a.getPlayerName(), a.getTopic(),
                        a.getScore() + " / " + a.getTotal(),
                        String.format("%.1f%%", a.getPercentage()),
                        a.getGrade(), a.getTakenAt()});
                sum += a.getPercentage();
            }
            summaryLabel.setText(attempts.isEmpty()
                    ? "No attempts yet - finish a quiz and it will appear here."
                    : String.format("%d attempt(s)  |  Average: %.1f%%",
                    attempts.size(), sum / attempts.size()));
        } catch (DatabaseException e) {
            summaryLabel.setText("Could not load history: " + e.getMessage());
        }
    }

    private void clearHistory(MainFrame frame) {
        int choice = JOptionPane.showConfirmDialog(this,
                "This permanently deletes ALL saved quiz attempts for every player.\nContinue?",
                "Clear History", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                QuizRepository.clearHistory();
                loadRows();
            } catch (DatabaseException e) {
                frame.showDatabaseError(e);
            }
        }
    }
}
