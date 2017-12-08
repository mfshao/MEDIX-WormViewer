package object;

import java.util.ArrayList;

/**
 *
 * @author mshao1
 */
public class DVConfiguration {

    private String dvStrainTypeId = "";
    private String dvTableName = "";
    private String dvSelectedColumn = "";

    public final String generateDVSQLQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(dvSelectedColumn);
        sb.append(" FROM ");
        sb.append(dvTableName);
        
        System.out.println(sb.toString());
        return sb.toString();
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
