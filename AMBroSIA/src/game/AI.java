package game;

/**
 *
 * @author Nikolaos Bukas
 */
import java.util.ArrayList;
import java.util.Random;

public class AI {
    public ArrayList<int[]> pathFind()
    {
        return null;
    }
    
    public void alienPath()
    {
        
    }
    
    public int[] targetPS(PlayerShip playerShip)
    {
        return playerShip.getCoord();
    }
    
    private int randomHeading()
    {
        Random randomGen = new Random();
        //int number = randomGen.next(9);
        return 0;
        
    }
}