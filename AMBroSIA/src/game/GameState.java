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

    /*
     * Note on Synchronization:
     * The way GameState is written, methods are provided that return lists of
     * objects, thus allowing someone to write code that iterates through
     * a list or retrieves only a select few items.  While this gives
     * a lot of flexibility, it introduces some problems; namely, that of 
     * synchronization: objects can be added or removed from the list
     * at any time, which can cause another thread iterating through
     * the list to freeze.  While this involves multithreading, it also 
     * occurs when the game is made to run single-threaded, as the keyboard
     * runs in its own thread.
     * 
     * Thus the decision was made to fix the root of the problem: namely,
     * the ability to modify a list while another thread is iterating through
     * it.  After much trial and error, we found that creating a new,
     * independent list whenever a list is returned - as well as making
     * sure that additions/deletions and the creation of the new list in
     * question are all synchronized,  fixed the issue, as iterations
     * are always performed on a list that cannot be modified
     * by another thread.
     * 
     * Note that objects contained in the lists do not need
     * synchronization, as most operations are atomic, and those that are
     * not are not affected by concurrency issues - i.e. removing 
     * an object when it is already removed is OK.
     */
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
    //information for current player
    private int currentScore;
    private int level;
    //stored information (particuarly useful in two player)
    private int player1Level;
    private int player2Level;
    private int player1Score;
    private int player2Score;
    private boolean isPlayerDead;
    private boolean playerTwoTurn;
    
    private int P1BombUsed;
    private int P2BombUsed;
    private int p2AlienDestroyed;
    private int p1AlienDestroyed;
    
    //synchronization objects
    private static final Object asteroidSync = new Object();
    private static final Object projectileSync = new Object();
    private static final Object explosionSync = new Object();
    private static final Object bonusSync = new Object();
    private int p1AsteroidDestroyed;
    private int p2AsteroidDestroyed;
    private int p1shootCounter;
    private int p2shootCounter;

    

    /**
     * Creates GameState with default values.
     *
     */
    public GameState() {
        resetToDefaults();
    }

    /**
     * Adds asteroid to the asteroid list.
     *
     * @param asteroid asteroid to be inserted in asteroid list
     */
    public void addAsteroid(Asteroid asteroid) {
        synchronized (asteroidSync) {
            this.asteroidList.add(asteroid);
        }
    }

    /**
     * Removes asteroid from the asteroid list.
     *
     * @param asteroid asteroid to be removed from the asteroid list
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

    //add a whole list of asteroids at once
    /**
     * Adds list of asteroids at once.
     *
     * @param list list of asteroids to be added to the asteroid list
     */
    public void addAsteroidsList(ArrayList<Asteroid> list) {
        synchronized (asteroidSync) {
            this.asteroidList.addAll(list);
        }
    }

    /**
     * Adds projectile to the projectile list.
     *
     * @param projectile projectile to be added to the projectile list
     */
    public void addProjectile(Projectile projectile) {
        synchronized (projectileSync) {
            this.projectileList.add(projectile);
        }
    }

    /**
     * Removes single projectile from the projectile list.
     *
     * @param projectile projectile to be removed from the projectile list
     */
    public void removeProjectile(Projectile projectile) {
        synchronized (projectileSync) {
            this.projectileList.remove(projectile);
        }
    }

    /**
     * Removes all projectile from the projectile list.
     *
     * @param list list of projectile to be removed from projectile list
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
     * @param explosion explosion to be added to the explosion list
     */
    public void addExplosion(MapObjectTTL explosion) {
        synchronized (explosionSync) {
            this.explosionList.add(explosion);
        }
    }

    /**
     * Removes single explosion from the explosion list.
     *
     * @param explosion explosion to be removed from the explosion list
     */
    public void removeExplosion(MapObjectTTL explosion) {
        synchronized (explosionSync) {
            this.explosionList.remove(explosion);
        }
    }

    /**
     * Removes all explosion from the explosion list.
     *
     * @param list list of explosions that needs to be removed from explosion
     * list
     */
    public void removeListOfExplosions(Collection<?> list) {
        synchronized (explosionSync) {
            this.explosionList.removeAll(list);
        }
    }

    //return explosion list
    /**
     * Returns explosion list.
     *
     * @return list of all explosions
     */
    public ArrayList<MapObjectTTL> getExplosions() {
        synchronized (explosionSync) {
            return new ArrayList<MapObjectTTL>(this.explosionList);
        }
    }

    /**
     * Adds bonus drops to the bonus drop list.
     *
     * @param bonusDrop bonus drop to be added to the bonus drop list
     */
    public void addBonusDrop(BonusDrop bonusDrop) {
        synchronized (bonusSync) {
            this.bonusList.add(bonusDrop);
        }
    }

    /**
     * Removes a single bonus drops from the bonus drop list.
     *
     * @param bonusDrop bonus drop to be removed from the bonus drop list
     */
    public void removeBonusDrop(BonusDrop bonusDrop) {
        synchronized (bonusSync) {
            this.bonusList.remove(bonusDrop);
        }
    }

    /**
     * Removes all bonus drops from list.
     *
     * @param list list of bonus drops to be removed from the bonus drop list
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
     * @param alienShip alien ship to be added to the game
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
     * Removes alien ship from the game.
     */
    public void removeAlienShip() {
        this.alienShip = null;
    }

    /**
     * Adds player ship.
     *
     * @param playerShip player ship to be added to the game
     */
    public void addPlayerShip(PlayerShip playerShip) {
        this.playerShip = playerShip;
    }

    /**
     * Removes player ship from the game.
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

    //change current level
    /**
     * Changes current level.
     *
     * @param level new level
     */
    public void setLevel(int level) {
        this.level = level;
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
     * @param score new current score
     */
    public void addToCurrentScore(int score) {
        this.currentScore += score;
    }

    /**
     * Resets current score to 0.
     */
    public void resetCurrentScore() {
        this.currentScore = 0;
    }

    //set the score of player 1
    /**
     * Sets the score of player 1.
     *
     * @param score
     */
    public void setPlayer1Score(int score) {
        player1Score = score;
    }

    /**
     * Returns the score of Player 1. Only valid once game is over.
     *
     * @return score of player 1
     */
    public int getPlayer1Score() {
        return player1Score;
    }

    /**
     * Returns the level of Player 1. Only valid once game is over.
     *
     * @return level of player 1
     */
    public int getPlayer1Level() {
        return player1Level;
    }

    //player 1, player 2 level useful in two player to store data while the other is playing
    /**
     * Sets Player 1's level. Useful in two player mode to store data while the
     * other is playing.
     *
     * @param p1Level player 1's level
     */
    public void setPlayer1Level(int p1Level) {
        player1Level = p1Level;
    }

    /**
     * Returns the score of Player 2. Only valid once game is over.
     *
     * @return score of player 2
     */
    public int getPlayer2Level() {
        return player2Level;
    }

    /**
     * Sets the level of Player 2.
     *
     * @param p2Level player 2's level
     */
    public void setPlayer2Level(int p2Level) {
        player2Level = p2Level;
    }

    //same idea as setting playerX level
    /**
     * Sets score of Player 2.
     *
     * @param score player 2's score
     */
    public void setPlayer2Score(int score) {
        player2Score = score;
    }

    /**
     * Returns score of Player 2. Only valid once game is over.
     *
     * @return Player 2's score
     */
    public int getPlayer2Score() {
        return player2Score;
    }

    /**
     * Sets whether or not the player is dead.
     *
     * @param isDead true if dead, false otherwise
     */
    public void setPlayerDead(boolean isDead) {
        isPlayerDead = isDead;
    }

    //whether or not the player is dead (and not just in the process of respawning)
    /**
     * Checks whether or not the player is dead.
     *
     * @return true if dead false otherwise
     */
    public boolean isPlayerDead() {
        return isPlayerDead;
    }

    /**
     * Get the amount of bomb used by P1
     * @return 
     */
    public int getP1BombUsed(){
        return P1BombUsed;
    }
    
    /**
     * Get the amount of bomb used by P2
     * @return 
     */
    public int getP2BombUsed(){
        return P2BombUsed;
    }
    /**
     * 
     */
    public void addP1BombUsed(){
        this.P1BombUsed = ++this.P2BombUsed;
    }
    
    /**
     * 
     */
    public void addP2BombUsed(){
        this.P2BombUsed = ++this.P2BombUsed;
    }

     /**
     * Get the amount of aliens killed by P1
     * @return 
     */
    public int getP1alienDestroyed(){
        return p1AlienDestroyed;
    }
    
    /**
     * Get the amount of aliens killed by P2
     * @return 
     */
    public int getP2alienDestroyed(){
        return p2AlienDestroyed;
    }
    /**
     * 
     */
    public void addP1alienDestroyed(){
        this.p1AlienDestroyed = ++p1AlienDestroyed;
    }
    
    /**
     * 
     */
    public void addP2alienDestroyed(){
        this.p2AlienDestroyed = ++p2AlienDestroyed;
    }
    
     /**
     * Get the amount of asteroids killed by P1
     * @return 
     */
    public int getP1asteroidDestroyed(){
        return p1AsteroidDestroyed;
    }
    
    /**
     * Get the amount of asteroids killed by P2
     * @return 
     */
    public int getP2asteroidDestroyed(){
        return p2AsteroidDestroyed;
    }
    /**
     * 
     */
    public void addP1asteroidDestroyed(){
        this.p1AsteroidDestroyed = ++p1AsteroidDestroyed;
    }
    
    /**
     * 
     */
    public void addP2asteroidDestroyed(){
        this.p2AsteroidDestroyed = ++p2AsteroidDestroyed;
    }
 
     /**
     * Get the amount of asteroids killed by P1
     * @return 
     */
    public int getP1shootCounter(){
        return p1shootCounter;
    }
    
    /**
     * Get the amount of asteroids killed by P2
     * @return 
     */
    public int getP2shootCounter(){
        return p2shootCounter;
    }
    /**
     * 
     */
    public void setP1shootCounter(int shot){
        this.p1shootCounter = p1shootCounter + shot;
    }
    
    /**
     * 
     */
    public void setP2shootCounter(int shot){
        this.p2shootCounter = p2shootCounter + shot;
    }    
    
    /**
     * Checks if it is Player 2's turn to play.
     *
     * @return true if Player 2's turn, false otherwise
     */
    public boolean isPlayerTwoTurn() {
        return playerTwoTurn;
    }

    /**
     * Sets the variable playerTwoTurn.
     * @param playerTwo true if Player 2's turn, false otherwise
     */
    public void setPlayerTwoTurn(boolean playerTwo) {
        playerTwoTurn = playerTwo;
    }

    //resets everything to defaults
    /**
     * Resets everything to default. (Returns to Level 1)
     */
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
    /**
     * Performs the function of the bomb. Removes alien and all asteroids.
     */
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
