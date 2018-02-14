package graphics;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import javax.swing.JFileChooser;
import object.FilePathConfiguration;
import singleton.ConfigurationManager;
import singleton.ConnectionManager;
import utils.DatabaseTableInserter;
import utils.MasterFileCreater;
import utils.TableCreater;
import utils.Utils;

/**
 *
 * @author MSHAO1
 */
public class DataParserPanel extends javax.swing.JPanel {

    /**
     * Creates new form DataParserPanel
     */
    public DataParserPanel() {
        initComponents();
    }

    private boolean validateFilePath(String filePath) {
        if (!Files.exists(Paths.get(filePath + "\\data"))) {
            consoleDisplayTextArea.append("Error: no 'data' path under root!\n");
            Utils.displayErrorMessage(this, "Error: no 'data' path under root!\n");
            return false;
        }
        return true;
    }

    private boolean validateMFFilePath(String filePath) {
        if (!Files.exists(Paths.get(filePath + "\\data\\movementFeatures.csv"))) {
            consoleDisplayTextArea.append("Error: no movementFeatures.csv under data folder!\n");
            Utils.displayErrorMessage(this, "Error: no movementFeatures.csv under data folder!\n");
            return false;
        } else if (!Files.exists(Paths.get(filePath + "\\matlab\\AllFeatures.csv"))) {
            consoleDisplayTextArea.append("Error: no AllFeatures.csv under matlab folder!\n");
            Utils.displayErrorMessage(this, "Error: no AllFeatures.csv under matlab folder!\n");
            return false;
        }
        consoleDisplayTextArea.setText("");
        return true;
    }

