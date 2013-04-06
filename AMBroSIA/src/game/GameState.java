package game;

import java.util.ArrayList;
import java.util.Collection;

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
    private int currentScore;
    private int player1Score;
    private int player2Score;
    private int level;
    private int player1Level;
    private int player2Level;
    private boolean isPlayerDead;
    private boolean playerTwoTurn;
    private static final Object asteroidSync = new Object();
    private static final Object projectileSync = new Object();
    private static final Object explosionSync = new Object();
    private static final Object bonusSync = new Object();

    /**
     * Creates <i>GameState</i> using given parameters. It also creates empty
     * lists for asteroid, projectile, bonus, and explosions.
     *
     * @param level
     * @param currentScore
     */
    public GameState() {
        resetToDefaults();
    }

    /**
     * Adds asteroid to the asteroid list.
     *
     * @param asteroid
     */
    public void addAsteroid(Asteroid asteroid) {
        synchronized (asteroidSync) {
            this.asteroidList.add(asteroid);
        }
    }

    /**
     * Removes asteroid from the asteroid list.
     *
     * @param asteroid
     */
    public void removeAsteroid(Asteroid asteroid) {
        synchronized (asteroidSync) {
            this.asteroidList.remove(asteroid);
        }
    }

    /**
     * Returns the list of asteroids.
     *
     * @return list of asteroids
     */
    public ArrayList<Asteroid> getAsteroids() {
        synchronized (asteroidSync) {
            return new ArrayList<Asteroid>(this.asteroidList);
        }
    }

    public void addAsteroidsList(ArrayList<Asteroid> list) {
        synchronized (asteroidSync) {
            this.asteroidList.addAll(list);
        }
    }

    /**
     * Adds projectile to the projectile list.
     *
     * @param projectile
     */
    public void addProjectile(Projectile projectile) {
        synchronized (projectileSync) {
            this.projectileList.add(projectile);
        }
    }

    /**
     * Removes single projectile from the projectile list.
     *
     * @param projectile
     */
    public void removeProjectile(Projectile projectile) {
        synchronized (projectileSync) {
            this.projectileList.remove(projectile);
        }
    }

    /**
     * Removes all projectile from the projectile list.
     *
     * @param list
     */
    public void removeListOfProjectiles(Collection<?> list) {
        synchronized (projectileSync) {
            this.projectileList.removeAll(list);
        }
    }

    /**
     * Returns list of projectiles.
     *
     * @return list of projectiles
     */
    public ArrayList<Projectile> getProjectiles() {
        synchronized (projectileSync) {
            return new ArrayList<Projectile>(this.projectileList);
        }
    }

    /**
     * Adds explosion to the explosion list.
     *
     * @param explosion
     */
    public void addExplosion(MapObjectTTL explosion) {
        synchronized (explosionSync) {
            this.explosionList.add(explosion);
        }
    }

    /**
     * Removes single explosion from the explosion list.
     *
     * @param explosion
     */
    public void removeExplosion(MapObjectTTL explosion) {
        synchronized (explosionSync) {
            this.explosionList.remove(explosion);
        }
    }

    /**
     * Removes all explosion from the explosion list.
     *
     * @param list
     */
    public void removeListOfExplosions(Collection<?> list) {
        synchronized (explosionSync) {
            this.explosionList.removeAll(list);
        }
    }

    public ArrayList<MapObjectTTL> getExplosions() {
        synchronized (explosionSync) {
            return new ArrayList<MapObjectTTL>(this.explosionList);
        }
    }

    /**
     * Adds bonus drops to the bonus drop list.
     *
     * @param bonusDrop
     */
    public void addBonusDrop(BonusDrop bonusDrop) {
        synchronized (bonusSync) {
            this.bonusList.add(bonusDrop);
        }
    }

    /**
     * Removes a single bonus drops from the bonus drop list.
     *
     * @param bonusDrop
     */
    public void removeBonusDrop(BonusDrop bonusDrop) {
        synchronized (bonusSync) {
            this.bonusList.remove(bonusDrop);
        }
    }

    /**
     * Removes all bonus drops from list.
     *
     * @param list
     */
    public void removeListOfBonusDrops(Collection<?> list) {
        synchronized (bonusSync) {
            this.bonusList.removeAll(list);
        }
    }

    /**
     * Returns list of all bonus drops.
     *
     * @return list of all bonus drops
     */
    public ArrayList<BonusDrop> getBonusDrops() {
        synchronized (bonusSync) {
            return new ArrayList<BonusDrop>(this.bonusList);
        }
    }

    /**
     * Adds alien ship.
     *
     * @param alienShip
     */
    public void addAlienShip(AlienShip alienShip) {
        this.alienShip = alienShip;
    }

    /**
     * Returns alien ship.
     *
     * @return alien ship
     */
    public AlienShip getAlienShip() {
        return this.alienShip;
    }

    /**
     * Sets the variable <i>alienShip</i> to null.
     */
    public void removeAlienShip() {
        this.alienShip = null;
    }

    /**
     * Adds player ship.
     *
     * @param playerShip
     */
    public void addPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    /**
     * Sets the variable <i>playerShip</i> to null.
     */
    public void removePlayerShip() {
        this.playerShip = null;
    }

    /**
     * Returns player ship.
     *
     * @return player ship
     */
    public PlayerShip getPlayerShip() {
        return this.playerShip;
    }

    /**
     * Returns current level.
     *
     * @return integer value representing level
     */
    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void increaseLevel() {
        this.level++;
    }

    /**
     * Returns true if level is complete, false otherwise.
     *
     * @return boolean value (true if complete, false otherwise)
     */
    public boolean isLevelComplete() {
        return true;
    }

    /**
     * Returns the current score.
     *
     * @return current score
     */
    public int getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Add to high score the value of score.
     *
     * @param score
     */
    public void addToCurrentScore(int score) {
        this.currentScore += score;
    }
    
    public void resetCurrentScore() {
        this.currentScore = 0;
    }

    public void setPlayer1Score(int score) {
        player1Score = score;
    }

    /**
     * only valid once game is over
     *
     * @return
     */
    public int getPlayer1Score() {
        return player1Score;
    }

    /**
     * only valid once game is over
     *
     * @return
     */
    public int getPlayer1Level() {
        return player1Level;
    }

    public void setPlayer1Level(int p1Level) {
        player1Level = p1Level;
    }

    /**
     * only valid once game is over
     *
     * @return
     */
    public int getPlayer2Level() {
        return player2Level;
    }

    public void setPlayer2Level(int p2Level) {
        player2Level = p2Level;
    }

    public void setPlayer2Score(int score) {
        player2Score = score;
    }

    /**
     * only valid once game is over
     *
     * @return
     */
    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayerDead(boolean isDead) {
        isPlayerDead = isDead;
    }

    public boolean isPlayerDead() {
        return isPlayerDead;
    }

    public boolean isPlayerTwoTurn() {
        return playerTwoTurn;
    }

    public void setPlayerTwoTurn(boolean playerTwo) {
        playerTwoTurn = playerTwo;
    }

    //resets everything to defaults
    public void resetToDefaults() {
        this.playerShip = null;
        this.level = 1;
        this.currentScore = 0;
        isPlayerDead = false;
        playerTwoTurn = false;
        playerShip = null;
        alienShip = null;
        player1Score = 0;
        player2Score = 0;

        synchronized (asteroidSync) {
            this.asteroidList = new ArrayList<Asteroid>();
        }
        synchronized (projectileSync) {
            this.projectileList = new ArrayList<Projectile>();
        }
        synchronized (bonusSync) {
            this.bonusList = new ArrayList<BonusDrop>();
        }
        synchronized (explosionSync) {
            this.explosionList = new ArrayList<MapObjectTTL>();
        }
    }

    //essentially, performs the function of the bomb - remove alien & all asteroids
    public void bombUsed() {
        AlienShip alien = getAlienShip();
        if (alien != null) {
            alien.destroy(true);
        }
        for (Asteroid element : getAsteroids()) {
            element.destroy(true);
        }
    }
}
