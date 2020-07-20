package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

public interface BookInfoReceiver {
    void sendBookInfoToReceiver(BookInfo info);
}
