package gui;

import game.GameAssets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Anthony Chin
 */
public class TitlePanel extends JPanel{

    private Image img = GameAssets.titleImage;
    private Font font = new Font(Font.MONOSPACED,Font.PLAIN,30);

    
    /**
     * Constructor which sets up image location, size, etc.
     */
    public TitlePanel() {
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }
    
    public TitlePanel(GridLayout gd){
        super(gd);
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);        
    }

    /**
     * Draws image.
     *
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        
        g.drawImage(img, 0, 0, this.getMinimumSize().width, this.getMinimumSize().height, this);
        g.setFont(font);
        g.setColor(Color.red);
        g.drawString("Anthony Chin", 10, 100);
        g.drawString("Haisin Yip", 10, 130);
        g.drawString("Meong Hee Seo", 10, 160);
        g.drawString("Michael Smith", 10, 190);
        g.drawString("Nikolaos Bukas", 10, 220);
    }
}
