package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */
public class TutorialPanel extends JPanel {

    private Image img;

    
    /**
     * Creates TutorialPanel with Image.
     *
     * @param img image for tutorial panel
     */
    public TutorialPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setLayout(null);
    }

    
    /**
     * Sets background image.
     *
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, this.getWidth()/2-img.getWidth(null)/2,this.getHeight()/2-img.getHeight(null)/2, img.getWidth(null), img.getHeight(null), null);
    }
}
