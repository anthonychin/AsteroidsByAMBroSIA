package game;

/**
 * The
 * <code>Ship</code> class is used as a generic representation of a ship, either
 * player or alien.
 *
 * @author Anthony Chin
 */
public class Ship extends MapObject {

    private int lives;

    /**
     * Constructs Ship with the given parameters.
     *
     * @param velocity magnitude and direction of ship
     * @param heading heading of ship (in degrees)
     * @param coordinates initial X, Y position of the ship
     * @param acceleration acceleration of the ship
     * @param gameState current game state
     * @param lives lives of the ship
     */
    public Ship(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState, int lives) {
        super(velocity, heading, coordinates, acceleration, gameState);
        this.lives = lives;
    }

    /**
     * Returns the value of the ship's lives.
     *
     * @return lives of the ship
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Sets the lives of the current ship to specified lives.
     *
     * @param lives new lives value
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Creates a projectile. Must be overridden.
     */
    public void shoot() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private int[] calculateCoordinate(int[] shipCoord) {
        shipCoord = this.getCoord();
        int i = 0;
        while (i < shipCoord.length) {
            shipCoord[i] = shipCoord[i] + 1;
        }
        return shipCoord;
    }
}
