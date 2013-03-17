package game;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Anthony
 */
public class PlayerShip extends Ship{
    final public static int MAX_VELOCITY = 12;
    final public static float ACCELERATION = 0.09f;
    final public static int DEACCELERATION = -2;
    final public static int FIRE_RATE = 5;
    final public static int ANGULAR_SPEED = 10;
    final public static int NUM_DEBRIS = 20;
    
    private int bomb;
    private int shieldPoints;
    private boolean isAccelerating = false;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;
    private boolean isShieldOn = false;
    
    public PlayerShip(float[] velocity, float heading, int[] coordinates, GameState gameState, int lives, int bomb, int shieldPoints){
        super(velocity, heading, coordinates, ACCELERATION, gameState, FIRE_RATE, lives);
        this.bomb = bomb;
        this.shieldPoints = shieldPoints;
    }
    
    public int getBomb(){
       return this.bomb;
    }
    
    public int addBomb(){
        return this.bomb + 1;
    }
    
    public void useBomb(){
        if(bomb > 0)
        {
            bomb = bomb-1;
            // do something
        }
        else
        {
            // do nothing
        }
    }
    
    public int getShieldPoints(){
        return this.shieldPoints;
    }
    
    public void setShieldPoints(int shieldpoints){
        this.shieldPoints = shieldpoints;
    }
    
    public void activateShield(){
        if (this.shieldPoints > 0){
            isShieldOn = true;
        }
    }
    
    public boolean getShieldStatus(){
        return isShieldOn; 
    }
    public boolean isDead(){
        if(this.getLives() == 0){
            return true;
        }
        return false;
    }
    
    public void accelerate(){
        this.isAccelerating = true;
    }
    public void turnLeft()
    {
        this.isTurningLeft = true;
    }
    public boolean getTurnLeft()
    {
        return this.isTurningLeft;
    }
    public void turnRight()
    {
        this.isTurningRight = true;
    }
    public boolean getTurnRight()
    {
        return this.isTurningRight;
    }
    
    public boolean getAccelerate(){
        return this.isAccelerating;
    }
    
    public void destroy()
    {
        createExplosionEffect();
        getGameState().removePlayerShip();
        try {
            Sound sound = new Sound("explosion.wav");
            sound.play();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(PlayerShip.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void createExplosionEffect()
    {
        for (int i = 0 ; i < NUM_DEBRIS; i++)
        {
            getGameState().addExplosion(new MapObject(new float[] {Difficulty.randExplosionVelocity(), Difficulty.randExplosionVelocity()}, Difficulty.randomHeading(), new int[] {this.getX(), this.getY()}, 0, getGameState()));
        }
    }
}
