package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class SellDataSet {

    @Getter
    private final SellChartSection section;

    private final Map<String, Integer> dataMap;

    public SellDataSet(SellChartSection section) {
        this.section = section;
        this.dataMap = new TreeMap<>();
    }

    public void addData(String columnName, int sellData) {
        dataMap.put(columnName, sellData);
    }

    public Set<Map.Entry<String, Integer>> getDataEntrySet() {
        return dataMap.entrySet();
    }
}
