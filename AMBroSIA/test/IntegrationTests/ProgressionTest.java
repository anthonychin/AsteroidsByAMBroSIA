package IntegrationTests;

import game.*;
import mapObjects.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Progression Test Cases: Not possible to test, due to a timer is needed
 * @author Anthony Chin
 */
public class ProgressionTest {
    private GameState gameState;
    private Progression progression;
    
    @Before
    public void setUp(){
        GameAssets.loadSounds();
        gameState = new GameState();
        progression = new Progression(gameState,false);
    }
    
    @After
    public void tearDown(){
        gameState = null;
        progression = null;
    }
    
    @Test
    public void testRun(){
        progression.run();
        assertTrue("progression runs: otherwise test would fail", true);
    }
    
    @Test
    public void testInitialLevel(){
        progression.setupInitialLevel();
        assertTrue("asteroids exist", gameState.getAsteroids().size()>0);
    }
    @Test
    public void testInitialLevel2(){
        progression.setupInitialLevel();
        assertNotNull("player exist", gameState.getPlayerShip());
    }
        
}
