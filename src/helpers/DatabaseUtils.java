/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;

/**
 *
 * @author Pantelis Ioannidis
 */
//Δημιουργία Derby database αν δεν υπάρχει κατα την πρώτη εκτέλεση του προγράμματος
public class DatabaseUtils {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/covid19statsdb;create=true";
    private static final String USERNAME = "plh24";
    private static final String PASSWORD = "plh24";    
    
    //Εκκίνηση του javadb
    public void StartServer(){
        try {
            NetworkServerControl serverControl = new NetworkServerControl(InetAddress.getLoopbackAddress(),1527,USERNAME,PASSWORD); 
            serverControl.start(null);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public void InitializeDatabase () {
        
        Connection connection=null;
        Statement statement=null;
        String SqlStatement;
        
        //Εκκίνηση του javadb
        StartServer();
        
        try{

           connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
           statement = connection.createStatement();

            // Δημιουργία του πίνακα COUNTRY
            SqlStatement = "create table COUNTRY\n" +
                        "(\n" +
                        "	COUNTRY INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY constraint COUNTRY_PK primary key,\n" +
                        "	NAME VARCHAR(100) NOT NULL constraint COUNTRY_NAME_UINDEX unique,\n" +
                        "    LAT DOUBLE,\n" +
                        "    LONG DOUBLE\n" +
                        ")";
            statement.executeUpdate(SqlStatement);
           
           // Δημιουργία του πίνακα COVIDDATA
            SqlStatement = "create table COVIDDATA\n" +
                            "(\n" +
                            "	COVIDDATA INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY \n" +
                            "	        constraint COVIDDATA_PK	primary key,\n" +
                            "	COUNTRY INTEGER NOT NULL\n" +
                            "		constraint COVIDDATA_COUNTRY_FK	references COUNTRY,\n" +
                            "	TRNDATE DATE NOT NULL,\n" +
                            "	DATAKIND SMALLINT NOT NULL,\n" +
                            "	QTY INTEGER NOT NULL,\n" +
                            "	PROODQTY INTEGER NOT NULL\n" +
                            ")";
            statement.executeUpdate(SqlStatement);
            
            SqlStatement = "alter table COVIDDATA\n" +
                            "add constraint COVIDDATA_UINDEX unique (COUNTRY,TRNDATE,DATAKIND)";
            statement.executeUpdate(SqlStatement);
        //Εδώ πιάνουμε γενικά τα exception που σχετίζονται με τον sql server    
        }catch(SQLException ex){
            //H database υπάρχει. Δεν χρειάζεται να γίνεi τίποτα. Αγνόησε το exception
            if(ex.getSQLState().toUpperCase().equals("X0Y32".toUpperCase())) {
                               
            } //Δεν έχει ξεκινήσει το JavaDB Server
            else if (ex.getSQLState().toUpperCase().equals("08001".toUpperCase())) {
                 JOptionPane.showMessageDialog(null, "Αποτυχία σύνδεσης με την βάση. Ελέγξτε αν έχει ξεκινήσει ο Java DB server.", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            //τα υπόλοιπα sql server exception που δεν πιάσαμε στις προηγούμενες περιπτώσεις
            else {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        //Εδώ πιάνουμε γενικά τα exception που δεν σχετίζονται με τον sql server και ενημερώνουμε τον χρήστη   
        }catch(Exception ex){      
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally{
            //Κλείνουμε το connection με την βάση
            try{
              if(statement!=null && connection!=null)
                 connection.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
