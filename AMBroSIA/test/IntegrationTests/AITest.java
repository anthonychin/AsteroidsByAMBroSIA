package IntegrationTests;

import MapObjects.AlienShip;
import MapObjects.PlayerShip;
import game.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Anthony Chin
 */
public class AITest {
    private GameState gameState;
    private AI ai;
    private PlayerShip playerShip;
    private AlienShip alienShip;

    @Before
    public void setUp(){
        gameState = new GameState();
        ai = new AI(gameState);
        GameAssets.loadSounds();
    }
    
    @After
    public void tearDown(){
        gameState = null;
        ai = null;
    }
    
    @Test 
    public void AItarget(){
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{500, 400}, gameState);
        
        gameState.addPlayerShip(playerShip);
        gameState.addAlienShip(alienShip);
        
        ai.targetPlayerShip();
        
        assertTrue("projectile exist",gameState.getProjectiles().size()>0);
    }
}
