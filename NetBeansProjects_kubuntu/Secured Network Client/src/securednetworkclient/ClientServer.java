package securednetworkclient;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mazharul
 */

public class ClientServer extends Thread
{
    ServerSocket serverSocket = null;
    DataTable tableModel = null;

    boolean listening;

    public ClientServer(DataTable table)
    {
        super("ClientServerThread");
        tableModel = table;
        listening = true;
    }
    
    @Override
    public void run()
    {
        try
        {
            serverSocket = new ServerSocket(6666);
        } 
        catch (IOException ex)
        {
            System.err.println("Could not listen on port: 5556.");
            System.exit(-1);
        }

        try
        {
            while (listening)
            {
                System.out.println("Server is listening");
                new ClientServerThread(serverSocket.accept(),tableModel).start();
            }


            serverSocket.close();
        }

        catch (IOException ex) 
        {
            Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}