package gui;

import game.GameState;
import mapObjects.PlayerShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * The
 * <code>DescriptionPanel</code> class prints game information in top left
 * corner. It prints information such as score, lives, levels, etc.
 *
 * @author Haisin Yip
 * @author Anthony Chin
 *
 */
//prints game information in top left corner - score, lives, level, etc.
public class DescriptionPanel extends JPanel {
    // private properties

    private GameState gameState;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    /**
     * Creates DescriptionPanel with given GameState.
     *
     * @param gameState current game state
     */
    public DescriptionPanel(GameState gameState) {
        this.gameState = gameState;
    }

    // Method that actually prints the current score, lives and level
    /**
     * Prints the current score, lives and level.
     *
     * @param graphics graphic that needs to be painted
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g2d = (Graphics2D) graphics;
        //set colors for each player differently
        if (gameState.isPlayerTwoTurn()) {
            g2d.setColor(Color.blue);
        } else {
            g2d.setColor(Color.red);
        }

        // stores the basic player and game information
        String stringInfo;
        stringInfo = "Current Score: " + gameState.getCurrentScore() + "    Level: " + gameState.getLevel();

        //want to make sure player is not null - need to store variable, as player may become null in gameState while printing
        PlayerShip player = gameState.getPlayerShip();
        if (player != null) {
            stringInfo = stringInfo + "    Lives " + player.getLives() + "    Bombs: " + player.getBomb() + "     Shields: " + player.getShieldPoints() + "    X: " + player.getX() + "    Y: " + player.getY() + "    Heading: " + Math.abs(player.getHeading()) % 360;
        }
        g2d.setFont(font);
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        
        g2d.drawString(stringInfo, 5, 20);
        // reset the graphics color to black
        g2d.setColor(Color.black);
    }
}
