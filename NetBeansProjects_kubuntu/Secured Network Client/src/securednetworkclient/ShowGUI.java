package securednetworkclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author mazharul
 */

public class ShowGUI implements Observer
{
    private DataTable tableModel;
    private JTable table;
    private Download selectedDownload;
    private boolean clearing;
    private int totaldownloads=0;
    private ClientServer CSTask1;
    private Client CTask2;
    private int j= -1;

    JButton buttonconnecttask= new JButton("Connect");
    JButton buttonnewtask= new JButton("New");
    JButton buttonruntask= new JButton("Run");
    JButton buttonstoptask= new JButton("Stop");
    JButton buttondeletetask= new JButton("Delete");
    JButton buttonuptask= new JButton("Up");
    JButton buttondowntask= new JButton("Down");
    JButton buttonredownload= new JButton("ReStart");
    JButton buttonsettings= new JButton("Settings");
    
    JMenuItem menuitemconnect = new JMenuItem("Connect");
    JMenuItem menuitemsettings = new JMenuItem("Settings");
    JMenuItem menuitemquit = new JMenuItem("Quit");
    JMenuItem menuitemnew  = new JMenuItem("New");
    JMenuItem menuitemrun  = new JMenuItem("Run");
    JMenuItem menuitempause  = new JMenuItem("Pause");
    JMenuItem menuitemdelete  = new JMenuItem("Delete");
    JMenuItem menuitemmoveup  = new JMenuItem("MoveUp");
    JMenuItem menuitemmovedown  = new JMenuItem("MoveDown");
    JMenuItem menuitemredownload  = new JMenuItem("ReDownLoad");
    JMenuItem menuitemabout  = new JMenuItem("About");

    JTextField addtextfield =new JTextField();

    File fileName;
    private Component addframe;
    private Component n;

    public JPanel createContentPane()
    {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.white);
        totalGUI.setMinimumSize(new Dimension(300, 200));
        totalGUI.setPreferredSize(new Dimension(800, 600));
        //totalGUI.setMaximumSize(new Dimension(300, 200));

        JPanel toolbar =new JPanel();
        toolbar.setBackground(Color.cyan);
        toolbar.setLocation(0, 0);
        toolbar.setSize(800,80);
        toolbar.setOpaque(true);


        buttonconnecttask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonconnecttask.png")));
        buttonnewtask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonnewtask.png")));
        buttonruntask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonruntask.png")));
        buttonstoptask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonstoptask.png")));
        buttondeletetask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttondeletetask.png")));
        buttonuptask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonuptask.png")));
        buttondowntask.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttondowntask.png")));
        buttonredownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonredownload.png")));
        buttonsettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/buttonsettings.png")));

