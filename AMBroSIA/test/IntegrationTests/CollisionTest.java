package IntegrationTests;

import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import game.*;
import static game.Collision.ALIEN_SHIELD_DAMAGE;
import static game.Collision.LARGE_ASTEROID_SHIELD_DAMAGE;
import static game.Collision.MEDIUM_ASTEROID_SHIELD_DAMAGE;
import static game.Collision.PROJECTILE_SHIELD_DAMAGE;
import static game.Collision.SMALL_ASTEROID_SHIELD_DAMAGE;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static org.junit.Assert.*;


/**
 *
 * @author Anthony Chin
 */
public class CollisionTest {

    private GameState gameState;
    private MapObjectTTL ttlObj;
    private AlienShip alienShip;
    private PlayerShip playerShip;
    private Asteroid roid;
    private BonusDrop bd;
    private static Projectile p2;
    private static Projectile p;
    private static ScheduledExecutorService timer;

    @Before
    public void setUp() {
        //gui = new MenuGUI(buttonPress,keyboard);
        gameState = new GameState();

        GameAssets.loadSounds();

        timer = Executors.newScheduledThreadPool(4);

    }

    @After
    public void tearDown() {
        gameState = null;
        timer.shutdown();
    }

    // Due to majority of the collision methods are in private and what calls it is the run method, the best way to test is to make my own test cases and check the collision logic
    @Test
    public void testCollisionPlayerAlien() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 280}, gameState);
        
        gameState.addAlienShip(alienShip);
        gameState.addPlayerShip(playerShip);
        // Sum of Polygon length
        // X boundary : 16 + 40
        // Y boundary : 20 + 14 (ship + alienship)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getAlienShip().getX()) <= 16 + 40 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getAlienShip().getY()) <= 20 + 14))) {
            collisionLogic(playerShip, alienShip);
        }
        assertNull("player ship destroyed", gameState.getPlayerShip());
    }

    @Test
    public void testCollisionPlayerAsteroid() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{400, 300}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        gameState.addPlayerShip(playerShip);
        gameState.addAsteroid(roid);
        // sum of polygon length
        // X boundary : 16 + 100
        // Y boundary : 20 + 109 (ship + asteroid)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getAsteroids().get(0).getX()) <= 16 + 100 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getAsteroids().get(0).getY()) <= 20 + 109))) {
            collisionLogic(playerShip, roid);
        }

        assertNull("player ship destroyed", gameState.getPlayerShip());
    }

    @Test
    public void testCollisionPlayerProjectile() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{590, 509}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 300}, gameState);
        p = new Projectile(playerShip, 350f, new int[]{600, 500}, gameState);
        p2 = new Projectile(alienShip, 350f, new int[]{595, 510}, gameState);

        gameState.resetToDefaults();
        gameState.addPlayerShip(playerShip);
        gameState.addAlienShip(alienShip);
        gameState.addProjectile(p);
        gameState.addProjectile(p2);
        // Summ of polygon length
        // X boundary : 16 + 6
        // Y boundary : 20 + 6 (ship + prjectile)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getProjectiles().get(1).getX()) <= 16 + 6 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getProjectiles().get(1).getY()) <= 20 + 6))) {
            collisionLogic(playerShip, p2);
        }
        assertNull("player ship destroyed", gameState.getPlayerShip());
    }

    @Test
    public void testCollisionPlayerBonusDrop() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{590, 509}, gameState, 1, 0, 0);
        bd = new BonusDrop(new int[]{591, 500}, gameState, 0);


        gameState.resetToDefaults();
        gameState.addPlayerShip(playerShip);
        gameState.addBonusDrop(bd);
        // X boundary : 16 + 16 (with respect to polygon length)
        // Y boundary : 20 + 16 (ship + bonus)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getBonusDrops().get(0).getX()) <= 16 + 16 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getBonusDrops().get(0).getY()) <= 20 + 16))) {
            collisionLogic(playerShip, bd);
        }
        assertEquals("bonus destroyed", gameState.getBonusDrops().size(), 0);
    }

    @Test
    public void testCollisionAlienShipPlayerShip() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 280}, gameState);

        gameState.addAlienShip(alienShip);
        gameState.addPlayerShip(playerShip);


        // X boundary : 16 + 40 
        // Y boundary : 20 + 14 (ship + alienShip)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getAlienShip().getX()) <= 16 + 40 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getAlienShip().getY()) <= 20 + 14))) {
            collisionLogic(playerShip, alienShip);
        }
        assertNull("alien ship destroyed", gameState.getAlienShip());

    }

    @Test
    public void testCollisionProjectilePlayer() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{590, 509}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 300}, gameState);
        p = new Projectile(playerShip, 350f, new int[]{600, 500}, gameState);
        p2 = new Projectile(alienShip, 350f, new int[]{595, 510}, gameState);

        gameState.resetToDefaults();
        gameState.addPlayerShip(playerShip);
        gameState.addAlienShip(alienShip);
        gameState.addProjectile(p);
        gameState.addProjectile(p2);

        // X boundary : 16 + 6
        // Y boundary : 20 + 6 (ship + prjectile)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getProjectiles().get(1).getX()) <= 16 + 6 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getProjectiles().get(1).getY()) <= 20 + 6))) {
            collisionLogic(playerShip, p2);
        }
        assertEquals("only player projectile exist", gameState.getProjectiles().size(), 1);
    }

    @Test
    public void testCollisionAsteroidPlayer() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{400, 300}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        gameState.addPlayerShip(playerShip);
        gameState.addAsteroid(roid);
        // X boundary : 16 + 100
        // Y boundary : 20 + 109 (ship + asteroid)
        if ((Math.abs(gameState.getPlayerShip().getX() - gameState.getAsteroids().get(0).getX()) <= 16 + 100 && (Math.abs(gameState.getPlayerShip().getY() - gameState.getAsteroids().get(0).getY()) <= 20 + 109))) {
            collisionLogic(playerShip, roid);
        }

        assertEquals("2 asteroids", gameState.getAsteroids().size(),2);
    }

    @Test
    public void testCollisionProjectileAlien() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{590, 509}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 300}, gameState);
        p = new Projectile(playerShip, 350f, new int[]{403, 302}, gameState);
        p2 = new Projectile(alienShip, 350f, new int[]{595, 510}, gameState);

        gameState.resetToDefaults();
        gameState.addPlayerShip(playerShip);
        gameState.addAlienShip(alienShip);
        gameState.addProjectile(p2);
        gameState.addProjectile(p);
        // Polygon boundary conditions
        // X boundary : 40 + 6 
        // Y boundary : 14 + 6 (alienship + prjectile)
        if ((Math.abs(gameState.getAlienShip().getX() - gameState.getProjectiles().get(1).getX()) <= 40 + 6 && (Math.abs(gameState.getAlienShip().getY() - gameState.getProjectiles().get(1).getY()) <= 14 + 6))) {
            collisionLogic(alienShip, p);
        }
        assertEquals("only player projectile exist", gameState.getProjectiles().size(), 1);
    }    
    
    @Test
    public void testCollisionAlienProjectile() {
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{590, 509}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 300}, gameState);
        p = new Projectile(playerShip, 350f, new int[]{403, 302}, gameState);
        p2 = new Projectile(alienShip, 350f, new int[]{595, 510}, gameState);

        gameState.resetToDefaults();
        gameState.addPlayerShip(playerShip);
        gameState.addAlienShip(alienShip);
        gameState.addProjectile(p2);
        gameState.addProjectile(p);
        // Polygon boundary conditions
        // X boundary : 40 + 6 
        // Y boundary : 14 + 6 (alienship + prjectile)
        if ((Math.abs(gameState.getAlienShip().getX() - gameState.getProjectiles().get(1).getX()) <= 40 + 6 && (Math.abs(gameState.getAlienShip().getY() - gameState.getProjectiles().get(1).getY()) <= 14 + 6))) {
            collisionLogic(alienShip, p);
        }
        assertNull("alien is dead", gameState.getAlienShip());
    } 

    @Test
    public void testCollisionAsteroidAlien() {
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 300}, gameState);
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{400, 300}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        gameState.addAlienShip(alienShip);
        gameState.addAsteroid(roid);
        // X boundary : 40 + 100
        // Y boundary : 14 + 109 (alienship + asteroid)
        if ((Math.abs(gameState.getAlienShip().getX() - gameState.getAsteroids().get(0).getX()) <= 40 + 100 && (Math.abs(gameState.getAlienShip().getY() - gameState.getAsteroids().get(0).getY()) <= 14 + 109))) {
            collisionLogic(alienShip, roid);
        }

        assertEquals("2 asteroids", gameState.getAsteroids().size(),2);
    }    

    @Test
    public void testCollisionAlienAsteroid() {
        alienShip = new AlienShip(new float[]{0, 0}, 0, new int[]{400, 300}, gameState);
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{400, 300}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        gameState.addAlienShip(alienShip);
        gameState.addAsteroid(roid);
        // X boundary : 40 + 100
        // Y boundary : 14 + 109 (alienship + asteroid)
        if ((Math.abs(gameState.getAlienShip().getX() - gameState.getAsteroids().get(0).getX()) <= 40 + 100 && (Math.abs(gameState.getAlienShip().getY() - gameState.getAsteroids().get(0).getY()) <= 14 + 109))) {
            collisionLogic(alienShip, roid);
        }

        assertNull("alien collisions",gameState.getAlienShip());
    }
    
    @Test
    public void testCollisionProjectileAsteroid(){
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{400, 300}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        p = new Projectile(playerShip, 350f, new int[]{403, 302}, gameState);
        gameState.addProjectile(p);
        gameState.addAsteroid(roid);
        // X boundary : 6 + 100
        // Y boundary : 6 + 109 (alienship + asteroid)
        if ((Math.abs(gameState.getProjectiles().get(0).getX() - gameState.getAsteroids().get(0).getX()) <= 6 + 100 && (Math.abs(gameState.getProjectiles().get(0).getY() - gameState.getAsteroids().get(0).getY()) <= 6 + 109))) {
            collisionLogic(p, roid);
        }       
        assertEquals("size of projectile list is empty", gameState.getProjectiles().size(),0);   
    }

    @Test
    public void testCollisionAsteroidProjectile(){
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{400, 300}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        p = new Projectile(playerShip, 350f, new int[]{403, 302}, gameState);
        gameState.addProjectile(p);
        gameState.addAsteroid(roid);
        // X boundary : 6 + 100
        // Y boundary : 6 + 109 (alienship + asteroid)
        if ((Math.abs(gameState.getProjectiles().get(0).getX() - gameState.getAsteroids().get(0).getX()) <= 6 + 100 && (Math.abs(gameState.getProjectiles().get(0).getY() - gameState.getAsteroids().get(0).getY()) <= 6 + 109))) {
            collisionLogic(p, roid);
        }       
        assertEquals("asteroid size is 2", gameState.getAsteroids().size(),2);   
    }
    
    private boolean collisionLogic(PlayerShip playerShip, Asteroid asteroid) {
        asteroid.destroy(false);
        //based on asteroid size, deal with player ship shield effects and destroy if necessary
        if (asteroid.getSize() == Asteroid.LARGE_ASTEROID_SIZE) {
            if (playerShip.getShieldPoints() >= LARGE_ASTEROID_SHIELD_DAMAGE) {
                playerShip.setShieldPoints(playerShip.getShieldPoints() - LARGE_ASTEROID_SHIELD_DAMAGE);
            } else if (playerShip.getLives() == 1) {
                playerShip.destroy();
            } else {
                playerShip.setLives(playerShip.getLives() - 1);
                playerShip.destroy();
            }
        } else if (asteroid.getSize() == Asteroid.MEDIUM_ASTEROID_SIZE) {
            if (playerShip.getShieldPoints() >= MEDIUM_ASTEROID_SHIELD_DAMAGE) {
                playerShip.setShieldPoints(playerShip.getShieldPoints() - MEDIUM_ASTEROID_SHIELD_DAMAGE);
            } else if (playerShip.getLives() == 1) {
                playerShip.destroy();
            } else {
                playerShip.setLives(playerShip.getLives() - 1);
                playerShip.destroy();
            }
        } else if (asteroid.getSize() == Asteroid.SMALL_ASTEROID_SIZE) {
            if (playerShip.getShieldPoints() >= SMALL_ASTEROID_SHIELD_DAMAGE) {
                playerShip.setShieldPoints(playerShip.getShieldPoints() - SMALL_ASTEROID_SHIELD_DAMAGE);
            } else if (playerShip.getLives() == 1) {
                playerShip.destroy();
            } else {
                playerShip.setLives(playerShip.getLives() - 1);
                playerShip.destroy();
            }
        }

        //return has the player been removed
        return gameState.getPlayerShip() == null;
    }

    private void collisionLogic(PlayerShip playerShip, BonusDrop bonusDrop) {
        //give bonus to player, then remove
        if (bonusDrop.getType() == BonusDrop.BOMB_BONUS_DROP) {
            playerShip.addBomb();
        } else if (bonusDrop.getType() == BonusDrop.LIFE_BONUS_DROP) {
            playerShip.setLives(playerShip.getLives() + 1);
        } else if (bonusDrop.getType() == BonusDrop.SHIELD_ONE_POINT_DROP) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 1);
        } else if (bonusDrop.getType() == BonusDrop.SHIELD_TWO_POINTS_DROP) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 2);
        } else if (bonusDrop.getType() == BonusDrop.SHIELD_THREE_POINTS_DROP) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 3);
        }
        bonusDrop.destroy();
    }

    private boolean collisionLogic(PlayerShip playerShip, AlienShip alienShip) {
        //take care of shield damage (and player destruction if necessary), and return true if the alien has been destroyed
        if (playerShip.getShieldPoints() >= ALIEN_SHIELD_DAMAGE) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() - ALIEN_SHIELD_DAMAGE);
        } else if (playerShip.getLives() == 1) {
            playerShip.destroy();
        } else {
            playerShip.setLives(playerShip.getLives() - 1);
            playerShip.destroy();
        }
        alienShip.destroy(false);
        //return has the player been removed
        return gameState.getPlayerShip() == null;
    }

    private boolean collisionLogic(PlayerShip playerShip, Projectile projectile) {

        //deal with shield effects, and destroy player if necessary.
        if (playerShip.getShieldPoints() >= PROJECTILE_SHIELD_DAMAGE) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() - PROJECTILE_SHIELD_DAMAGE);
        } else if (playerShip.getLives() == 1) {
            playerShip.destroy();
        } else {
            playerShip.setLives(playerShip.getLives() - 1);
            playerShip.destroy();
        }

        //always destroy projectile
        projectile.destroy();

        //return has the player been removed
        return gameState.getPlayerShip() == null;
    }

    private void collisionLogic(AlienShip alienShip, Asteroid asteroid) {
        //simply destroy both alien and asteroid

        alienShip.destroy(false);
        asteroid.destroy(false);
    }

    private void collisionLogic(AlienShip alienShip, Projectile projectile) {

        //destroy alien and projectile
        alienShip.destroy(false);
        projectile.destroy();
    }

    private void collisionLogic(Projectile projectile, Asteroid asteroid) {

        //destroy asteroid and projectile
        projectile.destroy();
        asteroid.destroy(false);
    }
}
