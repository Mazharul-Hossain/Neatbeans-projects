package securednetworkclient;

import java.io.*;
import java.net.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author mazharul
 */

public class Client extends Thread implements Serializable
{
    Socket socket = null;
    String username;

    static InetAddress userSocket[] = new InetAddress [1000];
    static String userName[] = new String [1000];
    static int userPort[] = new int [1000];
    static int i;

    ObjectInputStream oisIn;
    ObjectOutputStream oosOut;

    JFrame frame;
    JTextArea textArea;

    public Client()
    {
        super("Client");
    }
    public Client(String s)
    {
        super("Client");
        username = s;
        SetQueryFrame();
    }

    @Override
    public void run()
    {
        try 
        {
            socket = new Socket(InetAddress.getByName("localhost"), 4444);

            oosOut = new ObjectOutputStream(socket.getOutputStream());
            oisIn = new ObjectInputStream(socket.getInputStream());

            int a = 6666;
            oosOut.writeObject(username);
            oosOut.writeObject(Integer.valueOf(a) );
            oosOut.flush();

            ReqForCer sender = new ReqForCer();
            KeyPair sKey = sender.byKeyPair();
            
            oosOut.writeObject(sKey);
            oosOut.flush();
            Certificate c = (Certificate) oisIn.readObject();
            c.frameCertificate();


            //File myFile = new File("own.ser");
            //fileReceive(myFile, 1024*100 );
            //System.out.println("1");

            //CertificateAction cAr = new CertificateAction("own");
            //cAr.write(c);
            //Certificate cRr = cAr.read();
            //cRr.frameCertificate();

            int index = 0;
                while(true)
                {
                    if( index == 1)
                    {
                        System.out.println("2");
                        SocketQuery();
                        System.out.println("3");
                        ViewQuery();
                    }
                    else if(index == 5)//2147483647)
                    {
                        index = 0;
                    }
                    index++;

                }

            //socket.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void fileReceive( File myFile, int ind )
    {
        try
        {
            BufferedInputStream bis;
            BufferedOutputStream bos;

            int in;
            System.out.println("Start receiving");

            byte[] receivedData = new byte[ind];

            bis = new BufferedInputStream(socket.getInputStream());
            FileOutputStream fos = new FileOutputStream(myFile);
            bos = new BufferedOutputStream(fos);

            in = bis.read(receivedData);
            
            bos.write(receivedData, 0, in);

            System.out.println("Finish receiving");

            bos.close();
            //bis.close();

        } 
        catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    public synchronized void SocketQuery() throws IOException, ClassNotFoundException
    {
        i = oisIn.readInt();
        System.out.println("SocketQuery  " + i);
        for(int ind = 0; ind < i; ind++)
        {
            userName[ind] = (String) oisIn.readObject();
            System.out.println("SocketQuery  " + userName[ind]);
            userSocket[ind] = (InetAddress) oisIn.readObject();
            System.out.println("SocketQuery  " + userSocket[ind]);
            userPort[ind] =  oisIn.readInt();
            System.out.println("SocketQuery  " + userPort[ind]);
        }
        System.out.println("1");
    }

    public void ViewQuery()
    {
        SwingUtilities.invokeLater ( new Runnable()
            {
                public void run()
                {
                    ViewQueryFrame();
                }
            }
        );
    }
    private void SetQueryFrame()
    {        
        frame = new JFrame(" Online Friend ");
        frame.setLayout(null);
        frame.setSize(400, 700);

        JPanel addpanel =new JPanel();
        addpanel.setLayout(null);
        addpanel.setLocation(0,0);
        addpanel.setSize(400,700);
        
        textArea = new JTextArea();
        textArea.setLocation(10, 10);
        textArea.setSize(380, 680);
        textArea.setText("Who are Online : \n\n");

        textArea.setEditable(false);
        addpanel.add(textArea);
        frame.add(addpanel);
        frame.setVisible(false);
    }

    private void ViewQueryFrame()
    {
        textArea.setText("Who are Online : \n\n");
        for(int ind = 0; ind < i; ind++)
        {
            textArea.append(userName[ind] + "    :    "+ userSocket[ind] + "\n");
        }
        frame.setVisible(true);
    }
}