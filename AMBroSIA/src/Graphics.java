
import java.awt.Polygon;

/*
 * Graphics class, responsible for drawing objects on the screen.
 */

/**
 *
 * @author Michael Smith
 */
public class Graphics {
    
 private GameState memory;
    
 public Graphics(GameState gamestate)
 {
    memory = gamestate;
 }
        
 
 public void updateGraphics()
 {
     
 }
 
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
 
 private Polygon LargeAsteroidShape()
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
}
