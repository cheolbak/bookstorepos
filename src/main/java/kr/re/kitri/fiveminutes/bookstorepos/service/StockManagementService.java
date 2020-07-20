package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.BookDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookInfoSearchRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockManagementService {

    public Book searchBook(String isbn){
//        BookDAO bookDAO = new BookDAO();
        Book book;// = bookDAO.select(isbn);

//        log.info("book.title: {}", book.getBookTitle());
//
//        if(book.getBookISBN().equals("ERROR")){
            BookInfo dialogBook = BookInfoSearchRequester.requestBookSearchScopeISBN(isbn);
            book = Book.fromViewBookInfo(dialogBook);
//        }
        return book;
    }
}


