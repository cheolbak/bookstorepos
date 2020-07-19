package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.BookDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.util.DialogBookInfoRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.DialogBookInfo;
import lombok.extern.slf4j.Slf4j;

import static java.lang.System.exit;

@Slf4j
public class StockAdd {
    String isbn="";

    public StockAdd(){}
    public StockAdd(String isbn){
        this.isbn = isbn;
    }

    public Book searchBook(){
        BookDAO bookDAO = new BookDAO();
        Book book = new Book();
        book = bookDAO.select(isbn);

        System.out.println(isbn);
        log.info("book.title: {}", book.getBookTitle());

        if(book.getBookISBN().equals("ERROR")){
            DialogBookInfoRequester d = new DialogBookInfoRequester();
            DialogBookInfo dialogBook = d.requestBookSearchScopeISBN(isbn);

            book.setBookISBN(dialogBook.getIsbn());
            book.setBookTitle(dialogBook.getTitle());
            book.setBookAuthor(dialogBook.getAuthor());
            book.setBookPublisher(dialogBook.getPublisher());
            book.setBookMSRP(dialogBook.getPrice());
            book.setBookReleaseDate(dialogBook.getReleaseDate());
        }
        return book;
    }
}


