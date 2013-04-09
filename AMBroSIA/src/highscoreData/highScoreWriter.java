/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package highscoreData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Haisin Yip
 */
public class highScoreWriter
{
    private String[] scoreData;
    private String path;
    private File file;
    
    public highScoreWriter(String[] data, String path)
    {
        this.scoreData = data;
        this.path = path;
        this.file = new File(path);
    }
    
    public void writeToFile()
    {
        try
        {
            if(file.exists())
            {
                BufferedWriter fwriter = new BufferedWriter(new FileWriter(path, true));
                for(int i = 0 ; i < scoreData.length ; i++)
                {
                    fwriter.write(scoreData[i]);
                }
                fwriter.newLine();
                fwriter.close();
            }
            
            
            else
            {
                File f = new File(path);
                BufferedWriter fwriter = new BufferedWriter(new FileWriter(path, true));
                for(int i = 0 ; i < scoreData.length ; i++)
                {
                    fwriter.write(scoreData[i]);
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
