package kr.re.kitri.fiveminutes.bookstorepos.domain;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private String bookISBN;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private LocalDate bookReleaseDate;
    private int bookMSRP;

    @Builder.Default
    private int bookDiscountRate = 10;

    @Builder.Default
    private int bookPointRate = 5;

    @Builder.Default
    private int bookStock = 0;

    @Builder.Default
    private LocalDateTime insertDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime modifyDate = LocalDateTime.now();

    public static Book fromViewBookInfo(BookInfo info) {
        return Book.builder()
                .bookISBN(info.getIsbn())
                .bookTitle(info.getTitle())
                .bookAuthor(info.getAuthor())
                .bookPublisher(info.getPublisher())
                .bookReleaseDate(info.getReleaseDate())
                .bookMSRP(info.getPrice())
                .build();
    }
}
