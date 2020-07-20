package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class CustomerDAO {
    public Customer selectId(int id) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_id",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, id);
                        }
                    },
                    new DBPlug.MappingResultSet<Customer>() {
                        @Override
                        public Customer mapping(ResultSet resultSet) throws SQLException {
                            Customer customer = new Customer();
                            resultSet.next();
                            customer.setCustomerId(resultSet.getInt(1));
                            customer.setCustomerName(resultSet.getString(2));
                            customer.setCustomerTel(resultSet.getString(3));
                            customer.setCustomerPoint(resultSet.getInt(4));
                            customer.setCustomerTotalPrice(resultSet.getInt(5));
                            return customer;
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return new Customer(0, "ERROR", "ERROR", 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public Customer selectNameQuery(String query) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_name",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, "%" + query + "%");
                        }
                    },
                    new DBPlug.MappingResultSet<Customer>() {
                        @Override
                        public Customer mapping(ResultSet resultSet) throws SQLException {
                            Customer customer = new Customer();
                            resultSet.next();
                            customer.setCustomerId(resultSet.getInt(1));
                            customer.setCustomerName(resultSet.getString(2));
                            customer.setCustomerTel(resultSet.getString(3));
                            customer.setCustomerPoint(resultSet.getInt(4));
                            customer.setCustomerTotalPrice(resultSet.getInt(5));
                            return customer;
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return new Customer(0, "ERROR", "ERROR", 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public Customer selectTelQuery(String query) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_tel",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, "%" + query + "%");
                        }
                    },
                    new DBPlug.MappingResultSet<Customer>() {
                        @Override
                        public Customer mapping(ResultSet resultSet) throws SQLException {
                            Customer customer = new Customer();
                            resultSet.next();
                            customer.setCustomerId(resultSet.getInt(1));
                            customer.setCustomerName(resultSet.getString(2));
                            customer.setCustomerTel(resultSet.getString(3));
                            customer.setCustomerPoint(resultSet.getInt(4));
                            customer.setCustomerTotalPrice(resultSet.getInt(5));
                            return customer;
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return new Customer(0, "ERROR", "ERROR", 0, 0, LocalDateTime.now(), LocalDateTime.now());
    }

    public int insertCustomer(Customer customer) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("customer.insert",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, customer.getCustomerId());
                            pstmt.setString(2, customer.getCustomerName());
                            pstmt.setString(3, customer.getCustomerTel());
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int updatePoint(int id, int point) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("customer.update_point",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, point);
                            pstmt.setInt(2, id);
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int updateTel(int id, String tel) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("customer.update_tel",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, tel);
                            pstmt.setInt(2, id);
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int updateTotal(int id, int price) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("customer.update_total",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, price);
                            pstmt.setInt(2, id);
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public int deleteCustomer(int id) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("customer.delete",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, id);
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
