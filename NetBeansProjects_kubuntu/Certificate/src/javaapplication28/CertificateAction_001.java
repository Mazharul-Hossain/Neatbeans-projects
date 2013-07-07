package javaapplication28;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public final class CertificateAction_001
{
    private String filename;

    public CertificateAction_001(String filename)
    {
        setFile( filename );
    }

    public void setFile(String filename)
    {
        this.filename = filename;
    }

    public void write(Certificate_001 cer) throws FileNotFoundException, IOException
    {
        File outFile = new File( filename+".ser" );
        FileOutputStream outFileStream = new FileOutputStream( outFile );
        ObjectOutputStream outObjectStream = new ObjectOutputStream( outFileStream );

        outObjectStream.writeObject(cer);
        outObjectStream.close();
    }

    public Certificate_001 read( String filename ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Certificate_001 cr;
        File inFile = new File( filename+".ser");
        FileInputStream inFileStresm = new FileInputStream( inFile );
        ObjectInputStream inObjectStream = new ObjectInputStream( inFileStresm );

        cr = (Certificate_001)inObjectStream.readObject();
        inObjectStream.close();

        return cr;
    }

}
