package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchMeta {

    private int totalCount;
    private int pageableCount;
    private boolean isEnd;
    private List<DialogBookInfo> bookInfoList;

}
