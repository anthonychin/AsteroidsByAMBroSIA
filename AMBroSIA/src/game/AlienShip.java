package game;

/**
 *
 * @author Nikolaos, Anthony
 */
public class AlienShip extends Ship {

    final public static int FIRE_RATE = 8;

    public AlienShip(float[] velocity, int heading, int[] coordinates, GameState gameState) {
        super(velocity, heading, coordinates, 0, gameState, 1);
        GameAssets.alienDetected.play();
    }

    public void destroy(boolean bombUsed) {
        getGameState().removeAlienShip();
        GameAssets.crash.play();
        if (!bombUsed) {
            getGameState().addToHighScore(GameState.ALIEN_SCORE);
        }
    }
}