        buttonconnecttask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonconnecttask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonnewtask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonnewtask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonruntask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonruntask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonstoptask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonstoptask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttondeletetask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttondeletetask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonuptask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonuptask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttondowntask.setHorizontalTextPosition(SwingConstants.CENTER);
        buttondowntask.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonredownload.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonredownload.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonsettings.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonsettings.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonconnecttask.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionconnect();                    
                }
            }
        ) ;
        buttonconnecttask.setEnabled(true);

        buttonnewtask.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionaddframe();
                }
            }
        ) ;
        buttonnewtask.setEnabled(false);

        buttonruntask.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionrun();
                }
            }
        );
        buttonruntask.setEnabled(false);

        buttonstoptask.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionstop();
                }
            }
        );
        buttonstoptask.setEnabled(false);

        buttondeletetask.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actiondelete();
                }
            }
        );
        buttondeletetask.setEnabled(false);

        buttonuptask.addActionListener ( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        buttonuptask.setEnabled(false);

        buttondowntask.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );
        buttondowntask.setEnabled(false);

        buttonredownload.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );
        buttonredownload.setEnabled(false);

        buttonsettings.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );
        buttonsettings.setEnabled(true);

        toolbar.add(buttonconnecttask);
        toolbar.add(buttonnewtask);
        toolbar.add(buttonruntask);
        toolbar.add(buttonstoptask);
        toolbar.add(buttondeletetask);
        toolbar.add(buttonuptask);
        toolbar.add(buttondowntask);
        toolbar.add(buttonredownload);
        toolbar.add(buttonsettings);


        // Set up Downloads table.
        tableModel = new DataTable();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
            {
                public void valueChanged(ListSelectionEvent e)
                {
                    tableSelectionChanged();
                }
            }
        );
        // Allow only one row at a time to be selected.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set up ProgressBar as renderer for progress column.
        ProgressRenderer renderer = new ProgressRenderer(0, 100);
        renderer.setStringPainted(true); // show progress text
        table.setDefaultRenderer(JProgressBar.class, renderer);

        // Set table's row height large enough to fit JProgressBar.
        table.setRowHeight(
                (int) renderer.getPreferredSize().getHeight());

        // Set up downloads panel.
        JPanel downloadsPanel = new JPanel();
        downloadsPanel.setBorder(
                BorderFactory.createTitledBorder("Downloads"));
        downloadsPanel.setLayout(new BorderLayout());
        downloadsPanel.add(new JScrollPane(table),
                BorderLayout.CENTER);
        downloadsPanel.setLocation(0,80);
        downloadsPanel.setSize(800,470);   

        JPanel statusbar =new JPanel();
        statusbar.setLocation(0,550);
        statusbar.setSize(800,50);
        statusbar.setBackground(Color.cyan);

        totalGUI.add(statusbar);
        totalGUI.add(toolbar);
        totalGUI.add(downloadsPanel);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public JMenuBar createMenuBar()
    {
        //Create the menu bar.
        JMenuBar menuBar = new JMenuBar();

        //Add a JMenu
        JMenu menufile = new JMenu("File");
        JMenu menutasks = new JMenu("Tasks");
        JMenu menuhelp = new JMenu("Help");

        menuBar.add(menufile);
        menuBar.add(menutasks);
        menuBar.add(menuhelp);

        // Now we want to fill each of the menus.
        // Starters. This is a simple Menu, with three MenuItems.
        menuitemconnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemconnect.png")));
        menuitemsettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemsettings.png")));
        menuitemquit.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/quit.png")));
        menuitemnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemnew.png")));
        menuitemrun.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemrun.png")));
        menuitempause.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitempause.png")));
        menuitemdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemdelete.png")));
        menuitemmoveup.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemmoveup.png")));
        menuitemmovedown.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemmovedown.png")));
        menuitemredownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemredownload.png")));
        menuitemabout.setIcon(new javax.swing.ImageIcon(getClass().getResource("Resources/menuitemabout.png")));

        menufile.add(menuitemconnect);
        menufile.add(menuitemsettings);
        menufile.add(menuitemquit);
        menutasks.add(menuitemnew);
        menutasks.add(menuitemrun);
        menutasks.add(menuitempause);
        menutasks.add(menuitemdelete);
        menutasks.add(menuitemmoveup);
        menutasks.add(menuitemmovedown);
        menutasks.add(menuitemredownload);
        menuhelp.add(menuitemabout);

        menuitemconnect.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionconnect();
                }
            }
        );
        menuitemsettings.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );

        menuitemquit.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            }
        );

        menuitemnew.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionaddframe();
                }
            }
        );
        menuitemnew.setEnabled(false);

        menuitemrun.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionrun();
                }
            }
        );
        menuitemrun.setEnabled(false);

        menuitempause.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actionstop();
                }
            }
        );
        menuitempause.setEnabled(false);

        menuitemdelete.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    actiondelete();
                }
            }
        );
        menuitemdelete.setEnabled(false);

        menuitemmoveup.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );
        menuitemmoveup.setEnabled(false);

        menuitemmovedown.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );
        menuitemmovedown.setEnabled(false);

        menuitemredownload.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                }
            }
        );
        menuitemredownload.setEnabled(false);