    private boolean validateDPFilePath(String filePath) {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        HashSet<String> hSet = new HashSet<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                hSet.add(listOfFiles[i].getName());
            }
        }

        if (hSet.size() < 17) {
            consoleDisplayTextArea.append("Error: no 17 tables under current folder!\n");
            Utils.displayErrorMessage(this, "Error: no 17 tables under current folder!\n");
            return false;
        }

        for (String s : ConfigurationManager.getConfigurationManager().getDPConfiguration().getTABLE_NAMES()) {
            if (!hSet.contains(s + ".csv")) {
                consoleDisplayTextArea.append("Error: no " + s + " file under data folder!\n");
                Utils.displayErrorMessage(this, "Error: no " + s + " file under data folder!\n");
                return false;
            }
        }
        consoleDisplayTextArea.setText("");
        return true;
    }

    private void setupFileConfiguration(FilePathConfiguration fc, String filePath) {
        String[] filePathComponents = filePath.split("\\\\");
        if (filePathComponents.length > 0) {
            String cName = filePathComponents[filePathComponents.length - 1];
            fc.setAllPaths(filePath, cName);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        consoleDisplayScrollPane = new javax.swing.JScrollPane();
        consoleDisplayTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        outputHeadersCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        dtPathTextField = new javax.swing.JTextField();
        dtPathButton = new javax.swing.JButton();
        tableGenerationButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dpPathTextField = new javax.swing.JTextField();
        dpPathButton = new javax.swing.JButton();
        uploadIntoDBButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        mfPathTextField = new javax.swing.JTextField();
        mfPathButton = new javax.swing.JButton();
        mfGenerationButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(820, 540));
        setMinimumSize(new java.awt.Dimension(820, 540));

        consoleDisplayTextArea.setColumns(20);
        consoleDisplayTextArea.setRows(5);
        consoleDisplayTextArea.setFocusable(false);
        consoleDisplayScrollPane.setViewportView(consoleDisplayTextArea);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 65));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 65));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 65));

        outputHeadersCheckBox.setText("Output with Headers");
        outputHeadersCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputHeadersCheckBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Dataset Path");

        dtPathTextField.setFocusable(false);
        dtPathTextField.setMaximumSize(new java.awt.Dimension(240, 20));
        dtPathTextField.setMinimumSize(new java.awt.Dimension(240, 20));
        dtPathTextField.setPreferredSize(new java.awt.Dimension(240, 20));

        dtPathButton.setText("Browse");
        dtPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtPathButtonActionPerformed(evt);
            }
        });

        tableGenerationButton.setText("Generate 17 Tables");
        tableGenerationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableGenerationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dtPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dtPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(outputHeadersCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(tableGenerationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dtPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtPathButton)
                    .addComponent(outputHeadersCheckBox)
                    .addComponent(tableGenerationButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel2.setMaximumSize(new java.awt.Dimension(800, 65));
        jPanel2.setMinimumSize(new java.awt.Dimension(800, 65));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 65));

        jLabel2.setText("Dataset Path");

        dpPathTextField.setMaximumSize(new java.awt.Dimension(240, 20));
        dpPathTextField.setMinimumSize(new java.awt.Dimension(240, 20));
        dpPathTextField.setPreferredSize(new java.awt.Dimension(240, 20));

        dpPathButton.setText("Browse");
        dpPathButton.setMaximumSize(new java.awt.Dimension(150, 23));
        dpPathButton.setMinimumSize(new java.awt.Dimension(150, 23));
        dpPathButton.setPreferredSize(new java.awt.Dimension(150, 23));
        dpPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpPathButtonActionPerformed(evt);
            }
        });

        uploadIntoDBButton.setText("Upload Into DB");
        uploadIntoDBButton.setAutoscrolls(true);
        uploadIntoDBButton.setMaximumSize(new java.awt.Dimension(150, 23));
        uploadIntoDBButton.setMinimumSize(new java.awt.Dimension(150, 23));
        uploadIntoDBButton.setPreferredSize(new java.awt.Dimension(150, 23));
        uploadIntoDBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadIntoDBButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dpPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dpPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addComponent(uploadIntoDBButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dpPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uploadIntoDBButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setMaximumSize(new java.awt.Dimension(800, 65));
        jPanel3.setMinimumSize(new java.awt.Dimension(800, 65));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 65));

        jLabel3.setText("Dataset Path");

        mfPathTextField.setFocusable(false);
        mfPathTextField.setMaximumSize(new java.awt.Dimension(240, 20));
        mfPathTextField.setMinimumSize(new java.awt.Dimension(240, 20));
        mfPathTextField.setPreferredSize(new java.awt.Dimension(240, 20));

        mfPathButton.setText("Browse");
        mfPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mfPathButtonActionPerformed(evt);
            }
        });

        mfGenerationButton.setText("Generate Master File");
        mfGenerationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mfGenerationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(mfPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mfPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mfGenerationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mfPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mfPathButton)
                    .addComponent(mfGenerationButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consoleDisplayScrollPane)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(consoleDisplayScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void outputHeadersCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputHeadersCheckBoxActionPerformed
        // TODO add your handling code here:
        ConfigurationManager.getConfigurationManager().getGTConfiguration().setWithHeader(outputHeadersCheckBox.isSelected());
    }//GEN-LAST:event_outputHeadersCheckBoxActionPerformed

    private void tableGenerationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableGenerationButtonActionPerformed
        // TODO add your handling code here:
        if (dtPathTextField.getText().equals("")) {
            Utils.displayWarningMessage(this, "Please select directory first!");
            return;
        }
        TableCreater tc = new TableCreater(consoleDisplayTextArea);
        tc.createAllDBTables();
    }//GEN-LAST:event_tableGenerationButtonActionPerformed

    private void dtPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtPathButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose dataset directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile().getPath());
            if (validateFilePath(chooser.getSelectedFile().getPath())) {
                dtPathTextField.setText(chooser.getSelectedFile().getPath());
                setupFileConfiguration(ConfigurationManager.getConfigurationManager().getGTConfiguration(), chooser.getSelectedFile().getPath());
            }
        } else {
            System.out.println("No Selection");
        }
    }//GEN-LAST:event_dtPathButtonActionPerformed

    private void dpPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpPathButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose database tables directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile().getPath());
            if (validateDPFilePath(chooser.getSelectedFile().getPath())) {
                dpPathTextField.setText(chooser.getSelectedFile().getPath());
                setupFileConfiguration(ConfigurationManager.getConfigurationManager().getDPConfiguration(), chooser.getSelectedFile().getPath());
            }
        } else {
            System.out.println("No Selection");
        }
    }//GEN-LAST:event_dpPathButtonActionPerformed

    private void mfPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mfPathButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose dataset directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile().getPath());
            if (validateMFFilePath(chooser.getSelectedFile().getPath())) {
                mfPathTextField.setText(chooser.getSelectedFile().getPath());
                setupFileConfiguration(ConfigurationManager.getConfigurationManager().getMFConfiguration(), chooser.getSelectedFile().getPath());
            }
        } else {
            System.out.println("No Selection");
        }
    }//GEN-LAST:event_mfPathButtonActionPerformed

    private void mfGenerationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mfGenerationButtonActionPerformed
        // TODO add your handling code here:
        if (mfPathTextField.getText().equals("")) {
            Utils.displayWarningMessage(this, "Please select directory first!");
            return;
        }
        MasterFileCreater mfc = new MasterFileCreater(consoleDisplayTextArea);
        mfc.createMasterFile();
    }//GEN-LAST:event_mfGenerationButtonActionPerformed

    private void uploadIntoDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadIntoDBButtonActionPerformed
        // TODO add your handling code here:
        if (dpPathTextField.getText().equals("")) {
            Utils.displayWarningMessage(this, "Please select directory first!");
            return;
        }
        DatabaseTableInserter dti = new DatabaseTableInserter(consoleDisplayTextArea);
        dti.insertIntoDatabase();
    }//GEN-LAST:event_uploadIntoDBButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane consoleDisplayScrollPane;
    private javax.swing.JTextArea consoleDisplayTextArea;
    private javax.swing.JButton dpPathButton;
    private javax.swing.JTextField dpPathTextField;
    private javax.swing.JButton dtPathButton;
    private javax.swing.JTextField dtPathTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton mfGenerationButton;
    private javax.swing.JButton mfPathButton;
    private javax.swing.JTextField mfPathTextField;
    private javax.swing.JCheckBox outputHeadersCheckBox;
    private javax.swing.JButton tableGenerationButton;
    private javax.swing.JButton uploadIntoDBButton;
    // End of variables declaration//GEN-END:variables
}
