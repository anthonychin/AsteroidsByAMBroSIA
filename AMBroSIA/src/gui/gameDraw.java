
package gui;

import game.Asteroid;
import game.BonusDrop;
import game.GameState;
import game.MapObject;
import game.Projectile;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class gameDraw 
{
    
    public static void drawObjects(Graphics2D g2d, GameState memory)
    {
        //some nice settings that improve visual quality.  Some do not appear to have an effect however...
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

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
                g2d.draw(projectile.getShape());
            }
        }
        
        //draw bonus drops
        if (!memory.getBonusDrops().isEmpty())
        {
            ArrayList<BonusDrop> bonusList = memory.getBonusDrops();
            for (BonusDrop drop : bonusList)
            {
                g2d.draw(drop.getShape());
            }
        }
        
        //draw explosions
        if (!memory.getExplosions().isEmpty())
        {
            ArrayList<MapObject> explosionList = memory.getExplosions();
            for (MapObject explosion : explosionList)
            {
                g2d.draw(explosion.getShape());
            }
        }
    }
}
