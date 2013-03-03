/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikolaos
 */
public class GameState {
    
    public GameState();
    
    public void addAsteroid();
    
    public void removeAsteroid();
    
    public List<Asteroid> getAsteroids();
    
    public void addProjectile();
    
    public void removeProjectile();
    
    public List<Projectile> getProjectiles();
    
    public void addBonusDrop();
    
    public void removeBonusDrop();
    
    public List<BonusDrop> getBonusDrops();
    
    public void addAlienShip();
    
    public AlienShip getAlienShip();
    
    public void addPlayerShip();
    
    public PlayerShip getPlayerShip();
    
    public int getLevel();
    
    public boolean isLevelComplete();
    
    public int getHighScore();
    
    public void addToHighScore();
    
}
