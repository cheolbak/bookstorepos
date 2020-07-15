package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.view.SpringUtilities;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DialogBookInfoPanel extends JPanel {

    private final BookInfo bookInfo;

    public DialogBookInfoPanel(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridBagLayout());

        add(createBookImageLabel(), createImageLabelConstraints());
        add(createBookInfoPanel(), createInfoLabelConstraints());
        add(createStockAddButton(), createStockAddButtonConstraints());
    }

    private GridBagConstraints createImageLabelConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
    }

    private GridBagConstraints createInfoLabelConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 0.1;
        return c;
    }

    private GridBagConstraints createStockAddButtonConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        c.ipady = 10;
        return c;
    }

    private JLabel createBookImageLabel() {
        return new JLabel(new ImageIcon(bookInfo.getBookCoverImage()));
    }

    private JPanel createBookInfoPanel() {
        @Getter
        @RequiredArgsConstructor
        class Entry {
            private final String key;
            private final String value;
        }

        java.util.List<Entry> columnInfoMap = Arrays.asList(
                new Entry("제목", bookInfo.getTitle()),
                new Entry("저자", bookInfo.getAuthor()),
                new Entry("출판사", bookInfo.getPublisher()),
                new Entry("출시일", bookInfo.getReleaseDate().toString()),
                new Entry("ISBN", bookInfo.getIsbn()));

        JPanel panel = new JPanel(new SpringLayout());

        for (Entry entry : columnInfoMap) {
            JLabel columnLabel = new JLabel("<html><b>" + entry.getKey() + "</b></html>", JLabel.TRAILING);
            JLabel infoLabel = new JLabel(entry.getValue());
            panel.add(columnLabel);
            panel.add(infoLabel);
        }

        SpringUtilities.makeCompactGrid(panel, columnInfoMap.size(), 2, 6, 6, 6, 16);

        return panel;
    }

    private JButton createStockAddButton() {
        JButton button = new JButton("+");
        // TODO: Implements ActionListener - Click to Add Stock Page
        return button;
    }

}
