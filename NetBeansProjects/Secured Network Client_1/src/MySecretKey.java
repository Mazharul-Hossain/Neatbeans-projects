

import java.security.*;
import javax.crypto.*;

public class MySecretKey
{
    public MySecretKey(){}
    
    public SecretKey getSecretKey(KeyPair myKeyPair,KeyPair Kpair) throws NoSuchAlgorithmException, InvalidKeyException
    {
        KeyAgreement KeyAgree = KeyAgreement.getInstance("DH");
        KeyAgree.init( myKeyPair.getPrivate() );

        KeyAgree.doPhase(Kpair.getPublic(), true);
        byte[] SharedSecret = KeyAgree.generateSecret();

        System.out.println("secret : " + toHexString(SharedSecret));
        System.out.println("Secret Key is generated...........calling: MySecretKey.getSecretKey(KeyPair,KeyPair)");

        KeyAgree.doPhase(Kpair.getPublic(), true);
        SecretKey DesKey = KeyAgree.generateSecret("DES");

        return DesKey;                                 // Send it to that one who give me the file....ENCODING with it

    }


    private String toHexString(byte[] block)
    {
        StringBuffer buf = new StringBuffer();

        int len = block.length;

        for (int i = 0; i < len; i++)
        {
             byte2hex( block[i], buf );
             if (i < len-1)
                 buf.append(":");
        }
        return buf.toString();

    }

    private void byte2hex(byte b, StringBuffer buf)
    {
        char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                            '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }






}
