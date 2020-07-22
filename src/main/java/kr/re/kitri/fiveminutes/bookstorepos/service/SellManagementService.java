package kr.re.kitri.fiveminutes.bookstorepos.service;

import kr.re.kitri.fiveminutes.bookstorepos.dao.BookDAO;
import kr.re.kitri.fiveminutes.bookstorepos.dao.CustomerDAO;
import kr.re.kitri.fiveminutes.bookstorepos.dao.SellDAO;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Sell;
import kr.re.kitri.fiveminutes.bookstorepos.util.requester.BookInfoSearchRequester;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.BookInfo;
import kr.re.kitri.fiveminutes.bookstorepos.view.model.SellBookInfo;

public class SellManagementService {

    private BookDAO bookDAO = new BookDAO();

    private SellDAO sellDAO = new SellDAO();

    private CustomerDAO customerDAO = new CustomerDAO();

    public Book searchBook(String isbn){
        Book book = bookDAO.selectOne(isbn);

        //BookInfo dialogBook = BookInfoSearchRequester.requestBookSearchScopeISBN(isbn);
        //book = Book.fromViewBookInfo(dialogBook);
        return book;
    }

    public boolean IsSellBook(SellBookInfo sellBookInfo){

        return true;
    }

    public boolean pushSellInfo(Sell sell){
        int result = sellDAO.insertSell(sell);
        return result==1 ;
    }

    public boolean subStock(BookInfo BookInfo){
        int result = bookDAO.updateSubtractStock(1,BookInfo.getIsbn());
        return result==1;
    }

}
