package wormviewer;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mshao1
 */
public class PostgresSQLDBManager {

    public static ArrayList<String> getAllStrainTypeID() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> resultList = new ArrayList();

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT straintypeid FROM straintype");
            while (rs.next()) {
                resultList.add(rs.getString("straintypeid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public static ArrayList<String> getAllTableNames() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<String> resultList = new ArrayList();

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT table_name\n"
                    + "FROM information_schema.tables\n"
                    + "WHERE table_type='BASE TABLE'\n"
                    + "AND table_schema='public';");
            while (rs.next()) {
                resultList.add(rs.getString("table_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public static CachedRowSet getEverythingFromImageInfo() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSet rowset = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM imageinfo");
            rowset = new CachedRowSetImpl();
            rowset.populate(rs);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowset;
    }

    public static void main(String[] args) {
    }
}
