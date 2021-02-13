/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.APIController;
import controllers.DbOperations;
import entities.Country;
import entities.Coviddata;
import helpers.DateValidator;
import java.awt.Container;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import models.MappingData;
import models.PlottingData;
import models.TimeSeriesCase;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author panti
 */
public class FrameDataDisplay extends javax.swing.JFrame {

    /**
     * Creates new form FrameDataDisplay
     */
    DbOperations db;
    APIController api;
    static final String dateFormatPattern = "dd/MM/yyyy";
    static final String minDate = "01/01/2019";
    SimpleDateFormat simpleDateFormat ;
    
    //Οι παρακάτω μεταβλητές πέρνουν τιμές όταν επιλέγεται μια χώρα και είναι 
    //διαθέσιμες σε όλο το jframe
    List<Coviddata> confirmedList=null;
    List<Coviddata> recoveredList=null;
    List<Coviddata> deathsList=null;
    String selectedCountry;
    
    FramePlotLineChart chartFrame = new FramePlotLineChart();
    JPopupMenu popupmenu;
    
    public FrameDataDisplay() {
        initComponents();
        //χρησιμοποιούμε σε όλη την φόρμα μια μορφή ημερομηνίας
        simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
        //Φτιάχνουμε το αντικείμενο που μεσολαβεί για την επικοινωνία με την βάση
        db = new DbOperations();
        //Φτιάχνουμε το αντικείμενο που μεσολαβεί για την επικοινωνία με τo API
        api = new APIController();
        //αρχικοποιούμε τα components
        populateComponents();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbCountry = new javax.swing.JComboBox<>();
        tabsCases = new javax.swing.JTabbedPane();
        tabConfirmed = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConfirmed = new javax.swing.JTable();
        tabRecovered = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRecovered = new javax.swing.JTable();
        tabDeaths = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDeaths = new javax.swing.JTable();
        frmTxtDateFrom = new javax.swing.JFormattedTextField();
        frmTxtDateTo = new javax.swing.JFormattedTextField();
        chkDateFrom = new javax.swing.JCheckBox();
        chkDateTo = new javax.swing.JCheckBox();
        btnFilter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnShowMap = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnShowPlot = new javax.swing.JButton();
        chkConfirmed = new javax.swing.JCheckBox();
        chkRecovered = new javax.swing.JCheckBox();
        chkDeaths = new javax.swing.JCheckBox();
        chkDailyData = new javax.swing.JCheckBox();
        chkAccumulativeData = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnDeleteData = new javax.swing.JButton();

        setTitle("Προβολή δεδομένων Covid19 ανά χώρα");

        tabsCases.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabsCases.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabsCases.setInheritsPopupMenu(true);

        tabConfirmed.setLayout(new javax.swing.BoxLayout(tabConfirmed, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setFocusable(false);
        jScrollPane1.setName(""); // NOI18N

        tblConfirmed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ημερομηνία", "Κρούσματα", "Συνολικά"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblConfirmed);

        tabConfirmed.add(jScrollPane1);

        tabsCases.addTab("Επιβεβαιωμένα", tabConfirmed);

        tabRecovered.setLayout(new javax.swing.BoxLayout(tabRecovered, javax.swing.BoxLayout.LINE_AXIS));

        tblRecovered.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ημερομηνία", "Ανάρρωσαν", "Συνολικά"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblRecovered);

        tabRecovered.add(jScrollPane3);

        tabsCases.addTab("Ανάρρωσαν", tabRecovered);

        tabDeaths.setLayout(new javax.swing.BoxLayout(tabDeaths, javax.swing.BoxLayout.LINE_AXIS));

        tblDeaths.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ημερομηνία", "Θάνατοι", "Συνολικά"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDeaths);

        tabDeaths.add(jScrollPane2);

        tabsCases.addTab("Θανατοι", tabDeaths);

        frmTxtDateFrom.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        frmTxtDateFrom.setText("01/01/2000");
        frmTxtDateFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frmTxtDateFromActionPerformed(evt);
            }
        });

        frmTxtDateTo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        frmTxtDateTo.setText("01/01/2000");

