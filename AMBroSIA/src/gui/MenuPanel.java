package gui;

import game.GameAssets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
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
     * Constructor which sets up image location, size, borderlayout, etc.
     * @param borderLayout new border layout
     */
    public MenuPanel(BorderLayout borderLayout) {
        super(borderLayout);
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }
    
    //set up image location, size, etc.
    /**
     * Constructor which sets up image location, size, gridlayout,etc.
     * @param gridLayout new grid layout
     */    
    public MenuPanel(GridLayout gridLayout){
        super(gridLayout);
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);        
    }

    //draw image
    /**
     * Draws image.
     *
     * @param graphic graphic
     */
    @Override
    public void paintComponent(Graphics graphic) {
        graphic.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
