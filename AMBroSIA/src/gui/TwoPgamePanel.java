package gui;

import java.awt.Graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import game.GameState;

/**
 * The <code>TwoPgamePanel</code> class is the interface for 2-player game mode.
 * @author Haisin Yip
 * @author Anthony Chin
 */
public class TwoPgamePanel extends DescriptionPanel {
    
    // private properties
    private Image img;
    private GameState gameState;

    // initialize size and background image in 2 player mode panel 
    /**
     * Creates TwoPgamePanel using given parameters. It initializes size and background image in two player mode panel.
     * @param gameState current game state
     * @param img image for two player mode game panel
     */
    public TwoPgamePanel(GameState gameState, Image img) {
        super(gameState);
        this.gameState = gameState;

        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    /**
     * paint the various in-game elements
     * @param graphic graphics
     */
    @Override
    public void paint(Graphics graphic) {
        super.setBounds(0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT);
        super.paint(graphic);

        Graphics2D g2 = (Graphics2D) graphic;

        gameDraw.drawObjects(g2, gameState);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Calls paint through swing.
     */
    public void updatePanel() {
        repaint();
    }
    
    /**
     * Sets endgame background image.
     * @param graphic graphics
     */
    @Override
    public void paintComponent(Graphics graphic) {
        graphic.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}