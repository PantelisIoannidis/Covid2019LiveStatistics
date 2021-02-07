/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panti
 */
public class ShowMap {

    public void Display() {
        String webpage = "src\\covid19\\stats\\mappage.html";
        try {
            Runtime.getRuntime().exec("cmd /c start " + webpage);
        } catch (IOException ex) {
            Logger.getLogger(ShowMap.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
