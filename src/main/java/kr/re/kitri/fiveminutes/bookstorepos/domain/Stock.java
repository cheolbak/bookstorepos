package kr.re.kitri.fiveminutes.bookstorepos.domain;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Stock {

    private int stockId;
    private String bookISBN;
    private int stockAmount;
    private LocalDateTime stockDate;

    public static Stock fromStockBookInfo(StockBookInfo info) {
        return Stock.builder()
                .bookISBN(info.getIsbn())
                .stockAmount(info.getInsertStock())
                .build();
    }
}
