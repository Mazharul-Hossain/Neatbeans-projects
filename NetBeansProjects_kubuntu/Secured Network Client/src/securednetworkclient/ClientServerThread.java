package securednetworkclient;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author mazharul
 */

public class ClientServerThread extends Thread implements ActionListener
{
    private Socket socket = null;
    DataTable tableModel = null;
    String inputLine1 = null;
    int inputInt;
    JFrame addframe1=new JFrame("Accept File");
    private Component n;

    public ClientServerThread(Socket socket, DataTable table)
    {
	super("ClientServerThread");
        tableModel = table;
	this.socket = socket;
    }

    @Override
    public void run()
    {
        try {
            ObjectInputStream oisIn;
            oisIn = new ObjectInputStream(socket.getInputStream());
            inputLine1 = (String) oisIn.readObject();
            System.out.println(inputLine1);
            inputInt = oisIn.readInt();
            receiveFile();
            acceptFileHaha();
            //oisIn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveFile() throws IOException
    {
        BufferedInputStream bis;
        BufferedOutputStream bos;

        int in;

        
             byte[] receivedData = new byte[8192];
             bis = new BufferedInputStream(socket.getInputStream());
             bos = new BufferedOutputStream(new FileOutputStream ( "new.ser" ) );

             in = bis.read(receivedData);
                bos.write(receivedData,0,in);
             bos.close();
        
    }

    public void acceptFile()
    {
        addframe1.setLayout(null);
        addframe1.setSize(700, 400);


        JPanel addpanel =new JPanel();
        addpanel.setLayout(null);
        addpanel.setLocation(0,0);
        addpanel.setSize(700,400);
        addframe1.add(addpanel);


        
        JTextField addtextfield2 = new JTextField();
        addtextfield2.setText(Integer.toString(inputInt));
        addtextfield2.setEditable(false);
        addtextfield2.setLocation(250,150);
        addtextfield2.setSize(400,30);
        addpanel.add(addtextfield2);


        JTextField addtextfield3 = new JTextField();
        addtextfield3.setText("YES");
        addtextfield3.setEditable(false);
        addtextfield3.setLocation(250,250);
        addtextfield3.setSize(400,30);
        addpanel.add(addtextfield3);

        JTextField addtextfield1 = new JTextField("");//inputLine1);
        addtextfield1.setText(inputLine1);
        addtextfield1.setEditable(false);
        addtextfield1.setLocation(250,50);
        addtextfield1.setSize(400,30);
        addpanel.add(addtextfield1);


        JLabel fileName = new JLabel("File Name");
        fileName.setLocation(20, 50);
        fileName.setSize(100,30);
        addpanel.add(fileName);

        JLabel fileSize = new JLabel("File Size");
        fileSize.setLocation(20, 150);
        fileSize.setSize(100,30);
        addpanel.add(fileSize);

        JLabel certificate = new JLabel("Certificate");
        certificate.setLocation(20, 250);
        certificate.setSize(100,30);
        addpanel.add(certificate);

        JButton acceptButton=new JButton("Accept");
        acceptButton.setLocation(150,300);
        addpanel.add(acceptButton);
        acceptButton.setSize(100,30);
        acceptButton.addActionListener(this);

        JButton declineButton=new JButton("Decline");
        declineButton.setLocation(300,300);
        addpanel.add(declineButton);
        declineButton.setSize(100,30);
        declineButton.addActionListener(this);

        JButton viewButton=new JButton("View Certificate");
        viewButton.setLocation(450,300);
        addpanel.add(viewButton);
        viewButton.setSize(100,30);
        viewButton.addActionListener(this);
        
        
        addpanel.add(addtextfield2);
        addpanel.add(addtextfield3);

        addframe1.add(addpanel);
      
        addframe1.setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ae)
    {
        JFrame addframe = null;

        if( ae.getActionCommand().equals( "Accept" ) )
        {
            // display file dialog, so user can choose file to open
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );

            int result = fileChooser.showOpenDialog( n );

            File filename = fileChooser.getSelectedFile(); // get selected file

            // display error if invalid
            if ( ( filename == null ) || ( filename.getName().equals( "" ) ) )
            {
                JOptionPane.showMessageDialog( addframe, "Invalid File Name",
                            "Invalid File Name", JOptionPane.ERROR_MESSAGE );
                //System.exit( 1 );
            } // end if
            else
            {
                File file = new File (filename.toString() + "/" + inputLine1);
                tableModel.addDownload(new Download( file, inputInt, socket ) );
                addframe1.setVisible(false);
            }
        }
        else if( ae.getActionCommand().equals( "Decline" ) )
        {
            addframe1.setVisible(false);
        }
        else if( ae.getActionCommand().equals( "View Certificate" ) )
        {
            try
            {
                CertificateAction cA = new CertificateAction("new");
                Certificate cR = cA.read();

                cR.frameCertificate();

 /*
                SwingUtilities.invokeLater ( new Runnable()
                    {
                        public void run()
                        {
                             cR.frameCertificate();
                        }
                    }
                );
                */
            }
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(ClientServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(ClientServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger(ClientServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void acceptFileHaha()
    {
        SwingUtilities.invokeLater ( new Runnable()
            {
                public void run()
                {
                    acceptFile();
                }
            }
        );
    }
}