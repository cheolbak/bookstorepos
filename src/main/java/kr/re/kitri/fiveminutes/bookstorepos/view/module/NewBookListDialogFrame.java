package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewBookListDialogFrame extends JFrame {

    public NewBookListDialogFrame() throws HeadlessException {
        setTitle("책 검색");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 770);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
    }

    private void initPanel() {
        add(createSearchResultPanel(), BorderLayout.CENTER);
    }

    private JPanel createSearchResultPanel() {
        List<BookInfo> bookInfoList = new ArrayList<>();

        try {
            BufferedImage image = ImageIO.read(Paths.get(System.getProperty("user.home"), "Desktop", "9791190665216.jpg").toFile());

            BookInfo book1 = BookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .price(24000)
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            BookInfo book2 = BookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .price(24000)
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            BookInfo book3 = BookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .price(24000)
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            bookInfoList.add(book1);
            bookInfoList.add(book2);
            bookInfoList.add(book3);
        }
        catch (IOException ignore) { }

        return new DialogBookInfoListPanel(bookInfoList);
    }
}
