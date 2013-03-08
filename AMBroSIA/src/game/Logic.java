package game;


import gui.MenuGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
    
    //booleans for the key commands.  These need to be checked by the timer
    private boolean accelerate = false;
    private boolean turnLeft = false;
    private boolean turnRight = false;
    private boolean shoot = false;
    private boolean paused = false;
    
    public static void main(String args[])
    {
        
        gui = new MenuGUI(buttonPress,keyboard);
    }
    
    public static void startSinglePlayer()
    {
        gameState = new GameState(1, 0);
        GraphicsEngine graphicsEngine = new GraphicsEngine(gameState);
        Physics physicsEngine = new Physics(gameState);
        
        gameState.addPlayerShip(new PlayerShip(new int[] {0, 0}, 90, new int[] {0, 0}, 0, gameState, 3, 1, 3));
        setUpLevel(gameState, gameState.getLevel());
        
        while(gameState.getPlayerShip() != null || gameState.getLevel() <= MAX_LEVEL){
            physicsEngine.update();
            graphicsEngine.updateGraphics();
            ArrayList<MapObject> collisionList = physicsEngine.getCollisions();
            
            if(collisionList.size() != 0){
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
            
            // Enter whatever GUI stuff updating...
        }
        
        if(gameState.getPlayerShip() == null){
            // GUI to show Game Over
        }
        else{
            // GUI to show Player beat the game
        }
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
    
    private static void setUpLevel(GameState gameState, int level)
    {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println(e.getKeyCode());
        //handles most basic key commands.  Should activate a boolean stating that the key has been pressed
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            //accelerate
            accelerate = true;
            System.out.println("accelerate");
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            turnLeft = true;
            System.out.println("left");
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            turnRight = true;
            System.out.println("right");
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            gameState.getPlayerShip().useBomb();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            shoot = true;
            System.out.println("shoot");
        }
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            gameState.getPlayerShip().activateShield();
        }
        else if (e.getKeyCode() == KeyEvent.VK_P)
        {
            paused = !paused;
        }
    }
    
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        //stops doing whatever that keypress was doing
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            //accelerate
            accelerate = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            turnLeft = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            turnRight = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            shoot = false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == gui.singlePbutton)
        {
            //TEMPORARY: this code should be in something like setUpLevel or startSinglePlayer
            gameState = new GameState(1, 0);
            gameState.addPlayerShip(new PlayerShip(new int[] {0, 0}, 90, new int[] {0, 0}, 0, gameState, 3, 1, 3));
        }
        
        else if(e.getSource() == gui.twoPbutton)
        {
            
        }
        
        else if(e.getSource() == gui.leaderBoardButton)
        {
            
        }
        
        else if(e.getSource() == gui.tutorialButton)
        {
            
        }
        
        else if(e.getSource() == gui.quitButton)
        {
            System.exit(0);
        }
        gui.reactToButton(e,gameState);
    }
    
}
