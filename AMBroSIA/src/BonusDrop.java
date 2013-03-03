/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
import java.awt.Polygon;

public class BonusDrop extends MapObject {
    private int type;
    private int ttl;
    
    BonusDrop(int[] coordinates, Polygon shape, GameState gameState, int type, int ttl)
    {
        super(0, 0, coordinates, gameState);
        this.type = type;
        this.ttl = ttl;
    }
    
    public int getType()
    {
        return this.type;
    }
    
    public int getTTL()
    {
        return this.ttl;
    }
    
    public void destroy()
    {
        getGameState().removeBonusDrop(this);
    }
}
