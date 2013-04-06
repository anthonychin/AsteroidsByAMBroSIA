package game;

import java.awt.Color;

/**
 * The <code>Projectile</code> class provides the representation for the projectile
 * objects.
 *
 * @author Anthony Chin
 */
public class Projectile extends MapObjectTTL {

    /**
     * The velocity of Projectile. The default is 15.
     */
    public final static float PROJECTILE_SPEED = 15.0f;
    /**
     * Value of the owner when the player owns the projectile.
     */
    public final static int PLAYER_OWNER = 1;
    /**
     * Value of the owner when the alien owns the projectile.
     */
    public final static int ALIEN_OWNER = 2;
    private int velocity; // to be fixed
    private int owner;

    /**
     * Creates <i>Projectile</i> with the given parameters.
     *
     * @param ship
     * @param heading
     * @param coordinates
     * @param gameState
     */
    public Projectile(Ship ship, float heading, int[] coordinates, GameState gameState) {
        super(new float[]{0, 0}, heading, coordinates, 0, gameState);
        float[] velocity = {0, 0};
        velocity[0] = (float) (PROJECTILE_SPEED * Math.cos(Math.toRadians(heading - 90)));
        velocity[1] = (float) (PROJECTILE_SPEED * Math.sin(Math.toRadians(heading - 90)));

        this.setVelocity(new float[]{velocity[0], velocity[1]});
        
        //by default, we want yellow projectiles
        setColor(Color.YELLOW);
        //longer time to live than default
        this.setTTL(10);

        if (ship instanceof PlayerShip) {
            this.owner = PLAYER_OWNER;
        } else {
            this.owner = ALIEN_OWNER;
        }
    }

    /**
     * Returns the integer value that represents the owner of the
     * <i>Projectile.</i>
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
