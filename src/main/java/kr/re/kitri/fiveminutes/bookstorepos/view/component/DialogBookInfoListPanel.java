package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogBookInfoListPanel extends JPanel {

    private final List<BookInfo> bookInfoList;

    public DialogBookInfoListPanel(List<BookInfo> bookInfoList) {
        this.bookInfoList = bookInfoList;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createScrollPane(), BorderLayout.CENTER);
    }

    public JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createBookInfoListPanel());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

    public JPanel createBookInfoListPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        int count = 0;
        for (BookInfo bookInfo : bookInfoList) {
            panel.add(new DialogBookInfoPanel(bookInfo, (e) -> {}), createBookInfoPanelConstraints(count));
            count += 1;
        }

        return panel;
    }

    private GridBagConstraints createBookInfoPanelConstraints(int sequence) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridy = sequence;
        return c;
    }
}
