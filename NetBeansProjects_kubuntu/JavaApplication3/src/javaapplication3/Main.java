// Alal send a file to Babu


package javaapplication3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Main
{
 
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, FileNotFoundException, IOException, ClassNotFoundException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {


// CERTIFICATE of Allal............
        ReqForCer kpAlal = new ReqForCer();                         // Alal generate his "KEYPAIR".....
        Calendar cal = null;

        Certificate_004 cerAlal = new Certificate_004("Trusted certificate provider", "Client-Alal", "192.168.0.001", kpAlal.byKeyPair() , cal.getInstance() );

        System.out.println( "Before written - Alal : " + cerAlal.pk );
        cerAlal.frameCertificate();

        CertificateAction_004 cA_Alal = new CertificateAction_004("Client_Alal");
        cA_Alal.write(cerAlal);

        Certificate_004 cR_Alal = cA_Alal.read("Client_Alal");
        System.out.println( "After written - Alal : " + cR_Alal.pk );
        cR_Alal.frameCertificate();


// CERTIFICATE of Babu............
        ReqForCer kpBabu = new ReqForCer();                                     // Babu generate his "KEY PAIR".....
        Certificate_004 cerBabu = new Certificate_004("Trusted certificate provider", "Client-Babu", "192.168.0.025", kpBabu.byKeyPair() , cal.getInstance() );

        System.out.println( "Before written - Babu : " + cerBabu.pk );
        cerBabu.frameCertificate();

        CertificateAction_004 cA_Babu = new CertificateAction_004("Client_Babu");
        cA_Babu.write(cerBabu);

        Certificate_004 cR_Babu = cA_Babu.read("Client_Babu");
        System.out.println( "After written - Babu : " + cR_Babu.pk );
        cR_Babu.frameCertificate();

        
// Allal send "CERTIFICATE" to Babu , whether Babu want to receive FILE or not........
// So Babu got the "KEY PAIR" of Alam through the "CERTIFICATE"..........

// Now in Babu.......
        MySecretKey msKey_Babu = new MySecretKey();
        SecretKey sK_Babu = msKey_Babu.getSecretKey( cerBabu.getPublicKey(), cR_Alal.getPublicKey() );

// send "SECRET KEY" of Babu to Alam , indicator of accepting FILE.......

// in Alam......
        Encryp enFile = new Encryp();
        File pFile = new File("project.doc");
        File cFile = enFile.makeEncryp(sK_Babu, pFile);       

// send "cFile" to Babu.......

// in Babu...
        Decryp deFile = new Decryp();
        File dFile = deFile.makeDecryp(sK_Babu, cFile);
        

    }

}
