package securednetworkserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.KeyPair;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mazharul
 */

class ServerThread  extends Thread implements Serializable
{
    private Socket socket = null;
    String inputLine;
    
    static Socket userSocket[] = new Socket [1000];
    static String userName[] = new String [1000];
    static int userPort[] = new int [1000];
    static int i = 0;

    ObjectInputStream oisIn = null;
    ObjectOutputStream oosOut = null;

    public ServerThread(Socket accept)
    {
        super("ServerThread");
        socket = accept;
    }

    @Override
    public void run()
    {        
            try 
            {
                System.out.println("Socket in 1st try block");

                oisIn = new ObjectInputStream(socket.getInputStream());
                oosOut = new ObjectOutputStream(socket.getOutputStream());

                inputLine = (String) oisIn.readObject();
                Integer a = (Integer) oisIn.readObject();
                int b =  a;
                SetSocket( inputLine, b );
                System.out.println("1");

                KeyPair sKey = (KeyPair) oisIn.readObject();
                Certificate cer = new Certificate("NaMaKhaHo Co. Ltd.", getUserName(), socket.getInetAddress().toString(), sKey, Calendar.getInstance());
                CertificateAction cA = new CertificateAction(getUserName());
                cA.write(cer);
                Certificate c = cA.read();
                c.frameCertificate();

                oosOut.writeObject(c);
                oosOut.flush();

                //File myFile = new File( getUserName() + ".ser" );
                //fileSend(myFile);

                int index = 0;
                while(true)   //true error
                {
                    if( index == 0)
                    {
                        System.out.println("1");
                        SocketCheck();
                        System.out.println("2");
                        SocketQuery();
                        System.out.println("3");
                    }
                    else if(index == 5)//2147483647)
                    {
                        index = 0;
                    }
                    index++;
                    
                }
                //socket.close();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {                
                try
                {
                    oisIn.close();
                    //socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    private synchronized void SetSocket(String inputLine, int a)
    {

        userSocket[i] = socket;
        userName[i] = inputLine;
        userPort[i] = a;
        System.out.println("channel");
        System.out.println(userSocket[i]);
        System.out.println(userSocket[i].getInetAddress());
        System.out.println(userName[i]);
        System.out.println(userPort[i]);
        i++;
    }

    private synchronized String getUserName()
    {
        for(int j = 0; j < i; j++)
        {
            if(this.socket.equals(userSocket[j] ))
            {
                System.out.println(userName[j]);
                return userName[j];
            }
        }
        return null;
    }
    
    public synchronized void SocketCheck()
    {
        int j;
        int ind;
        
        for (j = 0; j < i; j++)
        {
            if( userSocket[j].isClosed() )
            {
                for (ind = j; ind < i; ind++)
                {
                    if( ! userSocket[ind].isClosed() )
                        break;
                }                
                int l = ind;
                for (int k = j; l < i; k++, l++)
                {
                    userSocket[k] = userSocket[l];
                    userName[k] = userName[l];
                    userPort[k] = userPort[l];
                }
                
                i = i - ( ind - j );
            }
        }
        System.out.println("Socket Check finish");
    }
    
    public synchronized void SocketQuery() throws FileNotFoundException, IOException
    {
        oosOut.writeInt(i);

        for(int ind = 0; ind < i; ind++)
        {            
            oosOut.writeObject(userName[ind]);
            oosOut.writeObject(userSocket[ind].getInetAddress());
            oosOut.writeInt(userPort[ind]);
            oosOut.flush();
        }        
    }

    public void fileSend( File myFile ) throws IOException
    {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try
        {
            byte[] mybytearray = new byte[(int) myFile.length()];

            fis = new FileInputStream(myFile);

            bis = new BufferedInputStream(fis);

            bis.read(mybytearray, 0, mybytearray.length);

            OutputStream os = socket.getOutputStream();

            System.out.println("Sending...");

            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                fis.close();
                //bis.close();
            } catch (IOException ex) {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void fileReceive( File myFile, int ind )
    {
        try
        {
            BufferedInputStream bis;
            BufferedOutputStream bos;

            int in;

            byte[] receivedData = new byte[ind];

            bis = new BufferedInputStream(socket.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(myFile));

            while ((in = bis.read(receivedData)) != -1)
            {
                bos.write(receivedData, 0, in);
            }

            bos.close();
            bis.close();

        } catch (IOException ex)
        {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}