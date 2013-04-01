
package gui;

import game.GameState;
import game.PlayerShip;
import game.Progression;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Haisin Yip
 * @author Anthony Chin
 * 
 */
public class DescriptionPanel extends JPanel {
    private GameState gameState;
    public DescriptionPanel(GameState gs){
        this.gameState = gs;
    }
   
    // Method to print the current score, lives and level
    @Override
    public void paint (Graphics g){
        super.paint(g);
        if(Progression.playerOneTurn){
            g.setColor(Color.red);
        }
        else{
            g.setColor(Color.blue);
        }
        String stringInfo;
        stringInfo = "Current Score " + gameState.getCurrentScore() + " Level " + gameState.getLevel();
        PlayerShip player = gameState.getPlayerShip();
        if (player != null) {
            stringInfo = stringInfo + " Lives " + player.getLives() + " Shields: " + player.getShieldPoints() + " x: " + player.getX() + " y: " + player.getY() + " heading: " + Math.abs(player.getHeading()) % 360;
        }
        g.drawString(stringInfo, 5, 10);
        // reset the graphics color to black
        g.setColor(Color.black);
    }
}
