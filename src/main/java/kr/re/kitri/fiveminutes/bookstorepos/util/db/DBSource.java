package kr.re.kitri.fiveminutes.bookstorepos.util.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class DBSource {

    private static DBSource instance;

    public static DBSource getInstance() {
        if (instance == null) {
            instance = new DBSource();
        }
        return instance;
    }

    @Getter
    private final DataSource dataSource;

    @Getter
    private final Map<String, QueryData> queryMap;

    private DBSource() {
        this.dataSource = initDataSource();
        this.queryMap = initQueryMapInResources();
        log.info("DBSource Created");
    }

    // Oracle ucp PoolDataSource를 이용한 Connection Pool
    // https://www.kdata.or.kr/info/info_04_view.html?type=techreport&page=143&dbnum=128408
    private DataSource initDataSource() {
        try {
            DBConnectInfo info = getDBConnectInfoInResources().orElseThrow();
            log.debug("{}", info);
            PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
            poolDataSource.setConnectionFactoryClassName(info.getDriver());
            poolDataSource.setURL(info.getUrl());
            poolDataSource.setUser(info.getUser());
            poolDataSource.setPassword(info.getPassword());
            poolDataSource.setInitialPoolSize(6);
            poolDataSource.setMinPoolSize(6);
            poolDataSource.setMaxPoolSize(10);
            log.debug("PoolDataSource Created");
            return poolDataSource;
        }
        catch (SQLException | NoSuchElementException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            System.exit(1);
        }
        return null;
    }

    private Optional<DBConnectInfo> getDBConnectInfoInResources() {
        try (
                InputStream in = DBPlug.class.getClassLoader().getResourceAsStream("db.yml");
                InputStreamReader inReader = new InputStreamReader(Objects.requireNonNull(in))
        ) {
            return Optional.of(new Yaml(new Constructor(DBConnectInfo.class)).loadAs(inReader, DBConnectInfo.class));
        } catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            System.exit(1);
        }
        return Optional.empty();
    }

    private Map<String, QueryData> initQueryMapInResources() {
        HashMap<String, QueryData> map = new HashMap<>();
        try (
                InputStream in = DBSource.class.getClassLoader().getResourceAsStream("query.yml");
                InputStreamReader inReader = new InputStreamReader(Objects.requireNonNull(in))
        ) {
            Yaml yaml = new Yaml(new Constructor(QueryData.class));
            for (Object o : yaml.loadAll(inReader)) {
                if (o instanceof QueryData) {
                    QueryData query = (QueryData) o;
                    log.trace("{}", query);
                    map.put(query.getTag(), query);
                }
            }
        }
        catch (IOException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            System.exit(1);
        }
        return map;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DBConnectInfo {
        private String driver;
        private String url;
        private String user;
        private String password;
    }

}
