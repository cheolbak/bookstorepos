package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookSearchScope {

    TITLE("제목", "d_titl"),
    PERSON("저자/번역자", "d_auth"),
    PUBLISHER("출판사", "d_publ"),
    ISBN("ISBN", "d_isbn");

    private final String viewName;

    @Getter
    private final String requestName;

    @Override
    public String toString() {
        return viewName;
    }
}
