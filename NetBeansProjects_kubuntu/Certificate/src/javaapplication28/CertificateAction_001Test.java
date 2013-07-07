package javaapplication28;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Calendar;


public class CertificateAction_001Test
{
    public static void main(String args[]) throws NoSuchAlgorithmException, FileNotFoundException, IOException, ClassNotFoundException
     {
        PublicKey k = KeyPairGenerator.getInstance("RSA").generateKeyPair().getPublic() ;
        //Calendar cal = null;

        Certificate_001 cer = new Certificate_001("Server", "Client", "192.168.0.001", k, Calendar.getInstance() );

        //cer.frameCertificate();

        CertificateAction_001 cA = new CertificateAction_001("Client");
        cA.write(cer);

        Certificate_001 cR = cA.read("Client");
        cR.frameCertificate();
        System.out.println( cR.getPublicKey() );
     }


}
