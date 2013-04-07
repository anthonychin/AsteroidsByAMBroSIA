package game;

/**
 * The bonus drop object.
 * @author Nikolaos
 */
public class BonusDrop extends MapObjectTTL {

    final public static int BOMB_BONUS_DROP = 0;
    final public static int LIFE_BONUS_DROP = 1;
    final public static int SHIELD_THREE_POINTS_DROP = 2;
    final public static int SHIELD_TWO_POINTS_DROP = 3;
    final public static int SHIELD_ONE_POINT_DROP = 4;
    private int type;
    //10 sec.
    final private int TIME_TO_LIVE = 25;

    //create bonus drop
    BonusDrop(int[] coordinates, GameState gameState, int type) {
        super(new float[]{0, 0}, 0, coordinates, 0, gameState);
        this.type = type;
        this.setTTL(TIME_TO_LIVE);
    }

    //the type of bonus drop
    public int getType() {
        return this.type;
    }

    //remove the bonus drop from the game
    @Override
    public void destroy() {
        getGameState().removeBonusDrop(this);
    }
}
