package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookSearchScope;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class BookInfoSearchRequesterTest {

    @Test
    public void request() {
        SearchMeta searchMeta = BookInfoSearchRequester.requestBookSearch(BookSearchScope.TITLE, "오라클 SQL", 1);
        log.info("{}", searchMeta);
    }
}
