
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
public class GameOverPanel extends JPanel {
    
    GameState gamestate;
    
    JTable StatisticsTable;
    JScrollPane scrollPane;
//    public GameOverPanel(int width, int height, GameState gs)
//    {
//        makeComponents(width, height);
//        makeLayout();
//    }

    
    private Image img;
    
    public GameOverPanel(Image img, GameState gs) {
        this.gamestate = gs;
        
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        makeComponents(getWidth(), getHeight());
        makeLayout();
        
        
    }
    
    private void makeComponents(int w, int h)
    {
        String highscore = String.valueOf(gamestate.getCurrentScore());
        String level = String.valueOf(gamestate.getLevel());
        
        String[] columnData = {"", ""};
        String[][] rowData = {{"Player name", "p1"},{"Highscore", highscore},{"Last level", level}};
        StatisticsTable = new JTable(rowData, columnData);
        StatisticsTable.setPreferredScrollableViewportSize(new Dimension(w/2, 3*h/5));
        StatisticsTable.setFillsViewportHeight(true);  
    }
    
    private void makeLayout()
    {
        setLayout(new FlowLayout());
        add(StatisticsTable);
        add(new JScrollPane(StatisticsTable));
    }
    
    @Override
    public void paint (Graphics g){
        super.paint(g);
        g.setColor(Color.red);
        g.setFont(new Font("default", Font.BOLD, 30));
        g.drawString("GAME OVER", this.getWidth()/2-this.getWidth()/16, 95*this.getHeight()/100);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
    
}
