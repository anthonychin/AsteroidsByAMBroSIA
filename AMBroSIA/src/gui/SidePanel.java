
package gui;

import game.GameState;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Anthony Chin
 * 
 */
public class SidePanel extends JPanel {
    private GameState gameState;
    public SidePanel(GameState gs){
        this.gameState = gs;
    }
    @Override
    public void paint (Graphics g){
        //super.setBounds(0, 0, 200, 20);
        super.paint(g);
        
        g.drawString("Highscore " + gameState.getHighScore(), 5, 10);
        g.drawString("Lives " + gameState.getPlayerShip().getLives(), 75, 10);
        g.drawString("Level " + gameState.getLevel(), 150, 10);
    }
}
