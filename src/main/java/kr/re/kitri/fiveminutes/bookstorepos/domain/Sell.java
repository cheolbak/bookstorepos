package kr.re.kitri.fiveminutes.bookstorepos.domain;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellBookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellUserInfo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Sell {

    private int sellId;
    private String bookISBN;
    private int customerId;
    private int usedPoint;
    private int sellPrice;
    private int sellCount;
    private LocalDateTime sellDate;


    public static Sell fromSellBookInfo(SellBookInfo bookInfo, SellUserInfo userInfo) {
        return Sell.builder()
                .bookISBN(bookInfo.getIsbn())
                .customerId(userInfo.getUserNum())
                .sellCount(bookInfo.getSellCount())
                .build();
    }
}