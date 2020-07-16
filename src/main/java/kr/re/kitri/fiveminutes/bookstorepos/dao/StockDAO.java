package kr.re.kitri.fiveminutes.bookstorepos.dao;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Stock;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
public class StockDAO {
    public int insertStock(Stock stock){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("stock.insert",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1,stock.getStockId());
                            pstmt.setString(2, stock.getBookISBN());
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

    public int updateAmount(int id, int amount){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("stock.update_amount",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, amount);
                            pstmt.setInt(2, id);
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
