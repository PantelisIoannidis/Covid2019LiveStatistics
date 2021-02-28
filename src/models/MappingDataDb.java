/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Pantelis Ioannidis
 */
// Κρατάμε τα στοιχεία που χρειάζονται για να αποτυπώσουμε μια χώρα στον χάρτη
public class MappingDataDb {
     String location;
     int confirmed;
     int deaths;
     int recovered;
     double lat;
     double long1;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLong1() {
        return long1;
    }

    public void setLong1(double long1) {
        this.long1 = long1;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
}
