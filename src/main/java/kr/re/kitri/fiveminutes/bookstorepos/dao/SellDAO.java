package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.domain.POSEntry;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Sell;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class SellDAO {
    public int insertSell(Sell sell) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("sell.insert",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, sell.getSellId());
                            pstmt.setString(2, sell.getBookISBN());
                            pstmt.setInt(3, sell.getCustomerId());
                        }
                    });
        }
        catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int updateCount(int id, int count){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("sell.update_count",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, count);
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

    public int updatePrice(int id, int price){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("sell.update_price",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, price);
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

    public int updateUsedPoint(int id, int used){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("sell.update_usedPoint",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, used);
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

    public Sell selectSell(int id) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("sell.selectSell",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, id);
                        }
                    },
                    new DBPlug.MappingResultSet<Sell>() {
                        @Override
                        public Sell mapping(ResultSet resultSet) throws SQLException {
                            Sell sell = new Sell();
                            resultSet.next();
                            sell.setSellId(resultSet.getInt(1));
                            sell.setBookISBN(resultSet.getString(2));
                            sell.setCustomerId(resultSet.getInt(3));
                            sell.setSellCount(resultSet.getInt(4));
                            sell.setSellDate(resultSet.getTimestamp(5).toLocalDateTime());
                            return sell;
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return new Sell(0,"ERROR",0,0,LocalDateTime.now());
    }

}
