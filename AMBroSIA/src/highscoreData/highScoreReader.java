/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package highscoreData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Haisin Yip
 */
public class highScoreReader
{
    private Scanner fileScanner;
    private String path;
    private File file;
    
    public highScoreReader(String path)
    {
        this.path = path;
        this.file = new File(path);
    }
    
    public String[][] readFile()
    {
        this.openFile();
        ArrayList<String[]> scoreList = new ArrayList<String[]>();
        try
        {
            if(file.exists())
            {
                while(fileScanner.hasNext())
                {
                    String[] scores = new String[10];
                    for(int i = 0 ; i < scores.length ; i++)
                    {
                        scores[i] = fileScanner.next();
                    }
                    scoreList.add(scores);
                }
                fileScanner.close();
                return to2Darray(scoreList);
            }
            
            else
            {
                fileScanner.close();
                return null;
            }
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        fileScanner.close();
        return to2Darray(scoreList);
    }
    
    public static String[][] to2Darray(ArrayList<String[]> list)
    {
        String[][] arr = new String[list.size()][10];
        for(int i = 0 ; i < list.size() ; i++)
        {
            for(int j = 0 ; j < 10 ; j++)
            {
                arr[i][j] = list.get(i)[j];
            }
        }
        return arr;
    }
    
    public void openFile()
    {
        try
        {
            fileScanner = new Scanner(new File(path));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void closeFile()
    {
        fileScanner.close();
    }
}
