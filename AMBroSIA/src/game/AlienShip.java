package game;

/**
 *
 * @author Nikolaos, Anthony
 */
public class AlienShip extends Ship {

    final public static int FIRE_RATE = 8;

    public AlienShip(float[] velocity, float heading, int[] coordinates, GameState gameState) {
        super(velocity, heading, coordinates, 0, gameState, 1);
        GameAssets.alienDetected.play();
    }

    public void destroy(boolean bombUsed) {
        getGameState().removeAlienShip();
        GameAssets.crash.play();
        if (!bombUsed) {
            getGameState().addToCurrentScore(GameState.ALIEN_SCORE);
        }
    }
}
