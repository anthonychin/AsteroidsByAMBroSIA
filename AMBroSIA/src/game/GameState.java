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
    private ArrayList<MapObject> explosionList;
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
        this.explosionList = new ArrayList<MapObject>();
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
    
    public void addPlayerShip(PlayerShip playerShip)
    {
        this.playerShip = playerShip;
    }
    
    public void removePlayerShip()
    {
        this.playerShip = null;
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