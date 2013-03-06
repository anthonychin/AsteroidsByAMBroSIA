package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class LeaderBoardPanel extends JPanel
{
	
	JLabel title;
	
	public LeaderBoardPanel()
	{
		makeComponents();
		makeLayout();
	}

	private void makeComponents() 
	{
		title = new JLabel("LeaderBoard");
	}

	private void makeLayout() 
	{
		setLayout(new GridLayout(1,1));
		add(title);
	}
}
