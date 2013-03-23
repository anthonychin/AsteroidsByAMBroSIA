package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */

public class TutorialPanel extends JPanel
{
    
    //    JLabel title;
    //
    //    public TutorialPanel()
    //    {
    //        makeComponents();
    //        makeLayout();
    //    }
    //
    //    private void makeComponents()
    //    {
    //        title = new JLabel("Tutorial");
    //    }
    //
    //    private void makeLayout()
    //    {
    //        setLayout(new GridLayout(1,1));
    //        add(title);
    //    }
    
    private Image img;
    
    public TutorialPanel(String img) {
        this(new ImageIcon(img).getImage());
    }
    
    public TutorialPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
