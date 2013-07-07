/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;

import javax.media.*;
import java.net.URL;

/**
 *
 * @author MAZHAR
 */

public class mp3 extends Thread
{

    private URL url;
    private MediaLocator mediaLocator;
    private Player playMP3;

    public mp3(String mp3)
    {
    try{
       this.url = new URL(mp3);
       }catch(java.net.MalformedURLException e)
          {System.out.println(e.getMessage());}
    }

    @Override
    public void run()
    {

    try{
       mediaLocator = new MediaLocator(url);
       playMP3 = Manager.createPlayer(mediaLocator);
        }catch(java.io.IOException e)
          {System.out.println(e.getMessage());
        }catch(javax.media.NoPlayerException e)
          {System.out.println(e.getMessage());}

    playMP3.addControllerListener(new ControllerListener()
      {
      public void controllerUpdate(ControllerEvent e)
         {
         if (e instanceof EndOfMediaEvent)
             {
             playMP3.stop();
             playMP3.close();
             }
         }
      }
     );
     playMP3.realize();
     playMP3.start();
     }
}