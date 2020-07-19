package kr.re.kitri.fiveminutes.bookstorepos.view.model;



import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
@Builder
public class SellBookInfo {

    private String title;
    private String author;
    private String publisher;
    private String ISBN;
    private int originPrice;
    private int sellPrice;
    private int reserves;
    private int stock;
    private BufferedImage bookCoverImage;

}
