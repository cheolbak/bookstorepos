package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String bookISBN;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private LocalDate bookReleaseDate;
    private int bookMSRP;
    private int bookDiscountRate;
    private int bookPointRate;
    private int bookStock;
    private LocalDateTime insertDate;
    private LocalDateTime modifyDate;

}
