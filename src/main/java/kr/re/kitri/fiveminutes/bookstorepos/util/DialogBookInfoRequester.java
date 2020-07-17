package kr.re.kitri.fiveminutes.bookstorepos.util;

import com.google.gson.*;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookSearchScope;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
            String html = Objects.requireNonNull(new OkHttpClient().newCall(req).execute().body()).string();
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
        catch (IOException | ParseException | NullPointerException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return infoList;
    }

    public static SearchMeta requestBookSearchEachTenResult(BookSearchScope scope, String query, int page) {
        String url = "https://dapi.kakao.com/v3/search/book?query="
                        + URLEncoder.encode(query, StandardCharsets.UTF_8)
                        + "&scope=" + scope.getRequestName()
                        + "&size=10&page=" + page;

        Request req = new Request.Builder()
                .get()
                .url(url)
                .addHeader("Authorization", "KakaoAK 2a22f05dfbf98250a6ae67b4eed0b461")
                .build();

        try {
            Response res = new OkHttpClient().newCall(req).execute();
            if (res.code() != 200) {
                throw new IOException("요청 에러: " + res.code() + ", " + Objects.requireNonNull(res.body(), "").string());
            }
            String body = Objects.requireNonNull(res.body()).string();

            JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
            JsonArray documents = jsonObject.get("documents").getAsJsonArray();
            JsonObject meta = jsonObject.get("meta").getAsJsonObject();

            return SearchMeta.builder()
                    .totalCount(meta.get("total_count").getAsInt())
                    .pageableCount(meta.get("pageable_count").getAsInt())
                    .isEnd(meta.get("is_end").getAsBoolean())
                    .bookInfoList(parseJsonToDialogBookInfoList(documents))
                    .build();
        }
        catch (IOException | NullPointerException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return SearchMeta.builder()
                .totalCount(0)
                .pageableCount(0)
                .isEnd(true)
                .bookInfoList(Collections.singletonList(DialogBookInfo.builder().build()))
                .build();
    }

    private static List<DialogBookInfo> parseJsonToDialogBookInfoList(JsonArray documents) throws IOException {
        List<DialogBookInfo> bookInfoList = new ArrayList<>();

        for (JsonElement document : documents) {
            JsonObject obj = document.getAsJsonObject();

            String isbn = Arrays.stream(obj.get("isbn").getAsString().split(" "))
                    .filter(s -> s.matches("^97[89][0-9]{10}$"))
                    .findFirst().orElseThrow(() -> new JsonParseException("ISBN을 찾을 수 없습니다."));

            String authors = joinJsonArrayString(obj.get("authors").getAsJsonArray(), "", "");
            String translators = joinJsonArrayString(obj.get("translators").getAsJsonArray(), " (번역: ", ")");

            bookInfoList.add(
                    DialogBookInfo.builder()
                            .isbn(isbn)
                            .title(obj.get("title").getAsString())
                            .author(authors + translators)
                            .publisher(obj.get("publisher").getAsString())
                            .price(obj.get("price").getAsInt())
                            .releaseDate(LocalDate.from(OffsetDateTime.parse(obj.get("datetime").getAsString())))
                            .bookCoverImage(BookCoverImageRequester.requestThumbnailBookCoverImage(isbn))
                            .build());
        }
        return bookInfoList;
    }

    private static String joinJsonArrayString(JsonArray jsonArray, String prefix, String suffix) {
        Iterator<JsonElement> it = jsonArray.iterator();
        StringJoiner joiner = new StringJoiner(", ", prefix, suffix);

        while (it.hasNext()) {
            joiner.add(it.next().getAsString());
        }
        return joiner.toString();
    }

}
