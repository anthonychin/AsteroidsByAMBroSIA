
package gui;

import game.GameState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
    String[] columnData = {"", ""};
    //String highscore = String.valueOf(gamestate.getCurrentScore());
    //
    String[][] rowData = {{"Player name", "p1"},{"Highscore", "highscore"},{"Number of Lives", "5"}};
    
    JScrollPane scrollPane;
//    public GameOverPanel(int width, int height, GameState gs)
//    {
//        makeComponents(width, height);
//        makeLayout();
//    }
//    public GameOverPanel(GameState gs)
//    {
//        super(gs);
//        this.gamestate = gs;
//    }
    
    
    private void makeComponents(int w, int h)
    {
        StatisticsTable = new JTable(rowData, columnData);
        StatisticsTable.setPreferredScrollableViewportSize(new Dimension(w-40, h-110));
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
        System.out.println("paintme");
        g.setColor(Color.red);
        g.drawString("GAME OVER", getWidth()/2, getHeight()/2);
           
        // reset the graphics color to black
        g.setColor(Color.black);
    }
    
    public void updateScreen()
    {
        System.out.println("update screen");
        repaint();
    }
    
}
