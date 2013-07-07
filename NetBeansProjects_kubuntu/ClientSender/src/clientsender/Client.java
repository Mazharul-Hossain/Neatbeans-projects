package clientsender;

import java.net.*;
 import java.io.*;

/**
 *
 * @author mazharul
 */

 class Client
 {
     Socket clientSocket;
     byte[] byteArray;

     BufferedInputStream bis;
     BufferedOutputStream bos;

     int in;

     BufferedReader inm = null;
     PrintWriter outm = null;

     public Client()
     {
         try
         {
             clientSocket = new Socket("localhost", 9632);
             System.out.println("i am client & connect");
             inm = new BufferedReader(new InputStreamReader(
                     clientSocket.getInputStream()));
             outm = new PrintWriter(clientSocket.getOutputStream(), true);
             System.out.println("------1--------" +clientSocket.isClosed());
             outm.println("msg 1: hi");
             System.out.println("from server__ " +inm.readLine());
             outm.println("msg 2: now i will send you a file");
             System.out.println("from server__ " +inm.readLine());
             sendFile();
             System.out.println("-------2-------" +clientSocket.isClosed());
             System.out.println("from server__ " +inm.readLine());
             System.out.println("-------3------nothing-" );
         }
         catch(IOException e)
         {
             e.printStackTrace();
         }
     }
     public void sendFile()
     {
         try
         {
             bis = new BufferedInputStream(new FileInputStream("/home/mazharul/NetBeansProjects/ClientSender/src/clientsender/False position.docx"));
             bos = new BufferedOutputStream(clientSocket.getOutputStream( ));
             byteArray = new byte[8192];
             while ((in = bis.read(byteArray)) != -1)
             {
                 bos.write(byteArray,0,in);
             }

             bis.close();
             bos.flush();
             bos.close();
         }
         catch (FileNotFoundException ex)
         {
             ex.printStackTrace();
             System.exit(1);
         }
         catch(IOException e)
         {
            e.printStackTrace();
            System.exit(1);
         }
     }
 }
