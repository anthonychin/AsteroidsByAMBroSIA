package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */

public class SinglePgamePanel extends JPanel
{
    
    // initialize side panel showing the player's current score, lives and current level
    JPanel sidePanel;
    JLabel highscore, lives, level;
    JButton pause;
    
    // constructor
    public SinglePgamePanel(int curScore, int curLvl, int curLife)
    {
        makeComponents(curScore, curLvl, curLife);
        makeLayout();
    }
    
    // create Single Player Mode panel's internal components
    private void makeComponents(int curScore, int curLvl, int curLife)
    {
        sidePanel = new JPanel();
        highscore = new JLabel("curScore");
        lives = new JLabel("curLife");
        level = new JLabel("curLvl");
    }
    
    // initializes layout for Single Player Mode panel's internal components
    private void makeLayout()
    {
        setLayout(new FlowLayout(0, 1, 1));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.add(highscore); sidePanel.add(lives); sidePanel.add(level);
        add(sidePanel);
    }
    
    //
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.setBounds(0,0,700,500);
        
        this.setBackground(Color.WHITE);
        g.setColor(Color.RED);
        g.drawString("Hello World", 350, 250);
        
    }
}
