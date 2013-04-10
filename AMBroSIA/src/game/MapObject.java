package game;

import java.awt.Polygon;

/**
 * The
 * <code>MapObject</code> class provides a general representation of game
 * objects (such as asteroids and aliens). It includes functionality present in
 * all objects (to some extent).
 *
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
     * Creates MapObject with given parameter.
     *
     * @param velocity magnitude and direction of the object
     * @param heading heading of the object
     * @param coordinates initial X, Y position of the object
     * @param acceleration acceleration of the object
     * @param gameState current game state
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
     * Returns velocity of MapObject.
     *
     * @return array representing velocity of the object
     */
    public float[] getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity of MapObject.
     *
     * @param velocity array containing magnitude and direction
     */
    public void setVelocity(float[] velocity) {
        this.velocity = velocity;
    }

    /**
     * Returns the acceleration of the MapObject.
     *
     * @return acceleration of the object
     */
    public float getAcceleration() {
        return this.acceleration;
    }

    /**
     * Sets the acceleration of the MapObject.
     *
     * @param acceleration new acceleration value
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Returns the heading in which the MapObject is facing.
     *
     * @return heading of the object
     */
    public float getHeading() {
        return this.heading;
    }

    /**
     * Sets the heading of MapObject.
     *
     * @param heading new heading of object
     */
    public void setHeading(float heading) {
        this.heading = heading;
    }

    /**
     * Returns the coordinates of MapObject.
     *
     * @return array of length 2 consisting of coordinates
     */
    public int[] getCoord() {
        return this.coordinates;
    }

    /**
     * Sets the coordinates of MapObject.
     *
     * @param coordinates new X, Y position of the object
     */
    public void setCoord(int[] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns x-coordinate of MapObject.
     *
     * @return X coordinate of the object
     */
    public int getX() {
        return coordinates[0];
    }

    /**
     * Sets x-coordinate of MapObject.
     *
     * @param x new X coordinate of the object
     */
    public void setX(int x) {
        this.coordinates[0] = x;
    }

    /**
     * Returns y-coordinate of MapObject.
     *
     * @return Y coordinate of the object
     */
    public int getY() {
        return coordinates[1];
    }

    /**
     * Sets y-coordinate of MapObject.
     *
     * @param y new Y coordinate of the object
     */
    public void setY(int y) {
        this.coordinates[1] = y;
    }

    /**
     * Returns shape of MapObject.
     *
     * @return shape of the object
     */
    public Polygon getShape() {
        return this.shape;
    }

    /**
     * Sets the shape of MapObject.
     *
     * @param shape new shape of the object
     */
    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    /**
     * Returns the game state.
     *
     * @return game state initially passed as a constructor argument
     */
    public GameState getGameState() {
        return this.gameState;
    }

    //unimplemented.  All implementing classes should override.
    /**
     * Removes the MapObject from the game state.
     */
    public void destroy() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
