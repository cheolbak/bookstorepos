package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.module.AuthDialogFrame;

import javax.swing.*;

public class BookStorePOSApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AuthDialogFrame::new);
    }
}
