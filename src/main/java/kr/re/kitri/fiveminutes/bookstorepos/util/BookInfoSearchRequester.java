package kr.re.kitri.fiveminutes.bookstorepos.util;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookSearchScope;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SearchMeta;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class BookInfoSearchRequester {

    public static List<DialogBookInfo> requestBookSearchManyISBNs(List<String> isbnList) {
        return isbnList.stream()
                .map(BookInfoSearchRequester::requestBookSearchScopeISBN)
                .collect(Collectors.toList());
    }

    public static DialogBookInfo requestBookSearchScopeISBN(String isbn) {
        return requestBookSearch(BookSearchScope.ISBN, isbn, 1).getBookInfoList().get(0);
    }

    public static SearchMeta requestBookSearch(BookSearchScope scope, String query, int page) {
        if (!(scope == BookSearchScope.ISBN && !query.matches("^97[89][0-9]{10}$"))) {
            String encodeQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            int start = (page - 1) * 10 + 1;
            String url = "https://openapi.naver.com/v1/search/book_adv.xml?"
                    + scope.getRequestName() + "=" + encodeQuery
                    + "&start=" + start;
            log.debug("search url request: {}", url);

            Request req = new Request.Builder()
                    .get()
                    .url(url)
                    .addHeader("X-Naver-Client-Id", "xENCZewdImaOqv7oLE6U")
                    .addHeader("X-Naver-Client-Secret", "kL8F9Copa0")
                    .build();
            log.debug("request Object Created");

            try {
                Response res = new OkHttpClient().newCall(req).execute();
                log.debug("Client request execute");
                if (res.code() != 200) {
                    throw new IOException("요청 에러: " + res.code() + ", " + Objects.requireNonNull(res.body(), "").string());
                }
                InputStream in = Objects.requireNonNull(res.body()).byteStream();

                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(in);
                log.debug("xml doc created");
                int totalCount = parseXmlToTotalCount(document);
                log.debug("totalCount: {}", totalCount);
                return SearchMeta.builder()
                        .totalCount(totalCount)
                        .bookInfoList(parseXmlToList(document))
                        .build();
            } catch (IOException | NullPointerException | ParserConfigurationException
                        | SAXException | XPathExpressionException e) {
                if (log.isDebugEnabled()) {
                    e.printStackTrace();
                }
            }
        }
        return SearchMeta.builder()
                .totalCount(0)
                .bookInfoList(Collections.singletonList(DialogBookInfo.builder().build()))
                .build();
    }

    private static int parseXmlToTotalCount(Document doc) throws XPathExpressionException {
        XPathExpression xPath = XPathFactory.newInstance().newXPath().compile("//rss/channel/total");
        return ((Double) xPath.evaluate(doc, XPathConstants.NUMBER)).intValue();
    }

    private static List<DialogBookInfo> parseXmlToList(Document doc) throws IOException, XPathExpressionException {
        List<DialogBookInfo> bookList = new ArrayList<>();

        XPathExpression xPath = XPathFactory.newInstance().newXPath().compile("//rss/channel/item");
        NodeList itemNodes = (NodeList) xPath.evaluate(doc, XPathConstants.NODESET);
        log.debug("itemNodes.getLength(): {}", itemNodes.getLength());
        ItemInfoIterator: for (int i = 0; i < itemNodes.getLength(); i++) {
            log.debug("iterator i: {}", i);
            log.debug("getNodeName(): {}", itemNodes.item(i).getNodeName());
            if (!itemNodes.item(i).getNodeName().equals("item")) {
                continue;
            }
            NodeList itemChildNodes = itemNodes.item(i).getChildNodes();

            DialogBookInfo.DialogBookInfoBuilder builder = DialogBookInfo.builder();
            String isbn = "";
            log.debug("itemChildNodes.getLength(): {}", itemChildNodes.getLength());
            for (int j = 0; j < itemChildNodes.getLength(); j++) {
                Node item = itemChildNodes.item(j);
                log.debug("item getNodeName: {}", item.getNodeName());
                String nodeName = item.getNodeName();
                switch (nodeName) {
                    case "title":
                        builder.title(removeTag(item.getTextContent()));
                        break;
                    case "author":
                        builder.author(item.getTextContent());
                        break;
                    case "price":
                        builder.price(Integer.parseInt(item.getTextContent()));
                        break;
                    case "publisher":
                        builder.publisher(item.getTextContent());
                        break;
                    case "pubdate":
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                        builder.releaseDate(LocalDate.parse(item.getTextContent(), formatter));
                        break;
                    case "isbn":
                        try {
                            isbn = Arrays.stream(item.getTextContent().split(" "))
                                    .filter(s -> s.matches("^97[89][0-9]{10}$"))
                                    .findFirst().orElseThrow(() -> new RuntimeException("유효한 ISBN이 존재하지 않습니다."));
                        } catch (RuntimeException e) {
                            if (log.isDebugEnabled()) {
                                e.printStackTrace();
                            }
                            continue ItemInfoIterator;
                        }
                        builder.isbn(isbn);
                        break;
                }
            }
            builder.bookCoverImage(BookCoverImageRequester.requestThumbnailBookCoverImage(isbn));

            bookList.add(builder.build());
        }
        return bookList;
    }

    private static final Pattern BOLD_START_TAG_CHECK = Pattern.compile("(.*)<b>(.*)");
    private static final Pattern BOLD_END_TAG_CHECK = Pattern.compile("(.*)</b>(.*)");

    private static String removeTag(String original) {
        String change = original;
        Matcher startMatcher = BOLD_START_TAG_CHECK.matcher(change);
        while (startMatcher.matches()) {
            change = startMatcher.replaceAll("$1$2");
            startMatcher = BOLD_START_TAG_CHECK.matcher(change);
        }
        Matcher endMatcher = BOLD_END_TAG_CHECK.matcher(change);
        while (endMatcher.matches()) {
            change = endMatcher.replaceAll("$1$2");
            endMatcher = BOLD_END_TAG_CHECK.matcher(change);
        }
        return change;
    }
}
