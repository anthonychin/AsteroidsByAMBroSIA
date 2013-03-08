package gui;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */

public class SinglePgamePanel extends JPanel
{
    private GameSate gameState;
    
    // initialize side panel showing the player's current score, lives and current level
    JPanel sidePanel;
    JLabel highscore, lives, level;
    JButton pause;
    
    // constructor
    public SinglePgamePanel(GameState gs)
    {
        makeComponents(gs);
        makeLayout();
    }
    
    // create Single Player Mode panel's internal components
    // the label components takes as input the current score, current level, and current lifestock
    private void makeComponents(GameState gs)
    {
        String score = String.valueOf(gs.getHighScore()); String lvl = String.valueOf(gs.getLevel()); String life = String.valueOf(gs.get);
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
        super.setBounds(0,0,700,500);
        
        this.setBackground(Color.WHITE);
    }
}
