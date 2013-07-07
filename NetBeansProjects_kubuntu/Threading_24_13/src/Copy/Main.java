package Copy;

import javax.swing.JFrame;

/**
 *
 * @author MAZHAR
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        CopyFile frame = new CopyFile();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("CopyFile");
        frame.setSize(400, 180);
        frame.setVisible(true);
    }

}
