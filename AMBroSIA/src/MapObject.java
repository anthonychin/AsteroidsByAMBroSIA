/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
public class MapObject {
    
    public MapObject(Vector velocity, int heading, float[] coordinates, ImageIcon img, GameState gs);
    
    public Vector getVelocity();
    
    public int getHeading();
    
    public void setHeading(int heading);
    
    ImageIcon getImage();;
    
    public float[] getCoord();
    
    public void setCoord(float[] coordinates);
    
    public void update();
    
    public void destroy();
    
}
