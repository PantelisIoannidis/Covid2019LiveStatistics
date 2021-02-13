/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.DatabaseUtils;

/**
 *
 * @author panti
 */
public class MainFrame extends javax.swing.JFrame {
    FrameDataManagement frameDataManagement = new FrameDataManagement();
    FrameDataDisplay frameDataDisplay = new FrameDataDisplay();
    FrameMapSelection frameMapSelection = new FrameMapSelection();
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        btnDataManagement = new javax.swing.JButton();
        btnDataDisplay = new javax.swing.JButton();
        btnMap = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        mnuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenuItem();
        mnuFrames = new javax.swing.JMenu();
        mnuDataManagement = new javax.swing.JMenuItem();
        mnuDataDisplay = new javax.swing.JMenuItem();
        mnuMap = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Σύστημα Covid19-Stats");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        Desktop.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnDataManagement.setText("Διαχείριση δεδομένων Covid19");
        btnDataManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataManagementActionPerformed(evt);
            }
        });

        btnDataDisplay.setText("Προβολή δεδομένων Covid19 ανά χώρα");
        btnDataDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataDisplayActionPerformed(evt);
            }
        });

        btnMap.setText("Προβολή δεδομένων Covid19 σε χάρτη");
        btnMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapActionPerformed(evt);
            }
        });

        btnExit.setText("Έξοδος");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        mnuFile.setText("Αρχείο");

        mnuExit.setText("Έξοδος");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuFile.add(mnuExit);

        mnuBar.add(mnuFile);

        mnuFrames.setText("Εργασίες");

        mnuDataManagement.setText("Διαχείριση δεδομένων Covid19");
        mnuDataManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDataManagementActionPerformed(evt);
            }
        });
        mnuFrames.add(mnuDataManagement);

        mnuDataDisplay.setText("Προβολή δεδομένων Covid19 ανά χώρα");
        mnuDataDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuDataDisplayActionPerformed(evt);
            }
        });
        mnuFrames.add(mnuDataDisplay);

        mnuMap.setText("Προβολή δεδομένων Covid19 σε χάρτη");
        mnuMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMapActionPerformed(evt);
            }
        });
        mnuFrames.add(mnuMap);

        mnuBar.add(mnuFrames);

        setJMenuBar(mnuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addComponent(btnDataManagement))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnMap)
                            .addComponent(btnDataDisplay)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(btnExit)))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnDataManagement)
                .addGap(18, 18, 18)
                .addComponent(btnDataDisplay)
                .addGap(18, 18, 18)
                .addComponent(btnMap)
                .addGap(30, 30, 30)
                .addComponent(btnExit)
                .addGap(0, 143, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        exitApp();
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuDataManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDataManagementActionPerformed
        showDataManagement();
    }//GEN-LAST:event_mnuDataManagementActionPerformed

    private void mnuDataDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuDataDisplayActionPerformed

        showDataDisplay();
    }//GEN-LAST:event_mnuDataDisplayActionPerformed

    private void mnuMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMapActionPerformed
        showMap();
    }//GEN-LAST:event_mnuMapActionPerformed

    private void btnDataManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataManagementActionPerformed
        showDataManagement();
    }//GEN-LAST:event_btnDataManagementActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        exitApp();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDataDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataDisplayActionPerformed
        showDataDisplay();
    }//GEN-LAST:event_btnDataDisplayActionPerformed

    private void btnMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapActionPerformed
        showMap();
    }//GEN-LAST:event_btnMapActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JButton btnDataDisplay;
    private javax.swing.JButton btnDataManagement;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem mnuDataDisplay;
    private javax.swing.JMenuItem mnuDataManagement;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuFrames;
    private javax.swing.JMenuItem mnuMap;
    // End of variables declaration//GEN-END:variables

    private void showDataDisplay() {
        if(frameDataDisplay==null){
            frameDataDisplay = new FrameDataDisplay();
        }
        frameDataDisplay.setVisible(true);
    }

    private void showMap() {
        if (frameMapSelection == null){
            frameMapSelection = new FrameMapSelection();
        }
        frameMapSelection.setVisible(true);
    }

    private void showDataManagement() {
        if (frameDataManagement == null){
            frameDataManagement = new FrameDataManagement();
        }
        frameDataManagement.setVisible(true);
    }

    private void exitApp() {
        System.exit(0);
    }
}
