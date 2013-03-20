package game;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingUtilities;

/**
 * The Time to live class contains code that checks the time to live of certain
 * objects (such as bonus drops, projectiles) and removes them from the game
 * once it is time for them to be removed.
 *
 * @author Michael
 */
public class timeToLive implements Runnable {

    private GameState gameState;

    public timeToLive(GameState gs) {
        gameState = gs;
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
            //iterator needed because only the iterator.remove() can remove an object while in the loop.  Anything else will run into issues.
            Iterator<? extends MapObjectTTL> iter = list.iterator();
            while (iter.hasNext()) {
                MapObjectTTL object = iter.next();
                
                //TTL is in seconds : if it still has life left we simply set it for the next one, else, it's gone
                if (object.getTTL() > 0) {
                    object.setTTL(object.getTTL() - 1);
                } 
                
                else {
                    iter.remove();
                }
            }
        }
    }
}
