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
     * @param gs current game state
     * @param img image for two player mode game panel
     */
    public TwoPgamePanel(GameState gs, Image img) {
        super(gs);
        this.gameState = gs;

        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        super.setBounds(0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT);
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        gameDraw.drawObjects(g2, gameState);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Calls paint through swing.
     */
    public void updatePanel() {
        repaint();
    }
    
     // set endgame background image
    /**
     * Sets endgame background image.
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}