package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Getter
@Builder
public class NewBookCondition {

    @Builder.Default
    private final YearMonth yearMonth = YearMonth.now();

    @Builder.Default
    // 월요일 기준 몇주차인지 계산
    private final int weekOfMonth =
            LocalDate.now().plusDays(
                            YearMonth.now().atDay(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
                        )
                        .until(YearMonth.now().atDay(1), ChronoUnit.DAYS))
                        .get(ChronoField.ALIGNED_WEEK_OF_MONTH);

    @Builder.Default
    private final Category category = Category.ALL;

    @Getter
    @RequiredArgsConstructor
    public enum Category {

        ALL("분야 종합", "00"),
        FICTION("소설", "01"),
        POEM_ESSAY("시/에세이", "03"),
        HUMANITIES("인문", "05"),
        FAMILY("가정/육아", "07"),
        COOK("요리", "08"),
        HEALTHY("건강", "09"),
        HOBBY_SPORTS("취미/실용/스포츠", "11"),
        ECONOMY("경제/경영", "13"),
        SELF_IMPROVEMENT("자기계발", "15"),
        POLITICS_SOCIETY("정치/사회", "17"),
        HISTORY_CULTURE("역사/문화", "19"),
        RELIGION("종교", "21"),
        ART("예술/대중문화", "23"),
        SCIENCE("과학", "29"),
        JOB("취업/수험서", "31"),
        COMPUTER("컴퓨터/IT", "33"),
        BABY("유아(0~7세)", "41"),
        CHILDREN("어린이(초등)", "42"),
        COMICS("만화", "47");

        private final String name;
        private final String code;

        @Override
        public String toString() {
            return name;
        }
    }
}
