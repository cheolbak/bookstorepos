package kr.re.kitri.fiveminutes.bookstorepos;

import kr.re.kitri.fiveminutes.bookstorepos.view.module.BarcodeImageReadDialogFrame;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class BookStorePOSApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BarcodeImageReadDialogFrame frame = new BarcodeImageReadDialogFrame();
            frame.setVisible(true);
        });
    }
}
