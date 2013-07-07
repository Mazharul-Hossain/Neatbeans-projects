package javaapplication32;

// Changing the look and feel.
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author mazharul
 */
public class LookAndFeelFrame extends JFrame
{
    // string names of look and feels
private final String strings[] = { "Metal", "Motif", "Windows" };
private UIManager.LookAndFeelInfo looks[]; // look and feels
private JRadioButton radio[]; // radio buttons to select look-and-feel
private ButtonGroup group; // group for radio buttons
private JButton button; // displays look of button
private JLabel label; // displays look of label
private JComboBox comboBox; // displays look of combo box
// set up GUI
public LookAndFeelFrame()
{
super( "Look and Feel Demo" );
JPanel northPanel = new JPanel(); // create north panel
northPanel.setLayout( new GridLayout( 3, 1, 0, 5 ) );
label = new JLabel( "This is a Metal look-and-feel",
SwingConstants.CENTER ); // create label
northPanel.add( label ); // add label to panel
button = new JButton( "JButton" ); // create button
northPanel.add( button ); // add button to panel
comboBox = new JComboBox( strings ); // create combobox
northPanel.add( comboBox ); // add combobox to panel
// create array for radio buttons
radio = new JRadioButton[ strings.length ];
JPanel southPanel = new JPanel(); // create south panel
southPanel.setLayout( new GridLayout( 1, radio.length ) );
group = new ButtonGroup(); // button group for looks-and-feels
ItemHandler handler = new ItemHandler(); // look-and-feel handler
for ( int count = 0; count < radio.length; count++ )
{
radio[ count ] = new JRadioButton( strings[ count ] );
radio[ count ].addItemListener( handler ); // add handler
group.add( radio[ count ] ); // add radio button to group
southPanel.add( radio[ count ] ); // add radio button to panel
} // end for
add( northPanel, BorderLayout.NORTH ); // add north panel
add( southPanel, BorderLayout.SOUTH ); // add south panel
// get installed look-and-feel information
looks = UIManager.getInstalledLookAndFeels();
radio[ 0 ].setSelected( true ); // set default selection
} // end LookAndFeelFrame constructor
// use UIManager to change look-and-feel of GUI
private void changeTheLookAndFeel( int value )
{
try // change look-and-feel
{
// set look-and-feel for this application
UIManager.setLookAndFeel( looks[ value ].getClassName() );
// update components in this application
SwingUtilities.updateComponentTreeUI( this );
} // end try
catch ( Exception exception )
{
} // end catch
} // end method changeTheLookAndFeel
// private inner class to handle radio button events
private class ItemHandler implements ItemListener
{
// process user's look-and-feel selection
public void itemStateChanged( ItemEvent event )
{
for ( int count = 0; count < radio.length; count++ )
{
if ( radio[ count ].isSelected() )
{
label.setText( String.format( "This is a %s look-and-feel",
strings[ count ] ) );
comboBox.setSelectedIndex( count ); // set combobox ichangeTheLookAndFeel( count ); // change look-and-fee} // end if
} // end for
} // end method itemStateChanged
    }
    }}
