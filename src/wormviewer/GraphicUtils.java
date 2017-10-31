
package wormviewer;

import java.util.Vector;
import javax.swing.JList;

/**
 *
 * @author MSHAO1
 */


public class GraphicUtils {
    
    public static void populateFeatureList(JList jList) {
        if (!ConfigurationManager.getConfigurationManager().getConfiguration().getStrainTypeId().isEmpty() && !ConfigurationManager.getConfigurationManager().getConfiguration().getTableName().isEmpty()) {
            Vector<String> columnNames = PostgresSQLDBManager.getAllTableColumnLabels(ConfigurationManager.getConfigurationManager().getConfiguration().getTableName());
            jList.setListData(columnNames);
        }
    }
}
