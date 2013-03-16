package game;


import gui.MenuGUI;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Nikolaos, Michael
 */
public class Logic extends KeyAdapter implements ActionListener{
    
    final public static int MAX_LEVEL = 30;
    final public static int ALIEN_SHIELD_DAMAGE = 1;
    final public static int LARGE_ASTEROID_SHIELD_DAMAGE = 3;
    final public static int MEDIUM_ASTEROID_SHIELD_DAMAGE = 2;
    final public static int SMALL_ASTEROID_SHIELD_DAMAGE = 1;
    final public static int PROJECTILE_SHIELD_DAMAGE = 1;
    
    private static GameState gameState;
    
    private static ActionListener buttonPress = new Logic();
    private static KeyListener keyboard = new Logic();
    private static MenuGUI gui;
    
    private static GraphicsEngine graphicsEngine;
    private static Physics physicsEngine;
    
    //the service used to execute all update functions
    private static ScheduledExecutorService timer = Executors.newScheduledThreadPool(4);
    
    //booleans for the key commands.  These need to be checked by the timer
    private boolean accelerate = false;
    private boolean turnLeft = false;
    private boolean turnRight = false;
    private boolean shoot = false;
    private boolean paused = false;
    
    public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        
        gui = new MenuGUI(buttonPress,keyboard);
        gui.showMenu();
    }
    
    public static void startTimer()
    {
        timer.scheduleAtFixedRate(graphicsEngine, 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(physicsEngine, 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(collisionCheck(), 0, 17, TimeUnit.MILLISECONDS);
        timer.scheduleAtFixedRate(gui, 0, 17, TimeUnit.MILLISECONDS);
    }
    public static void stopTimer()
    {
        timer.shutdown();
    }
    
    
    public static Runnable collisionCheck() {
        
        final Runnable collision = new Runnable() {
            @Override
            public void run() {
                ArrayList<MapObject> collisionList = physicsEngine.getCollisions();
                
                if(!collisionList.isEmpty()){
                    for(int i = 0; i < collisionList.size(); i =+ 2){
                        MapObject collisionOne = collisionList.get(i);
                        MapObject collisionTwo = collisionList.get(i + 1);
                        
                        if(collisionOne instanceof PlayerShip){
                            if(collisionTwo instanceof AlienShip){
                                // PlayerShip collided with AlienShip
                                if(collisionLogic((PlayerShip) collisionOne, (AlienShip) collisionTwo))
                                    break;
                            }
                            else if(collisionTwo instanceof Asteroid){
                                // PlayerShip collided with an Asteroid
                                if(collisionLogic((PlayerShip) collisionOne, (Asteroid) collisionTwo))
                                    break;
                            }
                            else if(collisionTwo instanceof Projectile){
                                // PlayerShip collided with a Projectile
                                if(collisionLogic((PlayerShip) collisionOne, (Projectile) collisionTwo))
                                    break;
                            }
                            else if(collisionTwo instanceof BonusDrop){
                                // PlayerShip collided with a BonusDrop
                                collisionLogic((PlayerShip) collisionOne, (BonusDrop) collisionTwo);
                            }
                        }
                        else{
                            if(collisionTwo instanceof Asteroid){
                                // AlienShip collided with an Asteroid
                                collisionLogic((AlienShip) collisionOne, (Asteroid) collisionTwo);
                            }
                            else if(collisionTwo instanceof Projectile){
                                // AlienShip collided with a Projectile
                                collisionLogic((AlienShip) collisionOne, (Projectile) collisionTwo);
                            }
                        }
                    }
                }
            }
        };
        return collision;
    }
    
    public static void startSinglePlayer()
    {
        setUpLevel();
    }
    
    public static void startTwoPlayer()
    {
        
    }
    
    public static void showTutorial()
    {
        
    }
    
    public boolean isPaused()
    {
        return paused;
    }
    
    public static void displayWinner()
    {
        
    }
    
    public static void displayGameOver()
    {
        
    }
    
    public static void displayPlayerTwoTurn()
    {
        
    }
    
    public static void gameLogic()
    {
        
    }
    
    private static boolean collisionLogic(PlayerShip playerShip, Asteroid asteroid)
    {
        if(asteroid.getSize() == Asteroid.LARGE_ASTEROID_SIZE){
            if(playerShip.getShieldPoints() >= LARGE_ASTEROID_SHIELD_DAMAGE){
                playerShip.setShieldPoints(playerShip.getShieldPoints() - LARGE_ASTEROID_SHIELD_DAMAGE);
            }
            else if(playerShip.getLives() == 1){
                playerShip.destroy();
            }
            else{
                playerShip.setLives(playerShip.getLives() - 1);
            }
        }
        else if(asteroid.getSize() == Asteroid.MEDIUM_ASTEROID_SIZE){
            if(playerShip.getShieldPoints() >= MEDIUM_ASTEROID_SHIELD_DAMAGE){
                playerShip.setShieldPoints(playerShip.getShieldPoints() - MEDIUM_ASTEROID_SHIELD_DAMAGE);
            }
            else if(playerShip.getLives() == 1){
                playerShip.destroy();
            }
            else{
                playerShip.setLives(playerShip.getLives() - 1);
            }
        }
        else if(asteroid.getSize() == Asteroid.SMALL_ASTEROID_SIZE){
            if(playerShip.getShieldPoints() >= SMALL_ASTEROID_SHIELD_DAMAGE){
                playerShip.setShieldPoints(playerShip.getShieldPoints() - SMALL_ASTEROID_SHIELD_DAMAGE);
            }
            else if(playerShip.getLives() == 1){
                playerShip.destroy();
            }
            else{
                playerShip.setLives(playerShip.getLives() - 1);
            }
        }
        
        return gameState.getPlayerShip() == null;
    }
    
    private static void collisionLogic(PlayerShip playerShip, BonusDrop bonusDrop)
    {
        if(bonusDrop.getType() == BonusDrop.BOMB_BONUS_DROP){
            playerShip.addBomb();
        }
        else if(bonusDrop.getType() == BonusDrop.LIFE_BONUS_DROP){
            playerShip.setLives(playerShip.getLives() + 1);
        }
        else if(bonusDrop.getType() == BonusDrop.SHIELD_ONE_POINT_DROP){
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 1);
        }
        else if(bonusDrop.getType() == BonusDrop.SHIELD_TWO_POINTS_DROP){
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 2);
        }
        else if(bonusDrop.getType() == BonusDrop.SHIELD_THREE_POINTS_DROP){
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 3);
        }
        
        bonusDrop.destroy();
    }
    
    private static boolean collisionLogic(PlayerShip playerShip, AlienShip alienShip)
    {
        if(playerShip.getShieldPoints() >= ALIEN_SHIELD_DAMAGE){
            playerShip.setShieldPoints(playerShip.getShieldPoints() - ALIEN_SHIELD_DAMAGE);
        }
        else if(playerShip.getLives() == 1){
            playerShip.destroy();
        }
        else{
            playerShip.setLives(playerShip.getLives() - 1);
        }
        
        alienShip.destroy();
        
        return gameState.getPlayerShip() == null;
    }
    
    private static boolean collisionLogic(PlayerShip playerShip, Projectile projectile)
    {
        if(playerShip.getShieldPoints() >= PROJECTILE_SHIELD_DAMAGE){
            playerShip.setShieldPoints(playerShip.getShieldPoints() - PROJECTILE_SHIELD_DAMAGE);
        }
        else if(playerShip.getLives() == 1){
            playerShip.destroy();
        }
        else{
            playerShip.setLives(playerShip.getLives() - 1);
        }
        
        projectile.destroy();
        
        return gameState.getPlayerShip() == null;
    }
    
    private static void collisionLogic(AlienShip alienShip, Asteroid asteroid)
    {
        alienShip.destroy();
        asteroid.destroy();
    }
    
    private static void collisionLogic(AlienShip alienShip, Projectile projectile)
    {
        alienShip.destroy();
        projectile.destroy();
    }
    
    private static void setUpLevel()
    {
        gameState = new GameState(1, 0);
        gameState.addPlayerShip(new PlayerShip(new int[] {1, 1}, -60, new int[] {250, 150}, 0, gameState, 3, 1, 3));
        gameState.addAsteroid(new Asteroid(new int[] {-1,-1}, -30, new int[] {700,580},gameState, Asteroid.LARGE_ASTEROID_SIZE));
        graphicsEngine = new GraphicsEngine(gameState);
        physicsEngine = new Physics(gameState);
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        //handles most basic key commands.  Should activate a boolean stating that the key has been pressed
        if (keyCode == KeyEvent.VK_UP)
        {
            //accelerate
            accelerate = true;
        }
        else if (keyCode == KeyEvent.VK_LEFT)
        {
            turnLeft = true;
        }
        else if (keyCode == KeyEvent.VK_RIGHT)
        {
            turnRight = true;
        }
        else if (keyCode == KeyEvent.VK_DOWN)
        {
            gameState.getPlayerShip().useBomb();
        }
        else if (keyCode == KeyEvent.VK_SPACE)
        {
            shoot = true;
        }
        else if (keyCode == KeyEvent.VK_BACK_SPACE)
        {
            gameState.getPlayerShip().activateShield();
        }
        else if (keyCode == KeyEvent.VK_P)
        {
            paused = !paused;
        }
    }
    
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        //stops doing whatever that keypress was doing
        if (keyCode == KeyEvent.VK_UP)
        {
            //accelerate
            accelerate = false;
        }
        else if (keyCode == KeyEvent.VK_LEFT)
        {
            turnLeft = false;
        }
        else if (keyCode == KeyEvent.VK_RIGHT)
        {
            turnRight = false;
        }
        else if (keyCode == KeyEvent.VK_SPACE)
        {
            shoot = false;
        }
    }
    
    
    //This section needs a LOT of work....
    @Override
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();
        if(action == gui.singlePbutton)
        {
            startSinglePlayer();
            gui.displaySingleP(gameState);
            startTimer();
        }
        
        else if(action == gui.twoPbutton)
        {
            gui.displayTwoP(gameState);
        }
        
        else if(action == gui.leaderBoardButton)
        {
            gui.displayLeaderBoard();
        }
        
        else if(action == gui.tutorialButton)
        {
            gui.displayTutorial();
        }
        
        else if(action == gui.backButton)
        {
            gui.goBack();
        }
        
        else if(e.getSource() == gui.quitButton)
        {
            System.exit(0);
        }
    }
}
