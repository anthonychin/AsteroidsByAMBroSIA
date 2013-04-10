package UnitTests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;




import game.*;
import gui.*;
import java.util.ArrayList;
import java.util.Random;
import org.junit.After;

/**
 *
 * @author Anthony Chin
 */
public class GameObjectTest {
    private GameState gameState;
    private AlienShip as;
    private PlayerShip ps;

    @Before
    public void setUp() throws Exception{
        gameState = new GameState();
        GameAssets.loadSounds();
    }
    
    @Test
    public void PlayerObject(){
        ps = new PlayerShip(new float[]{0, 0}, 0, new int[]{MenuGUI.WIDTH / 2, MenuGUI.HEIGHT / 2}, gameState, 1, 1, 1);
        gameState.addPlayerShip(ps);      
        assertTrue("PlayerShip is in the field", gameState.getPlayerShip()!=null);
    }

    @Test
    public void AsteroidObject(){
        Random randu = new Random();
        gameState.addAsteroid(new Asteroid(new float[]{1.5f, 1.5f}, randu.nextInt(360), new int[]{randu.nextInt(700), randu.nextInt(500)}, gameState, Asteroid.LARGE_ASTEROID_SIZE));
        assertTrue("Asteroid do not exist on the field", gameState.getAsteroids()!=null);    
    }

    @Test
    public void AlienObject(){
        as = new AlienShip(new float[]{1.5f,1.5f}, 20, new int[]{800,600}, gameState);
        gameState.addAlienShip(as);
        assertTrue("Alien do not exist on the field", gameState.getAlienShip()!=null);
    }
    
    @Test 
    public void ProjectileObject(){
        AlienObject();       
        gameState.addProjectile(new Projectile(as, 20, new int[]{800,600}, gameState));
        gameState.addProjectile(new Projectile(ps, 20, new int[]{800,600}, gameState));
        
        ArrayList<Projectile> al = gameState.getProjectiles();
        assertEquals("There should be two projectiles on the field", al.size(), 2);
    }
    
    @Test
    public void BonusDropObject(){
        gameState.addBonusDrop(new BonusDrop(new int[]{400,300}, gameState, 1));
        gameState.addBonusDrop(new BonusDrop(new int[]{300,400}, gameState, 2));
        gameState.addBonusDrop(new BonusDrop(new int[]{500,500}, gameState, 3));
        
        assertEquals("There should be 3 bonus drop on the field", gameState.getBonusDrops().size(),3);
    }
    
    @After
    public void tearDown(){
        gameState = null;
    }
    
}