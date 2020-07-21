package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

@FunctionalInterface
public interface BookInfoReceiver {
    void sendBookInfoToReceiver(String isbn);

}
