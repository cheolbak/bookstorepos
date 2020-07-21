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

    @Builder.Default
    private int customerId = -1;

    @Builder.Default
    private String customerName = "ERROR";

    @Builder.Default
    private String customerTel = "ERROR";

    @Builder.Default
    private int customerPoint = 0;

    @Builder.Default
    private int customerTotalPrice = 0;

    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime modifyDate = LocalDateTime.now();

}
