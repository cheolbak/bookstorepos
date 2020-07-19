package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Data
@Builder
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

}
