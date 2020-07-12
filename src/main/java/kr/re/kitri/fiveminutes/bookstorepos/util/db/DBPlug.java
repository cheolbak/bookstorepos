package kr.re.kitri.fiveminutes.bookstorepos.util.db;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class DBPlug implements AutoCloseable {

    private static final DataSource dataSource = initDataSource();
    private static final List<QueryData> queryDataList = getQueryDataListInResources();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DBConnectInfo {
        private String driver;
        private String url;
        private String user;
        private String password;
    }

    @FunctionalInterface
    public interface InjectPreparedStatement {
        void inject(PreparedStatement pstmt) throws SQLException;
    }

    @FunctionalInterface
    public interface MappingResultSet<T> {
        T mapping(ResultSet resultSet) throws SQLException;
    }

    private static DataSource initDataSource() {
        DBConnectInfo info = getDBConnectInfoInResources().orElseThrow();
        log.debug("{}", info);
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(info.getDriver());
        dataSource.setUrl(info.getUrl());
        dataSource.setUsername(info.getUser());
        dataSource.setPassword(info.getPassword());
        log.debug("BasicDataSource Created");
        return dataSource;
    }

    private static Optional<DBConnectInfo> getDBConnectInfoInResources() {
        try (
                InputStream in = DBPlug.class.getClassLoader().getResourceAsStream("db.yml");
                InputStreamReader inReader = new InputStreamReader(Objects.requireNonNull(in))
        ) {
            return Optional.of(new Yaml(new Constructor(DBConnectInfo.class)).loadAs(inReader, DBConnectInfo.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static List<QueryData> getQueryDataListInResources() {
        List<QueryData> queryDataList = new ArrayList<>();
        try (
                InputStream in = DBPlug.class.getClassLoader().getResourceAsStream("query.yml");
                InputStreamReader inReader = new InputStreamReader(Objects.requireNonNull(in))
        ) {
            Yaml yaml = new Yaml(new Constructor(QueryData.class));
            for (Object o : yaml.loadAll(inReader)) {
                if (o instanceof QueryData) {
                    QueryData query = (QueryData) o;
                    log.trace("{}", query);
                    queryDataList.add(query);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return queryDataList;
    }

    private final Connection connection;
    private final List<PreparedStatement> stateList = new LinkedList<>();

    public DBPlug() throws SQLException {
        connection = dataSource.getConnection();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stateList.add(stmt);
        return stmt;
    }

    public QueryData getQueryData(String tag) {
        return queryDataList.stream()
                .filter(query -> query.getTag().equals(tag))
                .findFirst().orElse(new QueryData("Error", ""));
    }

    public PreparedStatement getPreparedStatementInQuery(String tag) throws SQLException {
        return getPreparedStatement(getQueryData(tag).getQuery());
    }

    public int executeUpdateFromQuery(String tag, InjectPreparedStatement inject) throws SQLException {
        PreparedStatement pstmt = getPreparedStatementInQuery(tag);
        inject.inject(pstmt);
        return pstmt.executeUpdate();
    }

    public <T> T getMappedObjectFromExecuteQuery(String tag, InjectPreparedStatement inject,
                                                 MappingResultSet<T> mapping) throws SQLException {
        PreparedStatement pstmt = getPreparedStatementInQuery(tag);
        inject.inject(pstmt);
        return mapping.mapping(pstmt.executeQuery());
    }

    @Override
    public void close() throws SQLException {
        if (!stateList.isEmpty()) {
            for (PreparedStatement stmt : stateList) {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            }
            stateList.clear();
        }
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
