package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.MarginTitledBorderPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition.*;

public class NewBookListDialogFrame extends JFrame {

    public NewBookListDialogFrame() throws HeadlessException {
        setTitle("새로운 책");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 770);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
    }

    private void initPanel() {
        add(createConditionMatchPanel(), BorderLayout.NORTH);
        add(createResultPanel(), BorderLayout.CENTER);
    }

    private JPanel createConditionMatchPanel() {
        MarginTitledBorderPanel panel = new MarginTitledBorderPanel("조건 선택");
        panel.addSubPanel(createBookCategoryComboBox(), new GridBagConstraints());
        return panel;
    }

    private JComboBox<Category> createBookCategoryComboBox() {
        JComboBox<Category> combo = new JComboBox<>();
        for (Category category : Category.values()) {
            combo.addItem(category);
        }
        return combo;
    }



    private JPanel createResultPanel() {
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
