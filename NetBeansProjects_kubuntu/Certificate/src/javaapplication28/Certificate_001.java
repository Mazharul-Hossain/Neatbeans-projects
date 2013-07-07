package javaapplication28;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Calendar;

public class Certificate_001 implements Serializable
{
    String authenticator;
    String cname;
    String ip;
    PublicKey pk;
    Socket port;
    Calendar datetime;

    byte[] byteFormat;

     final int COL = 1;
     final int ROW = 8;
     final int L = 800;
     final int W = 400;

     public Certificate_001(String auth, String name, String IP, PublicKey p, Calendar c)
     {
          authenticator = auth ;
          cname = name;
          ip = IP;
          pk = p;
          datetime = c;
     }

     public Certificate_001()
     {
         this("","","",null,null);
     }

     public String getAuthenticator()
     {
         return authenticator;

     }
     public String getClientName()
     {
         return cname;

     }
     public String getIP()
     {
         return ip;

     }
     public PublicKey getPublicKey()
     {
         return pk;

     }
     public Calendar getDate()
     {
         return datetime;

     }

     public Socket getPort()
     {
          return port;
     }

     public boolean isAuthenticator(String auth)
     {
          return auth.equals(authenticator);
     }

     public boolean isName(String name)
     {
         return name.equals(cname);
     }
     public boolean isIP(String IP)
     {
         return IP.equals(ip);
     }
     public boolean isPublicKey(PublicKey p)
     {
        return p.equals(pk);
     }

     public boolean isPort(Socket po)
     {
          return po.equals(port);
     }


     public JFrame frameCertificate()
     {
         JFrame frame = new JFrame("Authentication Certificate");

         try
             {

                KeyFactory key = KeyFactory.getInstance("RSA");
                RSAPublicKeySpec keySpec = (RSAPublicKeySpec)key.getKeySpec(pk, RSAPublicKeySpec.class);

                JLabel auth_JLabel = new JLabel(" Authenticated By:  " + authenticator);
                JLabel name_JLabel = new JLabel(" Name            :  " + cname);
                JLabel ip_JLabel = new JLabel(" IP              :  " + ip);
                JLabel date_JLabel = new JLabel(" Issue Date      :  " + datetime.getTime() );
                JLabel pkE_JLabel = new JLabel(" Public Key: e = " + keySpec.getPublicExponent() );
                JLabel pkN_JLabel = new JLabel("            n = " + keySpec.getModulus() );

                GridLayout layout = new GridLayout();
                layout.setColumns(COL);
                layout.setRows(ROW);

                frame.setLayout(layout);
                frame.add(auth_JLabel);
                frame.add(name_JLabel);
                frame.add(ip_JLabel);
                frame.add(pkE_JLabel);
                frame.add(pkN_JLabel);
                frame.add(date_JLabel);

                frame.setVisible(true);
                frame.setSize(L, W);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                return frame;
            }
            catch (InvalidKeySpecException ex)
            {
                Logger.getLogger( Certificate_001.class.getName() ).log(Level.SEVERE, null, ex);
            }
            catch (NoSuchAlgorithmException e)
            {
                Logger.getLogger( Certificate_001.class.getName() ).log(Level.SEVERE, null, e);
            }

        return frame;
     }

}
