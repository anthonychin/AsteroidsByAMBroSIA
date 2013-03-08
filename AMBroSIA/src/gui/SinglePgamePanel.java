package gui;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.GameState;

/**
 *
 * @author Haisin Yip
 */

public class SinglePgamePanel extends JPanel
{
    private GameState gameState;
    
    // initialize side panel showing the player's current score, lives and current level
    JPanel sidePanel;
    JLabel highscore, lives, level;
    JButton pause;
    
    // constructor
    public SinglePgamePanel(GameState gs)
    {
        gameState = gs;
        makeComponents();
        makeLayout();
    }
    
    // create Single Player Mode panel's internal components
    // the label components takes as input the current score, current level, and current lifestock
    private void makeComponents()
    {
        String score = String.valueOf(gameState.getHighScore()); String lvl = String.valueOf(gameState.getLevel()); String life = String.valueOf(gameState.getPlayerShip().getLives());
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
        sidePanel.add(highscore); sidePanel.add(lives); sidePanel.add(level);
        add(sidePanel);
    }
    
    // paints content onto the Single-Player mode panel
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.setBounds(0,0,800,600);
        
        g.setColor(Color.red);
        g.drawString("Hello World", 220, 200);
        this.setBackground(Color.WHITE);
    }
}
