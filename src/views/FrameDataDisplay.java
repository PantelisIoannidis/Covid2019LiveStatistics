/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.toedter.calendar.IDateEditor;
import controllers.APIController;
import controllers.DbOperations;
import entities.Country;
import entities.Coviddata;
import java.awt.Container;
import java.awt.Image;
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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import models.MappingDataDb;
import models.PlottingData;
import models.TimeSeriesCase;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 * @author Efthimios Georgakis 
 * @author Aris Dimakakos
 */

// R3 Προβολή δεδομένων Covid19 ανά χώρα
public class FrameDataDisplay extends javax.swing.JFrame {

    /**
     * Creates new form FrameDataDisplay
     */
    DbOperations db;
    APIController api;
    static final String dateFormatPattern = "dd/MM/yyyy";
    static final Date minDate = new GregorianCalendar(2019, 0, 1).getTime();
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
        setIconImage();
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
        chkDateFrom = new javax.swing.JCheckBox();
        chkDateTo = new javax.swing.JCheckBox();
        btnFilter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDeleteData = new javax.swing.JButton();
        frmTxtDateFrom = new com.toedter.calendar.JDateChooser();
        frmTxtDateTo = new com.toedter.calendar.JDateChooser();
        btnShowMap = new javax.swing.JButton();
        chkConfirmed = new javax.swing.JCheckBox();
        chkRecovered = new javax.swing.JCheckBox();
        chkDeaths = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        chkDailyData = new javax.swing.JCheckBox();
        chkAccumulativeData = new javax.swing.JCheckBox();
        btnShowPlot = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblBackground = new javax.swing.JLabel();

        setTitle("Προβολή δεδομένων Covid19 ανά χώρα");
        setMaximumSize(new java.awt.Dimension(900, 690));
        setMinimumSize(new java.awt.Dimension(900, 690));
        setPreferredSize(new java.awt.Dimension(880, 690));
        setResizable(false);
        getContentPane().setLayout(null);

        getContentPane().add(cmbCountry);
        cmbCountry.setBounds(39, 30, 195, 26);

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

        getContentPane().add(tabsCases);
        tabsCases.setBounds(0, 70, 859, 400);

