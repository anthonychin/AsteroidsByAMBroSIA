
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
    static ActionListener buttonPress = new Logic();
    static KeyListener keyboard = new Logic();
    static MenuGUI gui;
    
    public static void main(String args[])
    {
	
        gui = new MenuGUI(buttonPress,keyboard);
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
        gui.reactToButton(e);
    }
    
}
