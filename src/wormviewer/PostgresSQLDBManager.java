package wormviewer;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author mshao1
 */
public class PostgresSQLDBManager {

    private static PostgresSQLDBManager postgresSQLDBManager = null;

    private final static String GET_ALL_STRAINTYPEIDS = "SELECT straintypeid FROM straintype";
    private final static String GET_ALL_TABLE_NAMES = "SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' AND table_schema='public'";

    static {
        postgresSQLDBManager = new PostgresSQLDBManager();
    }

    private PostgresSQLDBManager() {
    }

    public final static PostgresSQLDBManager getPostgresSQLDBManager() {
        return postgresSQLDBManager;
    }

    public static Vector<String> getAllTableColumnLabels(String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
        Vector<String> resultList = new Vector();
        final String query = new QueryFactory("SELECT * FROM ? WHERE false").set(tableName, false).toString();

        try {
            stmt = ConnectionManager.getConnectionManager().getConnection().createStatement();
            rs = stmt.executeQuery(query);
            int columnCount = rs.getMetaData().getColumnCount();
            resultList.add("*");
            for (int column = 1; column <= columnCount; column++) {
                resultList.add(rs.getMetaData().getColumnName(column));
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
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public static ArrayList<String> getAllStrainTypeIDs() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> resultList = new ArrayList();

        try {
            ps = ConnectionManager.getConnectionManager().getConnection().prepareStatement(GET_ALL_STRAINTYPEIDS);
            rs = ps.executeQuery();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public static ArrayList<String> getAllTableNames() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> resultList = new ArrayList();

        try {
            ps = ConnectionManager.getConnectionManager().getConnection().prepareStatement(GET_ALL_TABLE_NAMES);
            rs = ps.executeQuery();
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
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultList;
    }

    public static CachedRowSet getEverythingFromImageInfo() {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSet rowset = null;

        try {
            stmt = ConnectionManager.getConnectionManager().getConnection().createStatement();
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
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowset;
    }
}
