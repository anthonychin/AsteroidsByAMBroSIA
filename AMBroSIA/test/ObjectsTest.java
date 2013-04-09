import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;




import game.*;
import gui.*;
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

    public void NPCObject(){
        Random randu = new Random();
        gameState.addAsteroid(new Asteroid(new float[]{1.5f, 1.5f}, randu.nextInt(360), new int[]{randu.nextInt(700), randu.nextInt(500)}, gameState, Asteroid.LARGE_ASTEROID_SIZE));
        assertTrue("NPC objects are currently at the field", gameState.getAsteroids()!=null);    
    }

}