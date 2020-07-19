package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookCoverImageRequester;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
public class BookCoverImageTest {

    @Test
    public void requestImage() throws IOException, InterruptedException {
        BufferedImage bufferedImage = BookCoverImageRequester.requestBookCoverImage("9788958077633");
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(bufferedImage));
        frame.add(label);
        frame.setVisible(true);
        frame.pack();
        Thread.sleep(5000);
    }

    @Test
    public void requestThumbnailImage() throws IOException, InterruptedException {
        BufferedImage bufferedImage = BookCoverImageRequester.requestThumbnailBookCoverImage("9788958077633", 125, 190);
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(bufferedImage));
        frame.add(label);
        frame.setVisible(true);
        frame.pack();
        Thread.sleep(5000);
    }

}
