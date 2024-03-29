package game;

import mapObjects.Projectile;
import mapObjects.BonusDrop;
import mapObjects.MapObjectTTL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * The
 * <code>timeToLive</code> class contains code that checks the time to live of
 * certain objects (such as bonus drops, projectiles) and removes them from the
 * game once it is time for them to be removed.
 *
 * @author Michael
 */
public class TimeToLives implements Runnable {

    private GameState gameState;
    private final static Logger log = Logger.getLogger(TimeToLives.class.getName());

    /**
     * Creates new timeToLive using GameState.
     *
     * @param gameState state of current game
     */
    public TimeToLives(GameState gameState) {
        this.gameState = gameState;
        log.setLevel(Logic.LOG_LEVEL);
    }

    /**
     * Checks time to live of all bonus drops, explosions, and projectiles.
     */
    @Override
    public void run() {
        checkTTL();
    }

    private void checkTTL() {
        //work on all bonus drops, explosions, projectiles
        TTLLogic(gameState.getBonusDrops());
        TTLLogic(gameState.getExplosions());
        TTLLogic(gameState.getProjectiles());
    }

    private void TTLLogic(List<? extends MapObjectTTL> objectList) {
        if (!objectList.isEmpty()) {
            log.debug("Starting TTL Check");
            ArrayList<Object> toRemove = new ArrayList<Object>();
            for (MapObjectTTL object : objectList) {
                //TTL is in 200 msec increments : if it still has life left we simply set it for the next one, else, it's gone
                if (object.getTTL() > 0) {
                    object.setTTL(object.getTTL() - 1);
                } else {
                    toRemove.add(object);
                }
            }

            //now that we have a list of objects, we remove them
            //deal with types as appropriate.  OK to pull first entry always because we make sure befor that the list is not empty
            if (objectList.get(0) instanceof Projectile) {
                gameState.removeListOfProjectiles(toRemove);
            } else if (objectList.get(0) instanceof BonusDrop) {
                gameState.removeListOfBonusDrops(toRemove);
            } //last possibility
            else {
                gameState.removeListOfExplosions(toRemove);
            }
        }
    }
}
