package kr.re.kitri.fiveminutes.bookstorepos.dao;
import kr.re.kitri.fiveminutes.bookstorepos.domain.Book;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;

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

    public int updateStock(int stock, String isbn){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("book.update_stock",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, stock);
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

    public int updateAddStock(int stock, String isbn){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.executeUpdateFromQuery("book.update_AddStock",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, stock);
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

    public Book select(String isbn){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.getMappedObjectFromExecuteQuery("book.select",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, isbn);
                            log.info("inject isbn: {}", isbn);
                        }
                    },
                    new DBPlug.MappingResultSet<Book>() {
                        @Override
                        public Book mapping(ResultSet resultSet) throws SQLException {
                            log.debug("resultSet Count: {}", resultSet.getRow());
                            Book book = new Book();
                            resultSet.next();
                            book.setBookTitle(resultSet.getString(1));
                            book.setBookAuthor(resultSet.getString(2));
                            book.setBookPublisher(resultSet.getString(3));
                            book.setBookISBN(resultSet.getString(4));
                            book.setBookMSRP(resultSet.getInt(5));
                            book.setBookDiscountRate(resultSet.getInt(6));
                            book.setBookPointRate(resultSet.getInt(7));
                            book.setBookStock(resultSet.getInt(8));
                            book.setBookReleaseDate(resultSet.getTimestamp(9).toLocalDateTime().toLocalDate());
                            return book;
                        }
                    });
        } catch(SQLException e){
            if (log.isDebugEnabled()){
                e.printStackTrace();
            }
        }
        return new Book("ERROR", "ERROR", "ERROR", "ERROR", LocalDateTime.now().toLocalDate(), 0, 0, 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public Book selectAuthor(String author){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.getMappedObjectFromExecuteQuery("book.select_author",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, author);
                        }
                    },
                    new DBPlug.MappingResultSet<Book>() {
                        @Override
                        public Book mapping(ResultSet resultSet) throws SQLException {
                            Book book = new Book();
                            resultSet.next();
                            book.setBookTitle(resultSet.getString(1));
                            book.setBookAuthor(resultSet.getString(2));
                            book.setBookPublisher(resultSet.getString(3));
                            book.setBookReleaseDate(resultSet.getTimestamp(4).toLocalDateTime().toLocalDate());
                            book.setBookISBN(resultSet.getString(5));
                            return book;
                        }
                    });
        } catch (SQLException e){
            if(log.isDebugEnabled()){
                e.printStackTrace();
            }
        }
        return new Book("ERROR", "ERROR", "ERROR", "ERROR", LocalDateTime.now().toLocalDate(), 0, 0, 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public Book selectTitle(String title){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.getMappedObjectFromExecuteQuery("book.select_title",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, title);
                        }
                    },
                    new DBPlug.MappingResultSet<Book>() {
                        @Override
                        public Book mapping(ResultSet resultSet) throws SQLException {
                            Book book = new Book();
                            resultSet.next();
                            book.setBookTitle(resultSet.getString(1));
                            book.setBookAuthor(resultSet.getString(2));
                            book.setBookPublisher(resultSet.getString(3));
                            book.setBookReleaseDate(resultSet.getTimestamp(4).toLocalDateTime().toLocalDate());
                            book.setBookISBN(resultSet.getString(5));
                            return book;
                        }
                    });
        } catch (SQLException e){
            if(log.isDebugEnabled()){
                e.printStackTrace();
            }
        }
        return new Book("ERROR", "ERROR", "ERROR", "ERROR", LocalDateTime.now().toLocalDate(), 0, 0, 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public Book selectPublisher(String publisher){
        try(DBPlug dbPlug = new DBPlug()){
            return dbPlug.getMappedObjectFromExecuteQuery("book.select_publisher",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, publisher);
                        }
                    },
                    new DBPlug.MappingResultSet<Book>() {
                        @Override
                        public Book mapping(ResultSet resultSet) throws SQLException {
                            Book book = new Book();
                            resultSet.next();
                            book.setBookTitle(resultSet.getString(1));
                            book.setBookAuthor(resultSet.getString(2));
                            book.setBookPublisher(resultSet.getString(3));
                            book.setBookReleaseDate(resultSet.getTimestamp(4).toLocalDateTime().toLocalDate());
                            book.setBookISBN(resultSet.getString(5));
                            return book;
                        }
                    });
        } catch (SQLException e){
            if(log.isDebugEnabled()){
                e.printStackTrace();
            }
        }
        return new Book("ERROR", "ERROR", "ERROR", "ERROR", LocalDateTime.now().toLocalDate(), 0, 0, 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }
}
