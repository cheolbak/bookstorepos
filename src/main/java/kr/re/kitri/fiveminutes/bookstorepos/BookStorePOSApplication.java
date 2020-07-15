package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.module.AuthDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.NewBookListDialogFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class BookStorePOSApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new NewBookListDialogFrame();
            frame.setVisible(true);
        });
    }
}
