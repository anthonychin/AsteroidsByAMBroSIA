package game;

import game.GameState;

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
    
    public void targetPS()
    {
        PlayerShip ps = gameState.getPlayerShip();
        AlienShip as = gameState.getAlienShip();
        if (gameState.getAlienShip() != null && gameState.getPlayerShip() != null){
           int getX = ps.getX() - as.getX();
           int getY = ps.getY() - as.getY();
           
           float heading = (float)(Math.toDegrees(Math.tan(getY/getX)-90)%360);
           
           System.out.println(heading);
           gameState.addProjectile(new Projectile(as, heading, new int[]{as.getX(), as.getY()}, gameState));
        }
    }
    
    private int randomHeading()
    {
        Random randomGen = new Random();
        //int number = randomGen.next(9);
        return 0;
        
    }
}
