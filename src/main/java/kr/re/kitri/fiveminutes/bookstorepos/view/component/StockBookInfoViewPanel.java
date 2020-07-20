package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StockBookInfoViewPanel extends JPanel implements ListPanel.BookInfoViewPanelReceiver {

    private final JPanel innerPanel;

    public StockBookInfoViewPanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 30, 20));
        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(1, 2));
        initPanel();
    }

    private void initPanel() {
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        innerPanel.setBorder(BorderFactory.createTitledBorder(blackLine, "책 정보"));

        add(innerPanel, BorderLayout.CENTER);
    }

    @Override
    public void sendBookInfoToViewPanel(BookInfo bookInfo) {
        if (!(bookInfo instanceof StockBookInfo)) {
            return;
        }
        StockBookInfo info = (StockBookInfo) bookInfo;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        List<InfoLabelsPanel.Entry<JComponent>> entries = Arrays.asList(
                new InfoLabelsPanel.Entry<>("제목", reduceTextAndCreateLabel(info.getTitle())),
                new InfoLabelsPanel.Entry<>("저자", new JLabel(info.getAuthor())),
                new InfoLabelsPanel.Entry<>("출판사", new JLabel(info.getPublisher())),
                new InfoLabelsPanel.Entry<>("ISBN", new JLabel(info.getIsbn())),
                new InfoLabelsPanel.Entry<>("정가", new JLabel(currencyFormat.format(info.getPrice()))),
                new InfoLabelsPanel.Entry<>("판매가", new JLabel(currencyFormat.format(info.getPrice() * ((100 - info.getSalePercentPoint()) / 100.)))),
                new InfoLabelsPanel.Entry<>("적립금", new JLabel(String.valueOf(Math.round(info.getPrice() * (info.getPointSavePercentPoint() / 100.))))),
                new InfoLabelsPanel.Entry<>("현재 재고량", new JLabel(info.getCurrentStock() + "개")),
                new InfoLabelsPanel.Entry<>("추가 재고", new JTextField(Integer.toString(info.getInsertStock()))),
                // Fix Size Empty Entry
                new InfoLabelsPanel.Entry<>("           ", new JLabel("                                                           "))
        );
        JPanel marginPanel = new JPanel(new GridBagLayout());
        marginPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        InfoLabelsPanel<JComponent> panel = new InfoLabelsPanel<>(entries, 20, 40);
        marginPanel.add(panel, createInfoLabelConstraints());
        innerPanel.removeAll();
        innerPanel.add(marginPanel);
        innerPanel.add(new JLabel(new ImageIcon(Util.resizeImage(info.getBookCoverImage(), 200, 300))));
        innerPanel.updateUI();
    }

    private JLabel reduceTextAndCreateLabel(String text) {
        if (text.length() <= 28) {
            return new JLabel(text);
        }
        JLabel label = new JLabel(text.substring(0, 26) + "...");
        label.setToolTipText(text);
        return label;
    }

    private GridBagConstraints createInfoLabelConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.insets = new Insets(20, 10, 20, 10);
        c.weightx = 1;
        c.weighty = 1;
        return c;
    }
}
