
package javaapplication29;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Calendar;


public class Certificate_003Test
{
    public static void main(String args[]) throws NoSuchAlgorithmException
     {
        PublicKey k = KeyPairGenerator.getInstance("RSA").generateKeyPair().getPublic() ;
        Calendar cal = null;

        Certificate_003 cer = new Certificate_003("Server", "Client", "192.168.0.001", k, cal.getInstance() );

        cer.frameCertificate();

     }

}
