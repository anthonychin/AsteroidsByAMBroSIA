package gui;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Haisin Yip
 */

public class MenuGUI implements ActionListener
{
	
	static CardLayout cardLayout;
	static JPanel card = new JPanel();
	
	// menu buttons
	static JButton singlePbutton = new JButton("SINGLE-PLAYER MODE");
	static JButton twoPbutton = new JButton("TWO-PLAYER MODE");
	static JButton leaderBoardButton = new JButton("LEADERBOARD");
	static JButton tutorialButton = new JButton("TUTORIAL");
	static JButton quitButton = new JButton("QUIT");
	
	// leaderBoard back button
	static JButton backButtonL = new JButton("BACK");
	
	// tutorial back button
	static JButton backButtonT = new JButton("BACK");
	
	public static void main(String[] args)
	{
		// create and initialize frame
		JFrame frame = new JFrame("Asteroids");
		JPanel contentPane = (JPanel)frame.getContentPane();
		cardLayout = new CardLayout();
		card.setLayout(cardLayout);
		
		// create an action listener to listen for button presses in the MenuGUI
		ActionListener AL = new MenuGUI();
		
		// create menu page with 5 options
		JPanel cardMenu = new JPanel();
		cardMenu.setLayout(new GridLayout(2,1));
		JPanel menuPanel = new MenuPanel();
		cardMenu.add(menuPanel);
		// initialize the 5 buttons that shall be in the menu page
		JPanel buttonPanelMenu = new JPanel();
		buttonPanelMenu.setLayout(new GridLayout(5,1));
		buttonPanelMenu.add(singlePbutton); buttonPanelMenu.add(twoPbutton); buttonPanelMenu.add(leaderBoardButton); buttonPanelMenu.add(tutorialButton); buttonPanelMenu.add(quitButton);
		singlePbutton.addActionListener(AL); twoPbutton.addActionListener(AL); leaderBoardButton.addActionListener(AL); tutorialButton.addActionListener(AL); quitButton.addActionListener(AL);
		cardMenu.add(buttonPanelMenu);
		
		// create single player mode game page
		JPanel cardGame1P = new JPanel();
		
		// random highscore, current level and current life fed into SinglePgamePanel 
		JPanel singlePgamePanel = new SinglePgamePanel(1,2,3);
		cardGame1P.add(singlePgamePanel); 
		
		// create two-player mode game page
		JPanel cardGame2P = new JPanel();
		JPanel twoPgamePanel = new TwoPgamePanel();
		cardGame2P.add(twoPgamePanel); 
		
		// create tutorial page
		JPanel cardTutorial = new JPanel();
		cardTutorial.setLayout(new GridLayout(5,1));
		JPanel tutorialPanel = new TutorialPanel();
		cardTutorial.add(tutorialPanel);
		//initialize a back button
		JPanel buttonPanelTutorial = new JPanel();
		buttonPanelTutorial.add(backButtonT); backButtonT.addActionListener(AL);
		cardTutorial.add(buttonPanelTutorial);
		
		// create leaderboard page
		JPanel cardLeaderBoard = new JPanel();
		cardLeaderBoard.setLayout(new GridLayout(5,1));
		JPanel leaderBoardPanel = new LeaderBoardPanel();
		cardLeaderBoard.add(leaderBoardPanel); 
		//initialize a back button
		JPanel buttonPanelLeaderBoard = new JPanel();
		buttonPanelLeaderBoard.add(backButtonL); backButtonL.addActionListener(AL);
		cardLeaderBoard.add(buttonPanelLeaderBoard);
		
		card.add("Menu", cardMenu); card.add("Single-Player Mode", cardGame1P); card.add("Two-Player Mode", cardGame2P); card.add("Tutorial", cardTutorial); card.add("LeaderBoard", cardLeaderBoard); 
		cardLayout.show(card, "Menu");
		
		contentPane.add(card);
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(700, 500);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    }
        
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == singlePbutton)
		{
			cardLayout.show(card, "Single-Player Mode");
		}
		
		if(e.getSource() == twoPbutton)
		{
			cardLayout.show(card, "Two-Player Mode");
		}
		
		if(e.getSource() == leaderBoardButton)
		{
			cardLayout.show(card, "LeaderBoard");
		}
		
		if(e.getSource() == tutorialButton)
		{
			cardLayout.show(card, "Tutorial");
		}
		
		if(e.getSource() == quitButton)
		{
			System.exit(0);
		}
		
		if(e.getSource() == backButtonL)
		{
			cardLayout.show(card, "Menu");
		}
		
		if(e.getSource() == backButtonT)
		{
			cardLayout.show(card, "Menu");
		}
	}
}
