/**
 * The <code>GameState</code> class contains all the variables and object references that are required to keep the state of the game in memory.
 * It provides all the methods to add and remove objects from the game and get an ArrayList of the objects of the same type.
 * @author Nikolaos
 */
import java.util.ArrayList;


public class GameState {
    
    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Projectile> projectileList;
    private ArrayList<BonusDrop> bonusList;
    private ArrayList<MapObject> explosionList;
    private PlayerShip playerShip;
    private AlienShip alienShip;
    private int highScore;
    private int level;
    
    
    public GameState(PlayerShip ship, int level, int highScore)
    {
        this.playerShip = ship;
        this.level = level;
        this.highScore = highScore;
        
        this.asteroidList= new ArrayList<>(0);
        this.projectileList = new ArrayList<>(0);
        this.bonusList = new ArrayList<>(0);
        this.explosionList = new ArrayList<>(0);
    }
    
    public void addAsteroid(Asteroid asteroid) { this.asteroidList.add(asteroid); }
    
    public void removeAsteroid(Asteroid asteroid) { this.asteroidList.remove(asteroid); }
    
    public ArrayList<Asteroid> getAsteroids() { return this.asteroidList; }
    
    public void addProjectile(Projectile projectile)
    {
        this.projectileList.add(projectile);
    }
    
    public void removeProjectile(Projectile projectile)
    {
        this.projectileList.remove(projectile);
    }
    
    public ArrayList<Projectile> getProjectiles()
    {
        return this.projectileList;
    }
    
    public void addExplosion(MapObject explosion)
    {
        this.explosionList.add(explosion);
    }
    
    public void removeExplosion(MapObject explosion)
    {
        this.explosionList.remove(explosion);
    }
    
    public ArrayList<MapObject> getExplosions()
    {
        return this.explosionList;
    }
    
    public void addBonusDrop(BonusDrop bonusDrop)
    {
        this.bonusList.add(bonusDrop);
    }
    
    public void removeBonusDrop(BonusDrop bonusDrop)
    {
        this.bonusList.remove(bonusDrop);
    }
    
    public ArrayList<BonusDrop> getBonusDrops()
    {
        return this.bonusList;
    }
    
    public void addAlienShip(AlienShip alienShip)
    {
        this.alienShip = alienShip;
    }
    
    public AlienShip getAlienShip()
    {
        return this.alienShip;
    }
    
    public void removeAlienShip()
    {
        this.alienShip = null;
    }
    
    public void addPlayerShip(PlayerShip playership)
    {
        this.playerShip = playerShip;
    }
    
    public PlayerShip getPlayerShip()
    {
        return this.playerShip;
    }
    
    public int getLevel()
    {
        return this.level;
    }
    
    public boolean isLevelComplete()
    {
        return true;
    }
    
    public int getHighScore()
    {
        return this.highScore;
    }
    
    public void addToHighScore(int score)
    {
        this.highScore += score;
    }
}