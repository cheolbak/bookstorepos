package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Data
@Builder
public class DefaultBookInfo implements BookInfo {

    @Builder.Default
    private String isbn = "ERROR";

    @Builder.Default
    private String title = "";

    @Builder.Default
    private String author = "";

    @Builder.Default
    private String publisher = "";

    @Builder.Default
    private int price = 0;

    @Builder.Default
    private LocalDate releaseDate = LocalDate.EPOCH;

    private BufferedImage bookCoverImage;

}
