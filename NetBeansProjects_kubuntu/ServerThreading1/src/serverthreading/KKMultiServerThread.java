package serverthreading;

import java.net.*;
import java.io.*;

/**
 *
 * @author mazharul
 */

public class KKMultiServerThread extends Thread
{
    private Socket socket = null;

    public KKMultiServerThread(Socket socket)
    {
	super("KKMultiServerThread");
	this.socket = socket;
    }

    @Override
    public void run()
    {

	try
        {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	    String inputLine, outputLine;
	    //KnockKnockProtocol kkp = new KnockKnockProtocol();
	    //kkp.processInput(null);
	    
            outputLine = new String("Knock! Knock!");
            System.out.println("Connection1: "+ socket.getInetAddress().getHostName());
            System.out.println("Connection2: "+ socket.getInetAddress().getHostAddress());
            System.out.println("Connection3: "+ socket.getInetAddress().getHostAddress());
            System.out.println("Connection4: "+ socket.getInetAddress());
            System.out.println("Server: "+ outputLine);
            out.println(outputLine);

	    while ((inputLine = in.readLine()) != null)
            {
                System.out.println("Client: "+ inputLine);

                //outputLine = kkp.processInput(inputLine);
		System.out.println("Server: "+ outputLine);
                out.println(outputLine);

                if (outputLine.equals("Bye"))
		    break;
	    }

            out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

