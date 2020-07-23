package kr.re.kitri.fiveminutes.bookstorepos.dao;

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
                            pstmt.setInt(4,sell.getSellPrice());
                            pstmt.setInt(5,sell.getUsedPoint());
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

    public int selectDateRangeSum(LocalDateTime start, LocalDateTime end) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("sell.select_date_range_sum",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setTimestamp(1, Timestamp.valueOf(start));
                            pstmt.setTimestamp(2, Timestamp.valueOf(end));
                        }
                    },
                    new DBPlug.MappingResultSet<Integer>() {
                        @Override
                        public Integer mapping(ResultSet resultSet) throws SQLException {
                            if (resultSet.next()) {
                                return resultSet.getInt(1);
                            }
                            return 0;
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

    public int selectDateRangeSumScopeISBN(String isbn, LocalDateTime start, LocalDateTime end) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("sell.select_date_range_sum_scope_isbn",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setTimestamp(1, Timestamp.valueOf(start));
                            pstmt.setTimestamp(2, Timestamp.valueOf(end));
                            pstmt.setString(3, isbn);
                        }
                    },
                    new DBPlug.MappingResultSet<Integer>() {
                        @Override
                        public Integer mapping(ResultSet resultSet) throws SQLException {
                            if (resultSet.next()) {
                                return resultSet.getInt(1);
                            }
                            return 0;
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


    public int selectDateRangeSumScopeCustomer(int customerId, LocalDateTime start, LocalDateTime end) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("sell.select_date_range_sum_scope_customer",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setTimestamp(1, Timestamp.valueOf(start));
                            pstmt.setTimestamp(2, Timestamp.valueOf(end));
                            pstmt.setInt(3, customerId);
                        }
                    },
                    new DBPlug.MappingResultSet<Integer>() {
                        @Override
                        public Integer mapping(ResultSet resultSet) throws SQLException {
                            if (resultSet.next()) {
                                return resultSet.getInt(1);
                            }
                            return 0;
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
}
