package controllers;

import entities.*;
import models.TimeSeriesCase;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import models.*;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;


/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 * @author Aris Dimakakos
 */
public class DbRepository {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    //Αρχικοποιούμε τους entity manager και entity manager factory
    public DbRepository(){
        try {
            emf = Persistence.createEntityManagerFactory("Covid19-StatsPU");
            em = emf.createEntityManager();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Διέγραψε τα coviddata της χώρας
    public void  DeleteCovidData(Country country){
        try {
            em.getTransaction().begin();
            //Πάρε τα covid data της συγκεκριμένης χώρας
            List<Coviddata> covidDataList = (List<Coviddata>)em.createQuery("SELECT c FROM Coviddata c WHERE c.country = :country",Coviddata.class)
                        .setParameter("country",country)
                        .getResultList();
            //Σβήσε ένα προς ένα όλα τα ζεύγη τιμών της χώρας
            for(Coviddata cd : covidDataList)
                em.remove(cd);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Διέγραψε τα coviddata της χώρας, εφόσον δώσει άδεια ο χρήστης
    public boolean  AskAndDeleteCovidData(Country country){
        int reply = -1;
        try {
            String title = "Διαγραφή απο την βάση";
            String message ="Σίγουρα θέλετε να διαγράψετε τα δεδομένα για την χώρα : "+country.getName();
            reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            //Αν απαντήσει ναι, διέγραψε τα covid data της χώρας
            if (reply == JOptionPane.YES_OPTION) {
                DeleteCovidData(country);
            } 
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reply == JOptionPane.YES_OPTION;
    }
    
    //Διαγραφή όλων των covidData
    public void DeleteAllCovidData(){
        //Ζητα την άδεια του χρήστη
        String title = "Διαγραφή απο την βάση";
        String message ="Είστε σίγουρος ότι θέλετε να διαγράψετε όλα τα δεδομένα των χωρών;";
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try{
                //Διέγραψε όλα τα δεδομένα απο την βάση
                em.getTransaction().begin();
                int deletedCount = em.createQuery("DELETE FROM Coviddata c").executeUpdate();
                em.getTransaction().commit();
            } 
            catch (Exception ex){
                String msg = ex.getLocalizedMessage();
                System.out.println(msg);
            }
        } 
    }
    
    //Διαγραφή της χώρα
    public void  DeleteCountry(Country country){
        try {
            em.getTransaction().begin();
            //Δες αν υπάρχουν covid data για αυτή την χώρα και φέρτα
            List<Coviddata> covidDataList = (List<Coviddata>)em.createQuery("SELECT c FROM Coviddata c WHERE c.country = :country",Coviddata.class)
                        .setParameter("country",country)
                        .getResultList();
            //Αν υπάρχουν covid data, πάρε την έγκριση του χρήστη για να διαγράψεις την χώρα
            if(covidDataList.size()>0){
                String title = "Διαγραφή απο την βάση";
                String message ="Η χώρα "+country.getName()
                        +" έχει coviddata τα οποία θα πρέπει να διαγραφούν.\n"
                        +" Να συνεχίσουμε με την διαγραφή;";
                int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    //Διέγραψε τα coviddata της χώρας
                    for(Coviddata cd : covidDataList)
                        em.remove(cd);
                    //Διέγραψε απο την βάση την χώρα
                    em.remove(country);
                } 
            } else {
                //Διέγραψε απο την βάση την χώρα
                em.remove(country);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Διαγραφή όλων των χωρών
    public void DeleteAllCountries(){
        try {
            //Ζήτα την άδεια του χρήστη
            String title = "Διαγραφή απο την βάση";
            String message ="Είστε σίγουρος ότι θέλετε να διαγράψετε όλες τις χώρες;";
            int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                //Πάρε την λίστα των χωρών
                List<Country> countries = (List<Country>)em.createNamedQuery("Country.findAll")
                    .getResultList();
                //Διέγραψε τις χώρες μια προς μια
                for(Country country : countries)
                    DeleteCountry(country);
            } 
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Διέγραψε την χώρα, εφόσον δώσει άδεια ο χρήστης
    public boolean  AskAndDeleteCountry(Country country){
        String title = "Διαγραφή απο την βάση";
        String message ="Σίγουρα θέλετε να διαγράψετε την χώρα : "+country.getName();
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            //Διέγραψε την χώρα
            DeleteCountry(country);
        } 
        return reply == JOptionPane.YES_OPTION;
    }
    
    // Δίνουμε μια λίστα με τις χρονοσειρές των χωρών και αν οι χώρες δεν υπάρχουν τις προσθέτουμε στην βάση.
    public void AddCountriesThatAreNotInDB(List<CountryTimeSeries> ltm){
        try {
            // Διαβάζουμε όλες τις καταχωρήσεις γαι την συγκεκριμένη κατηγορία δεδομένων
            for(CountryTimeSeries tm : ltm){
                //Ψάχνουμε να δούμε αν υπάρχει χώρα με αυτό το όνομα στην βάση
                List<Country> countriesInDatabase = (List<Country>)em.createNamedQuery("Country.findByName").setParameter("name", tm.country).getResultList();
                //Αν όχι την προσθέτουμε
                if (countriesInDatabase.size()<=0)
                    AddNewCountryInDb(tm);
            }
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Χρησιμοποιείται απο την AddCountriesThatAreNotInDB. 
    // Χρησιμοποιεί τα δεδομένα της χώρα απο τα timeseries και προσθέτει την χώρα στην βάση
    private void AddNewCountryInDb(CountryTimeSeries tm) {
        try {
            Country country = new Country();
            country.setLat(Double.parseDouble(tm.Lat));
            country.setLong1(Double.parseDouble(tm.Long));
            country.setName(tm.country);
            em.getTransaction().begin();
            em.persist(country);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Διαβάζουμε τα timeseries και αν η χώρα υπάρχει στην βάση προσθέτουμε τα covid data.
    public void AddTimeSeriesInDatabase(List<CountryTimeSeries> ltm, TimeSeriesCase tmcase){
        try {
        // Διαβάζουμε όλες τις καταχωρήσεις για την συγκεκριμένη κατηγορία δεδομένων
        for(CountryTimeSeries tm : ltm){
            //Ψάχνουμε να δούμε αν υπάρχει χώρα με αυτό το όνομα στην βάση
            List<Country> countriesInDatabase = (List<Country>)em.createNamedQuery("Country.findByName").setParameter("name", tm.country).getResultList();
            //Αν ναι, προσθέτουμε τα covid data για την συγκεκριμένη κατηγορία
            if (countriesInDatabase.size()>=0){
                AddCountrysDataInDatabase(countriesInDatabase.get(0),tm,tmcase);     
            }
        }
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //// Χρησιμοποιείται απο την AddTimeSeriesInDatabase.
    //// 1ο Υπολογίζει τα νέα καθημερινά κρούσματα
    //// 2ο Προσθέτει στην βάση μόνο τα νεώτερα δεδομένα
    private void AddCountrysDataInDatabase(Country country,CountryTimeSeries tm,TimeSeriesCase tmCase) {
        try {
            //Εδώ Θα αποθηκεύσουμε τα δεδομένα που είναι υποψήφια να τα προσθέσουμε στην χώρα
            ArrayList<Coviddata> covid = new ArrayList<>();
            
            //innner class που μας παρέχει την μέθοδο fillData
            class FillCovidData {
                //Χρησιμοποιούμε με την λίστα με τα ζευγάρια ημερομηνία - πλήθος απο τα timeseries 
                // της συγκεκριμένης κατηγορίας και τα αντιστοιχούμε σε λίστα απο entity classes Coviddata 
                public ArrayList<Coviddata> fillData(Map<Date, Integer> data, int kind) {
                    ArrayList<Coviddata> list = new ArrayList<>();
                    int prodq = 0;
                    for (Map.Entry<Date, Integer> cov : data.entrySet()) {
                        Coviddata coviddata = new Coviddata();
                        coviddata.setCountry(country);
                        coviddata.setDatakind((short) kind);
                        coviddata.setTrndate(cov.getKey());
                        //To API μας δίνει το σωρευτικό πλήθος
                        coviddata.setProodqty(cov.getValue());
                        //Τα νέα κρούσματα είναι το προηγούμενο πλήθος μέιον το νέο
                        coviddata.setQty(cov.getValue() - prodq);
                        list.add(coviddata);
                        prodq = cov.getValue();
                    }
                    return list;
                }
            }
            
            //Καλούμε την fillData για να μετατρέπουμε τα δεδομένα μας σε λίστα αντικειμένων
            covid.addAll(new FillCovidData().fillData(tm.data, tmCase.ordinal()));
            //Παίρνουμε την λίστα των covid δεδομένων απο την βάση, πρώτα τα πιο πρόσφατα
            List<Coviddata> cd = (List<Coviddata>)em.createQuery("SELECT c FROM Coviddata c WHERE c.country = :country AND c.datakind = :datakind order by c.trndate desc",Coviddata.class)
                    .setParameter("datakind", tmCase.ordinal())
                    .setParameter("country",country)
                    .getResultList();
            
            Date lastSavedDate = new GregorianCalendar(2000, Calendar.JANUARY, 1).getTime();  
            // Βρίσκουμε την τελευταία ημερομηνία των δεδομένων στην βάση
            if(cd.size()>0)
                    lastSavedDate = cd.get(0).getTrndate();
            
            em.getTransaction().begin();
            for(Coviddata data : covid){
                // Για κάθε ζευγάρι τιμών απο το API ελέγχουμε να δούμε αν είναι 
                // πιο πρόσφατο απο αυτά που είναι στην βάση ώστε να το προσθέσουμε
                if(lastSavedDate.compareTo(data.getTrndate())<0)
                    country.getCoviddataList().add(data);  
            }
            em.persist(country);
            em.flush();
            em.getTransaction().commit();

        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Επιστρέφει την λίστα των χωρών που υπάρχουν στην βάση
    public List<Country> GetCountriesListFromDb(){
        List<Country> countries = new ArrayList<>();
        try {
            countries = (List<Country>)em.createNamedQuery("Country.findAll")
                    .getResultList();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  countries;
    }
    
    //Επιστρέφει την χώρα με βάση το όνομα
    public Country GetCountryFromDb(String countryName){
        List<Country> countries = new ArrayList<>();
        try {
            countries = (List<Country>)em.createNamedQuery("Country.findByName")
                    .setParameter("name",countryName)
                    .getResultList();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countries.get(0);
    }
    
    //Επιστρέφει τα δεδομένα μιας χώρα που χρειάζονται για τον χάρτη
    public MappingData GetCountrysMappingData(String countryName) {
        MappingData mp = new MappingData();
        Country country = GetCountryFromDb(countryName);
        mp.setLocation(countryName);
        mp.setLat(country.getLat());
        mp.setLong1(country.getLong1());
        mp.setConfirmed(GetAccumulativeCases(countryName,TimeSeriesCase.CONFIRMED));
        mp.setDeaths(GetAccumulativeCases(countryName,TimeSeriesCase.DEATHS));
        mp.setRecovered(GetAccumulativeCases(countryName,TimeSeriesCase.RECOVERED));
        return mp;
    }
    
    //Χρησιμοποιείται απο την GetCountrysMappingData 
    //για να υπολογίσουμε το σωρευτικό άθροισμα μιας κατηγορίας μιας χώρας.
    private int GetAccumulativeCases(String countryName,TimeSeriesCase tmCase){
        int cases=0;
        try {
            //Πέρνουμε μια λίστα με την πιο πρόσφατη ημερομηνία πρώτα, όπου είναι και το σωρευτικό άθροισμα
            List<Coviddata> data = em.createQuery("SELECT d FROM Coviddata d JOIN d.country c "
                    +"WHERE c.name= :name AND d.datakind = :datakind "
                    +" order by d.trndate DESC ",Coviddata.class)
                    .setParameter("name", countryName)
                    .setParameter("datakind", (short)tmCase.ordinal())
                    .getResultList();
            if(data.size()>0)
                cases=data.get(0).getProodqty();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cases;
    }
   
    //Επιστρέφει τα covid data μιας χώρας μιας κατηγορίας ανάμεσα σε 2 ημερομηνίες
    public List<Coviddata> GetCoviddataFromDb(String countryName,TimeSeriesCase tmCase,Date dateFrom,Date dateTo){
        List<Coviddata> data = new ArrayList<Coviddata>();
        try {
            data = em.createQuery("SELECT d FROM Coviddata d JOIN d.country c "
                +"WHERE c.name= :name AND d.datakind = :datakind "
                +"AND d.trndate >= :dateFrom AND d.trndate <= :dateTo "
                +" order by d.trndate",Coviddata.class)
                .setParameter("name", countryName)
                .setParameter("datakind", (short)tmCase.ordinal())
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .getResultList();
        } catch (Exception ex) {
            Logger.getLogger(DbRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
        
}
