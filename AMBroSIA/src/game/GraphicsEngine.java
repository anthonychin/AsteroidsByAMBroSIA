package game;

import mapObjects.AlienShip;
import mapObjects.MapObject;
import mapObjects.Projectile;
import mapObjects.PlayerShip;
import mapObjects.MapObjectTTL;
import mapObjects.Asteroid;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import org.apache.log4j.Logger;

/**
 * The
 * <code>GraphicsEngine</code> class is responsible for defining the shape of
 * all in-game objects, and placing said shapes at the appropriate locations
 * based on the position of the object. It uses polygons for all shapes.
 *
 * @author Michael Smith
 */
public class GraphicsEngine implements Runnable {

    private GameState gameState;
    private final static Logger log = Logger.getLogger(GraphicsEngine.class.getName());

    /**
     * Creates GraphicsEngine using given game state.
     *
     * @param gamestate current game state
     */
    public GraphicsEngine(GameState gamestate) {
        this.gameState = gamestate;
        log.setLevel(Logic.LOG_LEVEL);
    }

    /**
     * Updates the shape of all objects in preparation for physics calculations
     * and graphics drawing.
     */
    public void updateGraphics() {
        log.info("Graphics update start");
        if (gameState.getPlayerShip() != null) {
            updatePlayerShip();
        }
        if (!gameState.getAsteroids().isEmpty()) {
            updateAsteroids();
        }
        if (gameState.getAlienShip() != null) {
            updateAlien();
        }
        if (!gameState.getProjectiles().isEmpty()) {
            updateProjectiles();
        }
        if (!gameState.getBonusDrops().isEmpty()) {
            updateBonusDrops();
        }
        if (!gameState.getExplosions().isEmpty()) {
            updateExplosions();
        }
    }

    /*
     * Shape definitions below using polygons.  Standard axis convention (does not follow GUI convention; converted later)
     */
    private static Polygon playerShape() {
        return new Polygon(new int[]{0, 8, -8}, new int[]{10, -10, -10}, 3);
    }

    private static Polygon playerShapeThruster() {
        return new Polygon(new int[]{0, 8, 6, 0, -6, -8}, new int[]{10, -10, -15, -10, -15, -10}, 6);
    }

    private static Polygon smallAsteroidShape() {
        return new Polygon(new int[]{-15, -7, 4, 19, 7, 2, -8}, new int[]{4, 12, 20, 3, -9, -11, -5}, 7);
    }

    private static Polygon mediumAsteroidShape() {
        return new Polygon(new int[]{-30, -15, -10, -5, 10, 21, 32, 25, 10, -20}, new int[]{2, 20, 25, 31, 29, 13, 4, -20, -25, -24}, 10);
    }

    private static Polygon largeAsteroidShape() {
        return new Polygon(new int[]{-50, -41, -33, -7, 19, 32, 46, 49, 64, 62, 47, 43, 36, -15, -14, -47, -50}, new int[]{53, 62, 65, 60, 63, 66, 57, 43, 39, 12, -8, -29, -45, -28, -15, 15, 42}, 17);
    }

    private static Polygon alienShape() {
        return new Polygon(new int[]{-20, -15, -10, -5, 5, 10, 15, 20, 10, -10}, new int[]{0, 3, 9, 11, 11, 9, 3, 0, -3, -3}, 10);
    }

    private static Polygon projectileShape() {
        return new Polygon(new int[]{-3, 0, 3, 0}, new int[]{0, 3, 0, -3}, 4);
    }

    private static Polygon explosionShape() {
        return new Polygon(new int[]{-5, 5}, new int[]{0, 0}, 2);
    }

    private static Polygon bonusDropShape() {
        //TODO: Give bonus drop shape
        return new Polygon(new int[]{-8, 8, 8, -8}, new int[]{8, 8, -8, -8}, 4);
    }

    private void updatePlayerShip() {
        log.debug("Updating PS");
        PlayerShip playerShip = gameState.getPlayerShip();
        //move to appropriate position, rotate, set shape
        if (playerShip != null) {
            //show thruster if accelerating
            if (playerShip.getAccelerate()) {
                setPosition(playerShapeThruster(), playerShip);
            } else {
                setPosition(playerShape(), playerShip);
            }
        }
    }

    private void updateAlien() {
        log.debug("updating alien");
        AlienShip alienShip = gameState.getAlienShip();
        //translate and rotate, set shape
        if (alienShip != null) {
            setPosition(alienShape(), alienShip);
        }
    }

    private void updateProjectiles() {
        log.debug("updating projectiles");
        for (Projectile projectile : gameState.getProjectiles()) {
            setPosition(projectileShape(), projectile);
        }
    }

    private void updateExplosions() {
        log.debug("updating explosions");
        //for all explosions
        for (MapObjectTTL explosion : gameState.getExplosions()) {
            setPosition(explosionShape(), explosion);
        }
    }

    private void updateBonusDrops() {
        log.debug("updating bonus drops");
        //for all drops
        for (MapObject bonusDrop : gameState.getBonusDrops()) {
            setPosition(bonusDropShape(), bonusDrop);
        }
    }

    private void updateAsteroids() {
        log.debug("updating asteroids");
        //do for every asteroid in the game
        for (Asteroid asteroid : gameState.getAsteroids()) {
            //take care of different shapes for each size
            Polygon asteroidShape = smallAsteroidShape();
            if (asteroid.getSize() == Asteroid.LARGE_ASTEROID_SIZE) {
                asteroidShape = largeAsteroidShape();
            } else if (asteroid.getSize() == Asteroid.MEDIUM_ASTEROID_SIZE) {
                asteroidShape = mediumAsteroidShape();
            }
            setPosition(asteroidShape, asteroid);
        }
    }

    //performs rotation and translation on the shape of objects based on location and heading, and attaches the shape to the object in question once complete.
    private void setPosition(Polygon shape, MapObject gameobject) {
        AffineTransform transAndRot = new AffineTransform();

        //translate from std. math coordinate system used in shape definitons to proper location on map
        transAndRot.setToTranslation(gameobject.getX(), gameobject.getY());
        //rotate polygon by appropriate amount depending on polygon
        transAndRot.rotate(Math.toRadians(gameobject.getHeading()));

        //flip y axis (b/c of std. math convention used in shape definition)
        for (int i = 0; i < shape.ypoints.length; i++) {
            shape.ypoints[i] = -shape.ypoints[i];
        }

        //do transformation, point by point
        for (int j = 0; j < shape.xpoints.length; j++) {
            Point2D.Float origPoint = new Point2D.Float(shape.xpoints[j], shape.ypoints[j]);
            Point2D.Float transPoint = new Point2D.Float();
            transAndRot.transform(origPoint, transPoint);
            shape.xpoints[j] = Math.round(transPoint.x);
            shape.ypoints[j] = Math.round(transPoint.y);
        }
        gameobject.setShape(shape);
    }

    /**
     * Updates the shape of all in-game objects by placing them at the new
     * position of the object.
     */
    @Override
    public void run() {
        updateGraphics();
    }
}
