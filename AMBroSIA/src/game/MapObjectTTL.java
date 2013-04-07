package game;

import java.awt.Color;

/**
 *
 * @author Michael
 */
public class MapObjectTTL extends MapObject {

    private Color objectColor;
    private final static Color DEFAULT_COLOR = Color.WHITE;
    static private int TIME_TO_LIVE = 3;
    private int ttl;

    MapObjectTTL(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState, Color color) {
        super(velocity, heading, coordinates, acceleration, gameState);
        this.ttl = TIME_TO_LIVE;
        //set a default color
        objectColor = color;
    }
    
    MapObjectTTL(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState)
    {
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
     * Sets the Time to Live property.
     *
     * @param ttl
     */
    public void setTTL(int ttl) {
        this.ttl = ttl;
    }

    @Override
    public void destroy() {
        getGameState().removeExplosion(this);
    }
    

    /**
     * Returns the color of MapObjectTTL
     * @return color of the object
     */
    public Color getColor() {
        return objectColor;
    }


    /**
     * Sets the color of the MapObjectTTL
     * @param newColor new color of the object
     */
    public void setColor(Color newColor) {
        objectColor = newColor;
    }
}
