/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.APIController;
import controllers.DbOperations;
import entities.Country;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import models.MappingData;

/**
 *
 * @author panti
 */
public class FrameMapSelection extends javax.swing.JFrame {

    /**
     * Creates new form FrameMapSelection
     */
    DbOperations db;
    APIController api;
    
    public FrameMapSelection() {
        initComponents();
        //Φτιάχνουμε το αντικείμενο που μεσολαβεί για την επικοινωνία με την βάση
        db = new DbOperations();
        //Φτιάχνουμε το αντικείμενο που μεσολαβεί για την επικοινωνία με τo API
        api = new APIController();
        //γεμίζουμε το combo και την λίστα με τα ονόματα των χωρών
        populateCountryComboBoxAndList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbCountry = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCountries = new javax.swing.JList<>();
        btnShowMap = new javax.swing.JButton();

        setTitle("Χάρτης χωρών με δεδομένα covid19");

        jLabel1.setText("Επιλογή χώρας");

        jLabel2.setText("Υπόλοιπες χώρες στον χάρτη");

        jScrollPane1.setViewportView(lstCountries);

        btnShowMap.setText("Προβολή σε χάρτη");
        btnShowMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnShowMap)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowMap))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMapActionPerformed
        showMap();
    }//GEN-LAST:event_btnShowMapActionPerformed

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
            java.util.logging.Logger.getLogger(FrameMapSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMapSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMapSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMapSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMapSelection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShowMap;
    private javax.swing.JComboBox<String> cmbCountry;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstCountries;
    // End of variables declaration//GEN-END:variables



    //Φορτώνουμε το combo και την λιστα με τις χώρες
    private void populateCountryComboBoxAndList() {
       //φέρνουμε τις χώρες απο την βάση
        List<Country> countries = db.GetCountriesListFromDb();
        DefaultListModel listCountriesModel = new DefaultListModel();
        //προσθέτουμε μια προς μια τις χώρες
        for(Country country : countries) {
            cmbCountry.addItem(country.getName());
            listCountriesModel.addElement(country.getName());
        }
        lstCountries.setModel(listCountriesModel);
    }

    //Εμφανίζουμε τον χάρτη
    private void showMap() {
        //Δεν χρειαζόμαστε πλέον το παράθυρο επιλογής χωρών
        this.setVisible(false);
        //Η λίστα με τα ονόματα των χωρών που επιλέγει ο χρήστης
        List<String> selectedCountries = new ArrayList<String>();
        //Φορτώνουμε στην λίστα την επιλογή το combo
        selectedCountries.add(
                cmbCountry.getSelectedItem().toString());
        //Φορτώνουμε στην λίστα τις επιλογές της jlist
        selectedCountries.addAll(
            lstCountries.getSelectedValuesList());
        
        if(selectedCountries.size()<=0){
            JOptionPane.showConfirmDialog(null, "Επιλέξτε πρώτα μια χώρα", "Χάρτης χώρας", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        
        List<MappingData> mappingData = new ArrayList<MappingData>();
        //Φτιάχνουμε απο την λίστα των ονομάτων των χωρών μια λίστα mappingdata
        for(String name : selectedCountries){
            //Πέρνουμε τα τελευταία covid data της χώρας απο το API
            MappingData map = api.GetCountrysMapData(name);
            //Προσθέτουμε τις συντεταγμένες που έχουμε ήδη στην βάση
            Country country = db.GetCountryFromDb(name);
            map.setLat(country.getLat());
            map.setLong1(country.getLong1());
            
            mappingData.add(map);
        }
        //Καλούμε το παράθυρο του χάρτη και περνάμε τα δεδομένα
        ShowMap showMap = new ShowMap();
        showMap.Display(mappingData);
    }
}
