package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import kr.re.kitri.fiveminutes.bookstorepos.util.DateUtilities;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Builder
public class NewBookCondition {

    @Builder.Default
    private final YearMonth yearMonth = YearMonth.now();

    // 일요일 기준 몇주차인지 계산
    @Builder.Default
    private final int weekOfMonth = DateUtilities.currentWeekOfMonth(LocalDate.now(), DayOfWeek.SUNDAY);

    @Builder.Default
    private final Category category = Category.ALL;

    @Builder.Default
    private final Sort sort = Sort.SALE_QUANTITY;

    @RequiredArgsConstructor
    public enum Sort {
        SALE_QUANTITY("판매량순", "sale_qty"),
        NEAR_DATE("신상품순", "near_date"),
        SALE_PRICE_ASC("낮은가격순", "sale_pri_asc"),
        SALE_PRICE_DESC("높은가격순", "sale_pri_desc");

        private final String viewName;

        @Getter
        private final String requestName;

        @Override
        public String toString() {
            return viewName;
        }
    }

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
