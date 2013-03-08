
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michael
 */
public class Logic {
    
    final public static int MAX_LEVEL = 30;
    
    public static void main(String args[])
    {
        
    }
    
    public static void startSinglePlayer()
    {
        GameState gameState = new GameState(1, 0);
        GraphicsEngine graphicsEngine = new GraphicsEngine(gameState);
        Physics physicsEngine = new Physics(gameState);
        
        gameState.addPlayerShip(new PlayerShip(new int[] {0, 0}, 90, new int[] {0, 0}, 0, gameState, 3, 1, 3));
        setUpLevel(gameState, gameState.getLevel());
        
        while(gameState.getPlayerShip() != null || gameState.getLevel() <= MAX_LEVEL)
        {
            physicsEngine.update();
            graphicsEngine.updateGraphics();
            ArrayList<MapObject> collisionList = physicsEngine.getCollisions();
            
            
        }
        
        if(gameState.getPlayerShip() == null)
        {
            // GUI to show Game Over
        }
        else
        {
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
    
    public static void startIOListener()
    {
        
    }
    
    private static void setUpLevel(GameState gameState, int level)
    {
        
    }
    
}
