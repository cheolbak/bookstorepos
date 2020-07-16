package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DialogBookInfoRequester {

    public static List<DialogBookInfo> requestRecommendNewBook(NewBookCondition condition) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String newYmw = condition.getYearMonth().format(formatter) + condition.getWeekOfMonth();

        FormBody form = new FormBody.Builder()
                .add("mallGb", "KOR")
                .add("tabGB", "1")
                .add("subEjkGb", "KOR")
                .add("newYmw", newYmw)
                .add("linkClass", condition.getCategory().getCode())
                .add("sortColumn", "near_date")
                .add("excelYn", "Y")
                .add("seeOverYn", "Y")
                .add("pageNumber", "1")
                .add("perPage", "20")
                .add("targetPage", "")
                .add("filter", "ALL")
                .add("loginYN", "N")
                .build();

        Request req = new Request.Builder()
                .post(form)
                .url("http://www.kyobobook.co.kr/newproduct/newProductList.laf")
                .build();

        List<DialogBookInfo> infoList = new ArrayList<>();
        try {
            DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
            String html = new OkHttpClient().newCall(req).execute().body().string();
            Document doc = Jsoup.parse(html);
            Elements rowList = doc.select("table tr:gt(1)");
            for (Element row : rowList) {
                Elements cellList = row.select("td:gt(0)");
                String isbn = cellList.get(0).text();
                DialogBookInfo info = DialogBookInfo.builder()
                        .isbn(isbn)
                        .title(cellList.get(1).text())
                        .author(cellList.get(2).text())
                        .publisher(cellList.get(3).text())
                        .releaseDate(LocalDate.parse(cellList.get(4).text(), parseFormat))
                        .price(NumberFormat.getInstance().parse(cellList.get(6).text().substring(1)).intValue())
                        .bookCoverImage(BookCoverImageRequester.requestThumbnailBookCoverImage(isbn))
                        .build();
                infoList.add(info);
            }
        }
        catch (IOException | ParseException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return infoList;
    }

}
