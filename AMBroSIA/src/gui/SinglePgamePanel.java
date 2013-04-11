package gui;

import java.awt.Graphics;

import game.GameState;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * The
 * <code>SinglePgamePanel</code> class is the interface for single player mode.
 *
 * @author Haisin Yip
 */
public class SinglePgamePanel extends DescriptionPanel {

    private Image img;
    private GameState gameState;

    // set up panel
    /**
     * Creates SinglePgamePanel with given parameters.
     *
     * @param gameState current game state
     * @param img image for single player game panel
     */
    public SinglePgamePanel(GameState gameState, Image img) {
        super(gameState);
        this.gameState = gameState;

        this.img = img;
        setLayout(null);
    }

    /**
     * Creates SinglePgamePanel with GameState.
     *
     * @param gameState current game state
     */
    public SinglePgamePanel(GameState gameState) {
        super(gameState);
        this.gameState = gameState;
    }

    // paints content onto the Single-Player mode panel
    @Override
    public void paint(Graphics graphic) {
        //don't draw outside screen
        super.setBounds(0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT);

        //perform all required swing painting functions
        super.paint(graphic);

        //draw all on screen objects
        Graphics2D g2 = (Graphics2D) graphic;
        gameDraw.drawObjects(g2, gameState);

        //seems to reduce stuttering a bit. See http://docs.oracle.com/javase/7/docs/api/java/awt/Toolkit.html#sync%28%29
        Toolkit.getDefaultToolkit().sync();
    }

    //call paint through swing
    /**
     * Calls paint through swing.
     */
    public void updatePanel() {
        repaint();
    }

    //draw a background (only at the beginning)
    /**
     * Draws a background (only at the beginning).
     *
     * @param graphic graphics
     */
    @Override
    public void paintComponent(Graphics graphic) {
        graphic.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
