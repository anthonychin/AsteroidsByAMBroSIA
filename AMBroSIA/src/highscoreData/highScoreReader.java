package highscoreData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The
 * <code>highScoreReader</code> class reads the highscore information from highscore database(external text file)
 *
 * @author Haisin Yip
 */
public class highScoreReader {
    
    // private properties
    private Scanner fileScanner;
    private String path;
    private File file;
    private final String delims = ";";

    /**
     * initializes the path and a file
     * @param path new path
     */
    public highScoreReader(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * each row contains a player's score summary
     * returns the list of all player scores in a 2D array
     * @return list of all player scores in a 2D array
     */
    public String[][] readFile() {
        this.openFile();
        ArrayList<String[]> scoreList = new ArrayList<String[]>();
        try {
            // if file exists, read from it
            if (file.exists()) {
                while (fileScanner.hasNextLine()) {
                    
                    // parse out the delimiters from the file thn store the row information in a string array
                    String scoresLine = fileScanner.nextLine();
                    String[] scores = scoresLine.split(delims);
                    
                    // store string row information in an arraylist
                    scoreList.add(scores);
                }
                fileScanner.close();
                return to2Darray(scoreList);
            } // if it does not exist, abort
            else {
                fileScanner.close();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileScanner.close();
        return to2Darray(scoreList);
    }

    /**
     * turns arraylist of string arrays into 2d string array
     * @param list string array that needs to be turned into 2D string array
     * @return 2D string array
     */
    public static String[][] to2Darray(ArrayList<String[]> list) {
        String[][] arr = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < 8; j++) {
                arr[i][j] = list.get(i)[j];
            }
        }
        return arr;
    }

    /**
     * open file for usage
     */
    public void openFile() {

        if (file.exists()) {
            try {
                fileScanner = new Scanner(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // else if there is no file to read from, create a new blank file
        else {
            file = new File(path);
            try {
                file.createNewFile();
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    /**
     * close file for end of usage
     */ 
    public void closeFile() {
        fileScanner.close();
    }
}
