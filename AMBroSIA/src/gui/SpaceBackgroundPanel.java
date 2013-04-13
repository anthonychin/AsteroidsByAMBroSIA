package gui;

import game.GameAssets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * The
 * <code>StarBackGroundPanel</code> class draws the image for the menu.
 *
 * @author Anthony Chin
 */
public class SpaceBackgroundPanel extends JPanel {

    private Image img = GameAssets.spaceBackground;
    //set up image location, size, etc.

    /**
     * Constructor which sets up image location, size, etc.
     *
     * @param borderLayout new border layout
     */
    public SpaceBackgroundPanel(BorderLayout borderLayout) {
        super(borderLayout);
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    /**
     * Draws image.
     *
     * @param graphic graphics
     */
    @Override
    public void paintComponent(Graphics graphic) {
        graphic.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
