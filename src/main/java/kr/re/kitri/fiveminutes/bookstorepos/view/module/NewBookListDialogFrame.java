package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.util.WeekOfMonthCalc;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.MarginTitledBorderPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.PaginationPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition.Category;

public class NewBookListDialogFrame extends JFrame {

    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy년");

    public NewBookListDialogFrame() throws HeadlessException {
        setTitle("교보문고 화제의 신상품");

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
        add(new PaginationPanel(10, e -> {}), BorderLayout.SOUTH);
    }

    private JPanel createConditionMatchPanel() {
        MarginTitledBorderPanel panel = new MarginTitledBorderPanel("조건 선택");
        panel.addSubPanel(createBookCategoryComboBox(), createStandardConstraints(10, 20));
        return setConditionPanelAddComboBoxAndEvent(panel);
    }

    private JComboBox<Category> createBookCategoryComboBox() {
        JComboBox<Category> combo = new JComboBox<>();
        for (Category category : Category.values()) {
            combo.addItem(category);
        }
        return combo;
    }

    private JPanel setConditionPanelAddComboBoxAndEvent(MarginTitledBorderPanel panel) {
        JComboBox<String> yearComboBox = createYearComboBox();
        JComboBox<String> monthComboBox = new JComboBox<>();
        JComboBox<String> weekOfMonthComboBox = new JComboBox<>();

        yearComboBox.addActionListener(e -> {
            Object yearSelected = yearComboBox.getSelectedItem();
            if (yearSelected == null) {
                return;
            }
            changeItemAtMonthComboBox(Year.parse(yearSelected.toString(), YEAR_FORMATTER), monthComboBox);
        });

        Pattern monthPattern = Pattern.compile("^([0-9]{1,2})월$");

        monthComboBox.addActionListener(e -> {
            Object yearSelected = yearComboBox.getSelectedItem();
            Object monthSelected = monthComboBox.getSelectedItem();
            if (yearSelected == null || monthSelected == null) {
                return;
            }
            Matcher monthMatcher = monthPattern.matcher(monthSelected.toString());
            if (monthMatcher.matches()) {
                Year year = Year.parse(yearSelected.toString(), YEAR_FORMATTER);
                Month month = Month.of(Integer.parseInt(monthMatcher.group(1)));
                YearMonth yearMonth = YearMonth.of(year.getValue(), month);
                changeItemAtWeekOfMonthComboBox(yearMonth, weekOfMonthComboBox);
            }
        });

        panel.addSubPanel(yearComboBox, createStandardConstraints(5, 0));
        panel.addSubPanel(monthComboBox, createStandardConstraints(0, 0));
        panel.addSubPanel(weekOfMonthComboBox, createStandardConstraints(0, 5));
        panel.addSubPanel(createConditionAcceptButton(), createStandardConstraints(5, 5));

        yearComboBox.setSelectedItem(Year.now().format(YEAR_FORMATTER));
        monthComboBox.setSelectedItem(YearMonth.now().getMonth().getDisplayName(TextStyle.FULL, Locale.KOREA));
        weekOfMonthComboBox.setSelectedIndex(WeekOfMonthCalc.currentWeekOfMonth(LocalDate.now(), DayOfWeek.SUNDAY) - 1);

        return panel;
    }

    private void changeItemAtMonthComboBox(Year selectedYear, JComboBox<String> monthCombo) {
        monthCombo.removeAllItems();

        int endMonthValue = selectedYear.equals(Year.now())
                ? Month.from(LocalDate.now()).getValue()
                : Month.DECEMBER.getValue();

        for (int month = Month.JANUARY.getValue(); month <= endMonthValue; month++) {
            monthCombo.addItem(Month.of(month).getDisplayName(TextStyle.FULL, Locale.KOREA));
        }
        monthCombo.updateUI();
    }

    private void changeItemAtWeekOfMonthComboBox(YearMonth yearMonth, JComboBox<String> weekOfMonthCombo) {
        int endWeek = yearMonth.equals(YearMonth.now())
                ? WeekOfMonthCalc.currentWeekOfMonth(LocalDate.now(), DayOfWeek.SUNDAY)
                : yearMonth.atEndOfMonth().get(ChronoField.ALIGNED_WEEK_OF_MONTH);

        weekOfMonthCombo.removeAllItems();

        for (int i = 1; i <= endWeek; i++) {
            weekOfMonthCombo.addItem(i + "주");
        }
        weekOfMonthCombo.updateUI();
    }

    private JComboBox<String> createYearComboBox() {
        JComboBox<String> combo = new JComboBox<>();
        Year year = Year.now().minusYears(2);
        while (!year.isAfter(Year.now())) {
            combo.addItem(year.format(YEAR_FORMATTER));
            year = year.plusYears(1);
        }
        return combo;
    }

    private JButton createConditionAcceptButton() {
        return new JButton("조건 선택");
    }

    private GridBagConstraints createStandardConstraints(int left, int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, left, 10, right);
        c.anchor = GridBagConstraints.WEST;
        return c;
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
