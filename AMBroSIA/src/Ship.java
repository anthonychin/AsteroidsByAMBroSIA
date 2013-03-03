/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
public class Ship {
    private int lives;
    private int fireRate;
    
    Ship(int fireRate, int lives)
    {
        this.lives = lives;
        this.fireRate = fireRate;
    }
    
    public int getFireRate()
    {
        return this.fireRate;
    }
    
    public void setFireRate(int fireRate)
    {
        this.fireRate = fireRate;
    }
    
    public int getLives()
    {
        return this.lives;
    }
    
    public void setLives(int lives)
    {
        this.lives = lives;
    }
    
    public void shoot()
    {
        
    }
}
