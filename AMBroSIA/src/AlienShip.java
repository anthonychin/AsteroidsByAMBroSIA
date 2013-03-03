/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */


public class AlienShip extends Ship{
    private int heading;
    private Vector velocity;
    private float[] coordinates = new float[2];
    
    
    public AlienShip(int fireRate, int lives, int heading, Vector velocity, float[] coordinates){
        super(fireRate, lives);
        this.fireRate = 4;
        this.lives = 3;
    };
    
    public int getfireRate(){
        return this.fireRate;
    }
    
    public void setfireRate(int fireRate){
        this.fireRate = fireRate;
    }
    
    public int getLives(){
        return this.lives;
    }
    
    public void setLives(int lives){
        this.lives = lives;
    }
    
    public void shoot(){
       Projectile p = new Projectile(Vector velocity, int heading, float[] coordinates, ImageIcon img, GameState gs, int ttl);
    }
}
