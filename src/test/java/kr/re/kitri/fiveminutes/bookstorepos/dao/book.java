package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;

@Slf4j
public class book {
    @Test
    public void queryTest() throws SQLException {
        kr.re.kitri.fiveminutes.bookstorepos.domain.Book book
                = new kr.re.kitri.fiveminutes.bookstorepos.domain.Book();


        DBPlug source = new DBPlug();
        PreparedStatement pstmt = source.getPreparedStatementInQuery("book_insert");

        pstmt.executeQuery();
       /*
        set.next();
        String i = set.getString(2);
        log.info("Result: {}", i);*/
    }
}
