

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CertificateAction
{
    private String filename;
//    private String foldername;

    public CertificateAction( String f )
    {
        filename  = f;
 //       setFile( filename );
    }
/*
    public void setFile(String filename)
    {
        this.filename = filename;
    }
*/
    public void write(Certificate cer) throws FileNotFoundException, IOException
    {
/*
        File dir = new File("Certificate");
        if( !dir.isDirectory() )
        {
            dir.mkdir();
            foldername = dir.getName()+"/";
        }
        else
            foldername = "";


*/

//        File outFile = new File( foldername+filename+".ser" );

        File outFile = new File(filename+".ser" );
        FileOutputStream outFileStream = new FileOutputStream( outFile );
        ObjectOutputStream outObjectStream = new ObjectOutputStream( outFileStream );

        outObjectStream.writeObject(cer);
        System.out.println("writing CERTIFICATE........calling: CertificateAction_004.write(Certificate_004)");
        outObjectStream.close();
    }

    public Certificate read( ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        
        Certificate cr;
//        File inFile = new File( foldername+filename+".ser");

        File inFile = new File( filename+".ser" );
        FileInputStream inFileStream = new FileInputStream( inFile );
        ObjectInputStream inObjectStream = new ObjectInputStream( inFileStream );

        cr =  (Certificate) inObjectStream.readObject();
        System.out.println("reading CERTIFICATE....calling: CertificateAction_004.read()");
        inObjectStream.close();

        return cr;
    }

}
