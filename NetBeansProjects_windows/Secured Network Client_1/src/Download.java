

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author mazharul
 */

// This class downloads a file from a fileName.
class Download extends Observable implements Runnable
{
    // These are the status names.
    public static final String STATUSES[] = {"Downloading",
    "Paused", "Complete", "Cancelled", "Error", "Uploading"};

    // These are the status codes.
    public static final int DOWNLOADING = 0;
    public static final int PAUSED      = 1;
    public static final int COMPLETE    = 2;
    public static final int CANCELLED  = 3;
    public static final int ERROR     = 4;
    public static final int UPLOADING = 5;

    private File fileName; // download fileName
    private int size; // size of download in bytes
    private int downloaded; // number of bytes downloaded
    private int index;
    private int status; // current status of download
    private Socket socket;
    
    private SecretKey givToSender;

    private KeyPair sKey;
    private KeyPair rKey;

    // Constructor for Download.
    public Download(File fileName , int j, int s)
    {
        this.fileName = fileName;
        size = s;
        downloaded = 0;
        index = j;

        status = UPLOADING;
        
        // Begin the download.
        download();
    }

    Download(File fileName,  int s, Socket socket)
    {
        this.fileName = fileName;
        size = s;
        this.socket = socket;
        downloaded = 0;

        status = DOWNLOADING;

        // Begin the download.
        download();
    }

    // Get this download's fileName.
    public String getfileName()
    {
        System.out.println(fileName.getName());
        return fileName.getName();//toString();
    }

    // Get this download's size.
    public int getSize()
    {
        return size;
    }

    // Get this download's progress.
    public float getProgress()
    {
        return ((float) downloaded / size) * 100;
    }

    // Get this download's status.
    public int getStatus()
    {
        return status;
    }

    // Pause this download.
    public void pause()
    {
        status = PAUSED;
        stateChanged();
    }

    // Resume this download.
    public void resume()
    {
        status = DOWNLOADING;
        stateChanged();
        download();
    }

    // Cancel this download.
    public void cancel()
    {
        status = CANCELLED;
        stateChanged();
    }

    // Mark this download as having an error.
    private void error()
    {
        status = ERROR;
        stateChanged();
    }

    // Start or resume downloading.
    private void download()
    {
        Thread thread = new Thread(this);
        thread.start();
    }

    // Get file name portion of fileName.
    private String getFileName(File fileName)
    {
        String fName = fileName.getPath();
        return fName;
    }

/*
    // Download file.
    public void run()
    {
        RandomAccessFile file = null;
        InputStream stream = null;

        try {
            // Open connection to fileName.
            HttpfileNameConnection connection =
                    (HttpfileNameConnection) fileName.openConnection();

            // Specify what portion of file to download.
            connection.setRequestProperty("Range",
                    "bytes=" + downloaded + "-");

            // Connect to server.
            connection.connect();

            // Make sure response code is in the 200 range.
            if (connection.getResponseCode() / 100 != 2) {
                error();
            }

            // Check for valid content length.
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                error();
            }

      // Set the size for this download if it hasn't been already set.
            if (size == -1) {
                size = contentLength;
                stateChanged();
            }

            // Open file and seek to the end of it.
            file = new RandomAccessFile(getFileName(fileName), "rw");
            file.seek(downloaded);

            stream = connection.getInputStream();
            while (status == DOWNLOADING) {
        // Size buffer according to how much of the file is left to download.
                byte buffer[];
                if (size - downloaded > MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                } else {
                    buffer = new byte[size - downloaded];
                }

                // Read from server into buffer.
                int read = stream.read(buffer);
                if (read == -1)
                    break;

                // Write buffer to file.
                file.write(buffer, 0, read);
                downloaded += read;
                stateChanged();
            }

      // Change status to complete if this point was reached because downloading has finished.
            if (status == DOWNLOADING) {
                status = COMPLETE;
                stateChanged();
            }
        } catch (Exception e) {
            error();
        } finally {
            // Close file.
            if (file != null) {
                try {
                    file.close();
                } catch (Exception e) {}
            }

            // Close connection to server.
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {}
            }
        }
    }
*/
    // Notify observers that this download's status has changed.
    private void stateChanged()
    {
        setChanged();
        notifyObservers();
    }

