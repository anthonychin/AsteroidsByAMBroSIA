
package UnitTests;

import game.AlienShip;
import game.GameAssets;
import game.GameState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


/**
 * Alien Test Cases
 * @author Anthony Chin
 */
public class AlienTest {
    private GameState gameState;
    private AlienShip as;
    
    @Before
    public void setUp(){
        gameState = new GameState();
        GameAssets.loadSounds();
        as = new AlienShip(new float[]{1.5f,1.5f}, 20, new int[]{800,600}, gameState);
    }
    
    @After
    public void tearDown(){
        gameState = null;
        as = null;
    }
    
    @Test
    public void killAllAlien(){
        gameState.addAlienShip(as);
        as.destroy(false);
        assertNull("Alien should not exist", gameState.getAlienShip());
        assertEquals("Bonus drop should exist", gameState.getBonusDrops().size(), 1);
    }
    
    @Test
    public void killAlienWithBomb(){
        gameState.addAlienShip(as);
        as.destroy(true);
        assertNull("Alien should not exist", gameState.getAlienShip());
        assertEquals("Bonus drop shouldnt exist", gameState.getBonusDrops().size(), 0);
    }
}
