
package gui;

import game.Asteroid;
import game.BonusDrop;
import game.GameState;
import game.MapObject;
import game.Projectile;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class gameDraw {
    
    public static void drawObjects(Graphics2D g2d, GameState memory)
    {
        //draw asteroids
        if (!memory.getAsteroids().isEmpty())
        {
            ArrayList<Asteroid> asteroidList = memory.getAsteroids();
            for (Asteroid asteroid : asteroidList)
            {
                g2d.draw(asteroid.getShape());
            } 
        }
        
        
        //draw player
        if (memory.getPlayerShip() != null)
        {
            g2d.draw(memory.getPlayerShip().getShape());
        }
        
        
        //draw aliens
        if (memory.getAlienShip() != null)
        {
            g2d.draw(memory.getAlienShip().getShape());
        }
        
        //draw projectiles
        if (!memory.getProjectiles().isEmpty())
        {
            ArrayList<Projectile> projectileList = memory.getProjectiles();
            for (Projectile projectile : projectileList)
            {
                try{
                    g2d.draw(projectile.getShape());
                }
                catch (NullPointerException e){}
            }
        }
        
        //draw bonus drops
        if (!memory.getBonusDrops().isEmpty())
        {
            ArrayList<BonusDrop> bonusList = memory.getBonusDrops();
            for (BonusDrop drop : bonusList)
            {
                try{
                    g2d.draw(drop.getShape());
                }
                catch (NullPointerException e){}
            }
        }
        
        //draw explosions
        if (!memory.getExplosions().isEmpty())
        {
            ArrayList<MapObject> explosionList = memory.getExplosions();
            for (MapObject explosion : explosionList)
            {
                try{
                    g2d.draw(explosion.getShape());
                }
                catch (NullPointerException e){}
            }
        }
    }
}
