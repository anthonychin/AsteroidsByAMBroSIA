package game;

import java.awt.Color;
import java.util.Random;

/**
 * Alien object.
 * @author Nikolaos, Anthony
 */
public class AlienShip extends Ship {

    final private static int NUM_DEBRIS = 20;

    //create alien
    public AlienShip(float[] velocity, float heading, int[] coordinates, GameState gameState) {
        super(velocity, heading, coordinates, 0, gameState, 1);
        //play alien entering system sound
        GameAssets.alienDetected.play();
    }

    //remove alien from game: add bonus drop where it was destroyed, play destruction sound, add sto core
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
    
    //create all the debris (with proper color and position)
    private void createExplosionEffect() {
        for (int i = 0; i < NUM_DEBRIS; i++) {
            int x = getX();
            int y = getY();
            getGameState().addExplosion(new MapObjectTTL(new float[]{Difficulty.randExplosionVelocity(), Difficulty.randExplosionVelocity()}, Difficulty.randomHeading(), new int[]{x, y}, 0, getGameState(), Color.MAGENTA));
        }
    }
}
