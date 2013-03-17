package game;

/**
 *
 * @author Nikolaos
 */
import java.awt.Polygon;

public class Asteroid extends MapObject{
    final public static int LARGE_ASTEROID_SIZE = 3;
    final public static int MEDIUM_ASTEROID_SIZE = 2;
    final public static int SMALL_ASTEROID_SIZE = 1;
    
    final public static int ASTEROIDS_FROM_LARGE = 2;
    final public static int ASTEROIDS_FROM_MEDIUM = 3;
    
    private int size;
    
    Asteroid(float[] velocity, float heading, int[] coordinates, GameState gameState, int size)
    {
        super(velocity, heading, coordinates, 0, gameState);
        this.size = size;
    }
    
    public int getSize()
    {
        return this.size;
    }
    
    public void destroy(boolean bombUsed)
    {
        getGameState().removeAsteroid(this);
        
        if(size == 3)
        {
            for(int i = 0; i < ASTEROIDS_FROM_LARGE; i++)
            {
                getGameState().addAsteroid(new Asteroid(this.getVelocity(), this.getHeading(), this.getCoord(), this.getGameState(), MEDIUM_ASTEROID_SIZE));
            }
            
            if(!bombUsed) { getGameState().addToHighScore(GameState.LARGE_ASTEROID_SCORE); }
        }
        else if(size == 2)
        {
            for(int i = 0; i < ASTEROIDS_FROM_MEDIUM; i++)
            {
                getGameState().addAsteroid(new Asteroid(this.getVelocity(), this.getHeading(), this.getCoord(), this.getGameState(), SMALL_ASTEROID_SIZE));
            }
            
            if(!bombUsed) { getGameState().addToHighScore(GameState.MEDIUM_ASTEROID_SCORE); }
        }
        else
        {
            if(!bombUsed) { getGameState().addToHighScore(GameState.SMALL_ASTEROID_SCORE); }
        }
    }
}