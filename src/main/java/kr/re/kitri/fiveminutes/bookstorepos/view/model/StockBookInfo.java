package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookCoverImageRequester;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;

@Data
@Builder
@Slf4j
public class StockBookInfo implements BookInfo {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDate releaseDate;
    private int price;
    private BufferedImage bookCoverImage;

    @Builder.Default
    private int salePercentPoint = 10;

    @Builder.Default
    private int pointSavePercentPoint = 5;

    @Builder.Default
    private int currentStock = 0;

    @Builder.Default
    private int insertStock = 1;

    public static StockBookInfo fromBookInfo(BookInfo info) {
        return fromBookInfo(info, 1);
    }

    public static StockBookInfo fromBookInfo(BookInfo info, int insertStock) {
        return StockBookInfo.builder()
                .isbn(info.getIsbn())
                .title(info.getTitle())
                .author(info.getAuthor())
                .publisher(info.getPublisher())
                .releaseDate(info.getReleaseDate())
                .bookCoverImage(info.getBookCoverImage())
                .price(info.getPrice())
                .insertStock(insertStock)
                .build();
    }

    public static StockBookInfo fromBookDomain(Book book) {
        return fromBookDomain(book, 1);
    }

    public static StockBookInfo fromBookDomain(Book book, int insertStock) {
        BufferedImage image;
        try {
            image = BookCoverImageRequester.requestBookCoverImage(book.getBookISBN());
        }
        catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            image = new BufferedImage(200, 300, BufferedImage.TYPE_INT_RGB);
        }
        return StockBookInfo.builder()
                .isbn(book.getBookISBN())
                .title(book.getBookTitle())
                .author(book.getBookAuthor())
                .publisher(book.getBookPublisher())
                .releaseDate(book.getBookReleaseDate())
                .bookCoverImage(image)
                .price(book.getBookMSRP())
                .salePercentPoint(book.getBookDiscountRate())
                .pointSavePercentPoint(book.getBookPointRate())
                .currentStock(book.getBookStock())
                .insertStock(insertStock)
                .build();
    }

}
