//package webprogramming;

import java.net.UnknownHostException;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetAddress;

public class Client extends JFrame{
    
    private JTextField enterArea;
    private JTextArea contentArea;
    private PrintWriter output;
    private BufferedReader input;
    private Socket client;
    private String ip;
    private int port = 1;
    
    public Client(String host)
    {
         super("Client");
         ip = host;
         setLayout(new BorderLayout());
         
         enterArea = new JTextField();
         enterArea.addActionListener(
                 new ActionListener()
                {
                      public void actionPerformed(ActionEvent event)
                      {
                           sendData(event.getActionCommand());
                           enterArea.setText("");
                      }
                }
                 );
                 add(enterArea,BorderLayout.SOUTH);
                 
                 contentArea = new JTextArea();
                 
                 contentArea.setEditable(false);
                 
                 add(contentArea,BorderLayout.NORTH);
                 add(new JScrollPane(contentArea),BorderLayout.CENTER);
                 
                 
                 setSize(300,375);
                 setVisible(true);
    }
    
    
    public void runClient()
    {
        try{           
            getConnection();
            getStreams();
            processConnection();
            
            
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "IO Exception Occured");
        }
       catch(Exception ex)
       {
            JOptionPane.showMessageDialog(null,"Unknown Exception Occured");
       }
        finally
        {
           closeConnection();
        }
    }
    
    private void getConnection()throws IOException
    {
         display("Trying to Connect With Server.....");
         client = new Socket(InetAddress.getByName(ip),port);
         JOptionPane.showMessageDialog(null,"The IP of The Server is"+client.getInetAddress().getHostAddress());
         display("Found Server");
    }
    private void getStreams()throws IOException
    {
        //display("Trying tp get IO Streams");
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream());
        output.flush();
        display("Got IO Streams");
    }
    private void processConnection()throws IOException
    {
           String message="";
           setAreaEditable(false);
           
           do{
               try{
               
               message = input.readLine();
               display(message);
               }
               catch(Exception ex)
               {
                   JOptionPane.showMessageDialog(null,"Error Occured Inside processConnection");
               }
               
           
           }while(!message.equals("server>>Terminate"));
    }
    
    private void display(final String str)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run()
                    {
                       contentArea.append("\n"+str);
                    }
                }
                );
    }
    
    private void setAreaEditable(final boolean logic)
    {
         SwingUtilities.invokeLater(
                 new Runnable()
                {
                    public void run()
                    {
                        contentArea.setEditable(logic);
                    }
                }
                 );
    }
    
    private void sendData(String mess)
    {
        try{
             output.println("client>>"+mess);
             output.flush();
             display("client>>"+ mess);
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null,"Exception Occured Inside Sending Data");
        }
         
    }
    
    private void closeConnection()
    {
        try{
             input.close();
             output.close();
             client.close();
    	     System.exit(1);
        }
        catch(IOException ex)
        {
           JOptionPane.showMessageDialog(null, "IO Exception Occured");
        }
    }


    public static void main(String[] args) throws UnknownHostException
   {  
       if(args[0]!=null)
          {
		Client c = new Client(args[0]);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        c.runClient();
	  } 
        else
	{
  	    Client c = new Client(InetAddress.getLocalHost().getHostAddress());
	    c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    c.runClient();
	}
   }
}
