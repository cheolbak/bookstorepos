package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookInfoSearchRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;

public class SellManagementService {
    public Book searchBook(String isbn){
        Book book;
        BookInfo dialogBook = BookInfoSearchRequester.requestBookSearchScopeISBN(isbn);

        book = Book.fromViewBookInfo(dialogBook);
        return book;
    }
}
