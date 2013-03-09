package gui;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import game.GameState;

/**
 *
 * @author Haisin Yip
 */

public class MenuGUI
{
    
    CardLayout cardLayout;
    JPanel card = new JPanel();
    
    // menu buttons
    public JButton singlePbutton = new JButton("SINGLE-PLAYER MODE");
    public JButton twoPbutton = new JButton("TWO-PLAYER MODE");
    public JButton leaderBoardButton = new JButton("LEADERBOARD");
    public JButton tutorialButton = new JButton("TUTORIAL");
    public JButton quitButton = new JButton("QUIT");
    
    // leaderBoard back button
    JButton backButtonL = new JButton("BACK");
    
    // tutorial back button
    JButton backButtonT = new JButton("BACK");
    
    //main window
    JFrame frame;
    
    
    ActionListener buttonClick;
    KeyListener keyboard;
    /**
     * Starts the GUI Menu.
     * @param AL
     * @param keyb
     */
    public MenuGUI(ActionListener AL, KeyListener keyb)
    {
        // create and initialize frame
        frame = new JFrame("Asteroids");
        JPanel contentPane = (JPanel)frame.getContentPane();
        cardLayout = new CardLayout();
        card.setLayout(cardLayout);
        
        keyboard = keyb;
        
        //allow global access to actionlistener
        buttonClick = AL;
        
        // create menu page with 5 options
        JPanel cardMenu = new JPanel();
        cardMenu.setLayout(new GridLayout(2,1));
        JPanel menuPanel = new MenuPanel();
        cardMenu.add(menuPanel);
        // initialize the 5 buttons that shall be in the menu page
        JPanel buttonPanelMenu = new JPanel();
        buttonPanelMenu.setLayout(new GridLayout(5,1));
        buttonPanelMenu.add(singlePbutton); buttonPanelMenu.add(twoPbutton); buttonPanelMenu.add(leaderBoardButton); buttonPanelMenu.add(tutorialButton); buttonPanelMenu.add(quitButton);
        singlePbutton.addActionListener(buttonClick); twoPbutton.addActionListener(buttonClick); leaderBoardButton.addActionListener(buttonClick); tutorialButton.addActionListener(buttonClick); quitButton.addActionListener(buttonClick);
        cardMenu.add(buttonPanelMenu);
        
        
        card.add("Menu", cardMenu);
        cardLayout.show(card, "Menu");
        
        contentPane.add(card);
        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //NOTE: code below is a bit redundant with logic.  Can we do some form of code reuse?
    public void reactToButton(ActionEvent e, GameState gs)
    {
        frame.addKeyListener(keyboard);
        frame.setFocusable(true);
        
        if(e.getSource() == singlePbutton)
        {
            // create single player mode game page
            JPanel cardGame1P = new JPanel();
            cardGame1P.add(new SinglePgamePanel(gs));
            card.add("Single-Player Mode", cardGame1P);
            cardLayout.show(card, "Single-Player Mode");
            
            //allow keyboard input
        }
        
        else if(e.getSource() == twoPbutton)
        {
            // create single player mode game page (two player)
            JPanel cardGame2P = new JPanel();
            //cardGame.setLayout(new GridLayout(2,1)); not sure how to set layout for actual gameplay
            JPanel twoPgamePanel = new TwoPgamePanel();
            cardGame2P.add(twoPgamePanel);
            card.add("Two-Player Mode", cardGame2P);
            cardLayout.show(card, "Two-Player Mode");
            
            //allow keyboard input
        }
        
        else if(e.getSource() == leaderBoardButton)
        {
              // create leaderboard page
            JPanel cardLeaderBoard = new JPanel();
            cardLeaderBoard.setLayout(new GridLayout(5,1));
            JPanel leaderBoardPanel = new LeaderBoardPanel();
            cardLeaderBoard.add(leaderBoardPanel);
            //initialize a back button
            JPanel buttonPanelLeaderBoard = new JPanel();
            buttonPanelLeaderBoard.add(backButtonL); backButtonL.addActionListener(buttonClick);
            cardLeaderBoard.add(buttonPanelLeaderBoard);
            card.add("LeaderBoard", cardLeaderBoard);
            cardLayout.show(card, "LeaderBoard");
            
            // Leaderboard should not take keyboard input
            frame.setFocusable(false);
        }
        
        else if(e.getSource() == tutorialButton)
        {
            // create tutorial page
            JPanel cardTutorial = new JPanel();
            cardTutorial.setLayout(new GridLayout(5,1));
            JPanel tutorialPanel = new TutorialPanel();
            cardTutorial.add(tutorialPanel);
            //initialize a back button
            JPanel buttonPanelTutorial = new JPanel();
            buttonPanelTutorial.add(backButtonT); backButtonT.addActionListener(buttonClick);
            cardTutorial.add(buttonPanelTutorial);
            card.add("Tutorial", cardTutorial);
            cardLayout.show(card, "Tutorial");
            
            // Tutorial should not take keyboard input
            frame.setFocusable(false);
        }
        
        else if(e.getSource() == quitButton)
        {
            System.exit(0);
        }
        
        else if(e.getSource() == backButtonL)
        {
            cardLayout.show(card, "Menu");
            // Menu should not take keyboard input
            frame.setFocusable(false);
        }
        
        else if(e.getSource() == backButtonT)
        {
            cardLayout.show(card, "Menu");
            // Menu should not take keyboard input
            frame.setFocusable(false);
        }
    }
}
