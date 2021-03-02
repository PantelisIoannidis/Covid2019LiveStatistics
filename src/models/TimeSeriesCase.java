package models;

/**
 *
 * @author Pantelis Ioannidis
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

