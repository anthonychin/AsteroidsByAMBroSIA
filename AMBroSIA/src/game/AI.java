package game;

/**
 *
 * @author Anthony Chin
 */
import java.util.ArrayList;
import java.util.Random;

public class AI implements Runnable {
    private GameState gameState;
    
    public AI(GameState gs){
        this.gameState = gs;
    }
    
    //for AI thread
    public void run(){
        targetPS();
    }
    public ArrayList<int[]> pathFind()
    {
        return null;
    }
    
    public void alienPath()
    {
        
    }
    
    //get player, target it and fire
    public void targetPS()
    {
        PlayerShip ps = gameState.getPlayerShip();
        AlienShip as = gameState.getAlienShip();
        if (gameState.getAlienShip() != null && gameState.getPlayerShip() != null){
           int getX = ps.getX() - as.getX();
           int getY = ps.getY() - as.getY();
           
           //calculate heading to player
           float heading = (float)(Math.toDegrees(Math.tan(getY/getX)-90)%360);
           
           //fire
           gameState.addProjectile(new Projectile(as, heading, new int[]{as.getX(), as.getY()}, gameState));
        }
    }
}
