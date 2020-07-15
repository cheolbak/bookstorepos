package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MarginTitledBorderPanel extends JPanel {

    private final JPanel titledBorderPanel;

    public MarginTitledBorderPanel(String title) {
        this(title, 10, 10, 10, 10);
    }

    public MarginTitledBorderPanel(String title, int top, int left, int bottom, int right) {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        titledBorderPanel = createTitledBorderPanel(title);
        add(titledBorderPanel, BorderLayout.CENTER);
    }

    private JPanel createTitledBorderPanel(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(lineBorder, title);
        panel.setBorder(titledBorder);
        return panel;
    }

    public void addSubPanel(JComponent component, GridBagConstraints constraints) {
        titledBorderPanel.add(component, constraints);
    }
}
