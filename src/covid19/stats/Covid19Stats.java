/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19.stats;

import controllers.APIController;
import controllers.DbOperations;
import helpers.DatabaseUtils;
import models.TimeSeriesCase;
import java.util.List;
import models.CountryTimeSeries;
import views.*;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 * @author Efthimios Georgakis 
 * @author Aris Dimakakos
 */
public class Covid19Stats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Αν δεν υπάρχει η βάση δημιούργησε την
        DatabaseUtils dbUtils = new DatabaseUtils();
        dbUtils.InitializeDatabase();
                
       //Φτιάχνουμε την κεντρική οθόνη και την εμφανίζουμε
       MainFrame mainFrame = new MainFrame();
       mainFrame.setVisible(true);
        
    }
    
}
