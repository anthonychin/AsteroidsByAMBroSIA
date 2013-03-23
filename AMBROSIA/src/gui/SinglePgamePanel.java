package gui;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.GameState;
import java.awt.Graphics2D;
import java.awt.Toolkit;

/**
 *
 * @author Haisin Yip, Anthony Chin
 */

public class SinglePgamePanel extends JPanel
{
    private GameState gameState;
    
    // initialize side panel showing the player's current score, lives and current level
    JPanel sidePanel;
    JLabel highscore, lives, level;
    JButton pause;

    
    static String score;
    // constructor
    public SinglePgamePanel(GameState gs)
    {
        this.gameState = gs;
        makeComponents();
        makeLayout();
    }
    
    // create Single Player Mode panel's internal components
    // the label components takes as input the current score, current level, and current lifestock
    private void makeComponents()
    {
        score = String.valueOf(gameState.getHighScore()); 
        String lvl = String.valueOf(gameState.getLevel()); 
        String life = String.valueOf(gameState.getPlayerShip().getLives());
        sidePanel = new JPanel();
        highscore = new JLabel("Current Score: " + score);
        lives = new JLabel("Life Stock: " + life);
        level = new JLabel("Current Level: " + lvl);
    }
    
    // initializes layout for Single Player Mode panel's internal components
    private void makeLayout()
    {
        setLayout(new FlowLayout(0, 1, 1));
        sidePanel.setBackground(Color.GREEN);
        sidePanel.add(highscore); 
        sidePanel.add(lives); 
        sidePanel.add(level);
        add(sidePanel);
    }
    
    // paints content onto the Single-Player mode panel
    
    @Override
    public void paint(Graphics g)
    {
        super.setBounds(0,0,MenuGUI.WIDTH,MenuGUI.HEIGHT);
        super.paint(g);
        
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        
//        g.drawString("Highscore" + gameState.getHighScore(), 5, 10);
//        g.drawString("Lives" + gameState.getPlayerShip().getLives(), 75, 10);
//        g.drawString("Level" + gameState.getLevel(), 150, 10);
        
        gameDraw.drawObjects(g2, gameState);
        //seems to reduce stuttering a bit. See http://docs.oracle.com/javase/7/docs/api/java/awt/Toolkit.html#sync%28%29
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void updatePanel()
    {
        repaint();
    }
}
