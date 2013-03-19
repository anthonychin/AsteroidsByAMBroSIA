package game;

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
    private float[] velocity;
    private int[] coordinates;
    private float heading;
    private float acceleration;
    private GameState gameState;
    private Polygon shape;
    
    public MapObject(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState)
    {
        this.velocity = velocity;
        this.heading = heading;
        this.coordinates = coordinates;
        this.acceleration = acceleration;
        this.gameState = gameState;
        this.shape = new Polygon(new int[] {0}, new int[] {0}, 1);
    }
    
    public synchronized float[] getVelocity()
    {
        return this.velocity;
    }
    
    public synchronized void setVelocity(float[] velocity)
    {
        this.velocity = velocity;
    }
    
    public synchronized float getAcceleration()
    {
        return this.acceleration;
    }
    
    public synchronized void setAcceleration(float acceleration)
    {
        this.acceleration = acceleration;
    }
    
    public synchronized float getHeading()
    {
        return this.heading;
    }
    
    public synchronized void setHeading(float heading)
    {
        this.heading = heading;
    }
    
    public synchronized int[] getCoord()
    {
        return this.coordinates;
    }
    
    public synchronized void setCoord(int[] coordinates)
    {
        this.coordinates = coordinates;
    }
    
    public synchronized int getX()
    {
        return coordinates[0];
    }
    
    public synchronized void setX(int x)
    {
        this.coordinates[0] = x;
    }
    
    public synchronized int getY()
    {
        return coordinates[1];
    }
    
    public synchronized void setY(int y)
    {
        this.coordinates[1] = y;
    }
    
    public synchronized Polygon getShape()
    {
        return this.shape;
    }
    
    public synchronized void setShape(Polygon shape)
    {
        this.shape = shape;
    }
    
    public synchronized GameState getGameState()
    {
        return this.gameState;
    }
    
    //unimplemented.  All implementing classes should override.
    public synchronized void destroy()
    {
        
    }
}
