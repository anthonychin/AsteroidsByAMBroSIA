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
        
        //allow for keyboard input (but not for the menu)
        frame.addKeyListener(keyboard);
        frame.setFocusable(false);
    }
 
    public void displaySingleP(GameState gs) {
        frame.setFocusable(true);
        // create single player mode game page
        onePPanel = new SinglePgamePanel(gs);
        JPanel cardGame1P = new JPanel();
        cardGame1P.add(onePPanel);
        card.add("Single-Player Mode", cardGame1P);
        cardLayout.show(card, "Single-Player Mode");
        //let other methods know we are in single P mode
        singleP = true;
    }

    public void displayTwoP(GameState gs) {
        frame.setFocusable(true);
        // create single player mode game page (two player)
        twoPPanel = new TwoPgamePanel();
        JPanel cardGame2P = new JPanel();
        //cardGame.setLayout(new GridLayout(2,1)); not sure how to set layout for actual gameplay
        cardGame2P.add(twoPPanel);
        card.add("Two-Player Mode", cardGame2P);
    }
       
    public void displayLeaderBoard() {
        // create leaderboard page
        JPanel cardLeaderBoard = new JPanel();
        cardLeaderBoard.setLayout(new GridLayout(5, 1));
        JPanel leaderBoardPanel = new LeaderBoardPanel();
        cardLeaderBoard.add(leaderBoardPanel);
        //initialize a back button
        JPanel buttonPanelLeaderBoard = new JPanel();
        buttonPanelLeaderBoard.add(backButtonL);
        backButtonL.addActionListener(buttonClick);
        cardLeaderBoard.add(buttonPanelLeaderBoard);
        card.add("LeaderBoard", cardLeaderBoard);
        cardLayout.show(card, "LeaderBoard");
        // Leaderboard should not take keyboard input
    }

    public void displayTutorial() {
        JPanel cardTutorial = new JPanel();
        cardTutorial.setLayout(new GridLayout(5, 1));
        JPanel tutorialPanel = new TutorialPanel();
        cardTutorial.add(tutorialPanel);
        //initialize a back button
        JPanel buttonPanelTutorial = new JPanel();
        buttonPanelTutorial.add(backButtonT);
        backButtonT.addActionListener(buttonClick);
        cardTutorial.add(buttonPanelTutorial);
        card.add("Tutorial", cardTutorial);
        cardLayout.show(card, "Tutorial");
    }
        
    public void goBack() {
        cardLayout.show(card, "Menu");
    }

    public void updateDraw()
    {
//     PLACEHOLDER IF STATEMENT
//        if (singleP)
//        {
            onePPanel.updatePanel();
//        }
//        else
//        {
//            twoPPanel.updatePanel();
//        }
    }

    @Override
    public void run() {
        updateDraw();
    }
}
