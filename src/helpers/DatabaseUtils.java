/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Pantelis Ioannidis
 */

public class DatabaseUtils {
    private static final String PERSISTENCE_UNIT_NAME = "Covid19-StatsPU";
    private EntityManagerFactory emf;
    private EntityManager em;
    private static final String DB_URL = "jdbc:derby://localhost:1527/covid19stats;create=true";
    private static final String USER = "plh24";
    private static final String PASS = "plh24";    
    
    public boolean InitializeDatabase () {
        
        boolean errorStatus=false;
        Connection conn=null;
        Statement stmt=null;
        String SqlStatement;
        
        try{

           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           stmt = conn.createStatement();

            // Δημιουργία του πίνακα COUNTRY
            SqlStatement = "create table COUNTRY\n" +
                        "(\n" +
                        "	COUNTRY INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY constraint COUNTRY_PK primary key,\n" +
                        "	NAME VARCHAR(100) NOT NULL constraint COUNTRY_NAME_UINDEX unique,\n" +
                        "    LAT DOUBLE,\n" +
                        "    LONG DOUBLE\n" +
                        ")";
            stmt.executeUpdate(SqlStatement);
           
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
            stmt.executeUpdate(SqlStatement);
            
            SqlStatement = "alter table COVIDDATA\n" +
                            "add constraint COVIDDATA_UINDEX unique (COUNTRY,TRNDATE,DATAKIND)";
            stmt.executeUpdate(SqlStatement);
           
        }catch(SQLException ex){
            //H database υπάρχει. Δεν χρειάζεται na γίνεi τίποτα.
            if(ex.getSQLState().toUpperCase().equals("X0Y32".toUpperCase())) {
                               
            }
            else if (ex.getSQLState().toUpperCase().equals("08001".toUpperCase())) {
                 JOptionPane.showMessageDialog(null, "Αποτυχία σύνδεσης με την βάση. Ελέγξτε αν έχει ξεκινήσει ο Java DB server.", "Error", JOptionPane.ERROR_MESSAGE);
                errorStatus= true;
                System.exit(0);
            }
            else {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                errorStatus= true;
            }
            
        }catch(Exception ex){      
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           errorStatus= true;
        }finally{
            try{
              if(stmt!=null)
                 conn.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            try{
               if(conn!=null)
                  conn.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return errorStatus;
    }
}
