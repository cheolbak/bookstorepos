package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Stock {

    private int stockId;
    private String bookISBN;
    private int stockAmount;
    private LocalDateTime stockDate;

}
