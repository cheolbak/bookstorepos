package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.domain.Customer;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                            if (resultSet.next()) {
                                return Customer.builder()
                                        .customerId(resultSet.getInt(1))
                                        .customerName(resultSet.getString(2))
                                        .customerTel(resultSet.getString(3))
                                        .customerPoint(resultSet.getInt(4))
                                        .customerTotalPrice(resultSet.getInt(5))
                                        .build();
                            }
                            return Customer.builder().build();
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return Customer.builder().build();
    }

    public List<Customer> selectAllPaging(int offset, int fetchRows) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_all_paging",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, offset);
                            pstmt.setInt(2, fetchRows);
                        }
                    },
                    new DBPlug.MappingResultSet<List<Customer>>() {
                        @Override
                        public List<Customer> mapping(ResultSet resultSet) throws SQLException {
                            List<Customer> customers = new ArrayList<>();
                            while (resultSet.next()) {
                                customers.add(Customer.builder()
                                        .customerId(resultSet.getInt(1))
                                        .customerName(resultSet.getString(2))
                                        .customerTel(resultSet.getString(3))
                                        .customerPoint(resultSet.getInt(4))
                                        .customerTotalPrice(resultSet.getInt(5))
                                        .build());
                            }
                            return customers;
                        }
                    });
        }
        catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public int selectCount() {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_count", pstmt -> {},
                    new DBPlug.MappingResultSet<Integer>() {
                        @Override
                        public Integer mapping(ResultSet resultSet) throws SQLException {
                            resultSet.next();
                            return resultSet.getInt(1);
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

    public List<Customer> selectNameQuery(String query) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_name",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, query);
                        }
                    },
                    new DBPlug.MappingResultSet<List<Customer>>() {
                        @Override
                        public List<Customer> mapping(ResultSet resultSet) throws SQLException {
                            List<Customer> customers = new ArrayList<>();
                            while (resultSet.next()) {
                                customers.add(Customer.builder()
                                                .customerId(resultSet.getInt(1))
                                                .customerName(resultSet.getString(2))
                                                .customerTel(resultSet.getString(3))
                                                .customerPoint(resultSet.getInt(4))
                                                .customerTotalPrice(resultSet.getInt(5))
                                                .build());
                            }
                            return customers;
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public List<Customer> selectTelQuery(String query) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("customer.select_tel",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, query);
                        }
                    },
                    new DBPlug.MappingResultSet<List<Customer>>() {
                        @Override
                        public List<Customer> mapping(ResultSet resultSet) throws SQLException {
                            List<Customer> customers = new ArrayList<>();
                            while (resultSet.next()) {
                                customers.add(Customer.builder()
                                                .customerId(resultSet.getInt(1))
                                                .customerName(resultSet.getString(2))
                                                .customerTel(resultSet.getString(3))
                                                .customerPoint(resultSet.getInt(4))
                                                .customerTotalPrice(resultSet.getInt(5))
                                                .build());
                            }
                            return customers;
                        }
                    });
        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public int insertCustomer(Customer customer) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("customer.insert",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                           // pstmt.setInt(1, customer.getCustomerId());
                            pstmt.setString(1, customer.getCustomerName());
                            pstmt.setString(2, customer.getCustomerTel());
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
