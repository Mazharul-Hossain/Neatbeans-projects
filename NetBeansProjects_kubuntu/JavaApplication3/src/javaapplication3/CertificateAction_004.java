
package javaapplication3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CertificateAction_004
{
    private String filename;
//    private String foldername;

    public CertificateAction_004(String filename)
    {
        setFile( filename );
    }

    public void setFile(String filename)
    {
        this.filename = filename;
    }

    public void write(Certificate_004 cer) throws FileNotFoundException, IOException
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

    public Certificate_004 read( String filename ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        
        Certificate_004 cr;
//        File inFile = new File( foldername+filename+".ser");

        File inFile = new File( filename+".ser" );
        FileInputStream inFileStresm = new FileInputStream( inFile );
        ObjectInputStream inObjectStream = new ObjectInputStream( inFileStresm );

        cr = (Certificate_004)inObjectStream.readObject();
        System.out.println("reading CERTIFICATE....calling: CertificateAction_004.read()");
        inObjectStream.close();

        return cr;
    }

}
