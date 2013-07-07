
package javaapplication3;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class Encryp
{
    public Encryp(){};

    public File makeEncryp(SecretKey sKey, File inFile) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException
    {
       Cipher enCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
       enCipher.init(Cipher.ENCRYPT_MODE, sKey);

       FileInputStream inStream = new FileInputStream(inFile);

       File outFile = new File( "En_"+inFile.getName() );
       FileOutputStream outStream = new FileOutputStream(outFile);

       int fileSize = (int)inFile.length();

       byte[] plaintext = new byte[fileSize];

       inStream.read(plaintext);
       byte[] ciphertext = enCipher.doFinal(plaintext);
       outStream.write(ciphertext);

       inStream.close();
       outStream.close();

       System.out.println("Encryption Complete........");

       return outFile;
       
    }

}
