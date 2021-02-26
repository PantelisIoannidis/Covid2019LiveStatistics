/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Coviddata;
import java.util.List;

/**
 *
 * @author Pantelis Ioannidis
 */
//DTO κλάση για να περάσουμε δεδομένα στην γραφική παράσταση
public class PlottingData {
    String title="";
    //Οι λίστες των 3 κατηγοριών με τα covid δεδομένα
    List<Coviddata> confirmedList;
    List<Coviddata> recoveredList;
    List<Coviddata> deathsList;
    //Επέλεξε αν τα καθημερινά δεδομένα θα απεικονιστούν
    boolean showDailyData=false;
    //Επέλεξε αν τα σωρευτηκά δεδομένα θα απεικονιστούν
    boolean showAccumulativeData=false;
    
    public PlottingData(String title , 
            List<Coviddata> confirmedList,
            List<Coviddata> recoveredList,
            List<Coviddata> deathsList,
            boolean showDailyData,
            boolean showAccumulativeData){
        this.title =title;
        this.confirmedList = confirmedList;
        this.recoveredList = recoveredList;
        this.deathsList = deathsList;
        this.showDailyData = showDailyData;
        this.showAccumulativeData = showAccumulativeData;
    }
    
    public List<Coviddata> getConfirmedList(){
        return confirmedList;
    }
    public List<Coviddata> getRecoveredList(){
        return recoveredList;
    }
    public List<Coviddata> getDeathsList(){
        return deathsList;
    }
    public boolean getShowDailyData(){
        return showDailyData;
    }
    public boolean getShowAccumulativeData(){
        return showAccumulativeData;
    }
    public String getTitle(){
        return title;
    }
}
