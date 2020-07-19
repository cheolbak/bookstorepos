package kr.re.kitri.fiveminutes.bookstorepos.util.requester;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.NewBookCondition;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class NewBookInfoRequester {

    private NewBookInfoRequester() { }

    private static final File TEMP_DIR =
            Paths.get(System.getProperty("java.io.tmpdir"), "BookStorePOSApp").toFile();

    public static SearchMeta requestRecommendNewBookEachPage(NewBookCondition condition, int page) {
        YearMonth yearMonth = condition.getYearMonth();
        String year = String.valueOf(yearMonth.getYear());
        String month = String.format("%02d", yearMonth.getMonthValue());
        String week = String.valueOf(condition.getWeekOfMonth());
        String categoryCode = condition.getCategory().getCode();
        String sortCode = condition.getSort().getRequestName();

        initTempDir();
        String fileName = "newbook_" + year + month + week + "_" + categoryCode + "_" + sortCode + ".html";
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
                                        : requestWebNewBookHtmlAndSaveTempFile(year, month, week, categoryCode, sortCode, file);
            Document doc = Jsoup.parse(html);
            if (page > 0) {
                int totalCount = Integer.parseInt(doc.select("tr").last().select("td:eq(0)").get(0).text());
                metaBuilder.totalCount(totalCount);
                String selector = pagingSelectHtmlTag(totalCount, page);
                Elements rowList = doc.select(selector);
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
                            .bookCoverImage(BookCoverImageRequester.requestThumbnailBookCoverImage(isbn, 125, 190))
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

    @SuppressWarnings("ResultOfMethodCallIgnored")
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

    private static String requestWebNewBookHtmlAndSaveTempFile(String year, String month, String week, String categoryCode, String sortCode, File saveFile) {
        FormBody form = new FormBody.Builder()
                .add("mallGb", "KOR")
                .add("tabGB", "1")
                .add("subEjkGb", "KOR")
                .add("newYmw", year + month + week)
                .add("linkClass", categoryCode)
                .add("sortColumn", sortCode)
                .add("excelYn", "Y")
                .add("seeOverYn", "Y")
                .add("pageNumber", "1")
                .add("perPage", "20")
                .add("targetPage", "")
                .add("filter", "ALL")
                .add("loginYN", "N")
                .add("yyyy", year)
                .add("mm", month)
                .add("week", week)
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
}
