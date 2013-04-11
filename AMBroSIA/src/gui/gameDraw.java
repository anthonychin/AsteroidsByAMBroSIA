package gui;

import game.AlienShip;
import game.Asteroid;
import game.BonusDrop;
import game.MapObjectTTL;
import game.GameState;
import game.PlayerShip;
import game.Projectile;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

/**
 * The
 * <code>gameDraw</code> class is responsible for drawing all in game objects,
 * from asteroids to the player ship to bonus drops. Uses the Polygon class that
 * comes with Java.
 *
 * @author Michael
 */
public class gameDraw {

    /**
     * Draws all in-game objects.
     *
     * @param graphics2d 2 dimensional shape
     * @param gameState current game state
     */
    public static void drawObjects(Graphics2D graphics2d, GameState gameState) {
        //some nice settings that improve visual quality.  Some do not appear to have an effect however...
        graphics2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        graphics2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        //store default stroke so we can reset
        Stroke defaultStroke = graphics2d.getStroke();

        //draw asteroids
        ArrayList<Asteroid> asteroidList = gameState.getAsteroids();
        if (!asteroidList.isEmpty()) {
            graphics2d.setColor(Color.black);
            for (Asteroid asteroid : asteroidList) {
                Polygon shape = asteroid.getShape();

                //give asteroid gray outline
                graphics2d.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
                graphics2d.setColor(Color.GRAY);
                graphics2d.drawPolygon(shape);

                //fill asteroid black
                graphics2d.setColor(Color.black);
                graphics2d.fillPolygon(shape);
            }
        }
        //reset to default width for other objects for now
        graphics2d.setStroke(defaultStroke);

        //draw player
        PlayerShip player = gameState.getPlayerShip();
        if (player != null) {
            Color fill;
            Color shield;
            //adjust color depending on whose turn it is
            if (gameState.isPlayerTwoTurn()) {
                fill = Color.blue;
                shield = new Color(255, 0, 51); //light red
            } else {
                fill = Color.red;
                shield = new Color(89, 165, 253); //light blue
            }

            Polygon shape = player.getShape();
            graphics2d.setColor(fill);
            graphics2d.fillPolygon(shape);

            //shield, if activated
            if (player.getShieldStatus()) {
                graphics2d.setColor(shield);
                graphics2d.setStroke(new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            }
            graphics2d.drawPolygon(shape);
        }

        //reset color, stroke
        graphics2d.setColor(Color.BLACK);
        graphics2d.setStroke(defaultStroke);

        //draw alien
        AlienShip alien = gameState.getAlienShip();
        if (alien != null) {
            graphics2d.setColor(Color.MAGENTA);
            graphics2d.drawPolygon(alien.getShape());
        }

        //reset color
        graphics2d.setColor(Color.BLACK);

        //draw projectiles
        ArrayList<Projectile> projectileList = gameState.getProjectiles();
        if (!projectileList.isEmpty()) {
            for (Projectile projectile : projectileList) {
                graphics2d.setColor(projectile.getColor());
                graphics2d.fillPolygon(projectile.getShape());
            }
        }

        //reset color
        graphics2d.setColor(Color.BLACK);

        //draw bonus drops
        ArrayList<BonusDrop> bonusList = gameState.getBonusDrops();
        if (!bonusList.isEmpty()) {
            for (BonusDrop drop : bonusList) {
                Polygon bonusShape = drop.getShape();
                graphics2d.setColor(drop.getColor());
                graphics2d.fillPolygon(bonusShape);
                //create outline
                graphics2d.setColor(Color.GRAY);
                graphics2d.setStroke(new BasicStroke(1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
                graphics2d.drawPolygon(bonusShape);
            }
        }
        //reset color, stroke
        graphics2d.setStroke(defaultStroke);
        graphics2d.setColor(Color.BLACK);

        //draw explosions
        ArrayList<MapObjectTTL> explosionList = gameState.getExplosions();
        if (!explosionList.isEmpty()) {
            for (MapObjectTTL explosion : explosionList) {
                graphics2d.setColor(explosion.getColor());
                graphics2d.drawPolygon(explosion.getShape());
            }
        }

        //See http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#dispose%28%29
        graphics2d.dispose();
    }
}
