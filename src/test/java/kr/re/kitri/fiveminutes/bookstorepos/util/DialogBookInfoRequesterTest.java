package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DialogBookInfoRequesterTest {

    @Test
    public void isbnSearch() {
        String isbn = "9791136707031";
        DialogBookInfo info = DialogBookInfoRequester.requestBookSearchScopeISBN(isbn);
        log.info("info: {}", info.toString());
        assertEquals(info.getIsbn(), isbn);
    }

    @Test
    public void getNewBookList() {
        NewBookCondition condition = NewBookCondition.builder()
                .yearMonth(YearMonth.now())
                .weekOfMonth(DateUtilities.currentWeekOfMonth(LocalDate.now(), DayOfWeek.SUNDAY))
                .category(NewBookCondition.Category.COMICS)
                .sort(NewBookCondition.Sort.SALE_QUANTITY)
                .build();
        SearchMeta infoList = DialogBookInfoRequester.requestRecommendNewBookEachPage(condition, 2);
        log.debug("totalPage: {}, isEnd: {}", infoList.getTotalCount(), infoList.isEnd());
        for (DialogBookInfo info : infoList.getBookInfoList()) {
            log.debug("{}", info);
        }
    }

}
