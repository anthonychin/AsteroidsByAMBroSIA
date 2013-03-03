/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
public class GameState {
    
    public GameState(PlayerShip ship, int level, int highscore);
    
    public void addAsteroid(Asteroid a);
    
    public void removeAsteroid(Asteroid a);
    
    public List<Asteroid> getAsteroids();
    
    public void addProjectile(Projectile p);
    
    public void removeProjectile(Projectile p);
    
    public List<Projectile> getProjectiles();
    
    public void addBonusDrop(BonusDrop bd);
    
    public void removeBonusDrop(BonusDrop bd);
    
    public List<BonusDrop> getBonusDrops();
    
    public void addAlienShip(AlienShip as);
    
    public AlienShip getAlienShip();
    
    public void addPlayerShip(PlayerShip ps);
    
    public PlayerShip getPlayerShip();
    
    public int getLevel();
    
    public boolean isLevelComplete();
    
    public int getHighScore(int score);
    
    public void addToHighScore();
    
}
