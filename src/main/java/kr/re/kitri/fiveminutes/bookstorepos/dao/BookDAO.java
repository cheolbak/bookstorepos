package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Slf4j
public class BookDAO {
    public int insertBook(Book book) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("book.insert",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, book.getBookISBN());
                            pstmt.setString(2, book.getBookTitle());
                            pstmt.setString(3, book.getBookAuthor());
                            pstmt.setString(4, book.getBookPublisher());
                            pstmt.setTimestamp(5, Timestamp.valueOf(book.getBookReleaseDate().atTime(0, 0)));
                            pstmt.setInt(6, book.getBookMSRP());
                            pstmt.setInt(7, book.getBookDiscountRate());
                            pstmt.setInt(8, book.getBookPointRate());
                            pstmt.setInt(9, book.getBookStock());
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

    public int updateAddStock(int addStock, String isbn) {
        try (DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("book.update_add_stock",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, addStock);
                            pstmt.setString(2, isbn);
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

    public int updateSubtractStock(int subtractStock, String isbn){
        try (DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("book.update_subtract_stock",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, subtractStock);
                            pstmt.setString(2, isbn);
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

    public Book selectOne(String isbn){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.getMappedObjectFromExecuteQuery("book.select",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, isbn);
                        }
                    },
                    new DBPlug.MappingResultSet<Book>() {
                        @Override
                        public Book mapping(ResultSet resultSet) throws SQLException {
                            if (resultSet.next()) {
                                return Book.builder()
                                        .bookTitle(resultSet.getString(1))
                                        .bookAuthor(resultSet.getString(2))
                                        .bookPublisher(resultSet.getString(3))
                                        .bookISBN(resultSet.getString(4))
                                        .bookMSRP(resultSet.getInt(5))
                                        .bookDiscountRate(resultSet.getInt(6))
                                        .bookPointRate(resultSet.getInt(7))
                                        .bookStock(resultSet.getInt(8))
                                        .bookReleaseDate(resultSet.getTimestamp(9).toLocalDateTime().toLocalDate())
                                        .build();
                            }
                            return Book.builder().build();
                        }
                    });
        } catch(SQLException e){
            if (log.isDebugEnabled()){
                e.printStackTrace();
            }
        }
        return Book.builder().build();
    }
}
