
import java.awt.Polygon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony
 */
public class PlayerShip extends Ship{
    private int bomb;
    private int shieldPoints;
    private int acceleration;
    
    public PlayerShip(int velocity, int heading, int[] coordinates, GameState gameState,int fireRate, int lives, int bomb, int shieldPoints, int acceleration){
        super(velocity, heading, coordinates, gameState, fireRate, lives);
        this.bomb = bomb;
        this.shieldPoints = shieldPoints;
    }
    
    public int getBomb(){
       return this.bomb;
    }
    
    public void setBomb(int bomb){
        this.bomb = bomb;
    }
    
    public int getShieldPoints(){
        return this.shieldPoints;
    }
    
    public void setShieldPoints(int shieldpoints){
        this.shieldPoints = shieldpoints;
    }
    
    public boolean isDead(){
        if(this.lives == 0){
            return true;
        }
        return false;
    }
    public int getAcceleration(){
        return this.acceleration;
    }
    public void setAcceleration(int acceleration){
        this.acceleration = acceleration;
    }
    
    public accelerate(){
    
    }
}
