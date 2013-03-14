
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
        ArrayList<Asteroid> asteroidList = memory.getAsteroids();
        for (Asteroid asteroid : asteroidList)
        {
            try{
                g2d.draw(asteroid.getShape());
            }
            catch (NullPointerException e){}
        }
        
        //draw player
        try{
            g2d.draw(memory.getPlayerShip().getShape());
        }
        catch (NullPointerException e){}
        
        
        //draw aliens
        try{
            g2d.draw(memory.getAlienShip().getShape());
        }
        catch (NullPointerException e){}
        
        //draw projectiles
        ArrayList<Projectile> projectileList = memory.getProjectiles();
        for (Projectile projectile : projectileList)
        {
            try{
                g2d.draw(projectile.getShape());
            }
            catch (NullPointerException e){}
        }
        
        //draw bonus drops
        ArrayList<BonusDrop> bonusList = memory.getBonusDrops();
        for (BonusDrop drop : bonusList)
        {
            try{
                g2d.draw(drop.getShape());
            }
            catch (NullPointerException e){}
        }
        
        //draw explosions
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
