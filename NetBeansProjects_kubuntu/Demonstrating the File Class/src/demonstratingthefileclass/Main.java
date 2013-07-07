package demonstratingthefileclass;

import java.util.Scanner;

// Fig. 14.5: FileDemonstrationTest.java
// Testing the FileDemonstration class.

/**
 *
 * @author mazharul
 */
public class Main
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner( System.in );
        FileDemonstration application = new FileDemonstration();
        System.out.print( "Enter file or directory name here: " );
        application.analyzePath( input.nextLine() );

    }

}
