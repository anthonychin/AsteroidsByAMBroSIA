/**
 *
 * @author Nikolaos Bukas
 */

import java.util.ArrayList;
import java.lang.Math;
import java.awt.Polygon;
import java.awt.geom.Point2D;

public class Physics {
    public static void update(MapObject gameObject, int time)
    {
        int[] velocity = gameObject.getVelocity();
        int[] acceleration = calculate2DAcceleration(gameObject.getHeading(), gameObject.getAcceleration());
        
        int[] displacement = calculateDisplacement(velocity, acceleration, 1);
        gameObject.setX(gameObject.getX() + displacement[0]);
        gameObject.setY(gameObject.getY() + displacement[1]);
        
        gameObject.setVelocity(calculateNewVelocity(gameObject, velocity, acceleration, 1));
    }
    
    public static ArrayList<MapObject> getCollisions(PlayerShip playerShip, AlienShip alienShip, ArrayList<Asteroid> asteroids, ArrayList<Projectile> projectiles, ArrayList<BonusDrop> bonusDrops)
    {
        ArrayList<MapObject> listOfCollisions = new ArrayList<>(0);
        Polygon shipShape = Graphics.getPlayerShape();
        
        //Checking for collisions between PlayerShip and Asteroids
        for(int i = 0; i < asteroids.size(); i++)
        {
            Polygon asteroidShape;
            if(asteroids.get(i).getSize() == Asteroid.LARGE_ASTEROID_SIZE)
            {
                asteroidShape = Graphics.getLargeAsteroidShape();
            }
            else if(asteroids.get(i).getSize() == Asteroid.MEDIUM_ASTEROID_SIZE)
            {
                asteroidShape = Graphics.getMediumAsteroidShape();
            }
            else if(asteroids.get(i).getSize() == Asteroid.SMALL_ASTEROID_SIZE)
            {
                asteroidShape = Graphics.getSmallAsteroidShape();
            }
            
            if(detectCollision(shipShape, asteroidShape))
            {
                listOfCollisions.add(playerShip);
                listOfCollisions.add(asteroids.get(i));
            }
        }
        
        //Checking collisions between PlayerShip and Projectiles
        for(int i = 0; i < projectiles.size(); i++)
        {
            if(projectiles.get(i).getOwner() != Projectile.PLAYER_OWNER && detectCollision(shipShape, Graphics.getProjectileShape()))
            {
                listOfCollisions.add(playerShip);
                listOfCollisions.add(projectiles.get(i));
            }
        }
        
        //Checking collisions between PlayerShip and BonusDrops
        for(int i = 0; i < bonusDrops.size(); i++)
        {
            if(projectiles.get(i).getOwner() != Projectile.PLAYER_OWNER && detectCollision(shipShape, Graphics.getProjectileShape()))
            {
                listOfCollisions.add(playerShip);
                listOfCollisions.add(projectiles.get(i));
            }
        }
        
        if(alienShip != null)
        {
            shipShape = Graphics.getAlienShape();
            
            //Checking collisions between AlienShip and Asteroids
            for(int i = 0; i < asteroids.size(); i++)
            {
                Polygon asteroidShape;
                if(asteroids.get(i).getSize() == Asteroid.LARGE_ASTEROID_SIZE)
                {
                    asteroidShape = Graphics.getLargeAsteroidShape();
                }
                else if(asteroids.get(i).getSize() == Asteroid.MEDIUM_ASTEROID_SIZE)
                {
                    asteroidShape = Graphics.getMediumAsteroidShape();
                }
                else if(asteroids.get(i).getSize() == Asteroid.SMALL_ASTEROID_SIZE)
                {
                    asteroidShape = Graphics.getSmallAsteroidShape();
                }

                if(detectCollision(shipShape, asteroidShape))
                {
                    listOfCollisions.add(alienShip);
                    listOfCollisions.add(asteroids.get(i));
                }
            }
            
            //Checkin collisions between AlienShip and Projectiles
            for(int i = 0; i < projectiles.size(); i++)
            {
                if(projectiles.get(i).getOwner() != Projectile.ALIEN_OWNER && detectCollision(shipShape, Graphics.getProjectileShape()))
                {
                    listOfCollisions.add(alienShip);
                    listOfCollisions.add(projectiles.get(i));
                }
            }
        }
    }
    
    private static boolean detectCollision(Polygon shapeOne, Polygon shapeTwo)
    {
        for(int i = 0; i < shapeTwo.npoints; i++)
        {
            if (shapeOne.contains(shapeTwo.xpoints[i], shapeTwo.ypoints[i]))
                return true;    
        }
        for(int i = 0; i < shapeOne.npoints; i++)
        {
            if (shapeTwo.contains(shapeOne.xpoints[i], shapeOne.ypoints[i]))
                return true; 
        }
        
        return false;
    }
    
    private static int[] calculate2DAcceleration(int heading, int acceleration)
    {
        int[] acceleration2D = {0, 0};
        
        acceleration2D[0] = (int) (acceleration * Math.cos(heading));
        acceleration2D[1] = (int) (acceleration * Math.sin(heading));
       
        return acceleration2D;
    }
    
    private static int[] calculateNewVelocity(MapObject gameObject, int[] velocity, int[] acceleration, int time)
    {
        velocity[0] = velocity[0] + acceleration[0] * time;
        
        if(gameObject instanceof PlayerShip && velocity[0] > PlayerShip.MAX_VELOCITY)
        {
            velocity[0] = PlayerShip.MAX_VELOCITY;
        }
        
        velocity[1] = velocity[1] + acceleration[1] * time;
        
        if(gameObject instanceof PlayerShip && velocity[1] > PlayerShip.MAX_VELOCITY)
        {
            velocity[1] = PlayerShip.MAX_VELOCITY;
        }
        
        return velocity;
    }
    
    private static int[] calculateDisplacement(int[] velocity, int[] acceleration, int time)
    {
        int[] displacement = {0, 0};
        
        displacement[0] = (int) (velocity[0] * time + 0.5 * acceleration[0] * Math.pow(time, 2));
        displacement[1] = (int) (velocity[1] * time + 0.5 * acceleration[1] * Math.pow(time, 2));
        
        return displacement;
    }
}