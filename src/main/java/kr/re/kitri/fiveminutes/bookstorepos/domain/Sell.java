package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sell {

    private int sellId;
    private String bookISBN;
    private int customerId;
    private int sellCount;
    private LocalDateTime sellDate;

}
