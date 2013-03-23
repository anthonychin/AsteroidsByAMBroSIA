
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
   
    // Method to print the highscore, lives and level
    @Override
    public void paint (Graphics g){
        super.paint(g);
        
        g.drawString("Highscore " + gameState.getHighScore(), 5, 10);
        if(gameState.getPlayerShip() != null){
            g.drawString("Lives " + gameState.getPlayerShip().getLives(), 75, 10);
        }
        else {
            g.drawString("Lives " + 0, 75, 10);    
            //weird issue to fix
            // when there is an if statement checking lives, the game freezes for 2 seconds and generates event erros
            //g.drawString("GAME OVER", 360, 300);
        }
        g.drawString("Level " + gameState.getLevel(), 120, 10);
    }
}
