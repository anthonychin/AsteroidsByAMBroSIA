package game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The <code>PlayerShip</code> class defines all the properties and methods
 * appropriate to the player ship that are not included in Ship.
 *
 * @author Anthony Chin
 */
public class PlayerShip extends Ship {

    /**
     * Value of the maximum velocity of the player ship. The default is set to
     * 10.
     */
    final public static int MAX_VELOCITY = 8;
    /**
     * Value of the acceleration of the player ship. The default is set to
     * 0.09f.
     */
    final public static float ACCELERATION = 0.09f;
    /**
     * Value of the deceleration of the player ship. The default is set to -2.
     */
    final public static int DECELERATION = -2;
    /**
     * Value of the fire rate of the player ship. The default is set to 5.
     */
    final public static float FIRE_RATE = 0.2f;
    /**
     * Value of the angular speed of the player ship. The default is set to 10.
     */
    final public static int ANGULAR_SPEED = 30;
    /**
     * Value of the number of debris when the player ship gets destroyed. The
     * default is set to 20.
     */
    final public static int NUM_DEBRIS = 20;
    private int bomb;
    private int shieldPoints;
    private boolean isAccelerating = false;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;
    private boolean isShieldOn = false;
    
    private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PlayerShip.class.getName());

    /**
     * Creates <i>PlayerShip</i> with the given parameters.
     *
     * @param velocity
     * @param heading
     * @param coordinates
     * @param gameState
     * @param lives
     * @param bomb
     * @param shieldPoints
     */
    public PlayerShip(float[] velocity, float heading, int[] coordinates, GameState gameState, int lives, int bomb, int shieldPoints) {
        super(velocity, heading, coordinates, 0, gameState, lives);
        this.bomb = bomb;
        this.shieldPoints = shieldPoints;
        log.setLevel(Logic.LOG_LEVEL);
    }

    /**
     * Returns the amount of bombs.
     *
     * @return number of bombs
     */
    public int getBomb() {
        return this.bomb;
    }

    /**
     * Increase the amount of bombs by 1.
     *
     * @return number of bombs after the increment
     */
    public int addBomb() {
        return this.bomb + 1;
    }

    /**
     * Detonates the bomb.
     */
    public void useBomb() {
        if (bomb > 0) {
            bomb = bomb - 1;
            // do something
        } else {
            // do nothing
        }
    }

    /**
     * Returns the value of the shield points.
     *
     * @return value of the shield points
     */
    public int getShieldPoints() {
        return this.shieldPoints;
    }

    /**
     * Sets the amount of shield points using the parameter.
     *
     * @param shieldpoints
     */
    public void setShieldPoints(int shieldpoints) {
        this.shieldPoints = shieldpoints;
    }

    /**
     * Activates the shield for the <i>PlayerShip</i>.
     */
    public void activateShield() {
        if (this.shieldPoints > 0) {
            isShieldOn = true;
        }
    }

    /**
     * Checks if the shield is activated.
     *
     * @return true if shield is activated, false otherwise
     */
    public boolean getShieldStatus() {
        return isShieldOn;
    }

    /**
     * Checks if the <i>PlayerShip</i> is dead.
     *
     * @return true if player ship is dead, false otherwise
     */
    public boolean isDead() {
        if (this.getLives() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Sets the <i>PlayerShip</i> to accelerate.
     * @param isAccelerating 
     */
    public void accelerate(boolean isAccelerating) {
        if (isAccelerating) {
            this.setAcceleration(ACCELERATION);
        } else {
            this.setAcceleration(0);
        }
    }

    /**
     * Sets the <i>PlayerShip</i> to turn left.
     * @param turning 
     */
    public void turnLeft(boolean turning) {
        this.isTurningLeft = turning;
    }

    /**
     * Checks if <i>PlayerShip</i> is turning left.
     *
     * @return true if the ship is turning left, false otherwise
     */
    public boolean isTurningLeft() {
        return this.isTurningLeft;
    }

    /**
     * Sets the <i>PlayerShip</i> to turn right.
     * @param turning 
     */
    public void turnRight(boolean turning) {
        this.isTurningRight = turning;
    }

    /**
     * Checks if <i>PlayerShip</i> is turning right.
     *
     * @return true if the ship is turning right, false otherwise.
     */
    public boolean isTurningRight() {
        return this.isTurningRight;
    }

    /**
     * Checks if <i>PlayerShip</i> is accelerating.
     *
     * @return ture if ship is accelerating, false otherwise.
     */
    public boolean getAccelerate() {
        return this.isAccelerating;
    }

    @Override
    public void shoot() {
        getGameState().addProjectile(new Projectile(this, this.getHeading(), new int[]{this.getX(), this.getY()}, getGameState()));
        log.debug("Projectile added");
    }

    /**
     * Destroys the <i>PlayerShip</i>.
     */
    @Override
    public void destroy() {
        createExplosionEffect();
        if(getGameState().getPlayerShip().getLives() > 1){
            getGameState().removePlayerShip();
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
            }
            getGameState().addPlayerShip(this);
            getGameState().getPlayerShip().setCoord(new int[]{400,300});
            getGameState().getPlayerShip().setVelocity(new float[]{0,0});
            getGameState().getPlayerShip().setHeading(0);      
            getGameState().getPlayerShip().turnLeft(false);
            getGameState().getPlayerShip().turnRight(false);
            getGameState().getPlayerShip().accelerate(false);
        }
        else{
            getGameState().removePlayerShip();
        }    
        try {
            Sound sound = new Sound("missle.wav");
            sound.play();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createExplosionEffect() {
        for (int i = 0; i < NUM_DEBRIS; i++) {
            int x = getX();
            int y = getY();
            getGameState().addExplosion(new MapObjectTTL(new float[]{Difficulty.randExplosionVelocity(), Difficulty.randExplosionVelocity()}, Difficulty.randomHeading(), new int[]{x, y}, 0, getGameState()));
        }
    }
}
