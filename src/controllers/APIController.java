package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.util.Set;
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
        //Φτιάχνουμε το request για την λειτουργία που μας ενδιαφέρει
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

            //Προσθεσε τα data της πολιτείας στην χώρα. Κάνουμε αυτή την διαδικασία για να έχουμε μόνο
            //μια καταχώρηση ανα χώρα
            private CountryTimeSeries CombineStates(CountryTimeSeries ltm1, CountryTimeSeries ltm2) {
                //Κράτησε τα lat και Long της χώρας και όχι της πολιτείας
                if (ltm2.state.equals("")) {
                    ltm1.Lat = ltm2.Lat;
                    ltm2.Long = ltm2.Long;
                }
                //Διέτρεξε όλα τα data της λίστας2
                for (Map.Entry<Date, Integer> tm2 : ltm2.data.entrySet()) {
                    //Αν η ημερομηνία υπάρχει και στις δύο λίστες πρόσθεσε τις και βάλτην στην λίστα1
                    if (ltm1.data.containsKey(tm2.getKey())) {
                        ltm1.data.put(tm2.getKey(), ltm1.data.get(tm2.getKey()) + tm2.getValue());
                    } else {
                        //Αν δεν υπάρχει πρόσθεσε την καταχώρηση της λίστας2 στην λίστα1
                        ltm1.data.put(tm2.getKey(), tm2.getValue());
                    }
                }
                return ltm1;
            }

        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");

        //Φτιάχνουμε την βοηθητική κλάση
        ConvertMethods cm = new ConvertMethods();

        //Η λίστα με τα covid data της κατηγορίας
        List<CountryTimeSeries> countryTimeSeries = new ArrayList<>();
        //Παίρνουμε απο το API τα timeseries μιας κατηγορίας
        String data = cm.GetTimeSeriesJson(tmCase);
        //Το αντικείμενο στο οποίο θα αποθηκεύσουμε τα δεδομένα απο το API
        CountryTimeSeries country= new CountryTimeSeries();

        //Φτιάχνουμε τον parser
        JsonParser parser = new JsonParser();
        //Περνάμε τα data απο τον parser
        JsonElement jsonTree = parser.parse(data);
        if (jsonTree.isJsonObject()) {
            //έξαγουμε απο το jsontree έναν πίνακα με τις χώρες 
//            JsonObject jsonObject = jsonTree.getAsJsonObject();
//            JsonElement caseData = jsonObject.get(tmCase.name().toLowerCase());
//            JsonArray countriesList1 = caseData.getAsJsonArray();

            JsonArray countriesList = jsonTree.getAsJsonObject()
                    .get(tmCase.name().toLowerCase())
                    .getAsJsonArray();
            boolean skipCountry = false;
            //Διατρέχουμε όλες τις χώρες για την συγκεκριμένη κατηγορία
            for (int i = 0; i < countriesList.size(); i++) {
                skipCountry = false;
                //παίρνουμε όλα τα fields του json object της χώρας και τα εξετάζουμε ένα προς ένα
                JsonObject countryData = countriesList.get(i).getAsJsonObject();
                //Δημιουργούμε ένα αντικείμενο χώρα για να το γεμίσουμε με δεδομένα
                country = new CountryTimeSeries();
                for (int j = 0; j < countryData.size(); j++) {
                    
                    //μεταφέρουμε τα δεδομένα απο τα fields του json object σε μια map δομή δεδομένων
                    Set<Map.Entry<String, JsonElement>> elements = countryData.entrySet();
                    //Διατρέχουμε τα δεδομένα και να βάζουμε στην κλάση χώρα που δημιουργήσαμε προηγουμένος
                    for (Map.Entry<String, JsonElement> item : elements) {
                        String key = item.getKey().toString();
                        String value = item.getValue().toString();
                        if (key.equals("Province/State")) {
                            country.state = value.replace("\"", "");
                        } else if (key.equals("Country/Region")) {
                            country.country = value.replace("\"", "");
                            if (!CheckIfCountryIsInList(country.country)) {
                                skipCountry = true;
                            }
                        } else if (key.equals("Lat")) {
                            country.Lat = value;
                        } else if (key.equals("Long")) {
                            country.Long = value;
                        } else {
                            int val = Integer.parseInt(value);
                            Date date = null;
                            try {
                                date = formatter.parse(key);
                            } catch (ParseException ex) {
                                Logger.getLogger(APIController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            country.data.put(date, val);
                        }
                        if (skipCountry) {
                            break;
                        }
                    }
                    if (skipCountry) {
                        continue;
                    }

                }
                //Παρακάτω προσθέτουμε τα data της πολιτείας στην χώρα
                CountryTimeSeries alreadyInList = null;
                //ελέγχουμε την λίστα με τα αντικείμενα CountryTimeSeries που έχουμε μέχρι στιγμής
                for (CountryTimeSeries c : countryTimeSeries) //αν υπάρχει η χώρα ήδη στην λίστα σημαίνει πως μια απο τις δύο εγγραφές είναι
                //πολιτεία και θα πρέπει να συγχωνευτούν μεταξύ τους
                {
                    if (c.country.equals(country.country)) {
                        alreadyInList = c;
                        //σβήνουμε απο την λίστα την παλιά εγγραφή
                        countryTimeSeries.remove(alreadyInList);
                        //στην νέα εγγραφή προσθέτουμε τα στοιχεία της παλιάς
                        cm.CombineStates(country, alreadyInList);
                        break;
                    }
                }

                //Το προσθέτουμε στην λίστα με τα covid data της κατηγορίας
                countryTimeSeries.add(country);
            }

        }
        //Επιστρέφουμε την λίστα με τα covid data
        return countryTimeSeries;
    }

    public boolean CheckIfCountryIsInList(String name) {
        return (name.equals("Greece") || name.equals("Germany") || name.equals("Italy"));
    }
}
