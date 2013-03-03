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
    
    public void addAsteroid(Asteroid asteroid);
    
    public void removeAsteroid(Asteroid asteroid);
    
    public List<Asteroid> getAsteroids();
    
    public void addProjectile(Projectile projectile);
    
    public void removeProjectile(Projectile projectile);
    
    public List<Projectile> getProjectiles();
    
    public void addBonusDrop(BonusDrop bonusdrop);
    
    public void removeBonusDrop(BonusDrop bonusdrop);
    
    public List<BonusDrop> getBonusDrops();
    
    public void addAlienShip(AlienShip alienship);
    
    public AlienShip getAlienShip();
    
    public void addPlayerShip(PlayerShip playership);
    
    public PlayerShip getPlayerShip();
    
    public int getLevel();
    
    public boolean isLevelComplete();
    
    public int getHighScore(int score);
    
    public void addToHighScore();
    
}
