package object;

import java.util.ArrayList;

/**
 *
 * @author mshao1
 */
public class Configuration {

    private String strainTypeId = "";
    private String tableName = "";
    private ArrayList<String> selectedColumns = new ArrayList();
    private ArrayList<String> tableKeys = new ArrayList();
    private String dppStrainTypeId = "";
    private String dvStrainTypeId = "";
    private String dvTableName = "";
    private String dvSelectedColumn = "";

    public final String generateSQLQuery() {
        ArrayList<String> featuresList = new ArrayList();
        featuresList.addAll(tableKeys);
        for (String s : selectedColumns) {
            if (!featuresList.contains(s)) {
                featuresList.add(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (selectedColumns.contains("*")) {
            sb.append("SELECT * FROM ");
        } else {
            sb.append("SELECT ");
            for (String s : featuresList) {
                sb.append(s);
                if (featuresList.indexOf(s) != (featuresList.size() - 1)) {
                    sb.append(",");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("FROM ");
        }
        sb.append(tableName);

        return sb.toString();
    }

    public final String generateDVSQLQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(dvSelectedColumn);
        sb.append(" FROM ");
        sb.append(dvTableName);

        return sb.toString();
    }

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

    public ArrayList<String> getTableKeys() {
        return tableKeys;
    }

    public void setTableKeys(ArrayList<String> tableKeys) {
        this.tableKeys = tableKeys;
    }

    public String getDppStrainTypeId() {
        return dppStrainTypeId;
    }

    public void setDppStrainTypeId(String dppStrainTypeId) {
        this.dppStrainTypeId = dppStrainTypeId;
    }

    public String getDvStrainTypeId() {
        return dvStrainTypeId;
    }

    public void setDvStrainTypeId(String dvStrainTypeId) {
        this.dvStrainTypeId = dvStrainTypeId;
    }

    public String getDvTableName() {
        return dvTableName;
    }

    public void setDvTableName(String dvTableName) {
        this.dvTableName = dvTableName;
    }

    public String getDvSelectedColumn() {
        return dvSelectedColumn;
    }

    public void setDvSelectedColumn(String dvSelectedColumn) {
        this.dvSelectedColumn = dvSelectedColumn;
    }
    
    
}
