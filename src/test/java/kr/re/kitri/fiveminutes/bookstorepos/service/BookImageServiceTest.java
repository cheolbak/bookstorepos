package kr.re.kitri.fiveminutes.bookstorepos.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class BookImageServiceTest {

    @Test
    public void requestImageUrl() {
        BookImageService service = new BookImageService();
        String url = service.requestImageUrl("9788958077633");
        log.info("result: {}", url);
    }

}
