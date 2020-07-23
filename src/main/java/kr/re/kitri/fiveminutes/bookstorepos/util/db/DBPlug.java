package kr.re.kitri.fiveminutes.bookstorepos.util.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DBPlug implements AutoCloseable {

    private final Connection connection;

    private final List<PreparedStatement> stateList = new LinkedList<>();

    private final Map<String, QueryData> queryMap;

    public DBPlug() throws SQLException {
        connection = DBSource.getInstance().getDataSource().getConnection();
        queryMap = DBSource.getInstance().getQueryMap();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stateList.add(stmt);
        return stmt;
    }

    public QueryData getQueryData(String tag) {
        return queryMap.getOrDefault(tag, new QueryData("ERROR", ""));
    }

    public PreparedStatement getPreparedStatementInQuery(String tag) throws SQLException {
        QueryData queryData = getQueryData(tag);
        if (queryData.getQuery().isEmpty()) {
            throw new RuntimeException("QueryData를 불러올 수 없습니다. tag: " + tag);
        }
        return getPreparedStatement(queryData.getQuery());
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

    @FunctionalInterface
    public interface InjectPreparedStatement {
        void inject(PreparedStatement pstmt) throws SQLException;
    }

    @FunctionalInterface
    public interface MappingResultSet<T> {
        T mapping(ResultSet resultSet) throws SQLException;
    }
}
