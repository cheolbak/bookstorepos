package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

@Data
public class BookInfo {

    private String isbn;
    private String title;
    private String author;
    private LocalDate releaseDate;
    private BufferedImage bookCoverImage;

}
