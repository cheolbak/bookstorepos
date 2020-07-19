package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static kr.re.kitri.fiveminutes.bookstorepos.view.component.InfoLabelsPanel.Entry;

public class DialogBookInfoPanel extends JPanel {

    private final BookInfo bookInfo;
    private final AddStockClickListener addStockClickListener;

    public DialogBookInfoPanel(BookInfo bookInfo, AddStockClickListener addStockClickListener) {
        this.bookInfo = bookInfo;
        this.addStockClickListener = addStockClickListener;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridBagLayout());

        add(createBookImagePanel(), createStandardConstraints(25, 20));
        add(createBookInfoPanel(), createInfoLabelConstraints());
        add(createActionButtonPanel(), createStandardConstraints(0, 10));
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

    private GridBagConstraints createActionButtonConstraints(int row) {
        GridBagConstraints c = createStandardConstraints(10, 20);
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridy = row;
        c.ipady = 20;
        return c;
    }

    private JPanel createBookImagePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(new ImageIcon(bookInfo.getBookCoverImage()));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(label);
        return panel;
    }

    private JPanel createBookInfoPanel() {
        List<Entry<JComponent>> entryList = Arrays.asList(
                new Entry<>("제목", reduceTextAndCreateLabel(bookInfo.getTitle())),
                new Entry<>("저자", reduceTextAndCreateLabel(bookInfo.getAuthor())),
                new Entry<>("출판사", reduceTextAndCreateLabel(bookInfo.getPublisher())),
                new Entry<>("출시일", new JLabel(bookInfo.getReleaseDate().toString())),
                new Entry<>("정가", new JLabel(bookInfo.getPrice() + "원")),
                new Entry<>("ISBN", new JLabel(bookInfo.getIsbn()))
        );

        return new InfoLabelsPanel<>(entryList, 10, 16);
    }

    private JLabel reduceTextAndCreateLabel(String text) {
        if (text.length() <= 20) {
            return new JLabel(text);
        }
        JLabel label = new JLabel(text.substring(0, 18) + "...");
        label.setToolTipText(text);
        return label;
    }

    private JPanel createActionButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(createLinkDetailInfoPage(), createActionButtonConstraints(1));
        panel.add(createStockAddButton(), createActionButtonConstraints(2));
        return panel;
    }

    private JButton createLinkDetailInfoPage() {
        JButton button = new JButton("상세 정보");
        button.addActionListener(e -> {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    String baseUrl = "http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=";
                    URI uri = URI.create(baseUrl + bookInfo.getIsbn());
                    try {
                        desktop.browse(uri);
                    }
                    catch (IOException ignore) { }
                }
            }
        });
        return button;
    }

    private JButton createStockAddButton() {
        JButton button = new JButton("추가");
        button.addActionListener(e -> addStockClickListener.click(new AddStockClickEvent(bookInfo)));
        return button;
    }

    @FunctionalInterface
    public interface AddStockClickListener {
        void click(AddStockClickEvent e);
    }

    @Getter
    @RequiredArgsConstructor
    public static class AddStockClickEvent {
        private final BookInfo currentBookInfo;
    }
}
