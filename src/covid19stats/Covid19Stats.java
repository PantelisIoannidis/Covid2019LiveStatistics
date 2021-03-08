package covid19stats;

import helpers.DatabaseUtils;
import views.*;

/**
 *
 * @author Pantelis Ioannidis
 * @author Nick Dimitrakarakos
 * @author Efthimios Georgakis 
 * @author Aris Dimakakos
 */

 //Βασική κλάση και σημείο εισόδου της εφαρμογής 
public class Covid19Stats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
       //Σύνδεση με τον database server και δημιουργία βάσης αν δεν υπάρχει
       DatabaseUtils dbUtils = new DatabaseUtils();
       dbUtils.InitializeDatabase();
       //Θέτουμε το look and feel της εφαρμογής
       setLookAndFeel("Metal");
       //Φτιάχνουμε την κεντρική οθόνη και την εμφανίζουμε
       MainFrame mainFrame = new MainFrame();
       mainFrame.setVisible(true);
    }
    
    //Θέτουμε το look and feel της εφαρμογής
    //Αποδεκτά : Metal, Nimbus, CDE/Motif, Windows, Windows Classic
    public static void setLookAndFeel(String lookAndFeel) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
