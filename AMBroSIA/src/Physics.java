/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
import java.util.ArrayList;
import java.lang.Math;

public class Physics {
    public void update(MapObject mapObject)
    {
        
    }
    
    public void update(Ship ship)
    {
        
    }
    
    public void checkCollision(PlayerShip playerShip, AlienShip alienShip, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, ArrayList<BonusDrop> bonusDrops)
    {
        
    }
    
    private static int[] calculateNewVelocity(int[] initialVelocity, int[] acceleration, int time)
    {
        int[] velocity = {0, 0};
        
        velocity[0] = initialVelocity[0] + acceleration[0] * time;
        if(velocity[0] > PlayerShip.MAX_VELOCITY)
        {
            velocity[0] = PlayerShip.MAX_VELOCITY;
        }
        
        velocity[1] = initialVelocity[1] + acceleration[1] * time;
        if(velocity[1] > PlayerShip.MAX_VELOCITY)
        {
            velocity[1] = PlayerShip.MAX_VELOCITY;
        }
        
        return velocity;
    }
    
    private static int[] calculateDisplacement(int[] initialVelocity, int[] acceleration, int time)
    {
        int[] displacement = {0, 0};
        
        displacement[0] = (int) (initialVelocity[0] * time + 0.5 * acceleration[0] * Math.pow(time, 2));
        displacement[1] = (int) (initialVelocity[1] * time + 0.5 * acceleration[1] * Math.pow(time, 2));
        
        return displacement;
    }
}