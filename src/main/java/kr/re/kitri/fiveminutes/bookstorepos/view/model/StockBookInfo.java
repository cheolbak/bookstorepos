package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Data
@Builder
public class StockBookInfo {

    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private LocalDate releaseDate;
    private int originalPrice;
    private BufferedImage bookCoverImage;

    @Builder.Default
    private int salePercentPoint = 10;

    @Builder.Default
    private int pointSavePercentPoint = 5;

    @Builder.Default
    private int currentStock = 0;

    @Builder.Default
    private int insertStock = 1;

    public static StockBookInfo fromDialogBookInfo(DialogBookInfo dialogInfo) {
        return fromDialogBookInfo(dialogInfo, 1);
    }

    public static StockBookInfo fromDialogBookInfo(DialogBookInfo dialogInfo, int insertStock) {
        return StockBookInfo.builder()
                .isbn(dialogInfo.getIsbn())
                .title(dialogInfo.getTitle())
                .author(dialogInfo.getAuthor())
                .publisher(dialogInfo.getPublisher())
                .releaseDate(dialogInfo.getReleaseDate())
                .bookCoverImage(dialogInfo.getBookCoverImage())
                .originalPrice(dialogInfo.getPrice())
                .insertStock(insertStock)
                .build();
    }

}
