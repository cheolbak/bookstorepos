package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Stock;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class StockDAO {
    public int insertStock(Stock stock){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("stock.insert",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, stock.getBookISBN());
                            pstmt.setInt(2, stock.getStockAmount());
                        }
                    });
        }
        catch (SQLException e){
            if(log.isDebugEnabled()){
                e.printStackTrace();
            }
        }
        return 0;
    }
}
