package UnitTests;

import MapObjects.AlienShip;
import MapObjects.Projectile;
import game.GameAssets;
import game.GameState;
import MapObjects.PlayerShip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Projectile Test Cases
 * @author Anthony Chin
 */
public class ProjectileTest {
    private GameState gameState;
    private Projectile p;
    private Projectile p2;
    private PlayerShip ps;
    private AlienShip as;
    
    @Before
    public void setUp(){
        gameState = new GameState();
        GameAssets.loadSounds();        
        ps = new PlayerShip(new float[]{0, 0}, 0, new int[]{800, 600}, gameState, 3, 1, 3);
        as = new AlienShip(new float[]{1.5f,1.5f}, 20, new int[]{800,600}, gameState);
        p = new Projectile(ps, 350f, new int[]{600, 500},gameState);
        p2 = new Projectile(as, 350f, new int[]{610, 510},gameState);
        
    }
    
    @After
    public void tearDown(){
        gameState = null;
        p = null;
        p2 = null;
    }
    
    @Test
    public void getOwner(){
        gameState.addProjectile(p);
        gameState.addProjectile(p2);

        assertEquals("Player Projectile", p.getOwner(), 1);
        assertEquals("Alien Projectile", p2.getOwner(), 2);

    }
    
    @Test
    public void destroyProjectile(){
        gameState.addProjectile(p);
        p.destroy();
        assertEquals("projectile is gone", gameState.getProjectiles().size(), 0);
    }
}
