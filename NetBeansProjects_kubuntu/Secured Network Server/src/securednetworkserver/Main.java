package securednetworkserver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author mazharul
 */

public class Main
{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try
        {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }

        while (listening)
        {
            System.out.println("Server is listening");
            new ServerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }

}