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
public class SellBookInfo implements BookInfo {

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private LocalDate releaseDate;

    private int price;
    private int sellPrice;
    private int point;
    private int stock;

    @Builder.Default
    private int salePercentPoint = 10;

    @Builder.Default
    private int pointSavePercentPoint = 5;

    private BufferedImage bookCoverImage;


    public static SellBookInfo fromBookDomain(Book book) {
        BufferedImage image;
        int discountedPrice = book.getBookMSRP() - book.getBookMSRP()*(book.getBookDiscountRate()/100);
        int savedPoint = book.getBookMSRP()*(book.getBookPointRate()/100);
        try {
            image = BookCoverImageRequester.requestBookCoverImage(book.getBookISBN());
        }
        catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            image = new BufferedImage(200, 300, BufferedImage.TYPE_INT_RGB);
        }
        return SellBookInfo.builder()
                .isbn(book.getBookISBN())
                .title(book.getBookTitle())
                .author(book.getBookAuthor())
                .publisher(book.getBookPublisher())
                .releaseDate(book.getBookReleaseDate())
                .bookCoverImage(image)
                .price(book.getBookMSRP())
                .sellPrice(discountedPrice)
                .point(savedPoint)
                .salePercentPoint(book.getBookDiscountRate())
                .pointSavePercentPoint(book.getBookPointRate())
                .stock(book.getBookStock())
                .build();
    }
}
