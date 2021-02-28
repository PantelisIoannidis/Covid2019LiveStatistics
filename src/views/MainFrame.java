/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import helpers.DatabaseUtils;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 * @author Efthimios Georgakis 
 * @author Aris Dimakakos
 */

// R1 Κεντική οθόνη επιλογών
public class MainFrame extends javax.swing.JFrame {
    FrameDataManagement frameDataManagement = new FrameDataManagement();
    FrameDataDisplay frameDataDisplay = new FrameDataDisplay();
    FrameMapSelection frameMapSelection = new FrameMapSelection();
    FrameAbout frameAbout = new FrameAbout();
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        
        initComponents();
        setIconImage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDataManagement = new javax.swing.JButton();
        btnDataDisplay = new javax.swing.JButton();
        btnMap = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblHeader = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();
        mnuBar = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenuItem();
        mnuFrames = new javax.swing.JMenu();
        mnuDataManagement = new javax.swing.JMenuItem();
        mnuDataDisplay = new javax.swing.JMenuItem();
        mnuMap = new javax.swing.JMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Σύστημα Covid19-Stats");
        setMaximumSize(new java.awt.Dimension(800, 460));
        setMinimumSize(new java.awt.Dimension(800, 460));
        setPreferredSize(new java.awt.Dimension(800, 460));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 460));
        getContentPane().setLayout(null);

        btnDataManagement.setText("Διαχείριση δεδομένων Covid19");
        btnDataManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataManagementActionPerformed(evt);
            }
        });
        getContentPane().add(btnDataManagement);
        btnDataManagement.setBounds(250, 140, 240, 32);

        btnDataDisplay.setText("Προβολή δεδομένων Covid19 ανά χώρα");
        btnDataDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataDisplayActionPerformed(evt);
            }
        });
        getContentPane().add(btnDataDisplay);
        btnDataDisplay.setBounds(220, 200, 300, 32);

        btnMap.setText("Προβολή δεδομένων Covid19 σε χάρτη");
        btnMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapActionPerformed(evt);
            }
        });
        getContentPane().add(btnMap);
        btnMap.setBounds(220, 260, 300, 32);

        btnExit.setText("Έξοδος");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(300, 320, 150, 32);

        lblHeader.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        lblHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHeader.setText("Covid19 Stats");
        lblHeader.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblHeader);
        lblHeader.setBounds(210, 40, 320, 60);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/covidwallpaper.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 800, 430);

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

        mnuHelp.setText("Πληροφορίες");

        mnuAbout.setText("Σχετικά");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuAbout);

        mnuBar.add(mnuHelp);

        setJMenuBar(mnuBar);

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

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutActionPerformed
        showAboutWindow();
    }//GEN-LAST:event_mnuAboutActionPerformed

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
    private javax.swing.JButton btnDataDisplay;
    private javax.swing.JButton btnDataManagement;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnMap;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem mnuDataDisplay;
    private javax.swing.JMenuItem mnuDataManagement;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuFrames;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuMap;
    // End of variables declaration//GEN-END:variables

    private void showDataDisplay() {
        if(frameDataDisplay==null){
            frameDataDisplay = new FrameDataDisplay();
        }
        frameDataDisplay.populateComponents();
        frameDataDisplay.setVisible(true);
    }

    private void showMap() {
        if (frameMapSelection == null){
            frameMapSelection = new FrameMapSelection();
        }
        frameMapSelection.populateCountryComboBoxAndList();
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

    private void showAboutWindow() {
        if (frameAbout == null){
            frameAbout = new FrameAbout();
        }
        frameAbout.setVisible(true);
    }

    //Το Εικονίδιο στην γωνία του παραθύρου
    private void setIconImage() {
        Image image = new ImageIcon(this.getClass().getResource("/resources/covid-19.png")).getImage();
        this.setIconImage(image);
    }
}
