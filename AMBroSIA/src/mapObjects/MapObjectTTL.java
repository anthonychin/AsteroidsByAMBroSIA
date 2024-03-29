package mapObjects;

import game.GameState;
import java.awt.Color;

/**
 * The
 * <code>MapObjectTTL</code> class is a simple game object, similar to
 * MapObject, except with a color attribute that defines the object's color and
 * a Time To Live attribute that defines how long the object remains on screen
 * for.
 *
 * @author Michael
 */
public class MapObjectTTL extends MapObject {

    private Color objectColor;
    private final static Color DEFAULT_COLOR = Color.WHITE;
    static private int TIME_TO_LIVE = 3;
    private int ttl;

    /**
     * Creates MapObjectTTL with given parameters.
     *
     * @param velocity magnitude and direction of the object
     * @param heading heading of the object
     * @param coordinates initial X, Y position of the object
     * @param acceleration acceleration of the object
     * @param gameState current game state
     * @param color color of the object in game
     */
    public MapObjectTTL(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState, Color color) {
        super(velocity, heading, coordinates, acceleration, gameState);
        this.ttl = TIME_TO_LIVE;
        //set a default color
        objectColor = color;
    }

    /**
     * Creates MapObjectTTL with given parameters.
     *
     * @param velocity magnitude and direction of the object
     * @param heading heading of the object
     * @param coordinates initial X, Y position of the object
     * @param acceleration acceleration of the object
     * @param gameState current game state
     */
    public MapObjectTTL(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState) {
        this(velocity, heading, coordinates, acceleration, gameState, DEFAULT_COLOR);
    }

    /**
     * Returns the value of the Time to Live property.
     *
     * @return value of the time to live
     */
    public int getTTL() {
        return this.ttl;
    }

    /**
     * Sets the Time to Live property, in units of 200msec. (i.e. TTL = 5 would
     * be 1 second)
     *
     * @param ttl new time to live value
     */
    public void setTTL(int ttl) {
        this.ttl = ttl;
    }

    //remove from game
    @Override
    public void destroy() {
        getGameState().removeExplosion(this);
    }

    /**
     * Returns the color of MapObjectTTL
     *
     * @return color of the object
     */
    public Color getColor() {
        return objectColor;
    }

    /**
     * Sets the color of the MapObjectTTL
     *
     * @param newColor new color of the object
     */
    public void setColor(Color newColor) {
        objectColor = newColor;
    }
}
