package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoPanel;
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
        marginPanel.add(panel, BorderLayout.CENTER);

        Border colorBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder border = BorderFactory.createTitledBorder(colorBorder, "검색");
        panel.setBorder(border);

        panel.add(createSearchScopeComboBox());
        panel.add(createSearchInputTextField());
        panel.add(createSearchSubmitButton());

        return marginPanel;
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
        return new JTextField(26);
    }

    private JButton createSearchSubmitButton() {
        return new JButton("검색");
    }

    private JPanel createSearchResultPanel() {
        JPanel marginPanel = new JPanel();
        Border margin = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        marginPanel.setLayout(new BorderLayout());
        marginPanel.setBorder(margin);

        JPanel panel = new JPanel(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        marginPanel.add(scrollPane, BorderLayout.CENTER);

        try {
            BufferedImage image = ImageIO.read(Paths.get(System.getProperty("user.home"), "Desktop", "9791190665216.jpg").toFile());

            BookInfo book = BookInfo.builder()
                    .isbn("9791190665216")
                    .title("객체지향 사고 프로세스")
                    .author("맷 와일스펠드")
                    .publisher("제이펍")
                    .releaseDate(LocalDate.of(2020, 7, 3))
                    .bookCoverImage(image)
                    .build();

            for (int i = 0; i < 20; i++) {
                panel.add(new DialogBookInfoPanel(book), createBookInfoPanelConstraints(i));
            }
        }
        catch (IOException ignore) { }

        return marginPanel;
    }

    private GridBagConstraints createBookInfoPanelConstraints(int sequence) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.gridwidth = 1;
        c.gridy = sequence;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
    }

}
