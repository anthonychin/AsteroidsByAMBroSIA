package game;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Nikolaos, Anthony
 */
public class AlienShip extends Ship {

    final public static int FIRE_RATE = 8;
    final private static int NUM_DEBRIS = 20;

    public AlienShip(float[] velocity, float heading, int[] coordinates, GameState gameState) {
        super(velocity, heading, coordinates, 0, gameState, 1);
        GameAssets.alienDetected.play();
    }

    public void destroy(boolean bombUsed) {
        int[] lastCoord = getCoord();
        getGameState().removeAlienShip();
        GameAssets.crash.play();
        if (!bombUsed) {
            getGameState().addToCurrentScore(GameState.ALIEN_SCORE);
            getGameState().addBonusDrop(new BonusDrop(lastCoord, getGameState(), new Random().nextInt(5)));
        }
        createExplosionEffect();
    }
    
    private void createExplosionEffect() {
        for (int i = 0; i < NUM_DEBRIS; i++) {
            int x = getX();
            int y = getY();
            getGameState().addExplosion(new MapObjectTTL(new float[]{Difficulty.randExplosionVelocity(), Difficulty.randExplosionVelocity()}, Difficulty.randomHeading(), new int[]{x, y}, 0, getGameState(), Color.MAGENTA));
        }
    }
}
