package kr.re.kitri.fiveminutes.bookstorepos.view.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BookSearchScope {

    TITLE("제목", "title"),
    PERSON("저자/번역자", "person"),
    PUBLISHER("출판사", "publisher"),
    ISBN("ISBN", "isbn");

    private final String viewName;

    @Getter
    private final String requestName;

    @Override
    public String toString() {
        return viewName;
    }
}
