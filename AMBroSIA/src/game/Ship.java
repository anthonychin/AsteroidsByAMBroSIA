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
     * @param velocity speed of ship
     * @param heading heading of ship (in degrees)
     * @param coordinates initial x, y position of the ship
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
     * Creates a projectile.
     */
    public void shoot() {
        //this.getGameState().addProjectile(new Projectile(this, Projectile.PROJECTILE_VELOCITY, this.getHeading(), calculateCoordinate(this.getCoord()), this.getGameState()));   
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
