/**
 *
 * @author Nikolaos
 */
import java.awt.Polygon;

public class Asteroid extends MapObject{
    private int size;
    
    Asteroid(int velocity, int heading, int[] coordinates, Polygon shape, GameState gameState, int size)
    {
        super(velocity, heading, coordinates, shape, gameState);
        this.size = size;
    }
    
    public int getSize()
    {
        return this.size;
    }
}