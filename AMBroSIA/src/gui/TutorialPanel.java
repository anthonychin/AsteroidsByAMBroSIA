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

    //create panel
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

    // set endgame background image
    /**
     * Sets endgame background image.
     *
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
    }
}
