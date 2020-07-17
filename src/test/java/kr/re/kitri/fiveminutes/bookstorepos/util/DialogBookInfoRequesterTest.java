package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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

}
