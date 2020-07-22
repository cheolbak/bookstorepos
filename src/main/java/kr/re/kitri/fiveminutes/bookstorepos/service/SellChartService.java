package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellChartSection;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet;

import java.util.Arrays;

import static kr.re.kitri.fiveminutes.bookstorepos.view.model.SellDataSet.Entry;

public class SellChartService {

    public SellDataSet requestSellChartDataSet(SellChartSection section, String isbn, int customerId) {
        if (section == SellChartSection.SIX_MONTHS) {
            return SellDataSet.builder()
                    .section(section)
                    .data(Arrays.asList(new Entry("2020년 2월", 10),
                                        new Entry("2020년 3월", 30),
                                        new Entry("2020년 4월", 60),
                                        new Entry("2020년 5월", 67),
                                        new Entry("2020년 6월", 74),
                                        new Entry("2020년 7월", 77)))
                    .build();
        }
        return SellDataSet.builder()
                .section(section)
                .data(Arrays.asList(new Entry("6월 4째주", 10),
                                    new Entry("6월 5째주", 15),
                                    new Entry("7월 1째주", 18),
                                    new Entry("7월 2째주", 22),
                                    new Entry("7월 3째주", 26),
                                    new Entry("7월 4째주", 29)))
                .build();
    }

}
