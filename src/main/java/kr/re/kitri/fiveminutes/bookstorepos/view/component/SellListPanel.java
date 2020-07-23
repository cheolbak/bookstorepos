package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Sell;
import kr.re.kitri.fiveminutes.bookstorepos.service.SellManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.service.UserManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellBookInfo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class SellListPanel extends JPanel {

    private final SellBookInfoList bookInfoList;
    private final String buttonText;
    private final Class<? extends BookInfo> modelType;
    SellPanel sellPanel;


    SellManagementService sellManagementService;
    UserManagementService userManagementService;

    @Setter
    private AddButtonListener addButtonListener = infoList -> {};

    public SellListPanel(String buttonText, Class<? extends BookInfo> modelType, SellPanel sellPanel) {
        super(new BorderLayout());
        this.buttonText = buttonText;
        this.modelType = modelType;
        this.bookInfoList = createBookInfoList();
        this.sellPanel = sellPanel;


        sellManagementService = new SellManagementService();
        userManagementService = new UserManagementService();

        setPreferredSize(new Dimension(500, 900));
        setMaximumSize(new Dimension(500, Short.MAX_VALUE));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JScrollPane scrollPane = new JScrollPane(bookInfoList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(createListControlPanel(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(createAddPanel(), BorderLayout.SOUTH);
    }

    private JPanel createListControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(new JPanel(), createListControlMarginConstraints());
        panel.add(createRemoveAtSelectedButton(), createListControlConstraints());
        return panel;
    }

    private JButton createRemoveAtSelectedButton() {
        JButton button = new JButton("선택삭제");
        button.addActionListener(e -> bookInfoList.removeAtSelected());
        return button;
    }

    private GridBagConstraints createListControlConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
    }

    private GridBagConstraints createListControlMarginConstraints() {
        GridBagConstraints c = createListControlConstraints();
        c.weightx = 0.1;
        return c;
    }

    private SellBookInfoList createBookInfoList() {
        SellBookInfoList list = new SellBookInfoList();

        list.addListSelectionListener(e -> {
            BookInfo value = list.getSelectedValue();
            Book book = sellManagementService.searchBook(value.getIsbn());
            SellBookInfo sellBookInfo = SellBookInfo.fromBookDomain(book);
            if (list.getSelectedIndex() != -1) {
                sellPanel.refreshBookInfoView(sellBookInfo);
            }
        });
        return list;
    }

    private JPanel createAddPanel() {
        int total;

        JPanel panel = new JPanel(new GridBagLayout());

        JButton sellButton = new JButton(buttonText);

        JTextField sellPriceTextField = new JTextField();
        sellPriceTextField.setEditable(false);
        sellPriceTextField.setDragEnabled(true);
        sellPriceTextField.setText("0");
        sellPriceTextField.setHorizontalAlignment(JTextField.RIGHT);

        JTextField usingPointTextField = new JTextField();
        usingPointTextField.setEditable(true);
        usingPointTextField.setDragEnabled(true);
        usingPointTextField.setText("0");
        usingPointTextField.setHorizontalAlignment(JTextField.RIGHT);

        JTextField totalPriceTextField = new JTextField();
        totalPriceTextField.setEditable(false);
        totalPriceTextField.setDragEnabled(true);
        totalPriceTextField.setText("0");
        totalPriceTextField.setHorizontalAlignment(JTextField.RIGHT);

        bookInfoList.setChangeListListener(dataMap -> {
            int sum = dataMap.values().stream()
                    .map(bookInfo -> (SellBookInfo) bookInfo)
                    .mapToInt(SellBookInfo::getSellPrice).sum();

            NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
            sellPriceTextField.setText(numFormat.format(sum));
            sellPanel.refreshBookInfoView((SellBookInfo) bookInfoList.getSelectedValue());
        });


        sellButton.addActionListener(e -> {
            addButtonListener.action(bookInfoList.getDataList(modelType));
        });

        setAddButtonListener(infoList -> {
            NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
            List<Sell> sellList = new ArrayList<>();
            double count = infoList.size()*1.0;
            int usingPoint = Integer.parseInt(usingPointTextField.getText());
            int userId = Integer.parseInt(sellPanel.userNum.getText());
            int sellPrice;

            try {
               sellPrice  = numFormat.parse(sellPriceTextField.getText()).intValue();

                //고객 적립금 <> 사용 적립금 비교
                if(usingPoint > userManagementService.getCustomerPoint(userId)){
                    JOptionPane.showMessageDialog(this, "보유 적립금이 부족합니다.", "오류", JOptionPane.WARNING_MESSAGE);
                    usingPointTextField.setText("0");
                }
                //구매 가격 <> 사용 적립금 비교
                else if(usingPoint > sellPrice){
                    JOptionPane.showMessageDialog(this, "사용 적립금이 구입 금액을 초과했습니다.", "오류", JOptionPane.WARNING_MESSAGE);
                    usingPointTextField.setText("0");
                }
                else {
                    int savedPoint = 0;
                    for (BookInfo bookInfo : infoList) {
                        if (bookInfo instanceof SellBookInfo) {
                            System.out.println(bookInfo.getTitle());
                            savedPoint = savedPoint + ((SellBookInfo) bookInfo).getPoint();
                            Sell s = Sell.builder()
                                    .bookISBN(bookInfo.getIsbn())
                                    .customerId(userId)
                                    .sellCount(((SellBookInfo) bookInfo).getSellCount())
                                    .sellPrice(((SellBookInfo) bookInfo).getSellPrice())
                                    .usedPoint((int) (usingPoint / count))
                                    .build();
                            sellList.add(s);
                            sellManagementService.pushSellInfo(s);
                            sellManagementService.subStock(bookInfo);
                        }
                    }
                    totalPriceTextField.setText(numFormat.format(sellPrice - usingPoint));
                    userManagementService.updateCustomerInfo(userId, sellPrice, usingPoint, savedPoint);
                    bookInfoList.removeAll();
                    JOptionPane.showMessageDialog(this, "판매 완료\n 판매 금액: "+totalPriceTextField.getText(), "판매완료", JOptionPane.QUESTION_MESSAGE);
                    usingPointTextField.setText("0");
                    totalPriceTextField.setText("0");
                }
            } catch (ParseException parseException) {
                if (log.isDebugEnabled())
                    parseException.printStackTrace();
            }
        });

        panel.add(new JLabel("판매 금액: "), createAddStockStandardConstraints(1));
        panel.add(sellPriceTextField, createTotalTextFieldConstraints(1));

        panel.add(new JLabel("사용 적립금 : "), createAddStockStandardConstraints(2));
        panel.add(usingPointTextField, createTotalTextFieldConstraints(2));

        panel.add(new JLabel("최종 금액 : "), createAddStockStandardConstraints(3));
        panel.add(totalPriceTextField, createTotalTextFieldConstraints(3));
        panel.add(sellButton, createButtonConstraints());
        return panel;
    }

    private GridBagConstraints createAddStockStandardConstraints(int row) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridy = row;
        return c;
    }

    private GridBagConstraints createTotalTextFieldConstraints(int row) {
        GridBagConstraints c = createAddStockStandardConstraints(row);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 7;
        c.weightx = 0.1;
        return c;
    }

    private GridBagConstraints createButtonConstraints() {
        GridBagConstraints c = createAddStockStandardConstraints(1);
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;
        c.gridheight = 3;
        return c;
    }

    public void pushData(BookInfo bookInfo) {
        bookInfoList.put(bookInfo);
    }


    @FunctionalInterface
    public interface AddButtonListener {
        void action(List<? extends BookInfo> infoList);
    }

    @FunctionalInterface
    public interface BookInfoViewPanelReceiver {
        void sendBookInfoToViewPanel(BookInfo bookInfo);
    }
}
