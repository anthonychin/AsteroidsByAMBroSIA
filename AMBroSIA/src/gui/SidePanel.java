package gui;

import game.GameState;
import game.PlayerShip;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Anthony Chin
 *
 */
public class SidePanel extends JPanel {

    private GameState gameState;

    public SidePanel(GameState gs) {
        this.gameState = gs;
    }

    // Method to print the highscore, lives and level
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        String stringInfo;
        stringInfo = "Current Score " + gameState.getHighScore() + " Level " + gameState.getLevel();
        PlayerShip player = gameState.getPlayerShip();
        if (player != null) {
            stringInfo = stringInfo + " Lives " + player.getLives() + " Shields: " + player.getShieldPoints() + " x: " + player.getX() + " y: " + player.getY() + " heading " + Math.abs(player.getHeading()) % 360;
        }
        g.drawString(stringInfo, 5, 10);

        // reset the graphics color to black
        g.setColor(Color.black);
    }
}
