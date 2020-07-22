package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
public class SellDataSet {

    private final SellChartSection section;
    private final List<Entry> data;

    @Getter
    @RequiredArgsConstructor
    public static class Entry {
        private final String columnName;
        private final int sellCountData;
    }
}
