package softiq;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public final class UiTheme {
    public static final Color BACKGROUND = new Color(18, 24, 38);
    public static final Color SURFACE = new Color(27, 36, 54);
    public static final Color SURFACE_RAISED = new Color(37, 49, 70);
    public static final Color BORDER = new Color(55, 72, 96);
    public static final Color ACCENT = new Color(39, 143, 138);
    public static final Color ACCENT_HOVER = new Color(49, 163, 156);
    public static final Color DARK_TEXT = new Color(12, 24, 38);
    public static final Color TEXT = new Color(238, 243, 250);
    public static final Color MUTED = new Color(169, 183, 202);
    public static final Color SUCCESS = new Color(76, 190, 142);
    public static final Color DANGER = new Color(217, 99, 106);
    public static final Color WARNING = new Color(235, 183, 82);

    private UiTheme() {
    }

    public static void styleButton(JButton button, Color background, Color hover) {
        button.setUI(new BasicButtonUI());
        button.setFont(button.getFont().deriveFont(java.awt.Font.BOLD, 14f));
        button.setForeground(contrastingText(background));
        button.setBackground(background);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(9, 16, 9, 16)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                if (button.isEnabled()) {
                    button.setBackground(hover);
                    button.setForeground(contrastingText(hover));
                }
            }

            @Override
            public void mouseExited(MouseEvent event) {
                button.setBackground(background);
                button.setForeground(contrastingText(background));
            }
        });
    }

    private static Color contrastingText(Color background) {
        double red = linearChannel(background.getRed() / 255.0);
        double green = linearChannel(background.getGreen() / 255.0);
        double blue = linearChannel(background.getBlue() / 255.0);
        double luminance = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
        return luminance > 0.179 ? DARK_TEXT : TEXT;
    }

    private static double linearChannel(double channel) {
        return channel <= 0.04045
                ? channel / 12.92
                : Math.pow((channel + 0.055) / 1.055, 2.4);
    }
}
