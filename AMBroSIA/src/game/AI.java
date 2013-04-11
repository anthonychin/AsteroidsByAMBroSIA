package game;

import java.util.ArrayList;

/**
 * The
 * <code>AI</code> class serves a controller for the Alien Ship. In other words,
 * it will control how the Alien ship moves and reacts.
 *
 * @author Anthony Chin
 */
public class AI implements Runnable {

    private GameState gameState;

    /**
     * Creates AI with given game state
     *
     * @param gameState current game state
     */
    public AI(GameState gameState) {
        this.gameState = gameState;
    }

    //for AI thread
    /**
     * Runs targetPS method, which targets player ship and fires.
     */
    public void run() {
        targetPS();
    }

    /**
     * Returns coordinates representing the path it should follow.
     *
     * @return set of path coordinates
     */
    public ArrayList<int[]> pathFind() {
        return null;
    }

    /**
     * The path that alien ship traverses before disappearing.
     */
    public void alienPath() {
    }

    //get player, target it and fire
    /**
     * Finds the set of coordinates of where the player ship is and shoots the
     * projectile.
     */
    public void targetPS() {
        PlayerShip playerShip = gameState.getPlayerShip();
        AlienShip alienShip = gameState.getAlienShip();
        if (alienShip != null && playerShip != null) {
            //fire
            gameState.addProjectile(new Projectile(alienShip, Difficulty.randomHeading(), new int[]{alienShip.getX(), alienShip.getY()}, gameState));
        }
    }
}
