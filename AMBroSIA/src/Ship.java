/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */     
public class Ship {
    int lives;
    int fireRate;
    
    Ship(int fireRate, int lives)
    {
    }
    
    public int getFireRate()
    {
        return this.fireRate;
    }
    
    public void setFireRate(int fireRate)
    {
        
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
       Projectile p = new Projectile(int velocity, int heading, int[] coordinates, ImageIcon img, GameState gs, int ttl);        
    }
}
