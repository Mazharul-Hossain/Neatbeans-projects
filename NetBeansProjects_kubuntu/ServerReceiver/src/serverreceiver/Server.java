package serverreceiver;

/**
 *
 * @author mazharul
 */
import java.net.*;
 import java.io.*;

 class Server
 {
     BufferedInputStream bis;
     BufferedOutputStream bos;

     byte[] data;

     Socket socket;
     ServerSocket serverSocket;

     int in;

     BufferedReader inm = null;
     PrintWriter outm = null;

     public Server()
     {
         try
         {
             serverSocket = new ServerSocket(9632);
             System.out.println("i am server & listening...");
             socket = serverSocket.accept();
             System.out.println("a client connect");
             System.out.println("------1--------" +socket.isClosed());
             inm = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             outm = new PrintWriter(socket.getOutputStream(), true);
             System.out.println("from client: " +inm.readLine());
             outm.println("ack 1: hi....");
             System.out.println("from client: " +inm.readLine());
             outm.println("ack 2: ok....");
             receiveFile();
             System.out.println("------2--------" +socket.isClosed());
             outm.println("ack 3: take the file");
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
     }

     public void receiveFile()
     {
         try
         {
             byte[] receivedData = new byte[8192];
             bis = new BufferedInputStream(socket.getInputStream());
             bos = new BufferedOutputStream(new FileOutputStream("False position.docx"));
             while ((in = bis.read(receivedData)) != -1)
             {
                bos.write(receivedData,0,in);
             }
             bos.close();
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
     }
}