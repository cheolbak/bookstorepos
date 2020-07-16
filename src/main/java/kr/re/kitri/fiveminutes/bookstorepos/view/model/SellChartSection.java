package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SellChartSection {

    SIX_WEEKS("6주"),
    SIX_MONTHS("6개월");

    private final String tag;

    @Override
    public String toString() {
        return tag;
    }
}
