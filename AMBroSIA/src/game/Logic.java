package game;

import gui.MenuGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * The purpose of the
 * <code>Logic</code> class is to manage all other classes and methods in such a
 * way as to produce a playable game. This includes (but is not limited to)
 * calling for the creation of the main menu, displaying the leaderboard or
 * starting the game in either single or two player mode as appropriate,
 * depending on player actions.
 *
 * @author Nikolaos, Michael, Anthony
 *
 */
public class Logic extends KeyAdapter implements ActionListener {

    /**
     * Value of maximum level.
     */
    final public static int MAX_LEVEL = 30;
    //various essential objects
    private static GameState gameState;
    private static ActionListener buttonPress = new Logic();
    private static KeyListener keyboard = new Logic();
    private static MenuGUI gui;
    private static GraphicsEngine graphicsEngine;
    private static Physics physicsEngine;
    private static timeToLive ttlLogic;
    private static Collision collisionCheck;
    //fire rate limiter variables
    private static long initialShootTime;
    private static long currentShootTime;
    private static boolean shootKeyReleased = true;
    //is game paused boolean
    private boolean paused = false;
    //the service used to execute all update functions
    private static ScheduledExecutorService timer;
    //logger, global logging level
    private final static Logger log = Logger.getLogger(Logic.class.getName());
    /**
     * Logging level control - from no logging output to full logging.
     */
    public final static Level LOG_LEVEL = Level.OFF;

    /**
     * Main method; creates the main menu and acts as appropriate depending on
     * player input.
     *
     * @param args
     */
    public static void main(String args[]) {
        GameAssets.loadSounds();
        //set log configuration to defaults
        BasicConfigurator.configure();

        //create, display gui
        gui = new MenuGUI(buttonPress, keyboard);
        gui.showMenu();

        log.setLevel(LOG_LEVEL);
        log.info("GUI has been started");

        //background music - different exception handles for jdk6 compatibility
        GameAssets.theme.playLoop();
    }

