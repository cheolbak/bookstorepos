package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.MarginTitledBorderPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.PaginationPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
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
        add(createPaginationPanel(), BorderLayout.SOUTH);
    }

    private JPanel createSearchInputPanel() {
        MarginTitledBorderPanel panel = new MarginTitledBorderPanel("검색");

        panel.addSubPanel(createSearchScopeComboBox(), createStandardConstraints(10, 5));
        panel.addSubPanel(createSearchInputTextField(), createTextFieldConstraints());
        panel.addSubPanel(createSearchSubmitButton(), createStandardConstraints(5, 10));

        return panel;
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
        List<DialogBookInfo> dialogBookInfoList = new ArrayList<>();

        try {
            BufferedImage image = ImageIO.read(Paths.get(System.getProperty("user.home"), "Desktop", "9791190665216.jpg").toFile());

            DialogBookInfo book1 = DialogBookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .price(24000)
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            DialogBookInfo book2 = DialogBookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .price(24000)
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            DialogBookInfo book3 = DialogBookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .price(24000)
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            dialogBookInfoList.add(book1);
            dialogBookInfoList.add(book2);
            dialogBookInfoList.add(book3);
        }
        catch (IOException ignore) { }

        return new DialogBookInfoListPanel(dialogBookInfoList);
    }

    private PaginationPanel createPaginationPanel() {
        return new PaginationPanel(10, e -> {});
    }

}
