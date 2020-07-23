package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.SellDAO;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet.Entry;

public class SellChartService {

    private SellDAO sellDAO = new SellDAO();

    public SellDataSet requestSellChartDataSet(SellChartSection section, String isbn, int customerId) {
        Map<String, List<LocalDateTime>> rangeMap = section == SellChartSection.SIX_MONTHS
                                                ? getChartRangeMapForMonth()
                                                : getChartRangeMapForWeek();

        List<Entry> entries = new ArrayList<>();

        for (Map.Entry<String, List<LocalDateTime>> rangeEntry : rangeMap.entrySet()) {
            int countData;
            if(!isbn.equals("")){
                countData = sellDAO.selectDateRangeSumScopeISBN(isbn, rangeEntry.getValue().get(0), rangeEntry.getValue().get(1));
            }
            else if(customerId > 0){
                countData = sellDAO.selectDateRangeSumScopeCustomer(customerId, rangeEntry.getValue().get(0), rangeEntry.getValue().get(1));
            }
            else {
                countData = sellDAO.selectDateRangeSum(rangeEntry.getValue().get(0), rangeEntry.getValue().get(1));
            }
            entries.add(new Entry(rangeEntry.getKey(), countData));
        }

        return SellDataSet.builder()
                .section(section)
                .data(entries)
                .build();
    }

    private Map<String, List<LocalDateTime>> getChartRangeMapForMonth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월");
        TreeMap<String, List<LocalDateTime>> resultMap = new TreeMap<>();
        YearMonth now = YearMonth.now();
        for (int i = 0; i < 6; i++) {
            resultMap.put(now.minusMonths(i).format(formatter),
                    Arrays.asList(now.minusMonths(i).atDay(1).atTime(LocalTime.MIDNIGHT),
                            now.minusMonths(i).atEndOfMonth().atTime(LocalTime.MAX)));
        }
        return resultMap;
    }

    private Map<String, List<LocalDateTime>> getChartRangeMapForWeek() {
        TreeMap<String, List<LocalDateTime>> resultMap = new TreeMap<>();
        LocalDate baseStart = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        for (int i = 0; i < 6; i++) {
            LocalDate currentStart = baseStart.minusDays(i * 7);
            LocalDate currentEnd = currentStart.plusDays(6);
            String column = currentEnd.getMonthValue() + "월 " + currentEnd.get(WeekFields.SUNDAY_START.weekOfMonth()) + "째주";
            resultMap.put(column, Arrays.asList(currentStart.atTime(LocalTime.MIN), currentEnd.atTime(LocalTime.MAX)));
        }
        return resultMap;
    }
}
