//ftp://surendra.com/Hindi_MP3/Jo%20Jeeta%20Woh%20Sikandar/Jo%20Jeeta%20Woh%20Sikandar%20-%20Pehla%20Nasha.mp3
//ftp://surendra.com/Hindi_MP3/



import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

class FTPDownload implements Runnable
{
    Thread t;
    public FTPDownload()
    {
        t = new Thread();
    }

    public void run()
    {
        try
        {

                int c;

		String s1 =javax.swing.JOptionPane.showInputDialog("Enter the Link:");//ftp://193.43.36.131/Radio/MP3/2010/Catalonia-Univ-02-fr.mp3
		String s2 =s1.substring(s1.lastIndexOf('/')+1);

		Runtime.getRuntime().exec("explorer.exe");


		String s3=JOptionPane.showInputDialog("Enter the Directory:");

		URL fp = new URL(s1);
		URLConnection fpCon =fp.openConnection();

		int len= fpCon.getContentLength();

		if(len!=0)
		{
			//System.out.println("===Content===");
			InputStream input = fpCon.getInputStream();
			OutputStream fo =new FileOutputStream(s3+"/"+s2);
			int i=len;
			while(((c=input.read())!=-1))
			{
				fo.write(c);
				i--;
			}
			input.close();
			fo.close();
			JOptionPane.showMessageDialog(null,"The FTP Download is Complete.");
		}
		else
		{
			System.out.println("No Content");
		}
        }
        catch(Exception e)
        {
        }
    }
	
}
