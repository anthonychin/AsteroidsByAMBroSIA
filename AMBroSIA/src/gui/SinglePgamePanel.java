package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.GameState;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author Haisin Yip
 */
public class SinglePgamePanel extends DescriptionPanel {

    private Image img;
    private GameState gameState;
    
    // constructor
    public SinglePgamePanel(GameState gs, Image img) {
        super(gs);
        this.gameState = gs;
   
        this.img = img;
        setLayout(null);
    }
    
    public SinglePgamePanel(GameState gs)
    {
        super(gs);
        this.gameState = gs;
    }

    // paints content onto the Single-Player mode panel
    @Override
    public void paint(Graphics g) {
        super.setBounds(0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT);
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        gameDraw.drawObjects(g2, gameState);
        //seems to reduce stuttering a bit. See http://docs.oracle.com/javase/7/docs/api/java/awt/Toolkit.html#sync%28%29

        Toolkit.getDefaultToolkit().sync();
    }

    public void updatePanel() {
        repaint();
    }
    
   @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
