/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.TimeSeriesCase;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 */
public class APIController {

    //Κλήση του Rest API
    private String BaseCall(String endpoint, String... parameters) {
        //Προσθέτουμε στην url της υπηρεσίας την λειτουργία που μας ενδιαφέρει
        String urlToCall = "https://covid2019-api.herokuapp.com/" + endpoint;
        //Προσθέτουμε και τις παραμέτρους
        for (String parameter : parameters) {
            urlToCall += "/" + parameter;
        }
        //Φτιάχνουμε τον client για την υπηρεσία
        OkHttpClient client = new OkHttpClient();
        //Δτιάχνουμε το request για την λειτουργία που μας ενδιαφέρει
        Request request = new Request.Builder().url(urlToCall).build();
        //Κάνουμε την κλήση του REST API
        try (Response response = client.newCall(request).execute()) {
            //Αν η κληση ήταν επιτυχημένη, μας επιστρέφει το json ως string
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }   
    
    //Ζήτα απο το API την λίστα των χωρών μαζί με γενικά στατιστικά
    public MappingData GetCountrysMapData(String name) {
        // φτιάχνουμε το endpoint και κάνουμε την κλήση του API
        String restPoint = "v2/country/"+name;
        String stringResults = BaseCall(restPoint);
        //Μετατρέπουμε το jsonstring σε αντικείμενο με την βοήθεια τoυ gson 
        MappingData mp = new Gson().fromJson(stringResults, MappingData.class);
        return mp;
    }
    
    //Ζητάει απο το API μια κατηγορίας timeseries
    public List<CountryTimeSeries> GetTimeSeries(TimeSeriesCase tmCase) {
        
        //inner class με χρήσημες μεθόδος για την μετατροπή του jsonstring σε λίστα αντικειμένων
        class ConvertMethods {
            //Ζητά απο το API τα timeseries μιας κατηγορίας
            private String GetTimeSeriesJson(TimeSeriesCase tmCase) {
                String restPoint = "timeseries/";
                String stringResults = BaseCall(restPoint + tmCase.toString());
                return stringResults;
            }
            //Σπάει το jsonString του timeseries σε λίστα απο jsonstrings ανα χώρα
            private ArrayList<String> ExtractEachCountryString(String text) {
                ArrayList<String> countries = new ArrayList<String>();
                int left = text.indexOf("[");
                int right = text.indexOf("]");
                text = text.substring(left, right);
                //παίρνουμε κάθε substring μεταξύ { και }
                while (text.length() > 1) {
                    left = text.indexOf("{");
                    right = text.indexOf("}");
                    if (left < 0 || right < 0) {
                        break;
                    }
                    String cnt = text.substring(left, right + 1);
                    //αποθηκεύουμε κάθε substring στην λίστα με τα jsonsubstrings
                    countries.add(cnt);
                    text = text.substring(right + 1, text.length() - 1);
                }
                return countries;
            }

            //Μετατρέπουμε το jsonstring σε CountryTimeSeries αντικείμενο
            private CountryTimeSeries ConvertDataToCountry(CountryTimeSeries country, String data, TimeSeriesCase tmCase) {
                Gson gson = new Gson();
                Type keyPair = new TypeToken<Map<String, String>>() {}.getType();
                //To gson μετατρέπει την λίστα των jsonstring σε ζεύγος κλειδιού τιμών
                Map<String, String> countryData = gson.fromJson(data, keyPair);
                //Παίρνουμε τα δεδομένα απο το ζεύγος τιμών και τα αντιστοιχούμε στο αντικείμενο CountryTimeSeries 
                for (Map.Entry item : countryData.entrySet()) {
                    String key = item.getKey().toString();
                    String value = item.getValue().toString();
                    if (key.equals("Province/State")) {
                        country.state = value;
                    } else
                    if (key.equals("Country/Region")) {
                        country.country = value;
                    } else if (key.equals("Lat")) {
                            country.Lat = value;
                    } else if (key.equals("Long")) {
                            country.Long = value;
                    } else {
                        int val = Integer.parseInt(value);
                        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yy");
                        Date date = null;//Date.from(Instant.now());
                        try {
                            date = formatter1.parse(key);
                        } catch (ParseException ex) {
                            Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        country.data.put(date, val);
                    }
                }
                return country;
            }
            //Προσθεσε τα data της πολιτείας στην χώρα. Κάνουμε αυτή την διαδικασία για να έχουμε μόνο
            //μια καταχώρηση ανα χώρα
            private CountryTimeSeries CombineStates(CountryTimeSeries ltm1,CountryTimeSeries ltm2){
                //Κράτησε τα lat και Long της χώρας και όχι της πολιτείας
                if(ltm2.state.equals("")){
                    ltm1.Lat=ltm2.Lat;
                    ltm2.Long=ltm2.Long;
                }
                //Διέτρεξε όλα τα data της λίστας2
                for(Map.Entry<Date,Integer> tm2 : ltm2.data.entrySet()){
                    //Αν η ημερομηνία υπάρχει και στις δύο λίστες πρόσθεσε τις και βάλτην στην λίστα1
                    if(ltm1.data.containsKey(tm2.getKey())){
                        ltm1.data.put(tm2.getKey(), ltm1.data.get(tm2.getKey())+tm2.getValue()); 
                    } else {
                        //Αν δεν υπάρχει πρόσθεσε την καταχώρηση της λίστας2 στην λίστα1
                        ltm1.data.put(tm2.getKey(), tm2.getValue());
                    }
                }
                return ltm1;
            }
            
        }
        //Φτιάχνουμε την βοηθητική κλάση
        ConvertMethods cm = new ConvertMethods();
        
        //Η λίστα με τα covid data της κατηγορίας
        List<CountryTimeSeries> countryTimeSeries = new ArrayList<>();
        //Παίρνουμε απο το API τα timeseries μιας κατηγορίας
        String data = cm.GetTimeSeriesJson(tmCase);
        //Το αντικείμενο στο οποίο θα αποθηκεύσουμε τα δεδομένα απο το API
        CountryTimeSeries country;
        //Σπάμε το jsonString του timeseries σε λίστα απο jsonstrings ανα χώρα
        for (String singleCountryString : cm.ExtractEachCountryString(data)) {
            country = new CountryTimeSeries();
            //Μετατρέπουμε το jsonstring σε CountryTimeSeries αντικείμενο
            CountryTimeSeries item = cm.ConvertDataToCountry(country, singleCountryString, tmCase);
            
            //Παρακάτω προσθέτουμε τα data της πολιτείας στην χώρα
            CountryTimeSeries alreadyInList = null;
            //ελέγχουμε την λίστα με τα αντικείμενα CountryTimeSeries που έχουμε μέχρι στιγμής
            for(CountryTimeSeries c : countryTimeSeries)
                //αν υπάρχει η χώρα ήδη στην λίστα σημαίνει πως μια απο τις δύο εγγραφές είναι
                //πολιτεία και θα πρέπει να συγχωνευτούν μεταξύ τους
                if(c.country.equals(item.country)){
                    alreadyInList=c;
                    //σβήνουμε απο την λίστα την παλιά εγγραφή
                    countryTimeSeries.remove(alreadyInList);
                    //στην νέα εγγραφή προσθέτουμε τα στοιχεία της παλιάς
                    cm.CombineStates(item,alreadyInList);
                    break;
                }
            
            
            //Το προσθέτουμε στην λίστα με τα covid data της κατηγορίας
            countryTimeSeries.add(item);
        }      
        
        //Επιστρέφουμε την λίστα με τα covid data
        return countryTimeSeries;
    }

}
