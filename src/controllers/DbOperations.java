package controllers;

import java.util.List;
import java.util.stream.Collectors;
import models.CountryTimeSeries;
import models.TimeSeriesCase;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 * @author Aris Dimakakos
 */
//Χρησιμοπούμαι αυτή την κλάση για πιο σύνθετες εργασίες που θέλουν συνδυάζουν την πρόσβαση στην database και το API
public class DbOperations {
    APIController api;
    DbRepository dbRepository;

    //Standard constructor
    public DbOperations() {
        this.api = new APIController();
        this.dbRepository = new DbRepository();
    }
    
    public DbOperations(APIController api, DbRepository dbOperations) {
        this.api = api;
        this.dbRepository = dbRepository;
    }
    
    //Αν για λόγους ταχύτητας ο χρήστης επιλέξει περιορισμό χωρων, επιστρέφουμε μια λίστα με μόνο 3 χώρες 
    private List<CountryTimeSeries> limitCountries(List<CountryTimeSeries> ltm){
        return ltm.stream()
                .filter(x->x.country.equals("Greece") || x.country.equals("Germany") || x.country.equals("Italy"))
                .collect(Collectors.toList());
    }
    
    //Φέρνει τις χώρες απο το API και αποθήκευσε τις στην Βάση
    public void StoreNewCountries(TimeSeriesCase tmCase, boolean limitedNumberOfCountries){
        //Πάρε δεδομένα της tmCase κατηγορίας απο το API
        List<CountryTimeSeries> ltm = api.GetTimeSeries(tmCase,limitedNumberOfCountries);
        if(limitedNumberOfCountries)
            ltm=limitCountries(ltm);
        //Αποθηκεύει τις χώρες στην βάση
        dbRepository.AddCountriesThatAreNotInDB(ltm);
    }
    
    //Φέρνει τις χρονοσειρές απο το API και αποθήκευσε τις στην Βάση
    public void StoreCovidData(TimeSeriesCase tmCase, boolean limitedNumberOfCountries){
        //Πάρε δεδομένα της tmCase κατηγορίας απο το API
        List<CountryTimeSeries> ltm = api.GetTimeSeries(tmCase,limitedNumberOfCountries);
        if(limitedNumberOfCountries)
            ltm=limitCountries(ltm);
        //Αποθηκεύει τις χώρες στην βάση
        dbRepository.AddCountriesThatAreNotInDB(ltm);
        //Αποθήκευση χρονοσειρών στην βάση δεδομένων
        dbRepository.AddTimeSeriesInDatabase(ltm, tmCase);
    }
}
