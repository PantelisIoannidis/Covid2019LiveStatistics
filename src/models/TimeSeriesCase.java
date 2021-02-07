/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author panti
 */
// Enum type με τον οποίο διακρινουμε της κατηγοριες των timeseries
public enum TimeSeriesCase {
    CONFIRMED("confirmed"),
    DEATHS("deaths"),
    RECOVERED("recovered") ;
    private String restPoint;
    private TimeSeriesCase(String point) {
        this.restPoint = point;
    }

    //Override που μας επιστρέφει λεκτικό την επιλεγμένης τιμής
    @Override
    public String toString(){
        return restPoint;
    }
}

