package gui;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */

public class SinglePgamePanel extends JPanel
{
    
    // initialize side panel showing the player's current score,lives and current level
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
    }
    
    public SinglePgamePanel()
    {
	makeComponents();
	makeLayout();
    }

    private void makeLayout() 
    {
		
    }

    private void makeComponents() 
    {
		
    }
}
