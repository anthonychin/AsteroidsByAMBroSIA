package gui;

import game.GameAssets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Draws the image for the menu
 * @author Haisin Yip, Anthony Chin
 */
public class MenuPanel extends JPanel {

    private Image img = GameAssets.menuImage;

    //set up image location, size, etc.
    public MenuPanel() {
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    //draw image
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
