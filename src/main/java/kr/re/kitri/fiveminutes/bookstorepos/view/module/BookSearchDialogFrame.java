package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BookSearchDialogFrame extends JFrame {

    public BookSearchDialogFrame() throws HeadlessException {
        setTitle("책 검색");

        initPanel();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 770);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
    }

    private void initPanel() {
        add(createSearchInputPanel(), BorderLayout.NORTH);
        add(createSearchResultPanel(), BorderLayout.CENTER);
    }

    private JPanel createSearchInputPanel() {
        JPanel marginPanel = new JPanel();
        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        marginPanel.setLayout(new BorderLayout());
        marginPanel.setBorder(margin);

        JPanel panel = new JPanel();
        marginPanel.add(panel, BorderLayout.CENTER);

        Border colorBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder border = BorderFactory.createTitledBorder(colorBorder, "검색");
        panel.setBorder(border);

        panel.add(createSearchScopeComboBox());
        panel.add(createSearchInputTextField());
        panel.add(createSearchSubmitButton());

        return marginPanel;
    }

    private JComboBox<String> createSearchScopeComboBox() {
        JComboBox<String> box = new JComboBox<>();
        box.addItem("제목");
        box.addItem("저자/번역자");
        box.addItem("출판사");
        box.addItem("ISBN");
        return box;
    }

    private JTextField createSearchInputTextField() {
        return new JTextField(26);
    }

    private JButton createSearchSubmitButton() {
        return new JButton("검색");
    }

    private JPanel createSearchResultPanel() {
        JPanel marginPanel = new JPanel();
        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        marginPanel.setLayout(new BorderLayout());
        marginPanel.setBorder(margin);

        JPanel panel = new JPanel();
        marginPanel.add(panel, BorderLayout.CENTER);

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(border);

        return marginPanel;
    }

}
