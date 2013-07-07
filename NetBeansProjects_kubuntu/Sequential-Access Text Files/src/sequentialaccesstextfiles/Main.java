/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sequentialaccesstextfiles;

// Fig. 14.9: CreateTextFileTest.java
// Testing the CreateTextFile class.

/**
 *
 * @author mazharul
 */
public class Main
{
    public static void main(String[] args)
    {
        //CreateTextFile application = new CreateTextFile();
        ReadTextFile application = new ReadTextFile();

        application.openFile();
        
        //application.addRecords();
        application.readRecords();
        
        application.closeFile();

    } // end main
} // end class CreateTextFileTest
