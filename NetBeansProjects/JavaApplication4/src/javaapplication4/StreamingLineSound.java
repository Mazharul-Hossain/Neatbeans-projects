/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication4;

import javax.sound.sampled.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author MAZHAR
 */

public class StreamingLineSound extends Object
       implements LineListener {

    File soundFile;
    JDialog playingDialog;
    PCMFilePlayer player;

    public static void main (String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        try {
            StreamingLineSound s = new StreamingLineSound (f);
        } catch (Exception e) {
        }
    }

    public StreamingLineSound (File f)
        throws LineUnavailableException, IOException,
               UnsupportedAudioFileException {
        soundFile = f;
        // prepare a dialog to display while playing
        JOptionPane pane = new JOptionPane ("Playing " + f.getName(),
                                            JOptionPane.PLAIN_MESSAGE);
        playingDialog = pane.createDialog (null, "Streaming Sound");
        playingDialog.pack();


        player = new PCMFilePlayer (soundFile);
        // player.line.addLineListener (this);
        player.getLine().addLineListener (this);
        player.start();

    }


    // LineListener
    public void update (LineEvent le) {
        LineEvent.Type type = le.getType();
        if (type == LineEvent.Type.OPEN) {
            System.out.println ("OPEN");
        } else if (type == LineEvent.Type.CLOSE) {
            System.out.println ("CLOSE");
            System.exit (0);
        } else if (type == LineEvent.Type.START) {
            System.out.println ("START");
            playingDialog.setVisible(true);
        } else if (type == LineEvent.Type.STOP) {
            System.out.println ("STOP");
            playingDialog.setVisible(false);
            player.line.close();
        }
    }

}
