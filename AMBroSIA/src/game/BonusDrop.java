package game;

import java.awt.Color;

/**
 * The
 * <code>BonusDrop</code> class provides a general representation of all the
 * bonus drop objects.
 *
 * @author Nikolaos, Meong Hye Seo
 */
public class BonusDrop extends MapObjectTTL {

    /**
     * An int value that declares the type of bonus drop to bomb.
     */
    final public static int BOMB_BONUS_DROP = 0;
    /**
     * An int value that declares the type of bonus drop to life.
     */
    final public static int LIFE_BONUS_DROP = 1;
    /**
     * An int value that declares the type of bonus drop to 3 shield points.
     */
    final public static int SHIELD_THREE_POINTS_DROP = 2;
    /**
     * An int value that declares the type of bonus drop to 2 shield points.
     */
    final public static int SHIELD_TWO_POINTS_DROP = 3;
    /**
     * An int value that declares the type of bonus drop to 1 shield point.
     */
    final public static int SHIELD_ONE_POINT_DROP = 4;
    final private static Color LIFE_COLOR = Color.GREEN;
    final private static Color BOMB_COLOR = Color.RED;
    final private static Color SHIELD = Color.BLUE;
    private static int type;
    final private int TIME_TO_LIVE = 75;

    /**
     * Creates bonus drop with given parameters.
     *
     * @param coordinates initial X, Y position of the bonus drop
     * @param gameState current game state
     * @param type type of bonus drop
     */
    public BonusDrop(int[] coordinates, GameState gameState, int type) {
        super(new float[]{0, 0}, 0, coordinates, 0, gameState);
        this.type = type;
        this.setTTL(TIME_TO_LIVE);
        //set color based on type: 
        if (type == BOMB_BONUS_DROP) {
            this.setColor(BOMB_COLOR);
        } else if (type == LIFE_BONUS_DROP) {
            this.setColor(LIFE_COLOR);
        } else if ((type == SHIELD_THREE_POINTS_DROP) || (type == SHIELD_TWO_POINTS_DROP) || (type == SHIELD_ONE_POINT_DROP)) {
            this.setColor(SHIELD);
        }
    }

    /**
     * Returns the type of bonus drop.
     *
     * @return int value representing type of bonus drop
     */
    public int getType() {
        return this.type;
    }

    /**
     * Remove the bonus drop from the game
     */
    @Override
    public void destroy() {
        getGameState().removeBonusDrop(this);
    }
}
