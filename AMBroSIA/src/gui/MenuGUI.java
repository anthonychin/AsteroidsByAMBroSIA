package gui;

import game.GameAssets;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import game.GameState;
import game.Logic;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Haisin Yip
 * @author Michael Smith
 */
public class MenuGUI implements Runnable {
    //default panel width/height

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    /**
     * Default width of main window.
     */
    public static int WIDTH = DEFAULT_WIDTH;
    /**
     * Default height of main window.
     */
    public static int HEIGHT = DEFAULT_HEIGHT;
    //main window, associated elements
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel card = new JPanel();
    // menu buttons
    /**
     * Menu button for single player mode.
     */
    public JButton singlePbutton = new JButton("SINGLE-PLAYER MODE");
    /**
     * Menu button for two player mode.
     */
    public JButton twoPbutton = new JButton("TWO-PLAYER MODE");
    /**
     * Menu button for leaderboard.
     */
    public JButton leaderBoardButton = new JButton("LEADERBOARD");
    /**
     * Menu button for tutorial.
     */
    public JButton tutorialButton = new JButton("TUTORIAL");
    /**
     * Menu button for quit.
     */
    public JButton quitButton = new JButton("QUIT");
    //back button for both leaderboard and tutorial
    /**
     * Menu button for back in leaderboard and tutorial.
     */
    public JButton backButton = new JButton("BACK");
    //keyboard, button press handlers
    private ActionListener buttonClick;
    private KeyListener keyboard;
    //whether or not we are in single player mode
    private boolean singleP = false;
    //Panels for single and 2 player modes
    private SinglePgamePanel onePPanel;
    private TwoPgamePanel twoPPanel;
    //Panel for leaderboard
    private LeaderBoardPanel leaderBoardPanel;
    //Panel for endgame
    private EndGamePanel gameOverPanel;
    private final static Logger log = Logger.getLogger(MenuGUI.class.getName());

