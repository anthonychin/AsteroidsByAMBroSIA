package game;

/**
 * The
 * <code>Ship</code> class is used as a generic representation of a ship, either
 * player or alien.
 *
 * @author Anthony
 */
public class Ship extends MapObject {

    private int lives;
    private int fireRate;

    /**
     * Constructs <i>Ship</i> with the given parameters.
     *
     * @param velocity speed of the ship
     * @param heading
     * @param coordinates
     * @param acceleration
     * @param gameState
     * @param fireRate
     * @param lives
     */
    public Ship(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState, int fireRate, int lives) {
        super(velocity, heading, coordinates, acceleration, gameState);
        this.lives = lives;
        this.fireRate = fireRate;
    }

    /**
     * Returns the value of the ship's fire rate.
     *
     * @return fire rate of the ship
     */
    public int getFireRate() {
        return this.fireRate;
    }

    /**
     * Sets the fire rate of the current ship to specified fireRate.
     *
     * @param fireRate
     */
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
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
     * @param lives
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
