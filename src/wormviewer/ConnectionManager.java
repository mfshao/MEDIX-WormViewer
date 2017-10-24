
package wormviewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mshao1
 */


public class ConnectionManager {
    private static final String DB_URL = "jdbc:postgresql://cdmmedixsrv.cdm.depaul.edu:5432/c_elegans";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "dbadmin";
    private static Connection dbConnection = null;
    
    public static Connection getConnection() {
        if (dbConnection == null) {
            try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        return dbConnection;
    }
}
