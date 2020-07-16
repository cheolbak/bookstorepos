package kr.re.kitri.fiveminutes.bookstorepos.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class BookImageService {

    public BookImageService() {
    }

    public String requestImageUrl(String isbn) {
        if (!isbn.matches("^[0-9]{13}$")) {
            return "";
        }
        String requestUrl = "http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=" + isbn;

        try {
            Document document = Jsoup.connect(requestUrl).get();
            return document.select("div.cover img").attr("src");
        }
        catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
