import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class TutorialPanel extends JPanel
{
	
	JLabel title;
	
	public TutorialPanel()
	{
		makeComponents();
		makeLayout();
	}

	private void makeComponents() 
	{
		title = new JLabel("Tutorial");
	}

	private void makeLayout() 
	{
		setLayout(new GridLayout(1,1));
		add(title);
	}
}
