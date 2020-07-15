package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static kr.re.kitri.fiveminutes.bookstorepos.view.component.InfoLabelsPanel.Entry;

public class DialogBookInfoPanel extends JPanel {

    private final BookInfo bookInfo;

    public DialogBookInfoPanel(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridBagLayout());

        add(createBookImageLabel(), createStandardConstraints(20));
        add(createBookInfoPanel(), createInfoLabelConstraints());
        add(createStockAddButton(), createStockAddButtonConstraints());
    }

    private GridBagConstraints createStandardConstraints(int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, right);
        return c;
    }

    private GridBagConstraints createInfoLabelConstraints() {
        GridBagConstraints c = createStandardConstraints(10);
        c.weightx = 0.1;
        return c;
    }

    private GridBagConstraints createStockAddButtonConstraints() {
        GridBagConstraints c = createStandardConstraints(10);
        c.ipady = 10;
        return c;
    }

    private JLabel createBookImageLabel() {
        return new JLabel(new ImageIcon(bookInfo.getBookCoverImage()));
    }

    private JPanel createBookInfoPanel() {
        List<Entry> entryList = Arrays.asList(
                new Entry("제목", bookInfo.getTitle()),
                new Entry("저자", bookInfo.getAuthor()),
                new Entry("출판사", bookInfo.getPublisher()),
                new Entry("출시일", bookInfo.getReleaseDate().toString()),
                new Entry("정가", bookInfo.getPrice() + "원"),
                new Entry("ISBN", bookInfo.getIsbn()));

        return new InfoLabelsPanel(entryList, 8, 16);
    }

    private JButton createStockAddButton() {
        JButton button = new JButton("+");
        // TODO: Implements ActionListener - Click to Add Stock Page
        return button;
    }

}
