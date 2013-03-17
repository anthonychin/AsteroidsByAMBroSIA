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
        //this.getGameState().addProjectile(new Projectile(this, Projectile.PROJECTILE_VELOCITY, this.getHeading(), calculateCoordinate(this.getCoord())), this.getGameState()));
        //this.getGameState().addProjectile(new Projectile(this, Projectile.PROJECTILE_VELOCITY, this.getHeading(), calculateCoordinate(this.getCoord()), this.getGameState()));   
            //gameState.addProjectile(new Projectile(p1, Projectile.PROJECTILE_VELOCITY, 180, new int[] {405, 305}, gameState));   
        this.getGameState().addProjectile(new Projectile(this, new float[] {Projectile.PROJECTILE_SPEED, this.getHeading()}, this.getHeading(),calculateProjectileCoordinate(this.getHeading(), this.getCoord()), this.getGameState()));
    }
 
    private static int[] calculateProjectileCoordinate(float heading, int[] curCoordinates){
        int[] newCoordinates = new int[2];
                        
            newCoordinates[0] =  (int)(curCoordinates[0] + Math.cos(Math.toRadians(heading - 90))*15);
            newCoordinates[1] =  (int)(curCoordinates[1] + Math.sin(Math.toRadians(heading - 90))*15);
            
            if(newCoordinates[0] < 0){
                newCoordinates[0] = 790;
            }
            else if(newCoordinates[0] > 790){
                newCoordinates[0] = 10;
            }
            if(newCoordinates[1] < 0){
                newCoordinates[1] = 590;
            }
            else if(newCoordinates[1] > 590){
                newCoordinates[1] = 10;
            }         
        return newCoordinates;
    }      
}
