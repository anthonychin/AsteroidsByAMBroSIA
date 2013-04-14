
package IntegrationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import game.*;
import mapObjects.*;
/**
 * Time to Live Test Cases
 * @author Anthony Chin
 */
public class TimeToLivesTest {
    private GameState gameState;
    private TimeToLives ttl;
    private PlayerShip playerShip;
    private Projectile p;
    private MapObjectTTL mapObjectTTL;
    private BonusDrop bd;

    
    @Before
    public void setUp(){
        gameState = new GameState();
        ttl = new TimeToLives(gameState);
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{590, 509}, gameState, 1, 0, 0);
        p = new Projectile(playerShip, 350f, new int[]{600, 500}, gameState);
        bd = new BonusDrop(new int[]{591, 500}, gameState, 0);
        mapObjectTTL = new MapObjectTTL(new float[]{0,0},0f,new int[]{591, 500}, 0f,gameState);        
    }
    
    @After
    public void tearDown(){
        gameState = null;
        ttl = null;
        p = null;
        bd = null;
        mapObjectTTL = null;
    }
    
    @Test
    public void testValidTTL(){
        if(p.getTTL()>0 && bd.getTTL()>0 && mapObjectTTL.getTTL()>0){
            assertTrue("they are all valid",true);
        }
        else {
            assertTrue("they are not valid",false);
        }
    }
    // test the method to make sure no exception
    @Test
    public void testRun(){
        gameState.addProjectile(p);
        gameState.addBonusDrop(bd);
        gameState.addExplosion(mapObjectTTL);
        
        ttl.run();
        
    }
            
}
