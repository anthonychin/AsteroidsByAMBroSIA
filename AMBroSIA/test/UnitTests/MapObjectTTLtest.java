/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import game.MapObjectTTL;
import game.GameAssets;
import game.GameState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * MapObjectTTL test cases
 * @author Anthony Chin
 */
public class MapObjectTTLtest {
    private GameState gameState;
    private MapObjectTTL ttlObj;
    
    @Before
    public void setUp(){
        gameState = new GameState();
        ttlObj = new MapObjectTTL(new float[]{1.5f, 1.5f}, 350f, new int[]{650, 450},4, gameState);
    }
    
    @After
    public void tearDown(){
        gameState = null;
        ttlObj = null;
    }
    
    @Test
    public void getTTL(){
        assertTrue("Time to live need to be greater than 0", ttlObj.getTTL()>0);
    }
    
    @Test
    public void setTTL(){
        ttlObj.setTTL(20);
        assertEquals("Time to live is 20", 20, ttlObj.getTTL());
    }
}
