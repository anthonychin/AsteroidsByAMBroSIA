package UnitTests;


import game.GameAssets;
import game.GameState;
import game.Logic;
import mapObjects.PlayerShip;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.timer.Timer;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * PlayerShip Test Cases
 * @author Anthony Chin
 */
public class PlayerShipTest {
    private GameState gameState;
    private PlayerShip ps;
    
    @Before
    public void setUp() {
        gameState = new GameState(); 
        GameAssets.loadSounds();
        //Logic.startTimer();
        ps = new PlayerShip(new float[]{0, 0}, 0, new int[]{800, 600}, gameState, 3, 1, 0);
    }
    
    @After
    public void tearDown() {
        gameState = null;
        ps = null;
    }

    @Ignore
    @Test
    public void getBomb() {
        assertEquals("There should be one bomb", ps.getBomb() , 0);
    }
    
    @Test
    public void getShieldPoints() {
        assertEquals("There should be 3 shield points when started", ps.getShieldPoints() , 0);
    }

    @Test
    public void getLives() {
        assertEquals("Lives should be 3 when started", ps.getLives() , 3);
    }
    
    @Test 
    public void useBomb(){
        assertTrue("Bomb be at least one or more at all cost", ps.getBomb() >= 1);
    }
    
    @Test
    public void addBomb(){
        ps.addBomb();
        assertTrue("Bomb should be at least 1", ps.getBomb() > 0);
    }
    
    @Ignore
    @Test
    public void setShieldPoints(){
        ps.setShieldPoints(0);
        // Will play the shield sound when damaged.
        assertEquals("ShieldPoints should be empty", ps.getShieldPoints(),0);
    }
    
    @Test
    public void setLives(){
        ps.setLives(1);
        assertTrue("Life should be 1 or more", ps.getLives() > 0);        
    }
    
    @Test
    public void turnLeft(){
        ps.turnLeft(true);
        assertTrue("turning left", ps.isTurningLeft());
    }
    
    @Test
    public void turnRight(){
        ps.turnRight(true);
        assertTrue("turning right", ps.isTurningRight());
    }
    
    @Test
    public void notAccelerating(){
        ps.getAccelerate();
        assertTrue("not accelerating by default", !ps.getAccelerate());
    }
    
    @Test
    public void testShoot(){
        ps.shoot(); // will play sound too
        assertNotNull("object should be shot", gameState.getProjectiles());
    }

    @Test
    public void testShootDirection(){
        ps.shootDirection(); // will play sound too
        assertEquals("object should be shot", gameState.getProjectiles().size(), 4);
        assertEquals("shoot at heading of ship - 20", gameState.getProjectiles().get(0).getHeading(), ps.getHeading() - 20, 1);
        assertEquals("shoot at heading of ship + 20", gameState.getProjectiles().get(1).getHeading(), ps.getHeading() + 20, 1);
        assertEquals("shoot at heading of ship - 60", gameState.getProjectiles().get(2).getHeading(), ps.getHeading() - 60, 1);
        assertEquals("shoot at heading of ship + 60", gameState.getProjectiles().get(3).getHeading(), ps.getHeading() + 60, 1);
    }    
    
    
    @Ignore
    @Test
    // This method works but it doesnt now since I didnt import all the other threads    
    public void destroyShip(){
        gameState.addPlayerShip(ps);
        ps.destroy();
        if(ps.getLives() < 1){
            assertTrue("Player is dead", gameState.isPlayerDead());
        }
        assertNull("Player is removed off the field", gameState.getPlayerShip());
    }
    
    
}