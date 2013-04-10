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
     * @param gs current game state
     * @param img image for single player game panel
     */
    public SinglePgamePanel(GameState gs, Image img) {
        super(gs);
        this.gameState = gs;

        this.img = img;
        setLayout(null);
    }

    /**
     * Creates SinglePgamePanel with GameState.
     *
     * @param gs current game state
     */
    public SinglePgamePanel(GameState gs) {
        super(gs);
        this.gameState = gs;
    }

    // paints content onto the Single-Player mode panel
    @Override
    public void paint(Graphics g) {
        //don't draw outside screen
        super.setBounds(0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT);

        //perform all required swing painting functions
        super.paint(g);

        //draw all on screen objects
        Graphics2D g2 = (Graphics2D) g;
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
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
