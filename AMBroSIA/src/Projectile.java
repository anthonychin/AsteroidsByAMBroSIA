/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony
 */


public class Projectile extends MapObject {
    public final static int PROJECTILE_VELOCITY = 10;
    public final static int TIME_TO_LIVE = 5; //5 seconds
    
    private int velocity; // to be fixed
    private int ttl;
    
    
    public Projectile(int[] velocity, int heading, int[] coordinates, GameState gameState, int ttl){     
        super(velocity, heading, coordinates, gameState);
        this.ttl = ttl;      
    }
    
    public int getTTL(){
        return this.ttl;
    }
    public void setTTL(int ttl){
        this.ttl = ttl;
    }
    
}
    
}
