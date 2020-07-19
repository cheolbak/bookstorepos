package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.util.requester.NewBookInfoRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class NewBookInfoRequesterTest {

    @Test
    public void getNewBookList() {
        NewBookCondition condition = NewBookCondition.builder()
                .yearMonth(YearMonth.now())
                .weekOfMonth(Util.currentWeekOfMonth(LocalDate.now(), DayOfWeek.MONDAY))
                .category(NewBookCondition.Category.COMICS)
                .sort(NewBookCondition.Sort.SALE_QUANTITY)
                .build();
        SearchMeta infoList = NewBookInfoRequester.requestRecommendNewBookEachPage(condition, 2);
        log.debug("totalPage: {}", infoList.getTotalCount());
        for (DialogBookInfo info : infoList.getBookInfoList()) {
            log.debug("{}", info);
        }
    }

}
