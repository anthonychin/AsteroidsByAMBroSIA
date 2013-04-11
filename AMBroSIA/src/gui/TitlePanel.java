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
    
    /**
     * Constructor which sets up image location, size, etc
     * @param gridLayout new grid layout
     */
    public TitlePanel(GridLayout gridLayout){
        super(gridLayout);
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
        
        graphic.drawImage(img, 0, 0, this.getMinimumSize().width, this.getMinimumSize().height, this);
        graphic.setFont(font);
        graphic.setColor(Color.red);
        graphic.drawString("Anthony Chin", 10, 100);
        graphic.drawString("Haisin Yip", 10, 130);
        graphic.drawString("Meong Hee Seo", 10, 160);
        graphic.drawString("Michael Smith", 10, 190);
        graphic.drawString("Nikolaos Bukas", 10, 220);
    }
}
