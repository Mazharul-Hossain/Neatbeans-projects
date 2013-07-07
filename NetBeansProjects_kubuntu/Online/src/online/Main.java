/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package online;

/**
 *
 * @author mazharul
 */
import javax.swing.JFrame;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Profile labelFrame = new Profile(); // create LabelFrame
        labelFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        labelFrame.setSize( 500, 500 ); // set frame size
        labelFrame.setVisible( true ); // display frame
    }

}
