package kr.re.kitri.fiveminutes.bookstorepos.view.component;

@FunctionalInterface
public interface BookInfoReceiver {
    void sendBookInfoToReceiver(String isbn);
}
