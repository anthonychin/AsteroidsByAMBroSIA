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
     * @param gs current game state
     */
    public AI(GameState gs) {
        this.gameState = gs;
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
        PlayerShip ps = gameState.getPlayerShip();
        AlienShip as = gameState.getAlienShip();
        if (gameState.getAlienShip() != null && gameState.getPlayerShip() != null) {
            int getX = ps.getX() - as.getX();
            int getY = ps.getY() - as.getY();

            //calculate heading to player
            float heading = (float) (Math.toDegrees(Math.tan(getY / getX) - 90) % 360);

            //fire
            gameState.addProjectile(new Projectile(as, heading, new int[]{as.getX(), as.getY()}, gameState));
        }
    }
}
