package game;


import gui.MenuGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author Nikolaos, Michael
 */
public class Logic implements KeyListener, ActionListener{
    
    final public static int MAX_LEVEL = 30;
    final public static int ALIEN_SHIELD_DAMAGE = 1;
    final public static int LARGE_ASTEROID_SHIELD_DAMAGE = 3;
    final public static int MEDIUM_ASTEROID_SHIELD_DAMAGE = 2;
    final public static int SMALL_ASTEROID_SHIELD_DAMAGE = 1;
    final public static int PROJECTILE_SHIELD_DAMAGE = 1;
    
    private static GameState gameState;
    
    static ActionListener buttonPress = new Logic();
    static KeyListener keyboard = new Logic();
    static MenuGUI gui;
    
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
        return false;
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
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == MenuGUI.singlePbutton)
        {
            //TEMPORARY: this code should be in something like setUpLevel or startSinglePlayer
            gameState = new GameState(1, 0);
            gameState.addPlayerShip(new PlayerShip(new int[] {0, 0}, 90, new int[] {0, 0}, 0, gameState, 3, 1, 3));
        }
        
        else if(e.getSource() == MenuGUI.twoPbutton)
        {

        }
        
        else if(e.getSource() == MenuGUI.leaderBoardButton)
        {

        }
        
        else if(e.getSource() == MenuGUI.tutorialButton)
        {

        }
        
        else if(e.getSource() == MenuGUI.quitButton)
        {
            System.exit(0);
        }
        gui.reactToButton(e,gameState);
    }
    
}
