package models;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Pantelis Ioannidis
 */
//Βασική κλάση όπου αποθηκεύουμε τα timeseries μιας κατηγορίας μιας χώρας π.χ. Greece-recovered list
public class CountryTimeSeries {
    public String state;
    public String country;
    public String Lat;
    public String Long;
    public Map<Date,Integer> data;
    
    public CountryTimeSeries(){
        data = new TreeMap<>(comp);
    }
    
    // Θέλουμε να ταξινομούμε τα δεδομένα των TreeMap με βάση την ημερομηνία
    Comparator comp = new Comparator<Date>(){
        @Override
        public int compare(Date t, Date t1) {
            return t.compareTo(t1);
        }
    };
}