        chkDateFrom.setText("Απο");
        chkDateFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDateFromActionPerformed(evt);
            }
        });

        chkDateTo.setText("Εως");
        chkDateTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDateToActionPerformed(evt);
            }
        });

        btnFilter.setText("Φίλτρο");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });

        jLabel1.setText("Επιλογή χώρας");

        jLabel2.setText("Ημερομηνία");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Απεικόνιση σε χάρτη"));

        btnShowMap.setText("Προβολή σε χάρτη");
        btnShowMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(btnShowMap)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnShowMap)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Απεικόνιση σε διάγραμμα"));

        btnShowPlot.setText("Προβολή σε διάγραμμα");
        btnShowPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPlotActionPerformed(evt);
            }
        });

        chkConfirmed.setSelected(true);
        chkConfirmed.setText("Επιβεβαιωμένα");

        chkRecovered.setSelected(true);
        chkRecovered.setText("Ανάρρωσαν");

        chkDeaths.setSelected(true);
        chkDeaths.setText("Θανατοι");

        chkDailyData.setSelected(true);
        chkDailyData.setText("Καθημερινά");

        chkAccumulativeData.setText("Σωρευτικά");

        jLabel3.setText("Δεδομένα");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(chkConfirmed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(41, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkDeaths)
                            .addComponent(chkRecovered))
                        .addGap(157, 157, 157)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkAccumulativeData)
                    .addComponent(chkDailyData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3))
                    .addComponent(jSeparator1))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnShowPlot)
                .addGap(104, 104, 104))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(chkConfirmed))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkDailyData)
                    .addComponent(chkRecovered, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkDeaths)
                    .addComponent(chkAccumulativeData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnShowPlot)
                .addContainerGap())
        );

        btnDeleteData.setText("Διαγραφή δεδομένων");
        btnDeleteData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(frmTxtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frmTxtDateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDateTo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnFilter)
                .addGap(66, 66, 66))
            .addComponent(tabsCases)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnDeleteData)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkDateFrom)
                    .addComponent(chkDateTo)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frmTxtDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frmTxtDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter)
                    .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(tabsCases, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteData)
                        .addGap(22, 22, 22))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkDateToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDateToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDateToActionPerformed

    private void frmTxtDateFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frmTxtDateFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_frmTxtDateFromActionPerformed

    private void chkDateFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDateFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDateFromActionPerformed

    private void btnFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterActionPerformed
        onCountryChange();
    }//GEN-LAST:event_btnFilterActionPerformed

    private void btnShowPlotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPlotActionPerformed
        plotLineChart();
    }//GEN-LAST:event_btnShowPlotActionPerformed

    private void btnShowMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMapActionPerformed
        showMap();
    }//GEN-LAST:event_btnShowMapActionPerformed

    private void btnDeleteDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDataActionPerformed
        removeCountrysCoviddata();
    }//GEN-LAST:event_btnDeleteDataActionPerformed

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
            java.util.logging.Logger.getLogger(FrameDataDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDataDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDataDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDataDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameDataDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteData;
    private javax.swing.JButton btnFilter;
    private javax.swing.JButton btnShowMap;
    private javax.swing.JButton btnShowPlot;
    private javax.swing.JCheckBox chkAccumulativeData;
    private javax.swing.JCheckBox chkConfirmed;
    private javax.swing.JCheckBox chkDailyData;
    private javax.swing.JCheckBox chkDateFrom;
    private javax.swing.JCheckBox chkDateTo;
    private javax.swing.JCheckBox chkDeaths;
    private javax.swing.JCheckBox chkRecovered;
    private javax.swing.JComboBox<String> cmbCountry;
    private javax.swing.JFormattedTextField frmTxtDateFrom;
    private javax.swing.JFormattedTextField frmTxtDateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel tabConfirmed;
    private javax.swing.JPanel tabDeaths;
    private javax.swing.JPanel tabRecovered;
    private javax.swing.JTabbedPane tabsCases;
    private javax.swing.JTable tblConfirmed;
    private javax.swing.JTable tblDeaths;
    private javax.swing.JTable tblRecovered;
    // End of variables declaration//GEN-END:variables

    private void populateComponents() {
        //γεμίζουμε το combo με τα ονόματα των χωρών
        populateCountryComboBox();
        //αρχικοποίηση των textfield με τις ημερομηνίες
        populateDatesRangeBoxes();
        //φτιάχνουμε άδεια grids απλά για να πιάνουν χώρο μέχρι να έρθουν τα data
        resetCoviddataGrids();
        //Φτιάχνουμε Popup menu που βοηθάει στην επιλογή ημερομηνίων
        datePopupMenu();
    }
    
    
    private void setDateBack(String time){
        try {
            //Πέρνουμε την τελική ημερομηνία και ανάλογα με την παράμετρο θα αφαιρούμε χρόνο
            Date dateFrom = simpleDateFormat.parse(frmTxtDateTo.getText());
            LocalDateTime localDateTime = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if(time.equals("aWeek"))
               localDateTime = localDateTime.minusWeeks(1);
            if(time.equals("aMonth"))
               localDateTime = localDateTime.minusMonths(1);
            if(time.equals("thisYear"))
               localDateTime = localDateTime.withMonth(1).withDayOfMonth(1);
            if(time.equals("all"))
               localDateTime = localDateTime.withMonth(1).withDayOfMonth(1).withYear(2019);
            dateFrom = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            frmTxtDateFrom.setText( simpleDateFormat.format(dateFrom));
            chkDateFrom.setSelected(true);
            chkDateTo.setSelected(true);
            onCountryChange();
        } catch (ParseException ex) {
            Logger.getLogger(FrameDataDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void datePopupMenu(){
        //Φτιάχνουμε Popup menu που βοηθάει στην επιλογή ημερομηνίων
        popupmenu = new JPopupMenu("Αφαίρεσε χρόνο");   
        JMenuItem aWeek = new JMenuItem("Tελευταία εβδομάδα");  
        JMenuItem aMonth = new JMenuItem("Tελευταίος μήνας");  
        JMenuItem thisYear = new JMenuItem("Φέτος");  
        JMenuItem all = new JMenuItem("Όλα");
        aWeek.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            { 
                setDateBack("aWeek");
            } 
        }); 
        aMonth.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            { 
                setDateBack("aMonth");
            } 
        }); 
        thisYear.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            { 
                setDateBack("thisYear");
            } 
        });
        all.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            { 
                setDateBack("all");
            } 
        });
        popupmenu.add(aWeek); 
        popupmenu.add(aMonth); 
        popupmenu.add(thisYear); 
        popupmenu.add(all); 
        this.add(popupmenu);           
    }
    
    private void populateDatesRangeBoxes(){
        //Βάζουμε μια ημερομηνία πολύ πριν το covid και τα data μας για αρχική ημερομηνία
        frmTxtDateFrom.setText(minDate);
        //Προσθέτουμε έναν listener και ελέγχουμε όταν χάνει το focus αν η ημερομηνία έχει σωστή μορφή
        frmTxtDateFrom.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if(!(new DateValidator(dateFormatPattern).isValid(frmTxtDateFrom.getText())))
                {
                    JOptionPane.showConfirmDialog(null, "Η ημερομηνία δεν έχει σωστή μορφή", "Λάθος ημερομηνία", JOptionPane.PLAIN_MESSAGE);
                    frmTxtDateFrom.setText(minDate);
                }
            }
        });
        
        //Listener για το Popup menu που βοηθάει στην επιλογή ημερομηνίων
        frmTxtDateFrom.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {              
                 popupmenu.show(frmTxtDateFrom ,0,20 );
            }   
        });
        
        
        //Βάζουμε την σημερινή ημερομηνία για το εώς
        frmTxtDateTo.setText(simpleDateFormat.format(new Date()));
        //Προσθέτουμε έναν listener και ελέγχουμε όταν χάνει το focus αν η ημερομηνία έχει σωστή μορφή
        frmTxtDateTo.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if(!(new DateValidator(dateFormatPattern).isValid(frmTxtDateTo.getText())))
                {
                    JOptionPane.showConfirmDialog(null, "Η ημερομηνία δεν έχει σωστή μορφή", "Λάθος ημερομηνία", JOptionPane.PLAIN_MESSAGE);
                    frmTxtDateTo.setText(simpleDateFormat.format(new Date()));
                }
            }
        });
    }

    //γεμίζουμε το combo με τα ονόματα των χωρών
    private void populateCountryComboBox() {
        //φέρνουμε τις χώρες απο την βάση
        List<Country> countries = db.GetCountriesListFromDb();
        //προσθέτουμε μία επιλογή που ερμηνεύεται ως καμία χώρα
        cmbCountry.addItem("");
        
        //προσθέτουμε μια προς μια τις χώρες
        for(Country country : countries)
            cmbCountry.addItem(country.getName());
        
        //φτιάχνουμε έναν listener που ενεργοποιείται όταν κάποιος επιλέγει μια χώρα
        cmbCountry.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                onCountryChange();
            }
        });
             
    }
    
    private void resetCoviddataGrids() {
        //αρχικοποιούμε το grid με τα επιβεβαιωμένα κρούσματα
        DefaultTableModel modelConfirmed = new DefaultTableModel();
        modelConfirmed.setColumnIdentifiers(new String[]{"Ημερομηνία", "Κρούσματα","Συνολικά"});
        modelConfirmed.addRow(new String[]{"","",""});
        tblConfirmed.setModel(modelConfirmed);
        
        //αρχικοποιούμε το grid με όσους ανάρρωσαν
        DefaultTableModel modelRecovered = new DefaultTableModel();
        modelRecovered.setColumnIdentifiers(new String[]{"Ημερομηνία", "Ανάρρωσαν","Συνολικά"});
        modelRecovered.addRow(new String[]{"","",""});
        tblRecovered.setModel(modelRecovered);
        
        //αρχικοποιούμε το grid με τους θανάτους
        DefaultTableModel modelDeaths = new DefaultTableModel();
        modelDeaths.setColumnIdentifiers(new String[]{"Ημερομηνία", "Θάνατοι","Συνολικά"});
        modelDeaths.addRow(new String[]{"","",""});
        tblDeaths.setModel(modelDeaths);
    }
    
    //εκτελείται όταν επιλέγουμε χώρα ή πατάμε το πλήκτρο Φιλτρο
    private void onCountryChange(){
        //παίρνουμε απο το combo το όνομα τις επιλεγμένης χώρας 
        selectedCountry = cmbCountry.getSelectedItem().toString();      
        
        Date dateFrom=null;
        Date dateTo=null;
        //Δίνουμε αρχικές τιμές στις ημερομηνίες απο-εώς του φίλτρου
        try {
            dateFrom = simpleDateFormat.parse(minDate);
            dateTo = new Date();
            //αν είναι επιλεγμένα τα checkboxes χρησιμοποιούμε τις ημερομηνίες των textfields
            if(chkDateFrom.isSelected())
                dateFrom = simpleDateFormat.parse(frmTxtDateFrom.getText());
            if(chkDateTo.isSelected())
                dateTo = simpleDateFormat.parse(frmTxtDateTo.getText());
        } catch (ParseException ex) {
            Logger.getLogger(FrameDataDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Προετοιμάζουμε το model για το grid των κρουσμάτων
        DefaultTableModel modelConfirmed = new DefaultTableModel();
        modelConfirmed.setColumnIdentifiers(new String[]{"Ημερομηνία", "Κρούσματα","Συνολικά"});
        //παίρνουμε τα data απο την βάση
        confirmedList = db.GetCoviddataFromDb(selectedCountry, TimeSeriesCase.CONFIRMED,dateFrom,dateTo);
        //τα προσθέτουμε στο grid
        for(Coviddata coviddata : confirmedList)
            modelConfirmed.addRow(new Object[]{simpleDateFormat.format(coviddata.getTrndate()),coviddata.getQty(),coviddata.getProodqty()});
        tblConfirmed.setModel(modelConfirmed);
        
        //Προετοιμάζουμε το model για το grid όσων ανάρρωσαν
        DefaultTableModel modelRecovered = new DefaultTableModel();
        modelRecovered.setColumnIdentifiers(new String[]{"Ημερομηνία", "Ανάρρωσαν","Συνολικά"});
        //παίρνουμε τα data απο την βάση
        recoveredList = db.GetCoviddataFromDb(selectedCountry, TimeSeriesCase.RECOVERED,dateFrom,dateTo);
        //τα προσθέτουμε στο grid
        for(Coviddata coviddata : recoveredList)
            modelRecovered.addRow(new Object[]{simpleDateFormat.format(coviddata.getTrndate()),coviddata.getQty(),coviddata.getProodqty()});
        tblRecovered.setModel(modelRecovered);
        
        //Προετοιμάζουμε το model για το grid των θανάτων
        DefaultTableModel modelDeaths = new DefaultTableModel();
        modelDeaths.setColumnIdentifiers(new String[]{"Ημερομηνία", "Θάνατοι","Συνολικά"});
        //παίρνουμε τα data απο την βάση
        deathsList = db.GetCoviddataFromDb(selectedCountry, TimeSeriesCase.DEATHS,dateFrom,dateTo);
        //τα προσθέτουμε στο grid
        for(Coviddata coviddata : deathsList)
            modelDeaths.addRow(new Object[]{simpleDateFormat.format(coviddata.getTrndate()),coviddata.getQty(),coviddata.getProodqty()});
        tblDeaths.setModel(modelDeaths);
    }    

    private void plotLineChart() {
        //Βεβαιωνόμαστε ότι ο χρήστης έχει επιλέξει μια χώρα
        if(selectedCountry==null || selectedCountry.equals("")){
            JOptionPane.showConfirmDialog(null, "Επιλέξτε πρώτα μια χώρα", "Γράφημα χώρας", JOptionPane.PLAIN_MESSAGE);
            return;
        }      
        
        //Αρχικοποιούμε τις λίστες με τα δεδομένα covid
        String title=selectedCountry;
        List<Coviddata> confirmeddata= new ArrayList<Coviddata>();
        List<Coviddata> recovereddata= new ArrayList<Coviddata>();
        List<Coviddata> deathsdata= new ArrayList<Coviddata>();
        
        //Αν ο χρήστης έχει επιλέξει να εμφανιστεί η κατηγορία, φορτώνουμε τα 
        //data απο τις λίστες της κλάσης που είναι ήδη στο grid
        if(chkConfirmed.isSelected())
            confirmeddata= confirmedList;
        if(chkRecovered.isSelected())
            recovereddata= recoveredList;
        if(chkDeaths.isSelected())
            deathsdata= deathsList;
        
        //Φορτώνουμε το dto που θα χρησιμοποιήσουμε για με μεταφορά δεδομένων
        //και των επιλογών του χρήστη στο γράγημα.
        PlottingData plottingData = new PlottingData
            (title,confirmeddata,recovereddata,deathsdata,chkDailyData.isSelected(),chkAccumulativeData.isSelected());

        //Φτιάχνουμε το frame με το γράφημα
        if (chartFrame == null){
             chartFrame = new FramePlotLineChart();
        }
        //Περνάμε τα δεδομένα και το εμφανίζουμαι
        chartFrame.CalculatePlot(plottingData);
        chartFrame.setVisible(true);
        
    }

    private void showMap() {
        //Βεβαιωνόμαστε ότι ο χρήστης έχει επιλέξει μια χώρα
        if(selectedCountry==null || selectedCountry.equals("")){
            JOptionPane.showConfirmDialog(null, "Επιλέξτε πρώτα μια χώρα", "Χάρτης χώρας", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        //Πέρνουμε τα τελευταία covid data της χώρας απο το API
        MappingData map = api.GetCountrysMapData(selectedCountry);
        //Πέρνουμε τις συντεταγμένες της χώρας απο την βάση
        Country country = db.GetCountryFromDb(selectedCountry);
        map.setLat(country.getLat());
        map.setLong1(country.getLong1());
        ShowMap showMap = new ShowMap();
        showMap.Display(map);
    }

    //Διαγραφή των covid δεδομένων της χώρας με την σύμφωνη γνώμη του χρήστη
    private void removeCountrysCoviddata() {
        //Βεβαιωνόμαστε ότι ο χρήστης έχει επιλέξει μια χώρα
        if(selectedCountry==null || selectedCountry.equals("")){
            JOptionPane.showConfirmDialog(null, "Επιλέξτε πρώτα μια χώρα", "Διαγραφή δεδομένων", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        Country country = db.GetCountryFromDb(selectedCountry);
        db.AskAndDeleteCovidData(country);
    }
    
}
