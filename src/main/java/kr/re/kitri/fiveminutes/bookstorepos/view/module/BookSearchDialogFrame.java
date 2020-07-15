package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookSearchDialogFrame extends JFrame {

    public BookSearchDialogFrame() throws HeadlessException {
        setTitle("책 검색");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 770);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
    }

    private void initPanel() {
        add(createSearchInputPanel(), BorderLayout.NORTH);
        add(createSearchResultPanel(), BorderLayout.CENTER);
    }

    private JPanel createSearchInputPanel() {
        JPanel marginPanel = new JPanel();
        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        marginPanel.setLayout(new BorderLayout());
        marginPanel.setBorder(margin);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        marginPanel.add(panel, BorderLayout.CENTER);

        Border colorBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder border = BorderFactory.createTitledBorder(colorBorder, "검색");
        panel.setBorder(border);

        panel.add(createSearchScopeComboBox(), createStandardConstraints(10, 5));
        panel.add(createSearchInputTextField(), createTextFieldConstraints());
        panel.add(createSearchSubmitButton(), createStandardConstraints(5, 10));

        return marginPanel;
    }

    private GridBagConstraints createStandardConstraints(int left, int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, left, 10, right);
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    private GridBagConstraints createTextFieldConstraints() {
        GridBagConstraints c = createStandardConstraints(0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        return c;
    }

    private JComboBox<String> createSearchScopeComboBox() {
        JComboBox<String> box = new JComboBox<>();
        box.addItem("제목");
        box.addItem("저자/번역자");
        box.addItem("출판사");
        box.addItem("ISBN");
        return box;
    }

    private JTextField createSearchInputTextField() {
        return new JTextField();
    }

    private JButton createSearchSubmitButton() {
        return new JButton("검색");
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
