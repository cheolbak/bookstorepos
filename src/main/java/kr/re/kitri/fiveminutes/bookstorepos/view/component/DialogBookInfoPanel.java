package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import static kr.re.kitri.fiveminutes.bookstorepos.view.component.InfoLabelsPanel.Entry;

public class DialogBookInfoPanel extends JPanel {

    private final DialogBookInfo dialogBookInfo;
    private final ActionListener addButtonActionListener;

    public DialogBookInfoPanel(DialogBookInfo dialogBookInfo, ActionListener addButtonActionListener) {
        this.dialogBookInfo = dialogBookInfo;
        this.addButtonActionListener = addButtonActionListener;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridBagLayout());

        add(createBookImageLabel(), createStandardConstraints(20, 20));
        add(createBookInfoPanel(), createInfoLabelConstraints());
        add(createStockAddButton(), createStockAddButtonConstraints());
    }

    private GridBagConstraints createStandardConstraints(int left, int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, left, 10, right);
        return c;
    }

    private GridBagConstraints createInfoLabelConstraints() {
        GridBagConstraints c = createStandardConstraints(10, 10);
        c.weightx = 0.1;
        return c;
    }

    private GridBagConstraints createStockAddButtonConstraints() {
        GridBagConstraints c = createStandardConstraints(10, 20);
        c.ipady = 20;
        return c;
    }

    private JLabel createBookImageLabel() {
        JLabel label = new JLabel(new ImageIcon(dialogBookInfo.getBookCoverImage()));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    private JPanel createBookInfoPanel() {
        List<Entry<JComponent>> entryList = Arrays.asList(
                new Entry<>("제목", new JLabel(dialogBookInfo.getTitle())),
                new Entry<>("저자", new JLabel(dialogBookInfo.getAuthor())),
                new Entry<>("출판사", new JLabel(dialogBookInfo.getPublisher())),
                new Entry<>("출시일", new JLabel(dialogBookInfo.getReleaseDate().toString())),
                new Entry<>("정가", new JLabel(dialogBookInfo.getPrice() + "원")),
                new Entry<>("ISBN", new JLabel(dialogBookInfo.getIsbn())));

        return new InfoLabelsPanel<>(entryList, 8, 16);
    }

    private JButton createStockAddButton() {
        JButton button = new JButton("추가");
        button.addActionListener(addButtonActionListener);
        return button;
    }

}
