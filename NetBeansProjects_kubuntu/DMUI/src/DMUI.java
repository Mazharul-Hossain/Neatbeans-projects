/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanvir
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
//import java.awt.Component;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

// Class implements three different types of listener.

public class DMUI implements Observer {

    private DownloadsTableModel tableModel;
    private JTable table;
    private Download selectedDownload;
    private boolean clearing;
    private int totaldownloads=0;

    JButton buttonnewtask= new JButton("New");
    JButton buttonruntask= new JButton("Run");
    JButton buttonstoptask= new JButton("Stop");
    JButton buttondeletetask= new JButton("Delete");
    JButton buttonuptask= new JButton("Up");
    JButton buttondowntask= new JButton("Down");
    JButton buttonredownload= new JButton("ReStart");
    JButton buttonsettings= new JButton("Settings");
    JButton buttonproperty= new JButton("FTPDownLoad");


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

    URL verifiedUrl;

    // Add a new download.
    private void actionaddframe()
    {
        final JFrame addframe=new JFrame("New Download");
        addframe.setLayout(null);
        addframe.setSize(500, 150);

        JPanel addpanel =new JPanel();
        addpanel.setLayout(null);
        addpanel.setLocation(0,0);
        addpanel.setSize(500,150);

        addtextfield.setLocation(20,40);
        addtextfield.setSize(300,30);

        JButton addurlbutton=new JButton("Add URL");
        addurlbutton.setLocation(350,40);
        addpanel.add(addurlbutton);
        addurlbutton.setSize(100,30);
        addurlbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verifiedUrl = verifyUrl(addtextfield.getText());
                if (verifiedUrl != null) {
                    tableModel.addDownload(new Download(verifiedUrl));
                    addtextfield.setText(""); // reset add text field
                    totaldownloads++;
                    addframe.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(addframe,
                    "Invalid Download URL", "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addpanel.add(addtextfield);
        addframe.add(addpanel);
        addframe.setVisible(true);
    }


    private URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://"))
            return null;

        // Verify format of URL.
        URL newverifiedUrl = null;
        try {
            newverifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }

        // Make sure URL specifies a file.
        if (newverifiedUrl.getFile().length() < 2)
            return null;

        return newverifiedUrl;
    }

    // Called when table row selection changes.
    private void tableSelectionChanged() {
    /* Unregister from receiving notifications
       from the last selected download. */
        if (selectedDownload != null)
            selectedDownload.deleteObserver(DMUI.this);

    /* If not in the middle of clearing a download,
       set the selected download and register to
       receive notifications from it. */
        if (!clearing) {
            selectedDownload =
                    tableModel.getDownload(table.getSelectedRow());
            selectedDownload.addObserver(DMUI.this);
            updatebuttons();
        }
    }

    public JPanel createContentPane()
    {
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);
        totalGUI.setBackground(Color.white);
        totalGUI.setMinimumSize(new Dimension(300, 200));
        totalGUI.setPreferredSize(new Dimension(800, 600));
        //totalGUI.setMaximumSize(new Dimension(300, 200));

        JPanel toolbar =new JPanel();
        toolbar.setBackground(Color.blue);
        toolbar.setLocation(0, 0);
        toolbar.setSize(800,80);
        toolbar.setOpaque(true);
        


        buttonnewtask.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonnewtask.png")));
        buttonruntask.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonruntask.png")));
        buttonstoptask.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonstoptask.png")));
        buttondeletetask.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttondeletetask.png")));
        buttonuptask.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonuptask.png")));
        buttondowntask.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttondowntask.png")));
        buttonredownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonredownload.png")));
        buttonsettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonsettings.png")));
        buttonproperty.setIcon(new javax.swing.ImageIcon(getClass().getResource("buttonproperty.png")));

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

        buttonproperty.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonproperty.setVerticalTextPosition(SwingConstants.BOTTOM);

        buttonnewtask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionaddframe();
            }
        });
        buttonnewtask.setEnabled(true);

        buttonruntask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionrun();
            }
        });
        buttonruntask.setEnabled(false);

        buttonstoptask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionstop();
            }
        });
        buttonstoptask.setEnabled(false);

        buttondeletetask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actiondelete();
            }
        });
        buttondeletetask.setEnabled(false);

        buttonuptask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        
            }
        });
        buttonuptask.setEnabled(false);

        buttondowntask.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttondowntask.setEnabled(false);

        buttonredownload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonredownload.setEnabled(false);

        buttonsettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonsettings.setEnabled(true);

        buttonproperty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FTPDownload ft= new FTPDownload();
                ft.t.start();
            }
        });
        buttonproperty.setEnabled(true);

        toolbar.add(buttonnewtask);
        toolbar.add(buttonruntask);
        toolbar.add(buttonstoptask);
        toolbar.add(buttondeletetask);
        toolbar.add(buttonuptask);
        toolbar.add(buttondowntask);
        toolbar.add(buttonredownload);
        toolbar.add(buttonsettings);
        toolbar.add(buttonproperty);


        // Set up Downloads table.
        tableModel = new DownloadsTableModel();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(new
                ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tableSelectionChanged();
            }
        });
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


        menuitemquit.setIcon(new javax.swing.ImageIcon(getClass().getResource("quit.png")));
        menuitemnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemnew.png")));
        menuitemrun.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemrun.png")));
        menuitempause.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitempause.png")));
        menuitemdelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemdelete.png")));
        menuitemmoveup.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemmoveup.png")));
        menuitemmovedown.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemmovedown.png")));
        menuitemredownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemredownload.png")));
        menuitemabout.setIcon(new javax.swing.ImageIcon(getClass().getResource("menuitemabout.png")));

        menufile.add(menuitemquit);
        menutasks.add(menuitemnew);
        menutasks.add(menuitemrun);
        menutasks.add(menuitempause);
        menutasks.add(menuitemdelete);
        menutasks.add(menuitemmoveup);
        menutasks.add(menuitemmovedown);
        menutasks.add(menuitemredownload);
        menuhelp.add(menuitemabout);

        menuitemquit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuitemnew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionaddframe();
            }
        });

        menuitemrun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionrun();
            }
        });
        menuitemrun.setEnabled(false);

        menuitempause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionstop();
            }
        });
        menuitempause.setEnabled(false);

        menuitemdelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actiondelete();
            }
        });
        menuitemdelete.setEnabled(false);

        menuitemmoveup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuitemmoveup.setEnabled(false);

        menuitemmovedown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuitemmovedown.setEnabled(false);

        menuitemredownload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuitemredownload.setEnabled(false);

        menuitemabout.addActionListener(new ActionListener() {
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
               } catch (IOException ex) {
            //Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        });

        return menuBar;
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] Download Manager [=]");

        //Create and set up the content pane.
        DMUI demo = new DMUI();
        frame.setContentPane(demo.createContentPane());
        // We now also set the MenuBar of the Frame to our MenuBar
        frame.setJMenuBar(demo.createMenuBar());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update(Observable o, Object arg) {
        // Update buttons if the selected download has changed.
        if (selectedDownload != null && selectedDownload.equals(o))
            ;
            updatebuttons();
    }

    /* Update each button's state based off of the
     currently selected download's status. */
    private void updatebuttons() {
        if (selectedDownload != null) {
            int status = selectedDownload.getStatus();
            switch (status) {
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
        } else {
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

    // Clear the selected download.
    private void actiondelete() {
        clearing = true;
        tableModel.clearDownload(table.getSelectedRow());
        clearing = false;
        selectedDownload = null;
        updatebuttons();
    }

    // Pause the selected download.
    private void actionstop() {
        selectedDownload.pause();
        updatebuttons();
    }

    // Resume the selected download.
    private void actionrun() {
        selectedDownload.resume();
        updatebuttons();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}