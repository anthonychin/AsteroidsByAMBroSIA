package gui;

import java.awt.Graphics;

import game.GameState;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * The single player interface
 * @author Haisin Yip
 */
public class SinglePgamePanel extends DescriptionPanel {

    private Image img;
    private GameState gameState;

    // set up panel
    public SinglePgamePanel(GameState gs, Image img) {
        super(gs);
        this.gameState = gs;

        this.img = img;
        setLayout(null);
    }

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
    public void updatePanel() {
        repaint();
    }

    //draw a background (only at the beginning)
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
