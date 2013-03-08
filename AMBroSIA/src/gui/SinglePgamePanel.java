package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */

public class SinglePgamePanel extends JPanel
{
    
    // initialize side panel showing the player's current score,lives and current level
    JPanel sidePanel;
    JLabel highscore, lives, level;
    
    public SinglePgamePanel()
    {
	makeComponents();
	makeLayout();
    }

    private void makeComponents() 
    {
        sidePanel = new JPanel();
        highscore = new JLabel("Highscore");
        lives = new JLabel("Lives");
        level = new JLabel("Level");
    }

    private void makeLayout() 
    {
	setLayout(new GridLayout(1,3));
        sidePanel.add(highscore); sidePanel.add(lives); sidePanel.add(level);
        add(sidePanel); 
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        
        g.setColor(Color.BLUE);
        g.fillRect(25, 25, 100, 30);
        
        g.setColor(new Color(190,81,215));
        g.fillRect(25, 65, 100, 30);
        
        g.setColor(Color.RED);
        g.drawString("blaabla", 24, 24);
    }
}
