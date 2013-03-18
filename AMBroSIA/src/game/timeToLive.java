package game;

import java.util.ArrayList;

/**
 * The Time to live class contains code that checks the time to live of certain objects (such as bonus drops, projectiles) and removes them from the game once it is time for them to be removed.
 * @author Michael
 */
public class timeToLive implements Runnable{
    
    private GameState gameState;
    
    public timeToLive(GameState gs)
    {
        gameState = gs;
    }

    @Override
    public void run() {
        checkTTL();
    }
    
    private void checkTTL()
    {
        //work on all bonus drops, explosions, projectiles
        TTLLogic(gameState.getBonusDrops());
        TTLLogic(gameState.getExplosions());
        TTLLogic(gameState.getProjectiles());
    }
    
    private void TTLLogic(ArrayList<? extends MapObjectTTL> list)
    {
        if (!list.isEmpty())
        {
            for (MapObjectTTL object : list)
            {
                //if expired, delete, else, decrement counter
                if (object.getTTL() > 0)
                {
                    object.setTTL(object.getTTL()-1);
                }
                else
                {
                    object.destroy();
                }
            }
        }
    }
    
    
}
