/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTests;

import game.Asteroid;
import game.GameAssets;
import game.GameState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Asteroids Test Cases
 * @author Anthony Chin
 */
public class AsteroidsTest {
    private GameState gameState;
    private Asteroid roid;
    
    @Before
    public void setUp() {
        gameState = new GameState(); 
        GameAssets.loadSounds();
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{650, 450}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
    }
    
    @After
    public void tearDown() {
        gameState = null;
        roid = null;
    }
    
    @Test
    public void asteroidSize(){
        if(roid.getSize() == 3){
            assertEquals("Large Asteroid Size",roid.getSize(),3);
        }
        else if (roid.getSize() == 2){
            assertEquals("Medium Asteroid Size",roid.getSize(),2);        
        }
        else {
            assertEquals("Your size is " + roid.getSize() + "and you should get a Small Asteroid Size",roid.getSize(),1);                
        }
    }
    
    @Test
    public void destroyAsteroid(){
        gameState.addAsteroid(roid);
        int s = roid.getSize();
        roid.destroy(false);
        
        if(s > 1){
            assertEquals("2 smaller Asteroids exist", gameState.getAsteroids().size(), 2);
            assertEquals("smaller asteroid size", gameState.getAsteroids().get(0).getSize(), s-1);
            assertEquals("smaller asteroid size", gameState.getAsteroids().get(1).getSize(), s-1);
        }
        else {
            assertEquals("no roid shouldnt exist", gameState.getAsteroids().size(),0);
        }
    }
    
    @Test
    public void destroyAsteroidBomb(){
        gameState.addAsteroid(roid);
        roid.destroy(true);
        
        assertEquals("the asteroid do not exist", gameState.getAsteroids().size(),0);
    }
    
}
