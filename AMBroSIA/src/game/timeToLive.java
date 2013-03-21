package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.log4j.Logger;

/**
 * The Time to live class contains code that checks the time to live of certain
 * objects (such as bonus drops, projectiles) and removes them from the game
 * once it is time for them to be removed.
 *
 * @author Michael
 */
public class timeToLive implements Runnable {

    private GameState gameState;
    private final static Logger log = Logger.getLogger(timeToLive.class.getName());

    public timeToLive(GameState gs) {
        gameState = gs;
        log.setLevel(Logic.LOG_LEVEL);
    }

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

    private void TTLLogic(ArrayList<? extends MapObjectTTL> list) {
        if (!list.isEmpty()) {
            
            ArrayList<Object> toRemove = new ArrayList();
            CopyOnWriteArrayList<? extends MapObjectTTL> objectList = new CopyOnWriteArrayList(list);
            for (MapObjectTTL object : objectList) {             
                //TTL is in seconds : if it still has life left we simply set it for the next one, else, it's gone
                if (object.getTTL() > 0) {
                    object.setTTL(object.getTTL() - 1);
                } 
                else {
                    toRemove.add(object);
                }
            }
            
            //now that we have a list of objects, we remove them
            gameState.removeListOfProjectiles(toRemove);
        }
    }
}
