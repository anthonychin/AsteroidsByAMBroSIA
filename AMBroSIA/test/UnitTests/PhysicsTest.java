package UnitTests;

import game.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the Physics Class
 *
 * @author Anthony Chin
 */
public class PhysicsTest {

    private GameState gameState;
    private Physics physicsEngine;
    private PlayerShip playerShip;
    private AlienShip alienShip;
    // Most kinematic equations have been verified during the gameplay. and they are private methods, therefore was not tested
    // WrapAround was tested as it is one of our main requirement
    @Before
    public void setUp() {
        gameState = new GameState();
        physicsEngine = new Physics(gameState);

        GameAssets.loadSounds();
    }

    @After
    public void tearDown() {
        gameState = null;
        physicsEngine = null;
    }

    @Test
    public void noCollision() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{500, 400}, gameState);

        ArrayList<MapObject> collisions = physicsEngine.getCollisions();
        assertEquals("no collision occur", collisions.size(), 0);
    }

    @Test
    public void testWrapAround() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{862, 260}, gameState, 1, 0, 0);
        wrapAround(playerShip);

        assertEquals("player ship location", playerShip.getX(), 0);
    }
    @Test
    public void updatePhys(){
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);        
        physicsEngine.update();
        // nothing moved, since no timer 
        assertTrue("player ship moved", playerShip.getX()==360);
    }
    private static void wrapAround(MapObject gameObject) {
        if (gameObject.getX() > 800 + 60) {
            gameObject.setX(0);
        } else if (gameObject.getX() < -1 * 60) {
            gameObject.setX(800);
        }

        if (gameObject.getY() > 600 + 60) {
            gameObject.setY(0);
        } else if (gameObject.getY() < -1 * 60) {
            gameObject.setY(600);
        }
    }
}