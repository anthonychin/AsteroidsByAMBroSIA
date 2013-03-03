/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
import javax.swing.ImageIcon;

public class MapObject {
    private int velocity;
    private int heading;
    private int[] coordinates;
    private ImageIcon image;
    
    public MapObject(int heading, int[] coordinates, ImageIcon img)
    {
        this.heading = heading;
        this.coordinates = coordinates;
        this.image = img;
    }
    
    public int getVelocity()
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
    
    ImageIcon getImage()
    {
        return this.image;
    }
    
    public int[] getCoord()
    {
        return this.coordinates;
    }
    
    public void setCoord(int[] coordinates)
    {
        this.coordinates = coordinates;
    }
    
    public void update()
    {
        
    }
    
}
