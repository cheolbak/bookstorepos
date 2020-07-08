package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sell {

    private int sellId;
    private String bookISBN;
    private int customerId;
    private int sellCount;
    private LocalDateTime sellDate;

}
