/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author panti
 */
//Βασική κλάση όπου αποθηκεύουμε τα timeseries μιας κατηγορίας μιας χώρας
public class CountryTimeSeries {
    public String state;
    public String country;
    public String Lat;
    public String Long;
    public Map<Date,Integer> data;
    
    public CountryTimeSeries(){
        data = new TreeMap<>(comp);
    }
    
    // Θέλουμε να ταξινομούμε τα δεδομένα των TreeMap με βάση την ημερομηνία και το State
    Comparator comp = new Comparator<Date>(){
        @Override
        public int compare(Date t, Date t1) {
            return t.compareTo(t1);
        }
    };
    
}
