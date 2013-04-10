package game;

import java.awt.Color;
import java.util.Random;

/**
 * The
 * <code>AlienShip</code> class defines all the properties and methods
 * appropriate to the alien ships that are not included in Ship.
 */
public class AlienShip extends Ship {

    final private static int NUM_DEBRIS = 20;

    //create alien
    /**
     * Create AlienShip using given parameters.
     *
     * @param velocity magnitude and direction of the alien ship
     * @param heading heading of the alien ship
     * @param coordinates initial X, Y position of the alien ship
     * @param gameState current game state
     */
    public AlienShip(float[] velocity, float heading, int[] coordinates, GameState gameState) {
        super(velocity, heading, coordinates, 0, gameState, 1);
        //play alien entering system sound
        GameAssets.alienDetected.play();
    }

    //remove alien from game: add bonus drop where it was destroyed, play destruction sound, add sto core
    /**
     * Removes alien from the game state. Adds bonus drop where it was
     * destroyed, plays destruction sound, and adds score.
     *
     * @param bombUsed true if bomb was used false otherwise
     */
    public void destroy(boolean bombUsed) {
        int[] lastCoord = getCoord();
        getGameState().removeAlienShip();
        GameAssets.crash.play();
        if (!bombUsed) {
            getGameState().addToCurrentScore(GameState.ALIEN_SCORE);
            getGameState().addBonusDrop(new BonusDrop(lastCoord, getGameState(), new Random().nextInt(5)));
        }
        
        checkP1orP2();
        
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
    // check for p1 or p2
    private void checkP1orP2(){
        if(!getGameState().isPlayerTwoTurn()){
            getGameState().addP1alienDestroyed();
        }
        else{
            getGameState().addP2alienDestroyed();
        }    
    }
}