    /**
     * Starts the GUI Menu.
     *
     * @param AL input from ActionListener
     * @param keyb input from KeyListener
     */
    public MenuGUI(ActionListener AL, KeyListener keyb) {
        try {
            //use system theme: windows/mac/linux: looks better
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        log.setLevel(Logic.LOG_LEVEL);

        // create and initialize frame
        frame = new JFrame("AMBroSIA");
        cardLayout = new CardLayout();
        card.setLayout(cardLayout);
        frame.getContentPane().add(card);

        //allow global access to actionlistener,keyboard listener
        buttonClick = AL;
        keyboard = keyb;

        //set important parameters
        frame.setVisible(true);
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        //maximize game, and update the size to match
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        updateSize();

        //set up (but do not yet allow) keyboard input
        frame.addKeyListener(keyboard);
        frame.setFocusable(false);
    }

    /**
     * Displays menu.
     */
    public void showMenu() {
        //disable keyboard input
        frame.setFocusable(false);
        // create menu page panel, set it up, show it
        //JPanel cardMenu = new JPanel(new GridLayout(2, 1));
        JPanel cardMenu = new JPanel(new GridLayout(1, 1));

        cardMenu.add(new MenuPanel());


        // initialize the 5 buttons that shall be in the menu page
        JPanel buttonPanelMenu = new JPanel(new GridLayout(5, 1));

        buttonPanelMenu.add(singlePbutton);
        buttonPanelMenu.add(twoPbutton);
        buttonPanelMenu.add(leaderBoardButton);
        buttonPanelMenu.add(tutorialButton);
        buttonPanelMenu.add(quitButton);



        singlePbutton.setBackground(Color.DARK_GRAY);
        twoPbutton.setBackground(Color.DARK_GRAY);
        leaderBoardButton.setBackground(Color.DARK_GRAY);
        tutorialButton.setBackground(Color.DARK_GRAY);
        quitButton.setBackground(Color.DARK_GRAY);

        singlePbutton.setForeground(Color.pink);
        twoPbutton.setForeground(Color.pink);
        leaderBoardButton.setForeground(Color.pink);
        tutorialButton.setForeground(Color.pink);
        quitButton.setForeground(Color.pink);

        singlePbutton.addActionListener(buttonClick);
        twoPbutton.addActionListener(buttonClick);
        leaderBoardButton.addActionListener(buttonClick);
        tutorialButton.addActionListener(buttonClick);
        quitButton.addActionListener(buttonClick);

        //show menu
        cardMenu.add(buttonPanelMenu);
        card.add("Menu", cardMenu);
        cardLayout.show(card, "Menu");

        //allow user to resize
        frame.setResizable(true);
    }

    //show single player interface
    /**
     * Displays single player mode interface.
     *
     * @param gs current game state
     */
    public void displaySingleP(GameState gs) {
        //let other methods know we are in single P mode
        singleP = true;
        //allow keyboard input
        frame.setFocusable(true);
        //create panel, show it
        onePPanel = new SinglePgamePanel(gs, GameAssets.spaceBackground);
        JPanel cardGame1P = new JPanel();
        cardGame1P.add(onePPanel);
        card.add("Single-Player Mode", cardGame1P);
        cardLayout.show(card, "Single-Player Mode");

        //do not allow resizing at this point, as it can disrupt gameplay
        frame.setResizable(false);
    }

    //show two player interface
    /**
     * Displays two player mode interface.
     *
     * @param gs current game state
     */
    public void displayTwoP(GameState gs) {
        //let other methods know we are in Two P mode.  Virtually identical code to single player above
        singleP = false;
        frame.setFocusable(true);
        twoPPanel = new TwoPgamePanel(gs, GameAssets.spaceBackground);
        JPanel cardGame2P = new JPanel();
        cardGame2P.add(twoPPanel);
        card.add("Two-Player Mode", cardGame2P);
        cardLayout.show(card, "Two-Player Mode");
        frame.setResizable(false);
    }

    /**
     * Displays leaderboard screen.
     *
     * @param gs current game state
     */
    public void displayLeaderBoard(GameState gs) {
        leaderBoardPanel = new LeaderBoardPanel(GameAssets.spaceBackground, gs);
        JPanel cardLeaderBoard = new JPanel();
        cardLeaderBoard.setLayout(new BorderLayout());
        cardLeaderBoard.add(leaderBoardPanel);

        //initialize a back button
        JPanel buttonPanelLeaderBoard = new JPanel();
        buttonPanelLeaderBoard.add(backButton);
        buttonPanelLeaderBoard.setBackground(Color.black);
        backButton.addActionListener(buttonClick);

        //show panel
        cardLeaderBoard.add(buttonPanelLeaderBoard, BorderLayout.SOUTH);
        card.add("LeaderBoard", cardLeaderBoard);
        cardLayout.show(card, "LeaderBoard");
    }

    //show tutorial screen
    /**
     * Display tutorial screen.
     */
    public void displayTutorial() {
        //create panel
        MenuPanel cardTutorial = new MenuPanel();
        cardTutorial.setLayout(new BorderLayout());
        JPanel tutorialPanel = new TutorialPanel(GameAssets.tutorialImage);
        cardTutorial.add(tutorialPanel, BorderLayout.NORTH);

        //initialize a back button
        JPanel buttonPanelTutorial = new JPanel();
        buttonPanelTutorial.add(backButton);
        buttonPanelTutorial.setBackground(Color.black);
        backButton.addActionListener(buttonClick);
        cardTutorial.add(buttonPanelTutorial, BorderLayout.SOUTH);
        //cardTutorial.setBackground(Color.white);

        //show it
        card.add("Tutorial", cardTutorial);
        cardLayout.show(card, "Tutorial");
    }

    //display game over screen
    /**
     * Display game over screen.
     *
     * @param gs current game state
     * @param mode boolean value that determines whether its single player mode
     * or two player mode
     */
    public void displayGameOver(GameState gs, boolean mode) {
        //create panel
        gameOverPanel = new EndGamePanel(GameAssets.gameOverImage, gs, mode);
        JPanel cardGameOver = new JPanel();
        cardGameOver.setLayout(new BorderLayout());
        cardGameOver.add(gameOverPanel);

        //initialize a back button
        JPanel buttonPanelGameOver = new JPanel();
        buttonPanelGameOver.add(backButton);
        buttonPanelGameOver.setBackground(Color.black);
        backButton.addActionListener(buttonClick);
        cardGameOver.add(buttonPanelGameOver, BorderLayout.SOUTH);
        cardGameOver.setBackground(Color.white);
        card.add("GameOver", cardGameOver);
        cardLayout.show(card, "GameOver");
    }

    //essentially the back button
    /**
     * The back button.
     */
    public void goBack() {
        cardLayout.show(card, "Menu");
        //don't want keyboard working in menu
        frame.setFocusable(false);
    }

    //draw the game
    /**
     * Draws the game.
     */
    public void updateDraw() {
        if (singleP) {
            onePPanel.updatePanel();
        } else {
            twoPPanel.updatePanel();
        }
    }

    //for gui thread: update the window size variables, as well as the screen
    /**
     * Update the window size variables as well as the screen.
     */
    @Override
    public void run() {
        log.info("GUI updating");
        updateSize();
        updateDraw();
    }

    private void updateSize() {
        MenuGUI.WIDTH = frame.getWidth();
        MenuGUI.HEIGHT = frame.getHeight();
    }
}
