package kr.re.kitri.fiveminutes.bookstorepos.view.component;

import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;

public interface DialogBookInfoReceiver {
    void sendBookInfoToReceiver(DialogBookInfo info);
}
