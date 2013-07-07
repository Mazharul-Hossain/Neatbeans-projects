package demonstratingthefileclass;

// Fig. 14.4: FileDemonstration.java
// Demonstrating the File class.

import java.io.File;

/**
 *
 * @author mazharul
 */
public class FileDemonstration
{
    // display information about file user specifies
    public void analyzePath( String path )
    {
        // create File object based on user input
        File name = new File( path );
        if ( name.exists() ) // if name exists, output information about it
        {
            // display file (or directory) information
            System.out.printf("%s%s\n",name.getName(), " exists" );
            System.out.printf("%s\n",( name.isFile() ? "is a file" : "is not a file" ) );
            System.out.printf("%s\n",( name.isDirectory() ? "is a directory" :
                            "is not a directory" ) );
            System.out.printf("%s\n",( name.isAbsolute() ? "is absolute path" :
                            "is not absolute path" ) );
            System.out.printf("%s%s\n", "Last modified: ", name.lastModified() );
            System.out.printf("%s%s\n", "Length: ", name.length() );
            System.out.printf("%s%s\n","Path: ", name.getPath() );
            System.out.printf("%s%s\n","Absolute path: ",name.getAbsolutePath() );
            System.out.printf("%s%s\n","Parent: ", name.getParent() );

            if ( name.isDirectory() ) // output directory listing
            {
                String directory[] = name.list();
                System.out.println( "\n\nDirectory contents:\n" );
                for ( String directoryName : directory )
                    System.out.printf( "%s\n", directoryName );
            } // end if
        } // end outer if

        else // not file or directory, output error message
        {
            System.out.printf( "%s %s", path, "does not exist." );
        } // end else
    } // end method analyzePath
} // end class FileDemonstration