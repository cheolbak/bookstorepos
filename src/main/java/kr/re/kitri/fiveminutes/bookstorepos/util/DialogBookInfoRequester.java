package kr.re.kitri.fiveminutes.bookstorepos.util;

import com.google.gson.*;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookSearchScope;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DialogBookInfoRequester {

    private static final File TEMP_DIR =
            Paths.get(System.getProperty("java.io.tmpdir"), "BookStorePOSApp").toFile();

    public static SearchMeta requestRecommendNewBookEachPage(NewBookCondition condition, int page) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String newYmw = condition.getYearMonth().format(formatter) + condition.getWeekOfMonth();
        String categoryCode = condition.getCategory().getCode();
        String sortCode = condition.getSort().getRequestName();

        initTempDir();
        String fileName = "newbook_" + newYmw + "_" + categoryCode + "_" + sortCode + ".html";
        File file = new File(TEMP_DIR, fileName);

        List<DialogBookInfo> infoList = new ArrayList<>();
        SearchMeta.SearchMetaBuilder metaBuilder = SearchMeta.builder();
        try {
            // 이틀 이상 지났으면 새로 갱신
            if (Instant.ofEpochMilli(file.lastModified()).isBefore(Instant.now().minus(Duration.ofDays(2)))) {
                log.debug("Temp File Expired Two Days");
                log.debug("file: {}, lastModified: {}", file.getName(), LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault()));
                Files.deleteIfExists(file.toPath());
            }

            DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
            String html = file.exists() ? Files.readString(file.toPath())
                                        : requestWebNewBookHtmlAndSaveTempFile(newYmw, categoryCode, sortCode, file);
            Document doc = Jsoup.parse(html);
            if (page > 0) {
                int totalCount = Integer.parseInt(doc.select("tr").last().select("td:eq(0)").get(0).text());
                metaBuilder.totalCount(totalCount);
                metaBuilder.pageableCount(0);
                String selector = pagingSelectHtmlTag(totalCount, page);
                Elements rowList = doc.select(selector);
                metaBuilder.isEnd(selector.matches("^table tr:gt\\([0-9]+\\)$"));
                for (Element row : rowList) {
                    Elements cellList = row.select("td:gt(0)");
                    String isbn = cellList.get(0).text();
                    log.debug("getInfo: {}", isbn);
                    DialogBookInfo info = DialogBookInfo.builder()
                            .isbn(isbn)
                            .title(cellList.get(1).text())
                            .author(cellList.get(2).text())
                            .publisher(cellList.get(3).text())
                            .releaseDate(LocalDate.parse(cellList.get(4).text(), parseFormat))
                            .price(NumberFormat.getInstance().parse(cellList.get(5).text().substring(1)).intValue())
                            .bookCoverImage(BookCoverImageRequester.requestThumbnailBookCoverImage(isbn))
                            .build();
                    infoList.add(info);
                }
            }
        }
        catch (IOException | ParseException | NullPointerException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return metaBuilder.bookInfoList(infoList).build();
    }

    private static void initTempDir() {
        if (!TEMP_DIR.isDirectory()) {
            log.debug("TEMP_DIR is not directory.");
            TEMP_DIR.delete();
        }

        if (!TEMP_DIR.exists()) {
            log.debug("TEMP_DIR is not exist.");
            TEMP_DIR.mkdirs();
        }
    }

    private static String requestWebNewBookHtmlAndSaveTempFile(String newYmw, String categoryCode, String sortCode, File saveFile) {
        FormBody form = new FormBody.Builder()
                .add("mallGb", "KOR")
                .add("tabGB", "1")
                .add("subEjkGb", "KOR")
                .add("newYmw", newYmw)
                .add("linkClass", categoryCode)
                .add("sortColumn", sortCode)
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

        try {
            ResponseBody body = Objects.requireNonNull(new OkHttpClient().newCall(req).execute().body());
            String result = getBodyStringByEucKrStream(body.byteStream());
            Files.writeString(saveFile.toPath(), result, StandardCharsets.UTF_8,
                                    StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
            return result;
        }
        catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return "ERROR";
    }

    private static String pagingSelectHtmlTag(int totalPage, int page) {
        //1: 0 10 / 2: 10 20 / 3: 20 30
        int first = 1 + ((page - 1) * 10);
        int last = 2 + (page * 10);
        log.debug("paging first: {}, last: {}", first, last);
        if (last >= totalPage) {
            return "table tr:gt(" + first + ")";
        }
        return "table tr:gt(" + first + "):lt(" + last + ")";
    }

    private static String getBodyStringByEucKrStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "MS949"));
        String result = reader.lines().collect(Collectors.joining(""));
        reader.close();
        return result;
    }

    public static List<DialogBookInfo> requestBookSearchManyISBNs(List<String> isbnList) {
        return isbnList.stream()
                .map(DialogBookInfoRequester::requestBookSearchScopeISBN)
                .collect(Collectors.toList());
    }

    public static DialogBookInfo requestBookSearchScopeISBN(String isbn) {
        return requestBookSearchEachTenResult(BookSearchScope.ISBN, isbn, 1).getBookInfoList().get(0);
    }

    public static SearchMeta requestBookSearchEachTenResult(BookSearchScope scope, String query, int page) {
        if (!(scope == BookSearchScope.ISBN && !query.matches("^97[89][0-9]{10}$"))) {
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
            } catch (IOException | NullPointerException e) {
                if (log.isDebugEnabled()) {
                    e.printStackTrace();
                }
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
        if (!it.hasNext()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner(", ", prefix, suffix);

        while (it.hasNext()) {
            joiner.add(it.next().getAsString());
        }
        return joiner.toString();
    }

}
