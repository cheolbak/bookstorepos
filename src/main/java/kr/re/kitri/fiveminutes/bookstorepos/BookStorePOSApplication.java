package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.MainFrame;
import kr.re.kitri.fiveminutes.bookstorepos.view.module.BookSearchDialogFrame;

import javax.swing.*;

public class BookStorePOSApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
