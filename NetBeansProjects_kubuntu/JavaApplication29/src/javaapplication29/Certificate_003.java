
package javaapplication29;

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

public class Certificate_003 implements Serializable
{
    String authenticator;
    String cname;
    String ip;
    PublicKey pk;
    Socket port;
    Calendar datetime;

    byte[] byteFormat;

     /*      do add Icon at last */

     final int COL = 1;
     final int ROW = 8;
     final int L = 500;
     final int W = 300;

     public Certificate_003(String auth, String name, String IP, PublicKey p, Calendar c)
     {
          authenticator = auth ;
          cname = name;
          ip = IP;
          pk = p;
          datetime = c;
     }

     public Certificate_003()
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


                JTabbedPane tabbedPane = new JTabbedPane();

                JLabel auth_JLabel = new JLabel(" Authenticated By:  "  );
                JTextField auth_JText = new JTextField(20);
                auth_JText.setText(authenticator);
                auth_JText.setForeground(Color.blue);
                auth_JText.setBackground(Color.white);

                JLabel des_JLabel = new JLabel(" Description:  "  );
                JTextArea des_JArea = new JTextArea(20,25);
                des_JArea.setText(" It is extended service of server.\n"+
                                   " This authority is totally trust-worthy.\n" +
                                   " Certificate is authentic.\n"+
                                   "                                             ");
                des_JArea.setForeground(Color.blue);
                des_JArea.setBackground(Color.white);
                des_JArea.setEditable(false);

                JLabel date_JLabel = new JLabel(" Issue Date:  " );
                JTextField date_JText = new JTextField(15);
                date_JText.setText( " "+datetime.getTime()+"   " );
                date_JText.setForeground(Color.blue);
                date_JText.setBackground(Color.white);
                date_JText.setEditable(false);

                JPanel panel_1 = new JPanel();
                panel_1.add(auth_JLabel/*, BorderLayout.NORTH*/);
                panel_1.add(auth_JText/*, BorderLayout.LINE_END*/);
                panel_1.add(des_JLabel, BorderLayout.PAGE_END);
                panel_1.add(des_JArea/*, BorderLayout.LINE_END*/);
                panel_1.add(date_JLabel/*, BorderLayout.SOUTH*/);
                panel_1.add(date_JText/*, BorderLayout.LINE_END*/);
                panel_1.setBackground(Color.white);
                tabbedPane.addTab(" AUTHENTICATOR ", null, panel_1, "First Pane");





                JLabel name_JLabel = new JLabel(" Name            :  " + cname);
                JLabel ip_JLabel = new JLabel(" IP              :  " + ip);
                JPanel panel_2 = new JPanel();
                panel_2.add(name_JLabel);
                panel_2.add(ip_JLabel);
                panel_2.setBackground(Color.CYAN);
                tabbedPane.addTab(" CLIENT ",  null, panel_2, "Second Pane");

                JLabel pkE_JLabel = new JLabel(" Public Key: e = " + keySpec.getPublicExponent() );
                JLabel pkN_JLabel = new JLabel(" Modulus     n = " + keySpec.getModulus() );
                JPanel panel_3 = new JPanel();
                panel_3.add(pkE_JLabel, BorderLayout.CENTER);
                panel_3.add(pkN_JLabel);
                panel_3.setBackground(Color.BLUE);
                tabbedPane.addTab(" PRIVACY ", null, panel_3, "Third pane");

                frame.add(tabbedPane);
                frame.setResizable(false);
//                add(tabbedPane);
/*
                GridLayout layout = new GridLayout();
                layout.setColumns(COL);
                layout.setRows(ROW);
                frame.setLayout(layout);
/*                frame.add(auth_JLabel);
                frame.add(name_JLabel);
                frame.add(ip_JLabel);
                frame.add(pkE_JLabel);
                frame.add(pkN_JLabel);
                frame.add(date_JLabel);
*/
                frame.setVisible(true);
                frame.setSize(L, W);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                return frame;
            }
            catch (InvalidKeySpecException ex)
            {
                Logger.getLogger( Certificate_003.class.getName() ).log(Level.SEVERE, null, ex);
            }
            catch (NoSuchAlgorithmException e)
            {
                Logger.getLogger( Certificate_003.class.getName() ).log(Level.SEVERE, null, e);
            }

        return frame;
     }


}
