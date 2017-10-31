package wormviewer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MSHAO1
 */
public class UserInterfaceJFrame extends javax.swing.JFrame {

    private class DatasetComboBoxItemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Object item = e.getItem();
                ConfigurationManager.getConfigurationManager().getConfiguration().setStrainTypeId(item.toString());
            }
        }
    }

    private class TableComboBoxItemChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Object item = e.getItem();
                ConfigurationManager.getConfigurationManager().getConfiguration().setTableName(item.toString());
                ArrayList<String> tableKeys = PostgresSQLDBManager.getAllKeysOfTable(item.toString());
                ConfigurationManager.getConfigurationManager().getConfiguration().setTableKeys(tableKeys);
                GraphicUtils.populateFeatureList(featureSelectorList);
            }
        }
    }

    private class FeatureSelectorListSelectionHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                JList list = (JList) e.getSource();
                List selectionValues = list.getSelectedValuesList();
                Object[] selected = selectionValues.toArray();
                ArrayList<String> selectedFeatures = new ArrayList();
                for (Object o : selected) {
                    selectedFeatures.add(o.toString());
                }
                ConfigurationManager.getConfigurationManager().getConfiguration().setSelectedColumns(selectedFeatures);
            }
        }
    }

    /**
     * Creates new form NewJFrame
     */
    public UserInterfaceJFrame() {
        initComponents();
        initData();
    }

    private void initData() {
        datasetComboBox.removeAllItems();
        ArrayList<String> resultList = PostgresSQLDBManager.getAllStrainTypeIDs();
        for (String s : resultList) {
            datasetComboBox.addItem(s);
        }
        datasetComboBox.addItemListener(new DatasetComboBoxItemChangeListener());
        datasetComboBox.setSelectedIndex(0);
        ConfigurationManager.getConfigurationManager().getConfiguration().setStrainTypeId(datasetComboBox.getSelectedItem().toString());

        tableComboBox.removeAllItems();
        resultList = PostgresSQLDBManager.getAllTableNames();
        for (String s : resultList) {
            tableComboBox.addItem(s);
        }
        tableComboBox.addItemListener(new TableComboBoxItemChangeListener());
        tableComboBox.setSelectedIndex(0);
        System.out.print(ConfigurationManager.getConfigurationManager().getConfiguration().getTableName());
        ConfigurationManager.getConfigurationManager().getConfiguration().setTableName(tableComboBox.getSelectedItem().toString());
        ArrayList<String> tableKeys = PostgresSQLDBManager.getAllKeysOfTable(tableComboBox.getSelectedItem().toString());
        ConfigurationManager.getConfigurationManager().getConfiguration().setTableKeys(tableKeys);
        GraphicUtils.populateFeatureList(featureSelectorList);
        featureSelectorList.addListSelectionListener(new FeatureSelectorListSelectionHandler());
    }


    private static HashMap<String, ArrayList<Double>> prepareDataForFiveNumberSummary(CachedRowSet crs) {
        HashMap<String, ArrayList<Double>> resultMap = new HashMap<>();
        try {
            crs.beforeFirst();
            ResultSetMetaData metaData = crs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();

            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
                if (!ConfigurationManager.getConfigurationManager().getConfiguration().getTableKeys().contains(metaData.getColumnName(column)) && !metaData.getColumnName(column).equalsIgnoreCase("IsMoving") && !metaData.getColumnName(column).equalsIgnoreCase("Resolution")) {
                    resultMap.put(metaData.getColumnName(column), new ArrayList<>());
                }
            }

            while (crs.next()) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    if (resultMap.get(columnNames.get(columnIndex - 1)) != null) {
                        resultMap.get(columnNames.get(columnIndex - 1)).add(Double.parseDouble(crs.getObject(columnIndex).toString()));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInterfaceJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultMap;
    }

    private static DefaultTableModel buildMainDisplayTableModel(CachedRowSet crs) {
        try {
            crs.beforeFirst();
            ResultSetMetaData metaData = crs.getMetaData();
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();

            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (crs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    vector.add(crs.getObject(columnIndex));
                }
                data.add(vector);
            }

            return new DefaultTableModel(data, columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static DefaultTableModel buildSummaryDisplayTableModel(ArrayList<FiveNumberSummary> fnsList) {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("");
        for (FiveNumberSummary fns : fnsList) {
            columnNames.add(fns.getName());
        }

        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> vector = new Vector<>();
        data.add(Utils.generateDataVectorFromFiveNumberSummaryList("Min", fnsList));
        data.add(Utils.generateDataVectorFromFiveNumberSummaryList("1st Quartile", fnsList));
        data.add(Utils.generateDataVectorFromFiveNumberSummaryList("Median", fnsList));
        data.add(Utils.generateDataVectorFromFiveNumberSummaryList("3rd Quartile", fnsList));
        data.add(Utils.generateDataVectorFromFiveNumberSummaryList("Max", fnsList));

        return new DefaultTableModel(data, columnNames);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        mainScrollPane = new javax.swing.JScrollPane();
        datasetComboBox = new javax.swing.JComboBox<>();
        tableComboBox = new javax.swing.JComboBox<>();
        viewFeaturesButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        downloadDatasetButton = new javax.swing.JButton();
        summaryScrollPane = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        featureSelectorList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        viewFeaturesButton.setText("View Features");
        viewFeaturesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewFeaturesButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Dataset");

        jLabel2.setText("Select Table");

        jLabel3.setText("Select Features");

        downloadDatasetButton.setText("Download Dataset");
        downloadDatasetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadDatasetButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Summary Display");

        jScrollPane1.setViewportView(featureSelectorList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(datasetComboBox, 0, 150, Short.MAX_VALUE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(tableComboBox, 0, 150, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(summaryScrollPane)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(245, 245, 245)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(viewFeaturesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(downloadDatasetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datasetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(summaryScrollPane)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(viewFeaturesButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(downloadDatasetButton)))))
                .addContainerGap())
        );

        tableComboBox.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Features", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Plots", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Data Upload", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void downloadDatasetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadDatasetButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_downloadDatasetButtonActionPerformed

    private void viewFeaturesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewFeaturesButtonActionPerformed
        // TODO add your handling code here:
        System.out.println(ConfigurationManager.getConfigurationManager().getConfiguration().getStrainTypeId());
        System.out.println(ConfigurationManager.getConfigurationManager().getConfiguration().getTableName());
        System.out.println(ConfigurationManager.getConfigurationManager().getConfiguration().getSelectedColumns());
        CachedRowSet crs = PostgresSQLDBManager.getEntriesFromTable();
        if (crs != null) {
            JTable mainTable = new JTable(buildMainDisplayTableModel(crs));
            mainScrollPane.getViewport().add(mainTable);
            HashMap<String, ArrayList<Double>> resultMap = prepareDataForFiveNumberSummary(crs);
            System.out.println(resultMap.size());
            ArrayList<FiveNumberSummary> fnsList = StatisticsUtils.getAllFiveNumberSummaries(resultMap);
            
            JTable summaryTable = new JTable(buildSummaryDisplayTableModel(fnsList));
            summaryScrollPane.getViewport().add(summaryTable);
        }
    }//GEN-LAST:event_viewFeaturesButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> datasetComboBox;
    private javax.swing.JButton downloadDatasetButton;
    private javax.swing.JList<String> featureSelectorList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JScrollPane summaryScrollPane;
    private javax.swing.JComboBox<String> tableComboBox;
    private javax.swing.JButton viewFeaturesButton;
    // End of variables declaration//GEN-END:variables
}
