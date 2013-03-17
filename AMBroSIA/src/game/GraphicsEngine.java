package game;


import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * The Graphics class is responsible for defining the shape of all in-game objects, and placing said shapes at the appropriate locations based on the position of the object.
 * It uses polygons for all shapes.
 * 
 * @author Michael Smith
 */
public class GraphicsEngine implements Runnable{
    
 private GameState memory;
 private final int WIDTH = gui.MenuGUI.WIDTH;
 private final int HEIGHT = gui.MenuGUI.HEIGHT;
    
 public GraphicsEngine(GameState gamestate)
 {
    memory = gamestate;
 }
        
 /**
  * Updates the shape of all objects in preparation for physics calculations and graphics drawing.
  */
 public void updateGraphics()
 {
     if (memory.getPlayerShip() != null)
     {
         updatePlayerShip();
     }
     if (!memory.getAsteroids().isEmpty())
     {
         updateAsteroids();
     }
     if (memory.getAlienShip() != null)
     {
         updateAlien();
     }
     if (!memory.getProjectiles().isEmpty())
     {
         updateProjectiles();
     }
     if (!memory.getBonusDrops().isEmpty())
     {
         updateBonusDrops();
     }
     if (!memory.getExplosions().isEmpty())
     {
         updateExplosions();
     }
     
 }
 
 /*
  * Shape definitions below using polygons.  Standard axis convention (does not follow GUI convention; converted later)
  */
 private static Polygon playerShape()
 {
     //TODO: see if player shape size OK
     return new Polygon(new int[] {0,8,-8}, new int[] {10,-10,-10}, 3);
 }
 
 //TODO: Check all asteroid shapes. OK? Strange? Too many points?
 private static Polygon smallAsteroidShape()
 {
     return new Polygon(new int[] {15,-14,0,-3}, new int[] {-10,9,20,-20}, 4);
 }
 
 private static Polygon mediumAsteroidShape()
 {
     return new Polygon(new int[] {-24,-21,-7,2,16,21,11,3,-13}, new int[] {7,20,7,14,4,-2,-17,-14,2}, 9);
 }
 
 private static Polygon largeAsteroidShape()
 {
     return new Polygon(new int[] {-40,-37,-35,-19,-11,-7,2,8,18,24,21,25,19,13,6,-1,-9,-20},
             new int[] {3,9,14,20,27,34,30,33,22,9,-2,-7,-18,-27,-33,-24,-16,2},18);
 }
 
 private static Polygon alienShape()
 {
     //TODO: give shape to alien
     return new Polygon(new int[] {-5,-5,5,5}, new int[] {5,-5,5,-5},4);
 }
 
 private static Polygon projectileShape()
 {
     //TODO: See if projectile shape OK
     return new Polygon( new int[] {-1,0,1,0}, new int[] {0,1,0,-1},4);
 }
 
 public static Polygon explosionShape()
 {
     //TODO: Explosion shape OK?
     return new Polygon(new int[] {-5,5}, new int[] {0,0}, 2);
 }
 
 private static Polygon bonusDropShape()
 {
     //TODO: Give bonus drop shape
     return new Polygon(new int[] {-3,-3,3,3}, new int[] {3,-3,3,-3},4);
 }
 
 private void updatePlayerShip()
 {
     MapObject player = memory.getPlayerShip();
     //move to appropriate position, rotate, set shape
     setPosition(playerShape(), player);
 }
 
 private void updateAlien()
 {
     AlienShip alien = memory.getAlienShip();
     //translate and rotate, set shape
     setPosition(alienShape(),alien);
 }
 
 private void updateProjectiles()
 {
     ArrayList<Projectile> projectileList = memory.getProjectiles();
     //for all projectiles
     for (Projectile aProjectile : projectileList)
     {
         setPosition(projectileShape(),aProjectile);
     }
 }
 
 private void updateExplosions()
 {
     ArrayList<MapObject> explosionList = memory.getExplosions();
     //for all explosions
     for (MapObject explosion : explosionList)
     {
         setPosition(explosionShape(),explosion);
     }
 }
 
 private void updateBonusDrops()
 {
     ArrayList<BonusDrop> bonusDropList = memory.getBonusDrops();
     //for all drops
     for (MapObject aBonusDrop : bonusDropList)
     {
         setPosition(bonusDropShape(),aBonusDrop);
     }
 }
 
 private void updateAsteroids()
 {
     ArrayList<Asteroid> asteroidList = memory.getAsteroids();
     //do for every asteroid in the game
     for (Asteroid anAsteroid : asteroidList)
     {
         //take care of different shapes for each size
         Polygon asteroidShape = smallAsteroidShape();
         if (anAsteroid.getSize() == Asteroid.LARGE_ASTEROID_SIZE)
         {
             asteroidShape = largeAsteroidShape();
         }
         else if (anAsteroid.getSize() == Asteroid.MEDIUM_ASTEROID_SIZE)
         {
             asteroidShape = mediumAsteroidShape();
         }
         setPosition(asteroidShape,anAsteroid);
     }
 }
 
 //performs rotation and translation on the shape of objects based on location and heading, and attaches the shape to the object in question once complete.
 private void setPosition(Polygon shape, MapObject gameobject)
 {
     AffineTransform transAndRot = new AffineTransform();
     
     //translate from std. math coordinate system used in shape definitons to proper location on map
     transAndRot.setToTranslation(gameobject.getX(), gameobject.getY());
     //rotate polygon by appropriate amount depending on polygon
     transAndRot.rotate(Math.toRadians(gameobject.getHeading()));
    
     //flip y axis (b/c of std. math convention used in shape definition)
     for (int i = 0 ; i < shape.ypoints.length ; i++)
     {
         shape.ypoints[i] = -shape.ypoints[i];
     }
     
     //do transformation, point by point
     for (int j = 0 ;j < shape.xpoints.length ; j++)
     {
         Point2D.Float origPoint = new Point2D.Float(shape.xpoints[j],shape.ypoints[j]);
         Point2D.Float transPoint = new Point2D.Float();
         transAndRot.transform(origPoint, transPoint);
         shape.xpoints[j] = (int) transPoint.x;
         shape.ypoints[j] = (int) transPoint.y;
     }
     gameobject.setShape(shape);
 }

    @Override
    public void run() {
        updateGraphics();
    }
}
