/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication5;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.speech.*;
import javax.speech.synthesis.*;
import java.util.*;
/**
 *
 * @author MAZHAR
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Calendar calendar = new GregorianCalendar();
            String sayTime = "Its " + calendar.get(Calendar.HOUR) + " " + 
                    calendar.get(Calendar.MINUTE) + " " +
                    (calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
            Synthesizer synth = Central.createSynthesizer(null);
            synth.allocate();
            synth.resume();
            synth.speakPlainText(sayTime, null);
            synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
            synth.deallocate();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AudioException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EngineStateError ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EngineException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}