package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.module.AuthDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class BookStorePOSApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookSearchDialogFrame frame = new BookSearchDialogFrame();
            frame.setVisible(true);
        });
    }
}
