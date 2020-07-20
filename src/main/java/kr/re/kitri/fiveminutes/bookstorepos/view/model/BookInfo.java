package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import java.awt.image.BufferedImage;
import java.time.LocalDate;

public interface BookInfo {

    String getIsbn();
    String getTitle();
    String getAuthor();
    String getPublisher();
    LocalDate getReleaseDate();
    int getPrice();
    BufferedImage getBookCoverImage();

}
