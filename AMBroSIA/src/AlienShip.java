/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos, Anthony
 */

import java.awt.Polygon;

public class AlienShip extends Ship{
    
    public AlienShip(int velocity, int heading, int[] coordinates, GameState gameState, int fireRate, int lives){
        super(velocity, heading, coordinates, gameState, fireRate, lives);
    }
    
    public void destroy(){
        getGameState().removeAlienShip();
        getGameState().addToHighScore(GameState.ALIEN_SCORE);
    }
}
