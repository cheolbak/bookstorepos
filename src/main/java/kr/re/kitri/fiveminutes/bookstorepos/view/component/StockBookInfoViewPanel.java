package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class StockBookInfoViewPanel extends JPanel implements ListPanel.BookInfoViewPanelReceiver {

    private final JPanel innerPanel;
    private final ChangeBookInfoListener changeBookInfoListener;

    public StockBookInfoViewPanel(ChangeBookInfoListener changeBookInfoListener) {
        super(new BorderLayout());
        this.changeBookInfoListener = changeBookInfoListener;

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
                new InfoLabelsPanel.Entry<>("출시일", new JLabel(info.getReleaseDate().toString())),
                new InfoLabelsPanel.Entry<>("ISBN", new JLabel(info.getIsbn())),
                new InfoLabelsPanel.Entry<>("정가", new JLabel(currencyFormat.format(info.getPrice()))),
                new InfoLabelsPanel.Entry<>("판매가", createChangePriceFieldAndLabel(info)),
                new InfoLabelsPanel.Entry<>("적립금", createChangePointFieldAndLabel(info)),
                new InfoLabelsPanel.Entry<>("현재 재고량", new JLabel(info.getCurrentStock() + "개")),
                new InfoLabelsPanel.Entry<>("추가 재고", createInsertStockFieldAndButton(info)),
                // Fix Size Empty Entry
                new InfoLabelsPanel.Entry<>("           ", new JLabel("                                                                    "))
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

    private JPanel createInsertStockFieldAndButton(StockBookInfo info) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField stockField = new JTextField(Integer.toString(info.getInsertStock()), 6);
        JButton changeButton = new JButton("변경");
        ActionListener action = e -> {
            if (!stockField.getText().matches("^[0-9]*$")) {
                JOptionPane.showMessageDialog(this, "숫자값을 입력해주세요.");
                return;
            }
            int stock = Integer.parseInt(stockField.getText());
            info.setInsertStock(stock);
            stockField.setText(Integer.toString(info.getInsertStock()));
            changeBookInfoListener.changedBookInfo();
        };

        stockField.addActionListener(action);
        changeButton.addActionListener(action);

        panel.add(stockField);
        panel.add(changeButton);

        return panel;
    }

    private JPanel createChangePriceFieldAndLabel(StockBookInfo info) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField discountRateField = new JTextField(String.valueOf(info.getSalePercentPoint()), 5);
        discountRateField.setHorizontalAlignment(JTextField.RIGHT);
        JLabel priceLabel = new JLabel(currencyFormat.format(info.getPrice() * ((100 - info.getSalePercentPoint()) / 100.)));
        JButton changeButton = new JButton("변경");
        ActionListener listener = e -> {
            info.setSalePercentPoint(Integer.parseInt(discountRateField.getText()));
            priceLabel.setText(currencyFormat.format(info.getPrice() * ((100 - info.getSalePercentPoint()) / 100.)));
        };
        discountRateField.addActionListener(listener);
        changeButton.addActionListener(listener);
        panel.add(priceLabel);
        panel.add(discountRateField);
        panel.add(new JLabel("% 할인"));
        panel.add(changeButton);
        return panel;
    }

    private JPanel createChangePointFieldAndLabel(StockBookInfo info) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField pointRateField = new JTextField(String.valueOf(info.getPointSavePercentPoint()),5);
        pointRateField.setHorizontalAlignment(JTextField.RIGHT);
        JLabel pointLabel = new JLabel(decimalFormat.format(Math.round(info.getPrice() * (info.getPointSavePercentPoint() / 100.))));
        JButton changeButton = new JButton("변경");
        ActionListener listener = e -> {
            info.setPointSavePercentPoint(Integer.parseInt(pointRateField.getText()));
            pointLabel.setText(decimalFormat.format(Math.round(info.getPrice() * (info.getPointSavePercentPoint() / 100.))));
        };
        pointRateField.addActionListener(listener);
        changeButton.addActionListener(listener);
        panel.add(pointLabel);
        panel.add(pointRateField);
        panel.add(new JLabel("% 적립"));
        panel.add(changeButton);
        return panel;
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
