
package UnitTests;

import MapObjects.MapObject;
import game.GameAssets;
import game.GameState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Test the very basic map objects
 * @author Anthony Chin
 */
public class MapObjectTest {
    private GameState gameState;
    private MapObject obj;
    
    @Before
    public void setUp() {
        gameState = new GameState(); 
        GameAssets.loadSounds();
        obj = new MapObject(new float[]{1.5f, 1.5f}, 350f, new int[]{650, 450},4, gameState);
    }
    
    @After
    public void tearDown(){
        gameState = null;
        obj = null;
    }
    
    @Test
    public void getVelocity(){
        assertEquals("Test velocity as a floating point value", obj.getVelocity()[0], 1.5f, 0.000000005);
        assertEquals("Test velocity as a floating point value", obj.getVelocity()[1], 1.5f, 0.000000005);        
    }
    
    @Test
    public void setVelocity(){
        obj.setVelocity(new float[]{1.6f,1.6f});
        assertEquals("Test velocity as a floating point value", obj.getVelocity()[0], 1.6f, 0.000000005);
        assertEquals("Test velocity as a floating point value", obj.getVelocity()[1], 1.6f, 0.000000005);        
    }    
    
    @Test
    public void getX(){
        assertEquals("x position", obj.getX(), 650);
    }
    
    @Test
    public void getY(){
        assertEquals("y position", obj.getY(), 450);
    }
    
    @Test
    public void setX(){
        obj.setX(20);
        assertEquals("x position", obj.getX(), 20);
    }
    
    @Test
    public void setY(){
        obj.setY(20);
        assertEquals("y position", obj.getY(), 20);
    }
    
    @Test
    public void getGameState(){
        obj.getGameState();
        assertNotNull("map object should be in gameState", obj.getGameState());
    }
    
}
