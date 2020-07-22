package kr.re.kitri.fiveminutes.bookstorepos.view.module;

import kr.re.kitri.fiveminutes.bookstorepos.util.Util;
import kr.re.kitri.fiveminutes.bookstorepos.util.requester.NewBookInfoRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.BookInfoReceiver;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.DialogBookInfoListPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.MarginTitledBorderPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.component.PaginationPanel;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition.Sort;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition.Category;

public class NewBookListDialogFrame extends JFrame implements BookInfoReceiver {

    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy년");
    private final BookInfoReceiver bookInfoReceiver;

    private PaginationPanel paginationPanel;
    private NewBookCondition newBookCondition;
    private DialogBookInfoListPanel dialogBookInfoListPanel;

    public NewBookListDialogFrame(BookInfoReceiver bookInfoReceiver) throws HeadlessException {
        this.bookInfoReceiver = bookInfoReceiver;
        setTitle("교보문고 화제의 신상품");

        initPanel();

        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(670, 770);
        setLocationRelativeTo(null);
        setLocation(getX(), getY());
        setVisible(true);
    }

    private void initPanel() {
        paginationPanel = new PaginationPanel(1);
        dialogBookInfoListPanel = new DialogBookInfoListPanel(this);

        add(createConditionMatchPanel(), BorderLayout.NORTH);
        add(dialogBookInfoListPanel, BorderLayout.CENTER);
        add(paginationPanel, BorderLayout.SOUTH);
    }

    private JPanel createConditionMatchPanel() {
        MarginTitledBorderPanel panel = new MarginTitledBorderPanel("조건 선택");
        return setConditionPanelAddComponentsAndEvents(panel);
    }

    private JComboBox<Category> createBookCategoryComboBox() {
        JComboBox<Category> combo = new JComboBox<>();
        for (Category category : Category.values()) {
            combo.addItem(category);
        }
        return combo;
    }

    private JComboBox<Sort> createBookSortComboBox() {
        JComboBox<Sort> combo = new JComboBox<>();
        for (Sort sort : Sort.values()) {
            combo.addItem(sort);
        }
        return combo;
    }

    private JPanel setConditionPanelAddComponentsAndEvents(MarginTitledBorderPanel panel) {
        JComboBox<Category> categoryComboBox = createBookCategoryComboBox();
        JComboBox<String> yearComboBox = createYearComboBox();
        JComboBox<String> monthComboBox = new JComboBox<>();
        JComboBox<String> weekOfMonthComboBox = new JComboBox<>();
        JComboBox<Sort> sortComboBox = createBookSortComboBox();
        JButton conditionAcceptButton = new JButton("불러오기");


        yearComboBox.addActionListener(e -> {
            Object yearSelected = yearComboBox.getSelectedItem();
            if (yearSelected == null) {
                return;
            }
            changeItemAtMonthComboBox(Year.parse(yearSelected.toString(), YEAR_FORMATTER), monthComboBox);
        });

        Pattern monthPattern = Pattern.compile("^([0-9]{1,2})월$");
        Pattern weekPattern = Pattern.compile("^([0-9])주$");

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

        conditionAcceptButton.addActionListener(e -> {
            Object categoryObj = categoryComboBox.getSelectedItem();
            Object yearObj = yearComboBox.getSelectedItem();
            Object monthObj = monthComboBox.getSelectedItem();
            Object weekObj = weekOfMonthComboBox.getSelectedItem();
            Object sortObj = sortComboBox.getSelectedItem();
            if (yearObj == null || monthObj == null || weekObj == null
                    || !(categoryObj instanceof Category) || !(sortObj instanceof Sort)) {
                return;
            }

            Matcher monthMather = monthPattern.matcher(monthObj.toString());
            Matcher weekMather = weekPattern.matcher(weekObj.toString());
            if (!monthMather.matches() || !weekMather.matches()) {
                return;
            }

            Category category = (Category) categoryObj;
            Sort sort = (Sort) sortObj;
            Year year = Year.parse(yearObj.toString(), YEAR_FORMATTER);
            Month month = Month.of(Integer.parseInt(monthMather.group(1)));
            YearMonth yearMonth = YearMonth.of(year.getValue(), month);
            int weekOfMonth = Integer.parseInt(weekMather.group(1));

            newBookCondition = NewBookCondition.builder()
                    .yearMonth(yearMonth)
                    .weekOfMonth(weekOfMonth)
                    .category(category)
                    .sort(sort)
                    .build();

            updatePanel();
        });

        panel.addSubPanel(categoryComboBox, createStandardConstraints(10, 0));
        panel.addSubPanel(sortComboBox, createStandardConstraints(0, 5));
        panel.addSubPanel(yearComboBox, createStandardConstraints(5, 0));
        panel.addSubPanel(monthComboBox, createStandardConstraints(0, 0));
        panel.addSubPanel(weekOfMonthComboBox, createStandardConstraints(0, 5));
        panel.addSubPanel(conditionAcceptButton, createStandardConstraints(5, 5));

        yearComboBox.setSelectedItem(Year.now().format(YEAR_FORMATTER));
        monthComboBox.setSelectedItem(YearMonth.now().getMonth().getDisplayName(TextStyle.FULL, Locale.KOREA));
        weekOfMonthComboBox.setSelectedIndex(Util.currentWeekOfMonth(LocalDate.now(), DayOfWeek.MONDAY) - 1);

        Arrays.stream(conditionAcceptButton.getActionListeners()).forEach(actionListener -> actionListener.actionPerformed(null));

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
                ? Util.currentWeekOfMonth(LocalDate.now(), DayOfWeek.MONDAY)
                : yearMonth.atEndOfMonth().get(ChronoField.ALIGNED_WEEK_OF_MONTH);

        weekOfMonthCombo.removeAllItems();

        for (int i = 1; i <= endWeek; i++) {
            weekOfMonthCombo.addItem(i + "주");
        }
        weekOfMonthCombo.updateUI();
    }

    private void updatePanel() {
        SearchMeta searchMeta = NewBookInfoRequester.requestRecommendNewBookEachPage(newBookCondition, 1);
        if (searchMeta.getTotalCount() == 0) {
            dialogBookInfoListPanel.setBookInfoList(Collections.emptyList());
            paginationPanel.setLastPage(1);
            paginationPanel.setPageChangeListener(e -> {});
            dialogBookInfoListPanel.updateUI();
            paginationPanel.updateUI();
            JOptionPane.showMessageDialog(this, "검색 된 내용이 없습니다.\n다른 조건을 시도해보세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }
        dialogBookInfoListPanel.setBookInfoList(searchMeta.getBookInfoList());
        dialogBookInfoListPanel.updateUI();
        paginationPanel.setLastPage(searchMeta.getTotalCount() / 10 + 1);
        paginationPanel.setPageChangeListener(e -> {
            SearchMeta meta = NewBookInfoRequester.requestRecommendNewBookEachPage(newBookCondition, e.getCurrentPageNumber());
            dialogBookInfoListPanel.setBookInfoList(meta.getBookInfoList());
            dialogBookInfoListPanel.updateUI();
            e.getPaginationPanel().updateUI();
        });
        paginationPanel.updateUI();
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

    private GridBagConstraints createStandardConstraints(int left, int right) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, left, 10, right);
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    @Override
    public void sendBookInfoToReceiver(String isbn) {
        bookInfoReceiver.sendBookInfoToReceiver(isbn);
    }
}
