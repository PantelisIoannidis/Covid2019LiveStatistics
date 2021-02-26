/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.Country;

/**
 *
 * @author Pantelis Ioannidis
 */
// Κρατάμε τα στοιχεία που χρειάζονται για να τυπώσουμε μια χώρα
public class MappingData{
    public class Data {
        String location;

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

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }
        int confirmed;
        int deaths;
        int recovered;
        int active;
    }
    Data data;
    String dt;
    int ts;
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
    
    
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getTs() {
        return ts;
    }

    public void setTs(int ts) {
        this.ts = ts;
    }
}
