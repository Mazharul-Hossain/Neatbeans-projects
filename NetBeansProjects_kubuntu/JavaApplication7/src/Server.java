//package webprogramming;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;




public class Server extends JFrame{

    private JTextField enterField;
    private JTextArea contentArea;
    private ServerSocket server ;
    Socket connection;
    private BufferedReader input;
    private PrintWriter output;
    private  int  portNumber=1;
    private int count=0;
    private int highestClientNumber=1;
    
    public Server()
    {
        super("Server Computer");
        setLayout(new BorderLayout());
        
        enterField = new JTextField();
        enterField.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                         sendData(event.getActionCommand());
                         enterField.setText("");
                         
                    }
                }
                );
        add(enterField,BorderLayout.SOUTH);
        contentArea = new JTextArea();
        contentArea.setEditable(false);
        add(contentArea,BorderLayout.NORTH);
        add(new JScrollPane(contentArea),BorderLayout.CENTER);
        setSize(375,375);
        setVisible(true);       
        
    }
    
    public void runServer()
    {
       while(true)
        {
            try
            {
                server = new ServerSocket(portNumber,highestClientNumber);
                getConnection();
                getStreams();
                processConnection();
            }
            catch(IOException ex)
            {
                 JOptionPane.showMessageDialog(null,"IOException occured");
            }
            finally
            {
               closeConnection();
               count++;
            }
        }
    }
    
    private void getConnection()throws IOException
    {
         display("Trying to Connect.........");
         connection = server.accept();
         display("Connection"+ count+"Found From "+connection.getInetAddress().getHostAddress()+connection.getInetAddress().getHostName());
         
    }
    
    private void getStreams()throws IOException
    {
          input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
          output = new PrintWriter(connection.getOutputStream());
          output.flush();
          display("IO Streams Founded");
    }
    
    private void processConnection()throws IOException
    {
         String message = "Connecting ";
         //sendData(message);
         setEditable(false);  
         display(message);
         
         do{
            
             try{
                
                 
                 message = input.readLine();
                 display(message);
                 
               }
             catch(Exception ex)
             {
                  throw new IOException();
             }
         
         }while(!message.equals("client>>Terminate"));
    }
    
    private void closeConnection()
    {
        try{
         output.close();
         input.close();
         connection.close();
        }
        
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Connection Can not be Closed /n May be Something Wrong");
        }
    }
    
    private void display(final String message)
    {
        
         SwingUtilities.invokeLater(
                 
                 new Runnable()
                {
                    public void run()
                    {
                          contentArea.append("\n"+message);
                    }
                }
                 
                 );
    }
    
    private void setEditable(final boolean logic)
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
    
    private void sendData(String data)
    {
          try{
           
              output.println("server>>"+data);
              output.flush();
              display("server>>"+data);
          
          }
          catch(Exception ex)
          {
              JOptionPane.showMessageDialog(null,"Stream Problem");
          }
    }
    public static void main(String[] args)
    {
         Server s = new Server();
         s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         s.runServer();
    }
}
