

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
        try 
        {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

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
