package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListPanel extends JPanel {

    private final BookInfoList bookInfoList;
    private final String buttonText;

    @Setter
    private TotalFieldChangeListener totalFieldChangeListener = infoList -> "";

    @Setter
    private BookInfoViewPanelReceiver bookInfoViewPanelReceiver = bookInfo -> {};

    @Setter
    private AddButtonListener addButtonListener = infoList -> {};
    private JTextField totalField;

    public ListPanel(String buttonText) {
        super(new BorderLayout());
        this.buttonText = buttonText;
        this.bookInfoList = createBookInfoList();

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

    private BookInfoList createBookInfoList() {
        BookInfoList list = new BookInfoList();
        list.addListSelectionListener(e -> {
            BookInfo value = list.getSelectedValue();
            bookInfoViewPanelReceiver.sendBookInfoToViewPanel(value);
        });
        return list;
    }

    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        JButton stockAddButton = new JButton(buttonText);
        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setDragEnabled(true);
        totalField.setText("0");
        totalField.setHorizontalAlignment(JTextField.RIGHT);

        bookInfoList.setChangeListListener(dataMap -> {
            changeTotalField(totalFieldChangeListener);
            if (bookInfoList.getSelectedIndex() != -1) {
                bookInfoViewPanelReceiver.sendBookInfoToViewPanel(bookInfoList.getSelectedValue());
            }
        });

        stockAddButton.addActionListener(e -> {
            addButtonListener.action(bookInfoList.getDataList());
        });

        panel.add(new JLabel("총계 : "), createAddStockStandardConstraints());
        panel.add(totalField, createTotalTextFieldConstraints());
        panel.add(stockAddButton, createAddStockStandardConstraints());
        return panel;
    }

    private GridBagConstraints createAddStockStandardConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
    }

    private GridBagConstraints createTotalTextFieldConstraints() {
        GridBagConstraints c = createAddStockStandardConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 7;
        c.weightx = 0.1;
        return c;
    }

    public void pushData(BookInfo bookInfo) {
        bookInfoList.put(bookInfo);
    }

    public void changeTotalField(TotalFieldChangeListener l) {
        totalField.setText(l.change(bookInfoList.getDataList()));
    }

    public void clear() {
        bookInfoList.clear();
    }

    @FunctionalInterface
    public interface AddButtonListener {
        void action(List<? extends BookInfo> infoList);
    }

    @FunctionalInterface
    public interface BookInfoViewPanelReceiver {
        void sendBookInfoToViewPanel(BookInfo bookInfo);
    }

    @FunctionalInterface
    public interface TotalFieldChangeListener {
        String change(List<? extends BookInfo> infoList);
    }
}
