package game;

import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author Nikolaos
 */

public class Asteroid extends MapObject {

    final public static int LARGE_ASTEROID_SIZE = 3;
    final public static int MEDIUM_ASTEROID_SIZE = 2;
    final public static int SMALL_ASTEROID_SIZE = 1;
    final public static int ASTEROIDS_FROM_LARGE = 2;
    final public static int ASTEROIDS_FROM_MEDIUM = 2;
    private int size;
    
    private final static Logger log = Logger.getLogger(Asteroid.class.getName());

    Asteroid(float[] velocity, float heading, int[] coordinates, GameState gameState, int size) {
        super(velocity, heading, coordinates, 0, gameState);
        this.size = size;
        log.setLevel(Logic.LOG_LEVEL);
    }

    public int getSize() {
        return this.size;
    }

    public void destroy(boolean bombUsed) {
        log.debug("destroying asteroid " + this.toString());
        if (size == 3) 
        {
            for (int i = 0; i < ASTEROIDS_FROM_LARGE; i++) 
            {
                getGameState().addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(getGameState().getLevel()), Difficulty.randomAsteroidVelocity(getGameState().getLevel())}, Difficulty.randomHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), MEDIUM_ASTEROID_SIZE));
                //getGameState().addAsteroid(new Asteroid(new float[]{2f,22f}, Difficulty.randomAsteroidHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), MEDIUM_ASTEROID_SIZE));                
            }

            if (!bombUsed) 
            {
                getGameState().addToCurrentScore(GameState.LARGE_ASTEROID_SCORE);
            }
        } 
        
        else if (size == 2) 
        {
            for (int i = 0; i < ASTEROIDS_FROM_MEDIUM; i++) 
            {
                //getGameState().addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(), Difficulty.randomAsteroidVelocity()}, Difficulty.randomAsteroidHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), SMALL_ASTEROID_SIZE));
                getGameState().addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(getGameState().getLevel()), Difficulty.randomAsteroidVelocity(getGameState().getLevel())}, Difficulty.randomHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), SMALL_ASTEROID_SIZE));                
            }

            if (!bombUsed) 
            {
                getGameState().addToCurrentScore(GameState.MEDIUM_ASTEROID_SCORE);
            }
        } 
        
        else 
        {
            if (!bombUsed) 
            {
                getGameState().addToCurrentScore(GameState.SMALL_ASTEROID_SCORE);
            }
        }
        log.debug("removing asteroid " + this.toString() + " from game");
        getGameState().removeAsteroid(this);
        log.debug("asteroid " + this.toString() + " removed from gameState");
    }
}
