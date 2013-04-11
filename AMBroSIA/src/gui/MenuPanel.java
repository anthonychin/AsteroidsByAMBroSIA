package gui;

import game.GameAssets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * The
 * <code>MenuPanel</code> class draws the image for the menu.
 *
 * @author Haisin Yip
 * @author Anthony Chin
 */
public class MenuPanel extends JPanel {

    private Image img = GameAssets.menuImage;

    //set up image location, size, etc.
    /**
     * Constructor which sets up image location, size, etc.
     */
    public MenuPanel() {
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(new BorderLayout());
    }

    //draw image
    /**
     * Draws image.
     *
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT, this);
    }
}
