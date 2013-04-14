package mapObjects;

import game.GameState;
import java.awt.Color;

/**
 * The
 * <code>Projectile</code> class provides the representation for the projectile
 * objects.
 *
 * @author Anthony Chin
 */
public class Projectile extends MapObjectTTL {

    /**
     * The velocity of Projectile.
     */
    public final static float PROJECTILE_SPEED = 15.0f;
    /**
     * Value of the variable owner when the player owns the projectile.
     */
    public final static int PLAYER_OWNER = 1;
    /**
     * Value of the variable owner when the alien owns the projectile.
     */
    public final static int ALIEN_OWNER = 2;
    private int velocity; // to be fixed
    private int owner;
    private final static int TIME_TO_LIVE = 3;

    /**
     * Creates Projectile with the given parameters.
     *
     * @param ship owner of the projectile created
     * @param heading angle that the projectile is headed
     * @param coordinates initial X, Y coordinate
     * @param gameState current game state
     */
    public Projectile(Ship ship, float heading, int[] coordinates, GameState gameState) {
        super(new float[]{0, 0}, heading, coordinates, 0, gameState);
        float velocityX = (float) (PROJECTILE_SPEED * Math.cos(Math.toRadians(heading - 90)));
        float velocityY = (float) (PROJECTILE_SPEED * Math.sin(Math.toRadians(heading - 90)));

        this.setVelocity(new float[]{velocityX, velocityY});

        //longer time to live than default
        this.setTTL(TIME_TO_LIVE);

        if (ship instanceof PlayerShip) {
            this.owner = PLAYER_OWNER;
            // Yellow projectiles for player
            setColor(Color.YELLOW);
        } else {
            this.owner = ALIEN_OWNER;
            // Green projectiles to distinguish between player
            setColor(Color.GREEN);
        }
    }

    /**
     * Returns the integer value that represents the owner of the Projectile.
     * The owner is the one who created and fired the projectile.
     *
     * @return owner of the projectile
     */
    public int getOwner() {
        return this.owner;
    }

    /**
     * Destroys projectile.
     */
    @Override
    public void destroy() {
        getGameState().removeProjectile(this);
    }
}
