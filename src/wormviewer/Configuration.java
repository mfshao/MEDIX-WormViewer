
package wormviewer;

import java.util.ArrayList;

/**
 *
 * @author mshao1
 */


public class Configuration {
    private String strainTypeId = "";
    private String tableName = "";
    private ArrayList<String> selectedColumns = new ArrayList();

    public String getStrainTypeId() {
        return strainTypeId;
    }

    public void setStrainTypeId(String strainTypeId) {
        this.strainTypeId = strainTypeId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<String> getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(ArrayList<String> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }
}
