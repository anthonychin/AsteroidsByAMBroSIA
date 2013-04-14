package IntegrationTests;

import game.Difficulty;
import mapObjects.AlienShip;
import mapObjects.Projectile;
import game.GameAssets;
import game.GameState;
import mapObjects.PlayerShip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
/**
 * Difficulty Test Cases
 * @author Anthony Chin
 */
public class DifficultyTest {
    private GameState gameState;
    
    @Before
    public void setUp(){
        gameState = new GameState();
        
    }
    
    @After
    public void tearDown(){
        gameState = null;
    }
    
    @Test
    public void getHeading(){
        float heading = Difficulty.randomHeading();
        assertEquals("Heading should always be lower than 360", heading, 0, 360.000005);
    }
    
    @Ignore
    @Test
    public void getAsteroidVelocityBaseOnLevel(){
        // should be greater than one at all cost
        int maxLevel = 30;
        for (int i = 1; i < maxLevel; i++){
            float velocity = Difficulty.randomAsteroidVelocity(i);
            switch(i){
                case 1: assertEquals("Asteroid velocity should be 0.5 at minimum to prevent non moving asteroid: " +  velocity + "i: " + i, velocity, 0.5, 5);
                    break;
            }          
        }
    }
    
    
    // At level 6, there is an asteroid at the speed 0.47, which gets round down to 0
    // At level 11, there's an asteroid at speed -0.24
    @Test
    public void getAsteroidVelocity(){
      int maxLevel = 30;
        for(int i = 1; i < maxLevel; i++){
        float velocity = Difficulty.randomAsteroidVelocity(i);          
          if(velocity>=0.5){
            assertTrue("Asteroid velocity should be 0.5 at minimum to prevent non moving asteroid: " +  velocity + "i: " + i, velocity>=0.5);    
          }
          else{
              assertTrue("Asteroid needs to be smaller than -0.5 at minimum or 0.5" + velocity + "i: " + i, velocity<=-0.5);
          }
      }
    }
    
    
}
