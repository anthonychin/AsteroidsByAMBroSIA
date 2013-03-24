package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

/**
 * The
 * <code>GameState</code> class contains all the variables and object references
 * that are required to keep the state of the game in memory. It provides all
 * the methods to add and remove objects from the game and get an ArrayList of
 * the objects of the same type.
 *
 * @author Nikolaos Bukas
 */
public class GameState {

    /**
     * Score the player receives when large asteroid is destroyed.
     */
    final public static int LARGE_ASTEROID_SCORE = 20;
    /**
     * Score the player receives when medium asteroid is destroyed.
     */
    final public static int MEDIUM_ASTEROID_SCORE = 50;
    /**
     * Score the player receives when small asteroid is destroyed.
     */
    final public static int SMALL_ASTEROID_SCORE = 100;
    /**
     * Score the player receives when alien ship is destroyed.
     */
    final public static int ALIEN_SCORE = 200;
    
    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Projectile> projectileList;
    private ArrayList<BonusDrop> bonusList;
    private ArrayList<MapObjectTTL> explosionList;
    
    
    private PlayerShip playerShip;
    private AlienShip alienShip;
    private int highScore;
    private int level;
    
    private static final Object asteroidSync = new Object();
    private static final Object projectileSync = new Object();
    private static final Object explosionSync = new Object();
    private static final Object bonusSync = new Object();
    
//    private final Semaphore asteroidSync = new Semaphore(1,true);
//    private final Semaphore projectileSync = new Semaphore(1,true);
//    private final Semaphore explosionSync = new Semaphore(1,true);
//    private final Semaphore bonusSync = new Semaphore(1,true);

    /**
     * Creates <i>GameState</i> using given parameters. It also creates empty lists for asteroid, projectile, bonus, and explosions.
     * 
     * @param level
     * @param highScore
     */
    public GameState(int level, int highScore) {
        this.playerShip = null;
        this.level = level;
        this.highScore = highScore;

        this.asteroidList = new ArrayList<Asteroid>();
        this.projectileList = new ArrayList<Projectile>();
        this.bonusList = new ArrayList<BonusDrop>();
        this.explosionList = new ArrayList<MapObjectTTL>();
    }

    /**
     * Adds asteroid to the asteroid list.
     * @param asteroid
     */
    public void addAsteroid(Asteroid asteroid) {
        synchronized (asteroidSync) {
        this.asteroidList.add(asteroid);
        }
    }


    /**
     * Removes asteroid from the asteroid list.
     * @param asteroid
     */
    public void removeAsteroid(Asteroid asteroid) {
        synchronized (asteroidSync) {
        this.asteroidList.remove(asteroid);
        }
    }

    /**
     * Returns the list of asteroids.
     * @return list of asteroids
     */
    public ArrayList<Asteroid> getAsteroids() {
        synchronized (asteroidSync) {
        return new ArrayList<Asteroid>(this.asteroidList);
        }
    }

    /**
     * Adds projectile to the projectile list.
     * @param projectile
     */
    public void addProjectile(Projectile projectile) {
        synchronized (projectileSync) {
            this.projectileList.add(projectile);
        }
    }

    /**
     * Removes single projectile from the projectile list.
     * @param projectile
     */
    public void removeProjectile(Projectile projectile) {
        synchronized (projectileSync) {
            this.projectileList.remove(projectile);
        }
    }
    
    /**
     * Removes all projectile from the projectile list.
     * @param list
     */
    public void removeListOfProjectiles(Collection<?> list) {
        synchronized (projectileSync) {
            this.projectileList.removeAll(list);
        }
    }

    /**
     * Returns list of projectiles.
     * @return list of projectiles
     */
    public ArrayList<Projectile> getProjectiles() {
        synchronized (projectileSync) {
        return new ArrayList<Projectile>(this.projectileList);
        }
    }

    /**
     * Adds explosion to the explosion list.
     * @param explosion
     */
    public void addExplosion(MapObjectTTL explosion) {
        synchronized (explosionSync) {
            this.explosionList.add(explosion);
        }
    }

    /**
     * Removes single explosion from the explosion list.
     * @param explosion
     */
    public void removeExplosion(MapObjectTTL explosion) {
        synchronized (explosionSync) {
            this.explosionList.remove(explosion);
        }
    }
    
    /**
     * Removes all explosion from the explosion list.
     * @param list
     */
    public void removeListOfExplosions(Collection<?> list) {
        synchronized (explosionSync) {
            this.explosionList.removeAll(list);
        }
    }

    /**
     * 
     * @return
     */
    public ArrayList<MapObjectTTL> getExplosions() {
        synchronized (explosionSync) {
        return new ArrayList<MapObjectTTL>(this.explosionList);
        }
    }

    /**
     *
     * @param bonusDrop
     */
    public void addBonusDrop(BonusDrop bonusDrop) {
        synchronized (bonusSync) {
            this.bonusList.add(bonusDrop);
        }
    }

    /**
     *
     * @param bonusDrop
     */
    public void removeBonusDrop(BonusDrop bonusDrop) {
        synchronized (bonusSync) {
            this.bonusList.remove(bonusDrop);
        }
    }
    
    /**
     *
     * @param list
     */
    public void removeListOfBonusDrops(Collection<?> list) {
        synchronized (bonusSync) {
            this.bonusList.removeAll(list);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<BonusDrop> getBonusDrops() {
        synchronized (bonusSync) {
        return new ArrayList<BonusDrop>(this.bonusList);
        }
    }

    /**
     *
     * @param alienShip
     */
    public void addAlienShip(AlienShip alienShip) {
        this.alienShip = alienShip;
    }

    /**
     *
     * @return
     */
    public AlienShip getAlienShip() {
        return this.alienShip;
    }

    /**
     *
     */
    public void removeAlienShip() {
        this.alienShip = null;
    }

    /**
     *
     * @param playerShip
     */
    public void addPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    /**
     *
     */
    public void removePlayerShip() {
        this.playerShip = null;
    }

    /**
     *
     * @return
     */
    public PlayerShip getPlayerShip() {
        return this.playerShip;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return this.level;
    }

    /**
     *
     * @return
     */
    public boolean isLevelComplete() {
        return true;
    }

    /**
     *
     * @return
     */
    public int getHighScore() {
        return this.highScore;
    }

    /**
     *
     * @param score
     */
    public void addToHighScore(int score) {
        this.highScore += score;
    }
}