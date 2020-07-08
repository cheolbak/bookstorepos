package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Customer {

    private int customerId;
    private String customerName;
    private String customerTel;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

}
