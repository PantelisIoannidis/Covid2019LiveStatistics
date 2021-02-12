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
 * @author panti
 */
//DTO κλάση για να περάσουμε δεδομένα στην γραφική παράσταση
public class PlottingData {
    String title="";
    List<Coviddata> confirmedList;
    List<Coviddata> recoveredList;
    List<Coviddata> deathsList;
    boolean dailyData=false;
    boolean accumulativeData=false;
    
    public PlottingData(String title , 
            List<Coviddata> confirmedList,
            List<Coviddata> recoveredList,
            List<Coviddata> deathsList,
            boolean dailyData,
            boolean accumulativeData){
        this.title =title;
        this.confirmedList = confirmedList;
        this.recoveredList = recoveredList;
        this.deathsList = deathsList;
        this.dailyData = dailyData;
        this.accumulativeData = accumulativeData;
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
    public boolean getDailyData(){
        return dailyData;
    }
    public boolean getAccumulativeData(){
        return accumulativeData;
    }
    public String getTitle(){
        return title;
    }
}
