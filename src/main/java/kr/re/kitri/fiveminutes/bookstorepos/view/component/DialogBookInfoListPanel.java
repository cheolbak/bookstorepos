package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DialogBookInfoListPanel extends JPanel {

    @Setter
    private List<BookInfo> bookInfoList;

    private final BookInfoReceiver parentFrame;

    public DialogBookInfoListPanel(BookInfoReceiver parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(createScrollPane(), BorderLayout.CENTER);
    }

    @Override
    public void updateUI() {
        removeAll();
        add(createScrollPane(), BorderLayout.CENTER);
        super.updateUI();
    }

    public JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(createBookInfoListPanel());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        return scrollPane;
    }

    public JPanel createBookInfoListPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        if (bookInfoList == null) {
            return panel;
        }

        DialogBookInfoPanel.AddStockClickListener listener =
                e -> parentFrame.sendBookInfoToReceiver(e.getCurrentBookInfo().getIsbn());

        int count = 0;
        for (BookInfo bookInfo : bookInfoList) {
            panel.add(new DialogBookInfoPanel(bookInfo, listener), createBookInfoPanelConstraints(count));
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
