
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * The Graphics class is responsible for setting the shape of all in game objects, and placing said shapes at the appropriate locations based on the position of the object.
 * It uses polygons for all shapes.
 * 
 * @author Michael Smith
 */
public class Graphics {
    
 private GameState memory;
 private final int WIDTH = 800;
 private final int HEIGHT = 600;
    
 public Graphics(GameState gamestate)
 {
    memory = gamestate;
 }
        
 
 public void updateGraphics()
 {
     //update player ship
     updatePlayerShip();
     updateAsteroids();
 }
 
 /*
  * Shape definitions below using polygons.  Standard axis convention (does not follow GUI convention; converted later)
  */
 private Polygon playerShape()
 {
     return new Polygon(new int[] {0,8,-8}, new int[] {10,-10,-10}, 3);
 }
 
 private Polygon smallAsteroidShape()
 {
     return new Polygon(new int[] {15,-14,0,-3}, new int[] {-10,9,20,-20}, 4);
 }
 
 private Polygon mediumAsteroidShape()
 {
     return new Polygon(new int[] {-24,-21,-7,2,16,21,11,3,-13}, new int[] {7,20,7,14,4,-2,-17,-14,2}, 9);
 }
 
 private Polygon largeAsteroidShape()
 {
     return new Polygon(new int[] {-40,-37,-35,-19,-11,-7,2,8,18,24,21,25,19,13,6,-1,-9,-20},
             new int[] {3,9,14,20,27,34,30,33,22,9,-2,-7,-18,-27,-33,-24,-16,2},18);
 }
 
 private Polygon AlienShape()
 {
     //TODO: give shape to alien
     return new Polygon(new int[] {-5,-5,5,5}, new int[] {5,-5,5,-5},4);
 }
 
 private Polygon projectileShape()
 {
     //TODO: Check shape
     return new Polygon( new int[] {-1,0,1,0}, new int[] {0,1,0,-1},4);
 }
 
 private Polygon explosionShape()
 {
     //TODO: Check explosion
     return new Polygon(new int[] {-2,2}, new int[] {0,0}, 2);
 }
 
 private void updatePlayerShip()
 {
     MapObject player = memory.getPlayerShip();
     //set to default shape
     Polygon playerShape = playerShape();
     //move to appropriate position, rotate
     setPosition(playerShape, player);
     
     player.setShape(playerShape);
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
         anAsteroid.setShape(asteroidShape);
     }
 }
 
 private void setPosition(Polygon shape, MapObject gameobject)
 {
     AffineTransform transAndRot = new AffineTransform();
     //+ve theta -> points on +ve x axis go towards +ve y axis
     transAndRot.setToRotation(gameobject.getHeading());
     
     //add translation
     transAndRot.translate(WIDTH/2 + gameobject.getCoord()[0], HEIGHT/2 + gameobject.getCoord()[1]);
    
     //flip y axis
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
 }
}
