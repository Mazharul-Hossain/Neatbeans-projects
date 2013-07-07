package securednetworkclient;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author mazharul
 */

public class Main
{
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater ( new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            }
        );
    }

    private static void createAndShowGUI()
    {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(" Secured File Transfer Manager ");

        //Create and set up the content pane.
        ShowGUI demo = new ShowGUI();
        frame.setContentPane(demo.createContentPane());

        // We now also set the MenuBar of the Frame to our MenuBar
        frame.setJMenuBar(demo.createMenuBar());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
