package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

public interface DialogBookInfoReceiver {
    void sendBookInfoToReceiver(BookInfo info);
}
