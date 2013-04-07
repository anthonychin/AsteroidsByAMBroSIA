package game;

import static game.Logic.executeTask;
import gui.MenuGUI;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

/**
 * The
 * <code>PlayerShip</code> class defines all the properties and methods
 * appropriate to the player ship that are not included in Ship.
 *
 * @author Anthony Chin
 */
public class PlayerShip extends Ship {

    /**
     * Value of the maximum velocity of the player ship.
     */
    final public static int MAX_VELOCITY = 8;
    /**
     * Value of the acceleration of the player ship.
     */
    final public static float ACCELERATION = 0.09f;
    /**
     * Value of the deceleration of the player ship.
     */
    final public static int DECELERATION = -2;
    /**
     * Value of the fire rate of the player ship.
     */
    final public static float FIRE_RATE = 0.2f;
    /**
     * Value of the angular speed of the player ship.
     */
    final public static int ANGULAR_SPEED = 30;

    //in milliseconds
    final private static int RESPAWN_DELAY = 2500;
    /**
     * Value of the number of debris when the player ship gets destroyed. 
     */
    final public static int NUM_DEBRIS = 20;
    /**
     * Number of torpedos used when the bomb is used.
     */
    final public static int BOMB_BARRIER = 30;
    
    private int bomb;
    private int shieldPoints;
    private boolean isAccelerating = false;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;
    private boolean isShieldOn = false;
    private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PlayerShip.class.getName());

    /**
     * Creates PlayerShip with the given parameters.
     *
     * @param velocity magnitude and direction of the player ship
     * @param heading angle that the player ship is facing
     * @param coordinates initial x, y coordinate of the player ship
     * @param gameState current game state
     * @param lives lives of  player ship
     * @param bomb number of bomb that player ship has
     * @param shieldPoints shield point of player ship
     */
    public PlayerShip(float[] velocity, float heading, int[] coordinates, GameState gameState, int lives, int bomb, int shieldPoints) {
        super(velocity, heading, coordinates, 0, gameState, lives);
        this.bomb = bomb;
        this.shieldPoints = shieldPoints;
        GameAssets.warp.play();
        getGameState().setPlayerDead(false);
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
            GameAssets.bombUsed.play();
            createBombEffect();
            
            Thread explode = new Thread(){
            public void run(){
                getGameState().bombUsed();
            }
            };
            executeTask(explode,1000,TimeUnit.MILLISECONDS);
        } else {
            GameAssets.noBombs.play();
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
     * @param shieldpoints number representing the new value of shield point
     */
    public void setShieldPoints(int shieldpoints) {
        this.shieldPoints = shieldpoints;
        if (this.shieldPoints == 3) {
            GameAssets.shields3.play();
        } else if (this.shieldPoints == 2) {
            GameAssets.shields2.play();
        } else if (this.shieldPoints == 1) {
            GameAssets.shields1.play();
        } else {
            GameAssets.shields0.play();
        }
    }

    /**
     * Checks if the shield is activated.
     *
     * @return true if shield is activated, false otherwise
     */
    public boolean getShieldStatus() {
        return shieldPoints >= 1;
    }

    /**
     * Sets the PlayerShip to accelerate.
     *
     * @param isAccelerating true if accelerating, false otherwise
     */
    public void accelerate(boolean isAccelerating) {
        if (isAccelerating) {
            this.setAcceleration(ACCELERATION);
        } else {
            this.setAcceleration(0);
        }
    }

    /**
     * Sets the PlayerShip to turn left.
     *
     * @param turning boolean value, true if turning false otherwise
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
     *
     * @param turning boolean value, true if turning false otherwise
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
        GameAssets.playerFire.play();
        log.debug("Projectile added");
    }

    /**
     *  Shoot 4 Projectiles at heading of -20, 20, -60, 60 degree relative to the ship heading.
     */
    public void shootDirection() {
        getGameState().addProjectile(new Projectile(this, this.getHeading() - 20, new int[]{this.getX(), this.getY()}, getGameState()));
        getGameState().addProjectile(new Projectile(this, this.getHeading() + 20, new int[]{this.getX(), this.getY()}, getGameState()));  
        getGameState().addProjectile(new Projectile(this, this.getHeading() - 60, new int[]{this.getX(), this.getY()}, getGameState()));
        getGameState().addProjectile(new Projectile(this, this.getHeading() + 60, new int[]{this.getX(), this.getY()}, getGameState()));          
        GameAssets.playerFire.play();
        log.debug("Projectile added");
    }
    /**
     * Destroys the PlayerShip.
     */
    @Override
    public void destroy() {
        createExplosionEffect();

        if (getLives() > 1) {
            resetShip();
        } else {
            getGameState().removePlayerShip();
            getGameState().setPlayerDead(true);
        }
    }
    
    /**
     * Resets the player ship after it gets destroyed.
     */
    public void resetShip() {
        
        final PlayerShip oldShip = getGameState().getPlayerShip();
        Thread resetShip = new Thread() {
            public void run() {
                getGameState().addPlayerShip(oldShip);
                //to center of screen
                getGameState().getPlayerShip().setCoord(new int[]{MenuGUI.WIDTH/2, MenuGUI.HEIGHT/2});
                getGameState().getPlayerShip().setVelocity(new float[]{0, 0});
                getGameState().getPlayerShip().setShieldPoints(3);
                getGameState().getPlayerShip().setHeading(0);
                getGameState().getPlayerShip().turnLeft(false);
                getGameState().getPlayerShip().turnRight(false);
                getGameState().getPlayerShip().accelerate(false);
                GameAssets.warp.play();
            }
        };

        //check to see if it has already been set to null by an ongoing reset.
        if (oldShip != null) {
            getGameState().removePlayerShip();
            executeTask(resetShip, RESPAWN_DELAY, TimeUnit.MILLISECONDS);
        }
    }

    private void createExplosionEffect() {
        for (int i = 0; i < NUM_DEBRIS; i++) {
            int x = getX();
            int y = getY();
            Color shipColor;
            if (getGameState().isPlayerTwoTurn())
            {
                shipColor = Color.BLUE;
            }
            else
            {
                shipColor = Color.RED;
            }
            getGameState().addExplosion(new MapObjectTTL(new float[]{Difficulty.randExplosionVelocity(), Difficulty.randExplosionVelocity()}, Difficulty.randomHeading(), new int[]{x, y}, 0, getGameState(), shipColor));
        }
    }
    
    private void createBombEffect() {
        for (int i = 0; i < BOMB_BARRIER; i++) { 
            int x = getX();
            int y = getY();
            getGameState().addExplosion(new MapObjectTTL(new float[]{i*360, i*-360}, i, new int[]{x, y}, 0, getGameState()));
        }
    }
}
