package game;

import java.awt.Color;
import org.apache.log4j.Logger;

/**
 * The asteroid object.
 * @author Nikolaos
 */

public class Asteroid extends MapObject {

    //integer definitions for each size
    /**
     * Integer definitions for large size asteroid.
     */
    final public static int LARGE_ASTEROID_SIZE = 3;
    /**
     * Integer definitions for medium size asteroid.
     */
    final public static int MEDIUM_ASTEROID_SIZE = 2;
    /**
     * Integer definitions for small size asteroid.
     */
    final public static int SMALL_ASTEROID_SIZE = 1;
    /**
     * Number of asteroid created when large size asteroid is destroyed.
     */
    final public static int ASTEROIDS_FROM_LARGE = 2;
    /**
     * Number of asteroid created when medium size asteroid is destroyed.
     */
    final public static int ASTEROIDS_FROM_MEDIUM = 2;
    
    //number of debris pieces to spawn
    final private static int NUM_DEBRIS = 20;
    private int size;
    
    private final static Logger log = Logger.getLogger(Asteroid.class.getName());

    /**
     * Creates Asteroid using given parameter.
     * @param velocity magnitude and direction of the asteroid
     * @param heading heading of the asteroid
     * @param coordinates initial X, Y position of the asteroid
     * @param gameState current game state
     * @param size asteroid size
     */
    public Asteroid(float[] velocity, float heading, int[] coordinates, GameState gameState, int size) {
        super(velocity, heading, coordinates, 0, gameState);
        this.size = size;
        log.setLevel(Logic.LOG_LEVEL);
    }

    //size of asteroid
    /**
     * Return the size of the asteroid.
     * @return size of the asteroid
     */
    public int getSize() {
        return this.size;
    }

    //destroy asteroid: add score if no bomb was used, spawn new asteroids, remove it from the game, add debris
    /**
     * Destroys asteroid. Adds score if no bomb was used, spawns new asteroids, removes it from the game, and adds debris.
     * @param bombUsed true if bomb was used, false otherwise
     */
    public void destroy(boolean bombUsed) {
        log.debug("destroying asteroid " + this.toString());
        
        //bomb not used, get score, new asteroids spawned
        if (!bombUsed) {
            if (size == LARGE_ASTEROID_SIZE) {
                for (int i = 0; i < ASTEROIDS_FROM_LARGE; i++) {
                    getGameState().addAsteroid(new Asteroid(new float[]{Difficulty.randomAsteroidVelocity(getGameState().getLevel()), Difficulty.randomAsteroidVelocity(getGameState().getLevel())}, Difficulty.randomHeading(), new int[]{this.getX(), this.getY()}, this.getGameState(), MEDIUM_ASTEROID_SIZE));
                    getGameState().addToCurrentScore(GameState.LARGE_ASTEROID_SCORE);
                }
            } else if (size == MEDIUM_ASTEROID_SIZE) {
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
