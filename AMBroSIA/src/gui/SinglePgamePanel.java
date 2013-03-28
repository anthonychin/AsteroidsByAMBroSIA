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
public class SinglePgamePanel extends SidePanel {

    private Image img;
    private GameState gameState;
    // initialize side panel showing the player's current score, lives and current level
    JPanel sidePanel;
    JLabel highscore, lives, level;
    JButton pause;
    static String score;
    // constructor

    public SinglePgamePanel(GameState gs, Image img) {
        super(gs);
        this.gameState = gs;
        
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    // create Single Player Mode panel's internal components
    // the label components takes as input the current score, current level, and current lifestock
    // paints content onto the Single-Player mode panel
    @Override
    public void paint(Graphics g) {
        super.setBounds(0, 0, MenuGUI.WIDTH, MenuGUI.HEIGHT);
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.WHITE);

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
