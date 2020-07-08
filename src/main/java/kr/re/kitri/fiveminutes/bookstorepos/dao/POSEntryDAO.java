package kr.re.kitri.fiveminutes.bookstorepos.dao;

import kr.re.kitri.fiveminutes.bookstorepos.util.db.DBPlug;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class POSEntryDAO {

    public boolean insertPassword(String password) {
        try (DBPlug dbPlug = new DBPlug()) {
            PreparedStatement pstmt = dbPlug.getPreparedStatementInQuery("pos_entry.insert_password");
            pstmt.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
            return pstmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updatePassword(int id, String password) {
        try (DBPlug dbPlug = new DBPlug()) {
            PreparedStatement pstmt = dbPlug.getPreparedStatementInQuery("pos_entry.update_password");
            pstmt.setString(1, BCrypt.hashpw(password, BCrypt.gensalt()));
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() == 1;
        }
        catch (SQLException e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
