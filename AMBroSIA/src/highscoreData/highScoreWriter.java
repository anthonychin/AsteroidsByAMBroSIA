package highscoreData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * The
 * <code> highScoreWriter </code> class writes to an external text file which acts as a database containing all
 * highscores.
 *
 * @author Haisin Yip
 */
public class highScoreWriter {
    
    // private properties
    private String[] scoreData;
    private String path;
    private File file;
    
    // use semicolon as delimiter to separate the different string tokens in the text file
    private final String delimiter = ";";

    /**
     * Initializes data to be written, path, and file
     * @param data data to be written
     * @param path path to be written
     */
    public highScoreWriter(String[] data, String path) {
        this.scoreData = data;
        this.path = path;
        this.file = new File(path);
    }

    /**
     * writes all highscore entries to external text file
     */
    public void writeToFile() {
        try {
            // if file exists append content to it
            if (file.exists()) {
                BufferedWriter fwriter = new BufferedWriter(new FileWriter(path, true));
                for (int i = 0; i < scoreData.length; i++) {
                    // write a delimiter after each string token except the last string token 
                    if (i < scoreData.length - 1) {
                        fwriter.write(scoreData[i]);
                        fwriter.write(delimiter);
                    } else if (i == scoreData.length - 1) {
                        fwriter.write(scoreData[i]);
                    }
                }
                // write a new line after the last string token
                fwriter.newLine();
                fwriter.close();
            } 
            // else, create a new file and append to it
            else {
                File f = new File(path);
                BufferedWriter fwriter = new BufferedWriter(new FileWriter(path, true));
                for (int i = 0; i < scoreData.length; i++) {
                    // write a delimiter after each string token except the last string token
                    if (i < scoreData.length - 1) {
                        fwriter.write(scoreData[i]);
                        fwriter.write(delimiter);
                    } else if (i == scoreData.length - 1) {
                        fwriter.write(scoreData[i]);
                    }
                }
                // write a new line after the last string token
                fwriter.newLine();
                fwriter.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
