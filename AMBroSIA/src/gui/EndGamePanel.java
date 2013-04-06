
package gui;

import game.GameState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 *
 * @author Haisin Yip
 * 
 */
public class EndGamePanel extends JPanel {
    
    GameState gamestate;
    
    JTable StatisticsTable;
    JScrollPane scrollPane;
    
    private Image img;
    
    public EndGamePanel(Image img, GameState gs, boolean singleP) 
    {
        this.gamestate = gs;
        
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        makeComponents(getWidth(), getHeight(), singleP);
        makeLayout(); 
    }
    
    private void makeComponents(int w, int h, boolean singleP)
    {
        String player, highscore, level;
        
        if(singleP)
        { 
            highscore = String.valueOf(gamestate.getCurrentScore());
            level = String.valueOf(gamestate.getLevel());
            player = "p1";
            
        }
        
        else
        {
            int highscoreP1 = gamestate.getPlayer1Score();
            String levelP1 = String.valueOf(gamestate.getPlayer1Level());
            int highscoreP2 = gamestate.getPlayer2Score();
            String levelP2 = String.valueOf(gamestate.getPlayer2Level());
            
            if(highscoreP1 >= highscoreP2)
            {
                highscore = String.valueOf(highscoreP1);
                player = "p1";
                level = levelP1;
            }
            
            else
            {
                highscore = String.valueOf(highscoreP2);
                player = "p2";
                level = levelP2;
            }
        }
        
        String[] columnData = {"", ""};
        String[][] rowData = {{"Player name", player},{"Highscore", highscore},{"Last level", level}};
        StatisticsTable = new JTable(rowData, columnData);
        StatisticsTable.setPreferredScrollableViewportSize(new Dimension(w/2, h/12));
        StatisticsTable.setFillsViewportHeight(true);  
    }
    
    private void makeLayout()
    {
        setLayout(new FlowLayout());
        add(StatisticsTable);
        add(new JScrollPane(StatisticsTable));
    }
    
//    @Override
//    public void paint (Graphics g){
//        super.paint(g);
//        g.setColor(Color.red);
//        g.setFont(new Font("default", Font.BOLD, 100));
//        g.drawString("GAME OVER", this.getWidth()/3, this.getHeight()/2);
//    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
    
}
