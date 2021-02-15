/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Country;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.MappingData;
import models.MappingDataDb;

/**
 *
 * @author panti
 */
//Δείχνει τον χάρτη στον χρήστη
public class ShowMap {
    //Η σελίδα που δείχνουμε στον χρήστη
    final static String webpage = "src\\covid19\\stats\\mappage.html";
    //Το template της σελίδας με τις μεταβλητές προς αντικατάσταση
    final static String webPageTemplate = "src\\covid19\\stats\\maptemplate.html";
    
    //Καλεί τον default browser του συστήματος να δείξει την σελίδα του χάρτη.
    public void ShowStoredPage() {
        try {
            Runtime.getRuntime().exec("cmd /c start " + webpage);
        } catch (IOException ex) {
            Logger.getLogger(ShowMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Πέρνει μια μόνο χώρα, την κάνει λίστα και την περνάει στην Display
    public void Display(MappingDataDb data){
        ArrayList<MappingDataDb> mappingData = new ArrayList<MappingDataDb>();
        mappingData.add(data);
        Display(mappingData);
    }
    
    //Πέρνει μία mappingData λιστα και απεικονίζει τις χώρες
    public void Display(List<MappingDataDb> data){
        //Διαβάζει το html template απο τον κατάλογο του προγράμματος
        String html = ReadTemplate();
        //Μετατρέπει την λίστα των χωρών δομή location
        String json = MappingDataToJSArray(data);
        //Αντικαθιστούμε τις μεταβλητές πάνω στο template με τα δεδομένα 
        html = html.replaceFirst("/@%locationsjson%@/", json);
        html = html.replaceFirst("/@%zoomLevel%@/", "4");
        html = html.replaceFirst("/@%lat%@/", String.valueOf(data.get(0).getLat()));
        html = html.replaceFirst("/@%long%@/", String.valueOf(data.get(0).getLong1()));
        //Γράφουμε το html απο την μνήμη στον σκληρό
        WriteHtmlFile(html);
        //Δείχνουμε την σελίδα που δημιουργήθηκε
        ShowStoredPage();
    }
    
    //Διαβάζει το html template απο τον κατάλογο του προγράμματος
    private String ReadTemplate(){
        String html="";
        try {
            html = new String(Files.readAllBytes(Paths.get(webPageTemplate)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return html;
    }
    
    //Γράφει το html απο την μνήμη στον σκληρό
    private void WriteHtmlFile(String html){
        try {
            Files.write(Paths.get(webpage), html.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    //Μετατρέπει την λίστα των χωρών σε πίνακα javascript συμβατό 
    //με την δομή location της google
    private String MappingDataToJSArray(List<MappingDataDb> mappingData){
        String countriesArray="";
        for(MappingDataDb data : mappingData){
            countriesArray += String.format("['%s: %s, %s, %s',%s,%s ],\n",
                    data.getLocation(),
                    "Κρούσματα:"+data.getConfirmed(),
                    "Ανάρρωσαν:"+data.getRecovered(),
                    "Θάνατοι:"+data.getDeaths(),
                    String.valueOf(data.getLat()),
                    String.valueOf(data.getLong1()));
        }
        return countriesArray;
    }
    
}
