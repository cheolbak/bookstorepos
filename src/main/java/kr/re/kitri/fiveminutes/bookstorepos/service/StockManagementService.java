package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.BookDAO;
import kr.re.kitri.fiveminutes.bookstorepos.dao.StockDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Stock;
import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookInfoSearchRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.StockBookInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockManagementService {

    private final BookDAO bookDAO = new BookDAO();

    private final StockDAO stockDAO = new StockDAO();

    public StockBookInfo ifSelectElseThenSearchBook(String isbn){
        Book book = bookDAO.select(isbn);

        if(book.getBookISBN().equals("ERROR")){
            BookInfo dialogBook = BookInfoSearchRequester.requestBookSearchScopeISBN(isbn);
            book = Book.fromViewBookInfo(dialogBook);
            bookDAO.insertBook(book);
        }
        return StockBookInfo.fromBookDomain(book);
    }

    public boolean isBookInDataBaseElseInsert(BookInfo checkBook) {
        Book book = bookDAO.select(checkBook.getIsbn());
        if (book.getBookISBN().equals("ERROR")) {
            int i = bookDAO.insertBook(Book.fromViewBookInfo(checkBook));
            return i == 1;
        }
        return true;
    }

    public boolean pushStock(StockBookInfo stockBookInfo) {
        int stockResult = stockDAO.insertStock(Stock.fromStockBookInfo(stockBookInfo));
        int bookResult = bookDAO.updateAddStock(stockBookInfo.getInsertStock(), stockBookInfo.getIsbn());
        return stockResult == 1 && bookResult == 1;
    }
}


