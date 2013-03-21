
package gui;

import game.Asteroid;
import game.BonusDrop;
import game.MapObjectTTL;
import game.GameState;
import game.Projectile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

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

        Paint defaultPaint = g2d.getPaint();
        Stroke defaultStroke = g2d.getStroke();
        //draw asteroids
        if (!memory.getAsteroids().isEmpty()) 
        {
            CopyOnWriteArrayList<Asteroid> asteroidList = new CopyOnWriteArrayList(memory.getAsteroids());
            for (Asteroid asteroid : asteroidList)
            {
                //make asteroid border a little thicker
                Polygon shape = asteroid.getShape();
                g2d.setStroke(new BasicStroke(10,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
                g2d.setPaint(Color.GRAY);
                g2d.drawPolygon(shape);
                g2d.setPaint(defaultPaint);
                g2d.fillPolygon(shape);
//                g2d.drawPolygon(shape);
            } 
        }
        //reset to default width for other objects for now
        g2d.setStroke(defaultStroke);
        g2d.setPaint(defaultPaint);
        
        //draw player
        if (memory.getPlayerShip() != null)
        {
            g2d.draw(memory.getPlayerShip().getShape());
        }
        
        
        //draw alien
        if (memory.getAlienShip() != null)
        {
            g2d.draw(memory.getAlienShip().getShape());
        }
        
        //draw projectiles
        if (!memory.getProjectiles().isEmpty())
        {
            CopyOnWriteArrayList<Projectile> projectileList = new CopyOnWriteArrayList(memory.getProjectiles());
            for (Projectile projectile : projectileList)
            {
                g2d.draw(projectile.getShape());
            }
        }
        
        //draw bonus drops
        if (!memory.getBonusDrops().isEmpty())
        {
            CopyOnWriteArrayList<BonusDrop> bonusList = new CopyOnWriteArrayList(memory.getBonusDrops());
            for (BonusDrop drop : bonusList)
            {
                g2d.draw(drop.getShape());
            }
        }
        
        //draw explosions
        if (!memory.getExplosions().isEmpty())
        {
            CopyOnWriteArrayList<MapObjectTTL> explosionList  = new CopyOnWriteArrayList(memory.getExplosions());
            for (MapObjectTTL explosion : explosionList)
            {
                g2d.draw(explosion.getShape());
            }
        }
        
        //See http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#dispose%28%29
        g2d.dispose();
    }
}
