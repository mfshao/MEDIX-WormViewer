
package wormviewer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mshao1
 */


public class PostgresSQLDBManager {
    public static ResultSet getAllImageInfo() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM imageinfo");
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    
    public static void main(String[] args) {
    }
}