/*        menuitemabout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFrame frame;
                    frame = new JFrame("Read Me");
                    frame.setVisible(true);

                    FileReader inStream;
                    String s = "";
                    StringBuffer sb = new StringBuffer("");
                    int ch = 0;
                    JTextArea tA = new JTextArea();
                    JScrollPane scroller = new JScrollPane(tA);
                    File f=new File("src/ReadMe");
                    try {
                        inStream = new FileReader(f);
                     } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                        return;
                     }

                    while ((ch = inStream.read()) != -1) {
                        sb.append((char) ch);
                    }
                    s = sb.toString();

                    tA.setText(s);

                    frame.add(scroller);
                    frame.setSize(650, 720);
                    frame.setMinimumSize(new Dimension(650, 720));
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                     inStream.close();
               } 
                catch (IOException ex)
                {
            //Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        });
*/
        return menuBar;
    }

    private void actionaddframe()
    {
        buttonnewtask.setEnabled(false);

        final JFrame addframe1=new JFrame("New Add");
        addframe1.setLayout(null);
        addframe1.setSize(500, 250);

        JPanel addpanel =new JPanel();
        addpanel.setLayout(null);
        addpanel.setLocation(0,0);
        addpanel.setSize(500,250);

        addtextfield.setLocation(20,40);
        addtextfield.setSize(400,30);
        addtextfield.setText(""); // reset add text field

        JButton browsebutton=new JButton("Browse");
        browsebutton.setLocation(150,140);
        addpanel.add(browsebutton);
        browsebutton.setSize(100,30);

        JButton addfilebutton=new JButton("Add File");
        addfilebutton.setLocation(350,140);
        addpanel.add(addfilebutton);
        addfilebutton.setSize(100,30);

        
        browsebutton.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    //File fileName = getFile();
                     //   tableModel.addDownload ( new Download( fileName, (int) fileName.length()/1024 ) );
                        addtextfield.setText(getFile().getAbsolutePath()); // reset add text field
                       // totaldownloads++;
                       // buttonnewtask.setEnabled(true);
                        //addframe1.setVisible(false);
                }

                
            }
        ) ;

        addfilebutton.addActionListener ( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    fileName = new File(addtextfield.getText().trim());

                    if ( ( fileName == null ) || ( fileName.getName().equals( "" ) )  || (fileName.isDirectory()))
                    {
                        JOptionPane.showMessageDialog(addframe1, "Invalid File Name",
                        "Invalid File Name", JOptionPane.ERROR_MESSAGE );
                    } // end if
                    else
                    {
                        actionselectframe(fileName);
                        addtextfield.setText(""); // reset add text field
                        addframe1.setVisible(false);
                    }
                }
            }
        );

        addpanel.add(addtextfield);
        addframe1.add(addpanel);
        addframe1.setVisible(true);
    }
    private File getFile()
    {
        // display file dialog, so user can choose file to open
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );

        int result = fileChooser.showOpenDialog( n );

        // if user clicked Cancel button on dialog, return
        //if ( result == JFileChooser.CANCEL_OPTION )
            //System.exit( 1 );

        File filename = fileChooser.getSelectedFile(); // get selected file

        // display error if invalid
        if ( ( filename == null ) || ( filename.getName().equals( "" ) )   || (filename.isDirectory()))
        {
            JOptionPane.showMessageDialog( addframe, "Invalid File Name",
                        "Invalid File Name", JOptionPane.ERROR_MESSAGE );            
            //System.exit( 1 );
            return null;
        } // end if
        else
        {
            return filename;
        }        
    }

    private void actionconnect()
    {
        final JFrame addframe2=new JFrame("User Name");
        addframe2.setLayout(null);
        addframe2.setSize(500, 150);

        JPanel addpanel =new JPanel();
        addpanel.setLayout(null);
        addpanel.setLocation(0,0);
        addpanel.setSize(500,150);

        addtextfield.setLocation(20,40);
        addtextfield.setSize(300,30);

        JButton okbutton=new JButton(" OK ");
        okbutton.setLocation(350,40);
        addpanel.add(okbutton);
        okbutton.setSize(100,30);

        okbutton.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    if (addtextfield.getText().equals( "" ))
                    {
                        JOptionPane.showMessageDialog(addframe2,"Invalid User Name", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        addframe2.setVisible(false);

                        System.out.println("actionconnect...");

                        menuitemconnect.setEnabled(false);
                        menuitemnew.setEnabled(true);
                        buttonconnecttask.setEnabled(false);
                        buttonnewtask.setEnabled(true);

                        String s = addtextfield.getText();
                        System.out.println(s);
                        addtextfield.setText(""); // reset add text field

                        CSTask1 = new ClientServer (tableModel);
                        CTask2 = new Client (s);

                        Thread thread1 = new Thread ( CSTask1 );
                        Thread thread2 = new Thread ( CTask2 );

                        // execute the tasks with an ExecutorService
                        ExecutorService executor = Executors.newCachedThreadPool();
                        executor.execute( thread1 );
                        executor.execute( thread2 );

                        executor.shutdown();
                    }
                }
            }
        );

        addpanel.add(addtextfield);
        addframe2.add(addpanel);
        addframe2.setVisible(true);

        System.out.println("actionadduser...");
        //addtextfield.setText(""); // reset add text field
     }
    
    private void actionselectframe(final File fileName)
    {
        final JFrame addframe2=new JFrame(" User Name ");
        addframe2.setLayout(null);
        addframe2.setSize(500, 150);

        JPanel addpanel =new JPanel();
        addpanel.setLayout(null);
        addpanel.setLocation(0,0);
        addpanel.setSize(500,150);

        addtextfield.setLocation(20,40);
        addtextfield.setSize(300,30);
        //addtextfield.setText(""); // reset add text field

        JButton okbutton=new JButton(" OK ");
        okbutton.setLocation(350,40);
        addpanel.add(okbutton);
        okbutton.setSize(100,30);

        okbutton.addActionListener( new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    if (addtextfield.getText().equals( "" ))
                    {
                        JOptionPane.showMessageDialog(addframe2,"Invalid User Name", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        addframe2.setVisible(false);

                        System.out.println("actionselect...");

                        String s = addtextfield.getText();
                        System.out.println(s);
                        addtextfield.setText(""); // reset add text field

                        for(j = 0; j < Client.i; j++)
                        {
                            if(s.equals(Client.userName[j] ))
                            {
                                break;
                            }
                        }
                        buttonnewtask.setEnabled(true);
                        tableModel.addDownload(new Download(fileName , j,(int) fileName.length()/1024));
                        totaldownloads++;                     

                    }
                }
            }
        );

        addpanel.add(addtextfield);
        addframe2.add(addpanel);
        addframe2.setVisible(true);

        System.out.println("actionadduser...");
        //addtextfield.setText(""); // reset add text field
    }

    // Clear the selected download.
    private void actiondelete()
    {
        clearing = true;
        tableModel.clearDownload(table.getSelectedRow());
        clearing = false;
        selectedDownload = null;
        updatebuttons(); 
    }

    // Pause the selected download.
    private void actionstop()
    {
        selectedDownload.pause();
        updatebuttons(); 
    }

    // Resume the selected download.
    private void actionrun()
    {
        selectedDownload.resume();
        updatebuttons(); 
    }

    public void update(Observable o, Object arg)
    {
        // Update buttons if the selected download has changed.
        if (selectedDownload != null && selectedDownload.equals(o))
            ;
            updatebuttons();
    }
    
    // Update each button's state based off of the currently selected download's status.
    private void updatebuttons() 
    {
        if (selectedDownload != null) 
        {
            int status = selectedDownload.getStatus();
            
            switch (status)
            {
                case Download.DOWNLOADING:
                    buttonstoptask.setEnabled(true);
                    buttonruntask.setEnabled(false);
                    buttondeletetask.setEnabled(false);
                    buttonredownload.setEnabled(true);
                    menuitempause.setEnabled(true);
                    menuitemrun.setEnabled(false);
                    menuitemdelete.setEnabled(false);
                    menuitemredownload.setEnabled(true);
                    break;

                case Download.PAUSED:
                    buttonstoptask.setEnabled(false);
                    buttonruntask.setEnabled(true);
                    buttondeletetask.setEnabled(true);
                    buttonredownload.setEnabled(true);
                    menuitempause.setEnabled(false);
                    menuitemrun.setEnabled(true);
                    menuitemdelete.setEnabled(true);
                    menuitemredownload.setEnabled(true);
                    break;

                case Download.ERROR:
                    buttonstoptask.setEnabled(false);
                    buttonruntask.setEnabled(false);
                    buttondeletetask.setEnabled(true);
                    buttonredownload.setEnabled(true);
                    menuitempause.setEnabled(false);
                    menuitemrun.setEnabled(false);
                    menuitemdelete.setEnabled(true);
                    menuitemredownload.setEnabled(true);
                    break;

                default: // COMPLETE or CANCELLED
                    buttonstoptask.setEnabled(false);
                    buttonruntask.setEnabled(false);
                    buttondeletetask.setEnabled(true);
                    buttonredownload.setEnabled(true);
                    menuitempause.setEnabled(false);
                    menuitemrun.setEnabled(false);
                    menuitemdelete.setEnabled(true);
                    menuitemredownload.setEnabled(true);
            }
        } 
        else
        {
            // No download is selected in table.
            buttonstoptask.setEnabled(false);
            buttonruntask.setEnabled(false);
            buttondeletetask.setEnabled(false);
            buttonredownload.setEnabled(false);
            menuitempause.setEnabled(false);
            menuitemrun.setEnabled(false);
            menuitemdelete.setEnabled(false);
            menuitemredownload.setEnabled(false);
        }
    }

    // Called when table row selection changes.
    private void tableSelectionChanged()
    {
        // Unregister from receiving notifications from the last selected download.
        if (selectedDownload != null)
            selectedDownload.deleteObserver(ShowGUI.this);

        /* If not in the middle of clearing a download,
           set the selected download and register to
           receive notifications from it. */
        if (!clearing)
        {
            selectedDownload =
                    tableModel.getDownload(table.getSelectedRow());
            selectedDownload.addObserver(ShowGUI.this);
            updatebuttons();
        }
    }
}