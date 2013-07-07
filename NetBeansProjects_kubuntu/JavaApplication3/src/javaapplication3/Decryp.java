
package javaapplication3;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class Decryp
{
    public Decryp(){}

    public File makeDecryp(SecretKey sKey, File inFile) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher deCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, sKey);

        FileInputStream inStream = new FileInputStream(inFile);

       File outFile = new File( "De_"+inFile.getName() );
       FileOutputStream outStream = new FileOutputStream(outFile);

       int fileSize = (int)inFile.length();

       byte[] cipertext = new byte[fileSize];

       inStream.read(cipertext);
       byte[] recovertext = deCipher.doFinal(cipertext);
       outStream.write(recovertext);

       inStream.close();
       outStream.close();

       System.out.println("Decryption Complete.........");

       return outFile;


    }

}
