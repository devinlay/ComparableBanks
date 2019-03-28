
/**
 * This class will be used every time we need to read from a text file
 * 
 * OSSM AP Computer Science A
 * January 4, 2019
 */
import java.util.*;
import java.io.*;

public class ReadFromFile
{
    // you need to check if the String array being returned is null when using this method.  We may be editing it in the future.
    public static String[] ReadFile( String filename ) throws IOException
    {
        File f = new File( filename );

        Scanner sc = new Scanner( f );
        String[] linesFromFile = new String[1000]; // why is the array length set to 1000?
        
        int maxIndx = -1;  // why do we start at -1?

        while( sc.hasNext() )
        {
            maxIndx++;
            linesFromFile[maxIndx] =  sc.nextLine();
        }

        // must close Scanner, which closes the file
        sc.close();

        if( maxIndx > -1 )
        {
            String[] lines = new String[maxIndx+1];
            for( int i = 0; i <=maxIndx; i++ )
            {
                lines[i] = linesFromFile[i];
            }
            return lines;
        }

        return null;
    }
}