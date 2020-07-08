package kr.re.kitri.fiveminutes.bookstorepos.util.db;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DBPlugTest {
    @Test
    public void rawQueryTest() throws SQLException {
        DBPlug source = new DBPlug();
        PreparedStatement pstmt = source.getPreparedStatement("SELECT 1 + 1 FROM DUAL");
        ResultSet set = pstmt.executeQuery();
        set.next();
        int i = set.getInt(1);
        log.info("Result: {}", i);
        assertEquals(i, 2);
    }

    @Test
    public void atResourcesQueryTest() throws SQLException {
        DBPlug source = new DBPlug();
        PreparedStatement state = source.getPreparedStatementInQuery("test");
        ResultSet set = state.executeQuery();
        set.next();
        int i = set.getInt(1);
        log.info("Result: {}", i);
        assertEquals(i, 2);
    }
}
