package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.service.StockManagementService;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.NewBookListDialogFrame;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StockBookSearchPanel extends JPanel {

    @Setter
    private BookInfoReceiver bookInfoReceiver = e -> {};

    private final StockManagementService stockService;

    public StockBookSearchPanel(StockManagementService stockService) {
        this.stockService = stockService;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(25, 20, 20, 20));

        initPanel();
    }

    private void initPanel() {
        JTextField isbnField = new JTextField();
        JButton addListBook = new JButton("추가");
        ActionListener listener = e -> {
            if (!isbnField.getText().matches("^97[89][0-9]{10}$")) {
                return;
            }
            StockBookInfo book = stockService.ifSelectElseThenSearchBook(isbnField.getText());
            bookInfoReceiver.sendBookInfoToReceiver(book);
            isbnField.setText("");
        };

        isbnField.addActionListener(listener);
        addListBook.addActionListener(listener);

        add(new JLabel("ISBN : "));
        add(isbnField);
        add(addListBook);
        add(Box.createHorizontalGlue());
        add(createReadBarcodeButton());
        add(createSearchBookButton());
        add(createShowNewBookButton());
    }

    private JButton createReadBarcodeButton() {
        JButton button = new JButton("사진 인식");
        button.addActionListener(e -> new BarcodeImageReadDialogFrame(stockService, bookInfoReceiver));
        return button;
    }

    private JButton createSearchBookButton() {
        JButton button = new JButton("책 검색");
        button.addActionListener(e -> new BookSearchDialogFrame(stockService, bookInfoReceiver));
        return button;
    }

    private JButton createShowNewBookButton() {
        JButton button = new JButton("새로운 책");
        button.addActionListener(e -> new NewBookListDialogFrame(stockService, bookInfoReceiver));
        return button;
    }

}
