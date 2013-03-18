package game;

/**
 * The <code>GameState</code> class contains all the variables and object references that are required to keep the state of the game in memory.
 * It provides all the methods to add and remove objects from the game and get an ArrayList of the objects of the same type.
 * @author Nikolaos Bukas
 */
import java.util.ArrayList;


public class GameState {
    
    final public static int LARGE_ASTEROID_SCORE = 20;
    final public static int MEDIUM_ASTEROID_SCORE = 50;
    final public static int SMALL_ASTEROID_SCORE = 100;
    final public static int ALIEN_SCORE = 200;
    
    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Projectile> projectileList;
    private ArrayList<BonusDrop> bonusList;
    private ArrayList<MapObjectTTL> explosionList;
    private PlayerShip playerShip;
    private AlienShip alienShip;
    private int highScore;
    private int level;
    
    
    public GameState(int level, int highScore)
    {
        this.playerShip = null;
        this.level = level;
        this.highScore = highScore;
        
        this.asteroidList= new ArrayList<Asteroid>();
        this.projectileList = new ArrayList<Projectile>();
        this.bonusList = new ArrayList<BonusDrop>();
        this.explosionList = new ArrayList<MapObjectTTL>();
    }
    
    public synchronized void addAsteroid(Asteroid asteroid) { this.asteroidList.add(asteroid); }
    
    public synchronized void removeAsteroid(Asteroid asteroid) { this.asteroidList.remove(asteroid); }
    
    public synchronized ArrayList<Asteroid> getAsteroids() { return this.asteroidList; }
    
    public synchronized void addProjectile(Projectile projectile)
    {
        this.projectileList.add(projectile);
    }
    
    public synchronized void removeProjectile(Projectile projectile)
    {
        this.projectileList.remove(projectile);
    }
    
    public synchronized ArrayList<Projectile> getProjectiles()
    {
        return this.projectileList;
    }
    
    public synchronized void addExplosion(MapObjectTTL explosion)
    {
        this.explosionList.add(explosion);
    }
    
    public synchronized void removeExplosion(MapObjectTTL explosion)
    {
        this.explosionList.remove(explosion);
    }
    
    public synchronized ArrayList<MapObjectTTL> getExplosions()
    {
        return this.explosionList;
    }
    
    public synchronized void addBonusDrop(BonusDrop bonusDrop)
    {
        this.bonusList.add(bonusDrop);
    }
    
    public synchronized void removeBonusDrop(BonusDrop bonusDrop)
    {
        this.bonusList.remove(bonusDrop);
    }
    
    public synchronized ArrayList<BonusDrop> getBonusDrops()
    {
        return this.bonusList;
    }
    
    public synchronized void addAlienShip(AlienShip alienShip)
    {
        this.alienShip = alienShip;
    }
    
    public synchronized AlienShip getAlienShip()
    {
        return this.alienShip;
    }
    
    public synchronized void removeAlienShip()
    {
        this.alienShip = null;
    }
    
    public synchronized void addPlayerShip(PlayerShip playerShip)
    {
        this.playerShip = playerShip;
    }
    
    public synchronized void removePlayerShip()
    {
        this.playerShip = null;
    }
    
    public synchronized PlayerShip getPlayerShip()
    {
        return this.playerShip;
    }
    
    public synchronized int getLevel()
    {
        return this.level;
    }
    
    public synchronized boolean isLevelComplete()
    {
        return true;
    }
    
    public synchronized int getHighScore()
    {
        return this.highScore;
    }
    
    public synchronized void addToHighScore(int score)
    {
        this.highScore += score;
    }
}