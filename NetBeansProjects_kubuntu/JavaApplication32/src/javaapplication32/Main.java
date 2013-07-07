/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication32;

import javax.swing.JFrame;

/**
 *
 * @author mazharul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LookAndFeelFrame lookAndFeelFrame = new LookAndFeelFrame();
        lookAndFeelFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        lookAndFeelFrame.setSize( 300, 200 ); // set frame size
        lookAndFeelFrame.setVisible( true ); // display frame

    }

}
