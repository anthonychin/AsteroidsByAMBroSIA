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
import java.awt.Font;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

/**
 * The
 * <code>MenuGUI</code> class is the main GUI class. Responsible for the main
 * menu and the initialization of other GUI elements, such as single player mode
 * or the leaderboard.
 *
 * Also provides high-level access to methods responsible for redrawing the
 * screen.
 *
 * @author Haisin Yip
 * @author Michael Smith
 * @author Anthony Chin
 */
public class MenuGUI implements Runnable {
    //default panel width/height

    private final int FONT_SIZE = 30;
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
            e.printStackTrace();
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
        MenuPanel cardMenu = new MenuPanel(new GridLayout(2, 2));

        // initialize the 5 buttons that shall be in the menu page
        JPanel buttonPanelMenu = new JPanel(new GridLayout(5, 1));
        makeJButtonPanel(buttonPanelMenu);

        buttonPanelMenu.setOpaque(false);

        JPanel filler = new JPanel();
        filler.setOpaque(false);
        JPanel filler2 = new JPanel();
        filler2.setOpaque(false);
        JPanel filler3 = new JPanel();
        filler3.setOpaque(false);
//        JPanel filler4 = new JPanel();
//        filler4.setOpaque(false);
//        JPanel filler5 = new JPanel();
//        filler5.setOpaque(false);

        cardMenu.add(new TitlePanel());
        cardMenu.add(filler2);
        cardMenu.add(filler3);
        //cardMenu.add(filler4);
        cardMenu.add(buttonPanelMenu);
        //cardMenu.add(filler5);

        card.add("Menu", cardMenu);

        cardLayout.show(card, "Menu");
        //allow user to resize
        frame.setResizable(true);
    }

    //show single player interface
    /**
     * Displays single player mode interface.
     *
     * @param gameState current game state
     */
    public void displaySingleP(GameState gameState) {
        //let other methods know we are in single P mode
        singleP = true;
        //allow keyboard input
        frame.setFocusable(true);
        //create panel, show it
        onePPanel = new SinglePgamePanel(gameState, GameAssets.spaceBackground);
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
     * @param gameState current game state
     */
    public void displayTwoP(GameState gameState) {
        //let other methods know we are in Two P mode.  Virtually identical code to single player above
        singleP = false;
        frame.setFocusable(true);
        twoPPanel = new TwoPgamePanel(gameState, GameAssets.spaceBackground);
        JPanel cardGame2P = new JPanel();
        cardGame2P.add(twoPPanel);
        card.add("Two-Player Mode", cardGame2P);
        cardLayout.show(card, "Two-Player Mode");
        frame.setResizable(false);
    }

    /**
     * Displays leaderboard screen.
     *
     * @param gameState current game state
     */
    public void displayLeaderBoard(GameState gameState) {
        leaderBoardPanel = new LeaderBoardPanel(GameAssets.spaceBackground, gameState);
        SpaceBackgroundPanel cardLeaderBoard = new SpaceBackgroundPanel(new BorderLayout());
        cardLeaderBoard.add(leaderBoardPanel);

        //initialize a back button
        JPanel buttonPanelLeaderBoard = new JPanel();
        buttonPanelLeaderBoard.setOpaque(false);
        decorateButton(backButton);
        buttonPanelLeaderBoard.add(backButton);
        backButton.addActionListener(buttonClick);

        //show panel
        cardLeaderBoard.add(buttonPanelLeaderBoard, BorderLayout.SOUTH);
        card.add("LeaderBoard", cardLeaderBoard);
        cardLayout.show(card, "LeaderBoard");
    }

    /**
     * Display tutorial screen.
     */
    public void displayTutorial() {
        //create panel
        SpaceBackgroundPanel cardTutorial = new SpaceBackgroundPanel(new BorderLayout());
        JPanel tutorialPanel = new TutorialPanel(GameAssets.tutorialImage);
        cardTutorial.add(tutorialPanel);
        //initialize a back button
        JPanel buttonPanelTutorial = new JPanel();
        buttonPanelTutorial.setOpaque(false);
        decorateButton(backButton);
        buttonPanelTutorial.add(backButton);
        buttonPanelTutorial.setBackground(Color.black);
        backButton.addActionListener(buttonClick);
        cardTutorial.add(buttonPanelTutorial, BorderLayout.SOUTH);
        //cardTutorial.setBackground(Color.white);

        //show it
        card.add("Tutorial", cardTutorial);
        cardLayout.show(card, "Tutorial");
    }

    /**
     * Display game over screen.
     *
     * @param gameState current game state
     * @param playerOneTurn boolean value representing which player is playing
     * in two player mode, and in single player mode, it is true by default
     * @param Esc boolean is for checking if the escape key is pressed
     */
    public void displayGameOver(GameState gameState, boolean playerOneTurn, boolean Esc) {
        //create panel
        gameOverPanel = new EndGamePanel(GameAssets.gameOverImage, gameState, playerOneTurn, Esc, singleP);
        SpaceBackgroundPanel cardGameOver = new SpaceBackgroundPanel(new BorderLayout());
        cardGameOver.setLayout(new BorderLayout());
        cardGameOver.add(gameOverPanel);

        //initialize a back button
        JPanel buttonPanelGameOver = new JPanel();
        buttonPanelGameOver.setOpaque(false);
        decorateButton(backButton);
        buttonPanelGameOver.add(backButton);
        buttonPanelGameOver.setBackground(Color.black);
        backButton.addActionListener(buttonClick);
        cardGameOver.add(buttonPanelGameOver, BorderLayout.SOUTH);
        cardGameOver.setBackground(Color.white);
        card.add("GameOver", cardGameOver);
        cardLayout.show(card, "GameOver");
    }

    /**
     * The back button.
     */
    public void goBack() {
        cardLayout.show(card, "Menu");
        GameAssets.theme.playLoop();
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

    //Take the whole Jbutton and make them into a panel
    private void makeJButtonPanel(JPanel panel) {
        panel.add(singlePbutton);
        panel.add(twoPbutton);
        panel.add(leaderBoardButton);
        panel.add(tutorialButton);
        panel.add(quitButton);

        singlePbutton.addActionListener(buttonClick);
        twoPbutton.addActionListener(buttonClick);
        leaderBoardButton.addActionListener(buttonClick);
        tutorialButton.addActionListener(buttonClick);
        quitButton.addActionListener(buttonClick);

        decorateButton(singlePbutton);
        decorateButton(twoPbutton);
        decorateButton(leaderBoardButton);
        decorateButton(tutorialButton);
        decorateButton(quitButton);
    }

    //decorate the JButton
    private void decorateButton(JButton button) {
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, FONT_SIZE));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.pink);
    }
}
