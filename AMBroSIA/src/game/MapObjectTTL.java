package game;

import java.awt.Color;

/**
 *
 * @author Michael
 */
public class MapObjectTTL extends MapObject {

    final static private int TIME_TO_LIVE = 3;
    private Color objectColor;
    private final static Color DEFAULT_COLOR = Color.WHITE;
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
    
    public Color getColor() {
        return objectColor;
    }

    public void setColor(Color newColor) {
        objectColor = newColor;
    }
}
