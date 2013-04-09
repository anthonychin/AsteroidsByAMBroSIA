package game;

/**
 * The bonus drop object.
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
    private int type;
    //10 sec.
    final private int TIME_TO_LIVE = 25;

    //create bonus drop
    /**
     * Creates bonus drop with given parameters.
     * @param coordinates initial X, Y position of the bonus drop
     * @param gameState current game state
     * @param type type of bonus drop
     */
    public BonusDrop(int[] coordinates, GameState gameState, int type) {
        super(new float[]{0, 0}, 0, coordinates, 0, gameState);
        this.type = type;
        this.setTTL(TIME_TO_LIVE);
    }

    //the type of bonus drop
    /**
     * Returns the type of bonus drop.
     * @return int value representing type of bonus drop
     */
    public int getType() {
        return this.type;
    }

    //remove the bonus drop from the game
    @Override
    public void destroy() {
        getGameState().removeBonusDrop(this);
    }
}