        chkDateFrom.setText("Απο");
        chkDateFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDateFromActionPerformed(evt);
            }
        });
        getContentPane().add(chkDateFrom);
        chkDateFrom.setBounds(436, 6, 60, 24);

        chkDateTo.setText("Εως");
        chkDateTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDateToActionPerformed(evt);
            }
        });
        getContentPane().add(chkDateTo);
        chkDateTo.setBounds(604, 6, 60, 24);

        btnFilter.setText("Φίλτρο");
        btnFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterActionPerformed(evt);
            }
        });
        getContentPane().add(btnFilter);
        btnFilter.setBounds(766, 30, 90, 32);

        jLabel1.setText("Επιλογή χώρας");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 10, 180, 16);

        jLabel2.setText("Ημερομηνία");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(350, 30, 90, 16);

        btnDeleteData.setText("Διαγραφή δεδομένων");
        btnDeleteData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeleteData);
        btnDeleteData.setBounds(100, 580, 250, 32);

        frmTxtDateFrom.setDateFormatString("dd/MM/yyy");
        getContentPane().add(frmTxtDateFrom);
        frmTxtDateFrom.setBounds(436, 30, 150, 29);

        frmTxtDateTo.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(frmTxtDateTo);
        frmTxtDateTo.setBounds(604, 30, 150, 29);

        btnShowMap.setText("Προβολή σε χάρτη");
        btnShowMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMapActionPerformed(evt);
            }
        });
        getContentPane().add(btnShowMap);
        btnShowMap.setBounds(100, 520, 250, 32);

        chkConfirmed.setSelected(true);
        chkConfirmed.setText("Επιβεβαιωμένα");
        getContentPane().add(chkConfirmed);
        chkConfirmed.setBounds(560, 530, 120, 24);

        chkRecovered.setSelected(true);
        chkRecovered.setText("Ανάρρωσαν");
        getContentPane().add(chkRecovered);
        chkRecovered.setBounds(560, 550, 120, 24);

        chkDeaths.setSelected(true);
        chkDeaths.setText("Θανατοι");
        getContentPane().add(chkDeaths);
        chkDeaths.setBounds(560, 580, 120, 24);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(725, 530, 130, 10);

        jLabel3.setText("Δεδομένα");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(740, 510, 110, 16);

        chkDailyData.setSelected(true);
        chkDailyData.setText("Καθημερινά");
        getContentPane().add(chkDailyData);
        chkDailyData.setBounds(730, 540, 120, 24);

        chkAccumulativeData.setText("Σωρευτικά");
        getContentPane().add(chkAccumulativeData);
        chkAccumulativeData.setBounds(730, 570, 120, 20);

        btnShowPlot.setText("Προβολή σε διάγραμμα");
        btnShowPlot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPlotActionPerformed(evt);
            }
        });
        getContentPane().add(btnShowPlot);
        btnShowPlot.setBounds(570, 610, 250, 32);

        jLabel5.setText("Επιλογές γραφήματος");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(560, 480, 290, 16);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(560, 500, 300, 2);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/covidwallpaper.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1000, 667);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkDateToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDateToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDateToActionPerformed

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
    private com.toedter.calendar.JDateChooser frmTxtDateFrom;
    private com.toedter.calendar.JDateChooser frmTxtDateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JPanel tabConfirmed;
    private javax.swing.JPanel tabDeaths;
    private javax.swing.JPanel tabRecovered;
    private javax.swing.JTabbedPane tabsCases;
    private javax.swing.JTable tblConfirmed;
    private javax.swing.JTable tblDeaths;
    private javax.swing.JTable tblRecovered;
    // End of variables declaration//GEN-END:variables

    public void populateComponents() {
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
        //Πέρνουμε την τελική ημερομηνία και ανάλογα με την παράμετρο θα αφαιρούμε χρόνο
        Date dateFrom = frmTxtDateTo.getDate();
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
        frmTxtDateFrom.setDate(dateFrom);
        chkDateFrom.setSelected(true);
        chkDateTo.setSelected(true);
        onCountryChange();
    }
    
    private void datePopupMenu(){
        //Φτιάχνουμε Popup menu που βοηθάει στην επιλογή ημερομηνίων για την πρώτη ημερομηνία
        popupmenu = new JPopupMenu("");  
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
        frmTxtDateFrom.setDate(minDate);  
        
        //Listener για το Popup menu που βοηθάει στην επιλογή ημερομηνίων
        frmTxtDateFrom.getDateEditor().getUiComponent().addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent e) {              
                 popupmenu.show(frmTxtDateFrom ,0,20 );
            }   
        });

        //Βάζουμε την σημερινή ημερομηνία για το εώς
        frmTxtDateTo.setDate(new Date());
 
    }

    //γεμίζουμε το combo με τα ονόματα των χωρών
    private void populateCountryComboBox() {
        //Καθαρίζουμε το combo
        if(cmbCountry.getSelectedIndex()!=-1){
            cmbCountry.setModel(new DefaultComboBoxModel());
        }
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
        
        dateFrom = minDate;
        dateTo = new Date();
        //αν είναι επιλεγμένα τα checkboxes χρησιμοποιούμε τις ημερομηνίες των textfields
        if(chkDateFrom.isSelected())
            dateFrom = frmTxtDateFrom.getDate();
        if(chkDateTo.isSelected())
            dateTo = frmTxtDateTo.getDate();
        
        
        
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
        //Ο χρήστης δεν μπορεί να μην επιλέξει τουλάχιστον ένα απο τα ημερήσια και σωρευτικά δεδομένα
        if(!chkDailyData.isSelected() && !chkAccumulativeData.isSelected()){
            JOptionPane.showConfirmDialog(null, "Πρέπει να επιλέξετε τουλάχιστον ένα απο τα Καθημερινά/Σωρευτικά δεδομένα", "Γράφημα χώρας", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        //Ο χρήστης δεν μπορεί να μην επιλέξει τουλάχιστον ένα τύπο δεδομένων απο τα Επιβεβαιωμένα/Ανάρρωσαν/Θανατοι
        if(!chkConfirmed.isSelected() && !chkRecovered.isSelected() && !chkDeaths.isSelected()){
            JOptionPane.showConfirmDialog(null, "Πρέπει να επιλέξετε τουλάχιστον μια κατηγορία δεδομένων", "Γράφημα χώρας", JOptionPane.PLAIN_MESSAGE);
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
            (title,confirmeddata,recovereddata,deathsdata,
                    chkDailyData.isSelected(),chkAccumulativeData.isSelected());

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
        //Πέρνουμε τα τελευταία covid data της χώρας απο την βάση
        MappingDataDb map = db.GetCountrysMapData(selectedCountry);
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
        //Βρές την χώρα και αν ο χρήστης το επιβεβαιώσει διέγραψε τα covid data απο την βάση
        Country country = db.GetCountryFromDb(selectedCountry);
        boolean userReply = db.AskAndDeleteCovidData(country);
        if (userReply){
            //db.DeleteCountry(country); //todo:στην εκφώνηση δεν αναφέρεται η διαγραφή χώρας
            populateComponents();
        }
    }
    
    //Το Εικονίδιο στην γωνία του παραθύρου
    private void setIconImage() {
        Image image = new ImageIcon(this.getClass().getResource("/resources/covid-19.png")).getImage();
        this.setIconImage(image);
    }
    
}
