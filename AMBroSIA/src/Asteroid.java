/**
 *
 * @author Nikolaos
 */
import java.awt.Polygon;

public class Asteroid extends MapObject{
    final public static int LARGE_ASTEROID_SIZE = 3;
    final public static int MEDIUM_ASTEROID_SIZE = 2;
    final public static int SMALL_ASTEROID_SIZE = 1;
    
    private int size;
    
    Asteroid(int velocity, int heading, int[] coordinates, Polygon shape, GameState gameState, int size)
    {
        super(velocity, heading, coordinates, shape, gameState);
        this.size = size;
    }
    
    public int getSize()
    {
        return this.size;
    }
    
    public void destroy()
    {
        getGameState().removeAsteroid(this);
        if(size == 3)
        {
            getGameState().addAsteroid(new Asteroid(this.getVelocity(), this.getHeading(), this.getCoord, this.getShape(), this.getGameState, 2));
            
        }
        else if(size == 2)
        {
            
        }
        else
        {
            
        }
    }
}