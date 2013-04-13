package IntegrationTests;

import static org.mockito.Mockito.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import game.*;
import static game.Collision.ALIEN_SHIELD_DAMAGE;
import static game.Collision.LARGE_ASTEROID_SHIELD_DAMAGE;
import static game.Collision.MEDIUM_ASTEROID_SHIELD_DAMAGE;
import static game.Collision.PROJECTILE_SHIELD_DAMAGE;
import static game.Collision.SMALL_ASTEROID_SHIELD_DAMAGE;
import gui.MenuGUI;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.junit.Ignore;
/**
 *
 * @author Anthony Chin
 */
public class CollisionTest {
    private GameState gameState;
    private Logic logic;
    private MapObject mapObject;
    private MapObjectTTL ttlObj;
    private Physics physicEngine;
    private Collision collision;
    private AlienShip alienShip;
    private PlayerShip playerShip;
    private Asteroid roid;
    private BonusDrop bd;
    private static GraphicsEngine graphicsEngine;
    private static Physics physicsEngine;
    private static timeToLive ttlLogic;
    private static Collision collisionCheck;
    private static Progression gameProgress;
    private static AI gameAI;
    private Projectile p2;
    private Projectile p;

    private static ActionListener buttonPress = new Logic();
    private static KeyListener keyboard = new Logic();    
    
    private static MenuGUI gui;
    private static ScheduledExecutorService timer;
    
    
    @Before
    public void setUp(){
        //gui = new MenuGUI(buttonPress,keyboard);
        gameState = new GameState();
        physicEngine = new Physics(gameState);
        graphicsEngine = new GraphicsEngine(gameState);
        collision = new Collision(gameState,physicsEngine);
        ttlLogic = new timeToLive(gameState);
        gameProgress = new Progression(gameState, false);
        gameAI = new AI(gameState);
        
        GameAssets.loadSounds();

        timer = Executors.newScheduledThreadPool(4);
        //timer.scheduleAtFixedRate(graphicsEngine, 0, 17, TimeUnit.MILLISECONDS);
        //timer.scheduleAtFixedRate(physicsEngine, 0, 17, TimeUnit.MILLISECONDS);
        //timer.scheduleAtFixedRate(collision, 0, 17, TimeUnit.MILLISECONDS);
        //timer.scheduleAtFixedRate(gui, 0, 17, TimeUnit.MILLISECONDS);
        
    }
    
    @After
    public void tearDown(){
        gameState = null;
        mapObject = null;
        collision = null;
        timer.shutdown();
    }
    
    @Test
    public void testCollision(){
        mapObject = mock(MapObject.class);
        ttlObj = new MapObjectTTL(new float[]{1.5f, 1.5f}, 350f, new int[]{650, 450},4, gameState);
        playerShip = new PlayerShip(new float[]{1, 1}, 45, new int[]{360, 260}, gameState, 1, 0, 0);
        alienShip = new AlienShip(new float[]{0,0}, 0, new int[]{400,300}, gameState);
        roid = new Asteroid(new float[]{1.5f, 1.5f}, 350, new int[]{650, 450}, gameState, Asteroid.LARGE_ASTEROID_SIZE);
        bd = new BonusDrop(new int[]{400,400}, gameState, 0);
        p = new Projectile(playerShip, 350f, new int[]{600, 500},gameState);
        p2 = new Projectile(alienShip, 350f, new int[]{610, 510},gameState);                
        gameState.addAlienShip(alienShip);
        gameState.addPlayerShip(playerShip);
        gameState.addExplosion(ttlObj);
        gameState.addAsteroid(roid);
        gameState.addBonusDrop(bd);
        gameState.addProjectile(p);
        gameState.addProjectile(p2);

        // X boundary : 16 + 40
        // Y boundary : 20 + 14 (ship + aship)
        if((Math.abs(gameState.getPlayerShip().getX() - gameState.getAlienShip().getX()) <= 16 + 40 || (Math.abs(gameState.getPlayerShip().getY() - gameState.getAlienShip().getY()) <= 20 + 14))){
            collisionLogic(playerShip,alienShip);
        }
        assertNull("player ship destroyed", gameState.getPlayerShip());
        
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