    public void run()
    {
        ObjectInputStream oisIn;
        ObjectOutputStream oosOut;
         // sendfile
        if(status == UPLOADING )
        {
            try
            {
                socket = new Socket(Client.userSocket[index], Client.userPort[index]);
                oosOut = new ObjectOutputStream(socket.getOutputStream());
                oosOut.writeObject(this.getfileName());
                oosOut.flush();
                oosOut.writeInt(this.size);
                oosOut.flush();
                File myFile1 = new File("own.ser");
                fileSend(myFile1);


                FileInputStream fis = null;

                oisIn = new ObjectInputStream( socket.getInputStream() );
                SecretKey givToSender1 = (SecretKey) oisIn.readObject();

                Encryp toEnFile = new Encryp();
                File pFile = new File(fileName.getPath());
                File cFile = toEnFile.makeEncryp(givToSender1, pFile);

                byte[] mybytearray = new byte[(int) cFile.length()];
                fis = new FileInputStream(cFile);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.read(mybytearray, 0, mybytearray.length);
                OutputStream os = socket.getOutputStream();                
                os.write(mybytearray, 0, mybytearray.length);
                os.flush();
                System.out.println("Sending...");
                downloaded = (int) cFile.length();

                bis.close();

                fis.close();
                os.close();
                oosOut.close();
                oisIn.close();
                socket.close();

                // Change status to complete if this point was reached because downloading has finished.
                if (status == UPLOADING)
                {
                    status = COMPLETE;
                    stateChanged();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }

        else if( status == DOWNLOADING )
        {
            FileOutputStream fos = null;
            try 
            {
                int bytesRead;
                int current = 0;
                
                CertificateAction cAr = new CertificateAction("own");        
                Certificate cRr = cAr.read();
                rKey = cRr.getPublicKey();
                
                CertificateAction cAs = new CertificateAction("new");        
                Certificate cRs = cAs.read();
                sKey = cRs.getPublicKey();

                MySecretKey revGen = new MySecretKey();
                SecretKey givToSender1 = revGen.getSecretKey( rKey, sKey);

                oosOut = new ObjectOutputStream(socket.getOutputStream());
                oosOut.writeObject(givToSender1);
                
                // receive file
                BufferedInputStream bis;
                BufferedOutputStream bos;

                int in;
               // System.out.println("sddasd");
                byte[] receivedData = new byte[1024];

                bis = new BufferedInputStream(socket.getInputStream());
                bos = new BufferedOutputStream(new FileOutputStream(this.getfileName()));

                while ((in = bis.read(receivedData)) != -1)
                {
                    System.out.println(in);
                    bos.write(receivedData, 0, in);
                    downloaded = downloaded + in;
                }

                bos.flush();
                bos.close();
                //fos.close();
                bis.close();
                

                /*
                byte[] mybytearray = new byte[ size ];
                InputStream is = socket.getInputStream();
                fos = new FileOutputStream( this.getfileName() );
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bytesRead = is.read(mybytearray, 0, mybytearray.length);
                current = bytesRead;

                do
                {
                    bytesRead = is.read(mybytearray, current, mybytearray.length - current);
                    if (bytesRead >= 0) 
                    {
                        current += bytesRead;
                    }
                } while (bytesRead > -1);
                
                bos.write(mybytearray, 0, current);
                bos.flush();
                bos.close();
                fos.close();
                */

               System.out.println("mazhar check: " + this.getfileName());

                File cdFile = new File(this.getfileName());

                Decryp toDeFile = new Decryp();
                File dFile = toDeFile.makeDecryp(givToSender1, cdFile);
                FileInputStream inStream_1 = new FileInputStream(dFile);

                File outFile = new File(this.getFileName(fileName));
        FileOutputStream outStream = new FileOutputStream(outFile);

        int fileSize_1 = (int) dFile.length();

        byte[] byteArray_1 = new byte[fileSize_1];

        inStream_1.read(byteArray_1);
        outStream.write(byteArray_1);

        inStream_1.close();
        outStream.close();
        dFile.deleteOnExit();


                socket.close();

                if (status == DOWNLOADING)
                {
                    status = COMPLETE;
                    stateChanged();
                }

            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }            catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }            catch (IOException ex)
            {
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }

    }
    public void fileSend( File myFile ) throws FileNotFoundException, IOException
    {
        FileInputStream fis = null;
        BufferedInputStream bis = null;

                    byte[] mybytearray = new byte[(int) myFile.length()];

            fis = new FileInputStream(myFile);

            bis = new BufferedInputStream(fis);

            bis.read(mybytearray, 0, mybytearray.length);

            OutputStream os = socket.getOutputStream();

            System.out.println("Sending...");

            os.write(mybytearray, 0, mybytearray.length);
            os.flush();


                fis.close();
                System.out.println("Sending...");
                //bis.close();


    }
}