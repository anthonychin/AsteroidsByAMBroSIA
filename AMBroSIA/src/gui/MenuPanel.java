import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class MenuPanel extends JPanel
{
	
	JLabel title;
	
	public MenuPanel()
	{
		makeComponents();
		makeLayout();
	}

	private void makeComponents() 
	{
		title = new JLabel("Menu");
	}

	private void makeLayout() 
	{
		setLayout(new GridLayout(1,1));
		add(title);
	}
}
