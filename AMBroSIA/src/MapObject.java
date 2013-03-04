/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
import java.awt.Polygon;
import javax.swing.ImageIcon;

public class MapObject {
    private int[] velocity;
    private int heading;
    private int[] coordinates;
    private GameState gameState;
    private Polygon shape;
    
    public MapObject(int[] velocity, int heading, int[] coordinates, GameState gameState)
    {
        this.velocity = velocity;
        this.heading = heading;
        this.coordinates = coordinates;
        this.gameState = gameState;
    }
    
    public int[] getVelocity()
    {
        return this.velocity;
    }
    
    public int getHeading()
    {
        return this.heading;
    }
    
    public void setHeading(int heading)
    {
        this.heading = heading;
    }
    
    public int[] getCoord()
    {
        return this.coordinates;
    }
    
    public void setCoord(int[] coordinates)
    {
        this.coordinates = coordinates;
    }
    
    public Polygon getShape()
    {
        return this.shape;
    }
    
    public void setShape(Polygon shape)
    {
        this.shape = shape;
    }
    
    public GameState getGameState()
    {
        return this.gameState;
    }
    
    public void update()
    {
        
    }
    
    public void destroy()
    {
        
    }
}
