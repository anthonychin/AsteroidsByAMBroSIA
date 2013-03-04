/**
 *
 * @author Nikolaos, Anthony
 */

import java.awt.Polygon;

public class AlienShip extends Ship{
    final public static int FIRE_RATE = 8;
    
    public AlienShip(int[] velocity, int heading, int[] coordinates, GameState gameState, int fireRate, int lives){
        super(velocity, heading, coordinates, gameState, fireRate, lives);
    }
    
    public void destroy(boolean bombUsed){
        getGameState().removeAlienShip();
        
        if(!bombUsed) { getGameState().addToHighScore(GameState.ALIEN_SCORE); }
    }
}
