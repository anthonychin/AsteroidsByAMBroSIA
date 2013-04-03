package game;

import java.awt.Color;
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
    
    final private static int NUM_DEBRIS = 20;
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
        
        //bomb not used, get score, new asteroids spawned
        if (!bombUsed) {
            if (size == 3) {
                for (int i = 0; i < ASTEROIDS_FROM_LARGE; i++) {
                    getGameState().addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(getGameState().getLevel()), Difficulty.randomAsteroidVelocity(getGameState().getLevel())}, Difficulty.randomHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), MEDIUM_ASTEROID_SIZE));
                    getGameState().addToCurrentScore(GameState.LARGE_ASTEROID_SCORE);
                }
            } else if (size == 2) {
                for (int i = 0; i < ASTEROIDS_FROM_MEDIUM; i++) {
                    getGameState().addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(getGameState().getLevel()), Difficulty.randomAsteroidVelocity(getGameState().getLevel())}, Difficulty.randomHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), SMALL_ASTEROID_SIZE));
                }
                getGameState().addToCurrentScore(GameState.MEDIUM_ASTEROID_SCORE);
            } else {
                getGameState().addToCurrentScore(GameState.SMALL_ASTEROID_SCORE);
            }
        }

        //bomb or no bomb, remove asteroid
        getGameState().removeAsteroid(this);
        createExplosionEffect();
    }
    
    private void createExplosionEffect() {
        for (int i = 0; i < NUM_DEBRIS; i++) {
            int x = getX();
            int y = getY();
            getGameState().addExplosion(new MapObjectTTL(new float[]{Difficulty.randExplosionVelocity(), Difficulty.randExplosionVelocity()}, Difficulty.randomHeading(), new int[]{x, y}, 0, getGameState(), Color.GRAY));
        }
    }
}
