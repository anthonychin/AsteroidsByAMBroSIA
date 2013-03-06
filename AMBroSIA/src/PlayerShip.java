/**
 *
 * @author Anthony
 */
public class PlayerShip extends Ship{
    final public static int MAX_VELOCITY = 50;
    final public static int ACCELERATION = 3;
    final public static int DEACCELERATION = -2;
    final public static int FIRE_RATE = 5;
    final public static int ANGULAR_SPEED = 10;
    
    private int bomb;
    private int shieldPoints;
    
    public PlayerShip(int[] velocity, int heading, int[] coordinates, int acceleration, GameState gameState,int fireRate, int lives, int bomb, int shieldPoints){
        super(velocity, heading, coordinates, 0, gameState, fireRate, lives);
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
    
    public void useShield(){
        //do something
    }
    
    public boolean isDead(){
        if(this.getLives() == 0){
            return true;
        }
        return false;
    }
    
    public void accelerate(){
        
    }
}
