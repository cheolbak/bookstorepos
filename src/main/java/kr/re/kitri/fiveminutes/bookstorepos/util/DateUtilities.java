package kr.re.kitri.fiveminutes.bookstorepos.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

public class DateUtilities {

    public static int currentWeekOfMonth(LocalDate date, DayOfWeek baseDayOfWeek) {
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate baseDayAsWeekInFirstDay = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(baseDayOfWeek));
        int subtractDays = baseDayAsWeekInFirstDay.until(firstDayOfMonth).getDays();
        LocalDate result = date.plusDays(subtractDays);
        return result.isAfter(date.with(TemporalAdjusters.lastDayOfMonth()))
                ? date.get(ChronoField.ALIGNED_WEEK_OF_MONTH)
                : result.get(ChronoField.ALIGNED_WEEK_OF_MONTH);
    }
}
