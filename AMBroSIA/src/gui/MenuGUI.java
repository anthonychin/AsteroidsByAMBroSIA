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

public class MenuGUI implements Runnable
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    private CardLayout cardLayout;
    private JPanel card = new JPanel();
    
    // menu buttons
    public JButton singlePbutton = new JButton("SINGLE-PLAYER MODE");
    public JButton twoPbutton = new JButton("TWO-PLAYER MODE");
    public JButton leaderBoardButton = new JButton("LEADERBOARD");
    public JButton tutorialButton = new JButton("TUTORIAL");
    public JButton quitButton = new JButton("QUIT");
    
    // leaderBoard back button
    public JButton backButtonL = new JButton("BACK");
    
    // tutorial back button
    public JButton backButtonT = new JButton("BACK");
    
    //main window
    private JFrame frame;
    
    private ActionListener buttonClick;
    private KeyListener keyboard;
    
    //whether or not we are in single player mode
    private boolean singleP = false;
    
    //Panels for single and 2 player modes
    private SinglePgamePanel onePPanel;
    private TwoPgamePanel twoPPanel;
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
        
        //allow global access to actionlistener,keyboard listener
        buttonClick = AL;
        keyboard = keyb;
        
        // create menu page with 5 options
        JPanel cardMenu = new JPanel();
        cardMenu.setLayout(new GridLayout(2,1));
        cardMenu.add(new MenuPanel());
        // initialize the 5 buttons that shall be in the menu page
        JPanel buttonPanelMenu = new JPanel();
        buttonPanelMenu.setLayout(new GridLayout(5,1));
        buttonPanelMenu.add(singlePbutton); buttonPanelMenu.add(twoPbutton); buttonPanelMenu.add(leaderBoardButton); buttonPanelMenu.add(tutorialButton); buttonPanelMenu.add(quitButton);
        singlePbutton.addActionListener(buttonClick); twoPbutton.addActionListener(buttonClick); leaderBoardButton.addActionListener(buttonClick); tutorialButton.addActionListener(buttonClick); quitButton.addActionListener(buttonClick);
        cardMenu.add(buttonPanelMenu);
        
        
        card.add("Menu", cardMenu);
        cardLayout.show(card, "Menu");
        
        //show menu
        contentPane.add(card);
        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    //TODO: reduce code redudancy from logic
    public void reactToButton(ActionEvent e, GameState gs)
    {
        frame.addKeyListener(keyboard);
        frame.setFocusable(true);
        
        if(e.getSource() == singlePbutton)
        {
            // create single player mode game page
            onePPanel = new SinglePgamePanel(gs);
            JPanel cardGame1P = new JPanel();
            cardGame1P.add(onePPanel);
            card.add("Single-Player Mode", cardGame1P);
            cardLayout.show(card, "Single-Player Mode");
            //let other methods know we are in single P mode
            singleP = true;
        }
        
        else if(e.getSource() == twoPbutton)
        {
            // create single player mode game page (two player)
            twoPPanel = new TwoPgamePanel();
            JPanel cardGame2P = new JPanel();
            //cardGame.setLayout(new GridLayout(2,1)); not sure how to set layout for actual gameplay
            cardGame2P.add(twoPPanel);
            card.add("Two-Player Mode", cardGame2P);
            cardLayout.show(card, "Two-Player Mode");
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
    
    public void updateDraw()
    {
        if (singleP)
        {
            onePPanel.updatePanel();
        }
        else
        {
            twoPPanel.updatePanel();
        }
    }

    @Override
    public void run() {
        updateDraw();
    }
}
