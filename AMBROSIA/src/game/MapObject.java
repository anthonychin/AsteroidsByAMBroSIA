package game;

import java.awt.Polygon;

/**
 * The <code>MapObject</code> class provides a general representation of game objects (such as asteroids and aliens).
 * It includes functionality present in all objects (to some extent). 
 * @author Nikolaos
 */
public class MapObject {

    private float[] velocity;
    private int[] coordinates;
    private float heading;
    private float acceleration;
    private GameState gameState;
    private Polygon shape;

    /**
     * Creates <i>MapObject</i> with given parameter.
     * 
     * @param velocity
     * @param heading
     * @param coordinates
     * @param acceleration
     * @param gameState
     */
    public MapObject(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState) {
        this.velocity = velocity;
        this.heading = heading;
        this.coordinates = coordinates;
        this.acceleration = acceleration;
        this.gameState = gameState;
        this.shape = new Polygon(new int[]{0}, new int[]{0}, 1);
    }

    /**
     * Returns velocity of <i>MapObject</i>.
     * @return array representing velocity of the object
     */
    public float[] getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of <i>MapObject</i>.
     * @param velocity
     */
    public void setVelocity(float[] velocity) {
        this.velocity = velocity;
    }

    /**
     * Returns the acceleration of the <i>MapObject</i>.
     * @return acceleration
     */
    public float getAcceleration() {
        return this.acceleration;
    }

    /**
     * Sets the acceleration of the <i>MapObject</i>.
     * @param acceleration
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Returns the heading in which the <i>MapObject</i> is facing.
     * @return value of heading
     */
    public float getHeading() {
        return this.heading;
    }

    /**
     * Sets the heading of <i>MapObject</i>.
     * @param heading
     */
    public void setHeading(float heading) {
        this.heading = heading;
    }

    /**
     * Returns the coordinates of <i>MapObject</i>.
     * @return array of length 2 consisting of coordinates
     */
    public int[] getCoord() {
        return this.coordinates;
    }

    /**
     * Sets the coordinates of <i>MapObject</i>.
     * @param coordinates
     */
    public void setCoord(int[] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns x-coordinate of <i>MapObject</i>.
     * @return x-coordinate of an object
     */
    public int getX() {
        return coordinates[0];
    }

    /**
     * Sets x-coordinate of <i>MapObject</i>.
     * @param x
     */
    public void setX(int x) {
        this.coordinates[0] = x;
    }

    /**
     * Returns y-coordinate of <i>MapObject</i>.
     * @return y-coordinate of an object
     */
    public int getY() {
        return coordinates[1];
    }

    /**
     * Sets y-coordinate of <i>MapObject</i>.
     * @param y
     */
    public void setY(int y) {
        this.coordinates[1] = y;
    }

    /**
     * Returns shape of <i>MapObject</i>.
     * @return polygon representing shape
     */
    public Polygon getShape() {
        return this.shape;
    }

    /**
     * Sets the shape of <i>MapObject</i>.
     * @param shape
     */
    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    /**
     * Returns the game state.
     * @return gameState object initially passed as a constructor argument
     */
    public GameState getGameState() {
        return this.gameState;
    }

    //unimplemented.  All implementing classes should override.
    /**
     * Removes the <i>MapObject</i> from the game state. 
     */
    public void destroy() {
    }
}
