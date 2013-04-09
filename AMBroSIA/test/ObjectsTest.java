import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;




import game.*;
import gui.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Anthony Chin
 */
public class ObjectsTest {
    private GameState gameState;
    

    @Before
    public void setUp() throws Exception{
        gameState = new GameState();
        GameAssets.loadSounds();
    }
    
    //@Test
    public void testMultiply(){
        assertEquals("Test Condition 50 = 50", 50, 50);
        //fail("fail 91");
        // it is now 50 , 50 : Test shouldnt pass and fail should read
    }

    
//    @Test(expected = ArithmeticException.class)
//    public void divideByZero() {
//        int k = 1/0;
//        fail();
//    }
    
    
    @Test
    public void PlayerObject(){
        // Test passes if GameAssets.warp.play() is not used. (It gives a null pointer exception)
        gameState.addPlayerShip(new PlayerShip(new float[]{0, 0}, 0, new int[]{MenuGUI.WIDTH / 2, MenuGUI.HEIGHT / 2}, gameState, 1, 1, 1));      
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
        gameState.addAlienShip(new AlienShip(new float[]{1.5f,1.5f}, 20, new int[]{800,600}, gameState));
        assertTrue("Alien do not exist on the field", gameState.getAlienShip()!=null);
    }
    
    @Test 
    public void ProjectileObject(){
        AlienShip as = new AlienShip(new float[]{1.5f,1.5f}, 20, new int[]{800,600}, gameState);
        gameState.addAlienShip(as);        
        gameState.addProjectile(new Projectile(as, 20, new int[]{800,600}, gameState));
        ArrayList<Projectile> al = gameState.getProjectiles();
        assertTrue("Projectile object do not exist on the field", al!=null);
    }
}