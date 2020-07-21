package kr.re.kitri.fiveminutes.bookstorepos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private int customerId;
    private String customerName;
    private String customerTel;
    private int customerPoint;
    private int customerTotalPrice;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

}