    /**
     * Start the global timer responsible for keeping all game elements up to
     * date. The timer will use some form of multithreading to execute update
     * tasks concurrently.
     */
    public static void startTimer() {

        timer = Executors.newScheduledThreadPool(4);
        timer.scheduleAtFixedRate(graphicsEngine, 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(physicsEngine, 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(collisionCheck, 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(gui, 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(ttlLogic, 0, 200, TimeUnit.MILLISECONDS);

        //single threaded game loop testing thread
        //testTimer tester = new testTimer(graphicsEngine,physicsEngine,gui,collisionCheck(),ttlLogic);
        //timer.scheduleAtFixedRate(tester, 0, 17, TimeUnit.MILLISECONDS);


    }

    /**
     * Stops the timer.
     */
    public static void stopTimer() {
        timer.shutdown();
    }

    /**
     * ?
     *
     * @param command
     * @param delay
     * @param unit
     */
    public static void executeTask(Runnable command, long delay, TimeUnit unit) {
        timer.schedule(command, delay, unit);
    }

    /**
     * Start the single player game.
     */
    public static void startSinglePlayer() {
        GameAssets.theme.stop();
        setUpLevel();
    }

    /**
     * Starts the game in 2 player mode.
     */
    public static void startTwoPlayer() {
    }

    /**
     * Shows tutorial information to the player.
     */
    public static void showTutorial() {
    }

    /**
     * Checks if the game is paused.
     *
     * @return true if game is paused, false otherwise
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Displays text that the player has won (as appropriate for single or 2
     * player mode).
     */
    public static void displayWinner() {
    }

    /**
     * Displays "Game Over" message.
     */
    public static void displayGameOver() {
    }

    /**
     * Display information relevant to player two's turn.
     */
    public static void displayPlayerTwoTurn() {
    }

    //set up some game essentials
    private static void setUpLevel() {
        gameState = new GameState(1, 0);
        gameState.addPlayerShip(new PlayerShip(new float[]{0, 0}, 90, new int[]{250, 150}, gameState, 99, 0, 3));
        gameState.addAsteroid(new Asteroid(new float[]{-1, -1}, -30, new int[]{650, 500}, gameState, Asteroid.LARGE_ASTEROID_SIZE));
        //Random randu = new Random();
        //for (int i = 0; i < 5; i++){
        //gameState.addAsteroid(new Asteroid(new float[] {randu.nextInt(10),randu.nextInt(10)}, randu.nextInt(360), new int[] {randu.nextInt(700),randu.nextInt(500)},gameState, Asteroid.LARGE_ASTEROID_SIZE));
        //gameState.addProjectile(new Projectile(null, randu.nextFloat(), new int[] {randu.nextInt(800),randu.nextInt(600)},gameState));
        //}
        gameState.addAlienShip(new AlienShip(new float[] {1,1},0,new int[] {100,100},gameState));


        graphicsEngine = new GraphicsEngine(gameState);
        physicsEngine = new Physics(gameState);
        ttlLogic = new timeToLive(gameState);
        collisionCheck = new Collision(gameState, physicsEngine);

    }

    //called whenever a key is pressed (thread seperate from timer)
    /**
     * Handles event caused by user key presses.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        PlayerShip player = gameState.getPlayerShip();
        //handles most basic key commands.  Should activate a boolean stating that the key has been pressed
        if (keyCode == KeyEvent.VK_UP) {
            //accelerate
            if (!paused && player != null) {
                player.accelerate(true);
                GameAssets.thrusters.playLoop();
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (!paused && player != null) {
                player.turnLeft(true);
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (!paused && player != null) {
                player.turnRight(true);
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (!paused && player != null) {
                player.useBomb();
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            if (!paused && player != null) {
                if (shootKeyReleased) {
                    initialShootTime = System.currentTimeMillis();
                    shootKeyReleased = false;
                    player.shoot();
                } else if (!shootKeyReleased) {
                    currentShootTime = System.currentTimeMillis();
                    if ((currentShootTime - initialShootTime) > PlayerShip.FIRE_RATE * 1000) {
                        player.shoot();
                        initialShootTime = currentShootTime;
                    }
                }
            }
        } else if (keyCode == KeyEvent.VK_P) {
            if (!paused) {
                stopTimer();
                paused = true;
            } else {
                startTimer();
                paused = false;
            }
        } else if (keyCode == KeyEvent.VK_B) {
            if (!paused && player != null) {
                player.useBomb();
            }
        }
        if (keyCode == KeyEvent.VK_Z) {
            Random randu = new Random();
            gameState.addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(), Difficulty.randomAsteroidHeading()}, randu.nextInt(360), new int[]{randu.nextInt(700), randu.nextInt(500)}, gameState, Asteroid.LARGE_ASTEROID_SIZE));
        }
    }

    //same as keyPressed, except when it is released
    /**
     * Handle events caused by release of key.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        PlayerShip player = gameState.getPlayerShip();
        //stops doing whatever that keypress was doing
        if (keyCode == KeyEvent.VK_UP) {
            //accelerate
            if (player != null) {
                player.accelerate(false);
                GameAssets.thrusters.stop();
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (player != null) {
                player.turnLeft(false);
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (player != null) {
                player.turnRight(false);
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            shootKeyReleased = true;
        }
    }

    //This section needs a LOT of work....
    //called when a gui button is clicked
    /**
     * Handles events relating to the user clicking menu buttons.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();
        if (action == gui.singlePbutton) {
            startSinglePlayer();
            gui.displaySingleP(gameState);
            startTimer();
        } else if (action == gui.twoPbutton) {
            gui.displayTwoP(gameState);
        } else if (action == gui.leaderBoardButton) {
            gui.displayLeaderBoard();
        } else if (action == gui.tutorialButton) {
            gui.displayTutorial();
        } else if (action == gui.backButton) {
            gui.goBack();
        } else if (e.getSource() == gui.quitButton) {
            System.exit(0);
        }
    }
}
