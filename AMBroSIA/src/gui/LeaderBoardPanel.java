package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import game.GameState;
import highscoreData.highScoreReader;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The leaderboard, showing score information for past (and current) players
 *
 * @author Haisin Yip
 */
public class LeaderBoardPanel extends JPanel {

    
    JTable table;
    GameState gamestate;
    
    private Image img;
  
    String[] columns = {"Player", "Highscore", "Number of Lives", "Asteroid Destroyed", "Aliens destroyed", "Total deaths", "Kill-Death Ratio", "Level reached", "Bombs Used", "Shooting Accuracy"};
    String[][] rowdata;
    
    JScrollPane scrollPane;
    public LeaderBoardPanel(Image img, GameState gs)
    {
        this.gamestate = gs;
        
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        
        makeComponents(this.getWidth(), this.getHeight());
        makeLayout();
    }
    
    private void makeComponents(int w, int h)
    {
        highScoreReader reader = new highScoreReader("./src/highscoreData/scoreInfo.txt");
        reader.openFile();
        rowdata = reader.readFile();
        bblsort(rowdata);
        table = new JTable(rowdata, columns);
        table.setPreferredScrollableViewportSize(new Dimension(w/2, h));
        table.setFillsViewportHeight(true);  
    }

    private void makeLayout() {
        setLayout(new FlowLayout());
        add(table);
        add(new JScrollPane(table));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
    
    public static void bblsort(String[][] array)
    {
        for(int i = 0 ; i < array.length ; i++)
        {
            for(int j = 1 ; j < array.length-i ; j++)
            {
                if(Integer.parseInt(array[j-1][1]) < Integer.parseInt(array[j][1]))
                {
                    String[] tmp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
}
