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
        Book book = bookDAO.selectOne(isbn);

        if(book.getBookISBN().equals("ERROR")){
            BookInfo dialogBook = BookInfoSearchRequester.requestBookSearchScopeISBN(isbn);
            book = Book.fromViewBookInfo(dialogBook);
            int result = bookDAO.insertBook(book);
            log.debug("stockService.ifSelectElseThenSearchBook() result: {}", result == 1);
        }
        return StockBookInfo.fromBookDomain(book);
    }

    public void updateDiscountAndPointRate(StockBookInfo info) {
        bookDAO.updateDiscountAndPointRate(info.getIsbn(), info.getSalePercentPoint(), info.getPointSavePercentPoint());
    }

    public void pushStock(StockBookInfo stockBookInfo) {
        int stockResult = stockDAO.insertStock(Stock.fromStockBookInfo(stockBookInfo));
        int bookResult = bookDAO.updateAddStock(stockBookInfo.getInsertStock(), stockBookInfo.getIsbn());
        log.debug("stockService.pushStock() result: {}", stockResult == 1 && bookResult == 1);
    }
}


