package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.domain.POSEntry;
import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Slf4j
public class POSEntryDAO {

    public int updatePassword(int id, String password) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.executeUpdateFromQuery("pos_entry.update_password",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
                            pstmt.setInt(2, id);
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

    public POSEntry select(int id) {
        try (DBPlug dbPlug = new DBPlug()) {
            return dbPlug.getMappedObjectFromExecuteQuery("pos_entry.select",
                    new DBPlug.InjectPreparedStatement() {
                        @Override
                        public void inject(PreparedStatement pstmt) throws SQLException {
                            pstmt.setInt(1, id);
                        }
                    },
                    new DBPlug.MappingResultSet<POSEntry>() {
                        @Override
                        public POSEntry mapping(ResultSet resultSet) throws SQLException {
                            POSEntry posEntry = new POSEntry();
                            resultSet.next();
                            posEntry.setId(resultSet.getInt(1));
                            posEntry.setPassword(resultSet.getString(2));
                            posEntry.setModifyDate(resultSet.getTimestamp(3).toLocalDateTime());
                            return posEntry;
                        }
                    });
        }
        catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return new POSEntry(0, "ERROR", LocalDateTime.now());
    }

}
