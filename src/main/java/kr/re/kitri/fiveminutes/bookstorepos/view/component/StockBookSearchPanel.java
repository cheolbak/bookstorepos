package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import javax.swing.*;
import java.awt.*;

public class StockBookSearchPanel extends JPanel {

    public StockBookSearchPanel() {
        super(new GridBagLayout());
    }

    private JTextField createISBNField() {
        JTextField textField = new JTextField();
        textField.addActionListener(e -> {

        });
        return textField;
    }

    private JButton createAddButton() {
        JButton button = new JButton("추가");
        button.addActionListener(e -> {

        });
        return button;
    }

    private JButton createReadBarcodeButton() {
        JButton button = new JButton("추가");
        button.addActionListener(e -> {

        });
        return button;
    }

    private JButton createSearchBookButton() {
        JButton button = new JButton("추가");
        button.addActionListener(e -> {

        });
        return button;
    }

    private JButton createShowNewBookButton() {
        JButton button = new JButton("추가");
        button.addActionListener(e -> {

        });
        return button;
    }
}
