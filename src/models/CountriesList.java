/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panti
 */
// Αποθηκεύουμε τα δεδομένα απο το endpoint "countries"
public class CountriesList {
    public List<String> countries;
    public String dt;
    public int ts;
    
    public CountriesList(){
        countries = new ArrayList<>();
    }
}
