package highscoreData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * Writes to an external text file which acts as a database containing all highscores
 * @author Haisin Yip
 */
public class highScoreWriter
{
    // private properties
    private String[] scoreData;
    private String path;
    private File file;
    private final String delimiter = " ; ";
    
    // initializes data to be written, path, and file
    public highScoreWriter(String[] data, String path)
    {
        this.scoreData = data;
        this.path = path;
        this.file = new File(path);
    }
    
    // writes all highscore entries to external text file
    public void writeToFile()
    {
        try
        {
            // if file exists append content to it
            if(file.exists())
            {
                BufferedWriter fwriter = new BufferedWriter(new FileWriter(path, true));
                for(int i = 0 ; i < scoreData.length ; i++)
                {
                    fwriter.write(scoreData[i]);
                    fwriter.write(delimiter);
                }
                fwriter.newLine();
                fwriter.close();
            }
            
            // else, create a new file and append to it
            else
            {
                File f = new File(path);
                BufferedWriter fwriter = new BufferedWriter(new FileWriter(path, true));
                for(int i = 0 ; i < scoreData.length ; i++)
                {
                    fwriter.write(scoreData[i]);
                    fwriter.write(delimiter);
                }
                fwriter.newLine();
                fwriter.close();
            }
            
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
