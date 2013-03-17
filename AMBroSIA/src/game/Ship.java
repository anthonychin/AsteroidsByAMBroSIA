package game;

/**
 *
 * @author Anthony
 */

public class Ship extends MapObject{
    private int lives;
    private int fireRate;
    // Contructor
    public Ship(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState, int fireRate, int lives){
        super(velocity, heading, coordinates, acceleration, gameState);
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
        //this.getGameState().addProjectile(new Projectile(this, Projectile.PROJECTILE_VELOCITY, this.getHeading(), calculateCoordinate(this.getCoord()), this.getGameState()));   
    }
 
    private int[] calculateCoordinate(int[] shipCoord){
        shipCoord = this.getCoord();
        int i = 0;
        while(i < shipCoord.length){
            shipCoord[i] = shipCoord[i] + 1;
        }     
        return shipCoord;
    }    
}
