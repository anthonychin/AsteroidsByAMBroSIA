/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gui.MenuGUI;
import game.Difficulty.*;

/**
 *
 * @author Michael
 */
public class Progression implements Runnable{
    
    private GameState gameState;
    private boolean istwoPlayer = false;
    
    public Progression(GameState gs, boolean twoPlayer){
        gameState = gs;
        istwoPlayer = twoPlayer;
    }

    @Override
    public void run() {
        checkGameProgress();
        spawnAlien();
    }

    private void spawnAlien() {
        if (isAlienDestroyed() && Difficulty.spawnAlien()) {
            gameState.addAlienShip(new AlienShip(new float[]{Difficulty.randomAlienVelocity(), Difficulty.randomAlienVelocity()}, Difficulty.randomHeading(), new int[]{Difficulty.randomXPos(), Difficulty.randomYPos()}, gameState));
        }
    }
    
    private void checkGameProgress()
    {
        if (isPlayerDead())
        {
            Logic.stopTimer();
            Logic.displayGameOver();
        }
        
        else if (allAsteroidsDestroyed() && isAlienDestroyed())
        {
            setupLevel(gameState.getLevel()+1);
        }
    }
    
    
    private boolean isAlienDestroyed()
    {
        return gameState.getAlienShip() == null;
    }
    
    private boolean allAsteroidsDestroyed()
    {
        return gameState.getAsteroids().isEmpty();
    }
    
    private boolean isPlayerDead()
    {
        return gameState.isPlayerDead();
    }
    
    public void setupInitialLevel()
    {
        gameState.addPlayerShip(new PlayerShip(new float[]{0, 0}, 0, new int[]{MenuGUI.WIDTH/2, MenuGUI.HEIGHT/2}, gameState, 99, 0, 3));
        //addAsteroids(1);
        setupLevel(100);
        //gameState.addAsteroid(new Asteroid(new float[]{1.2f, 1.5f}, 0, new int[]{800, 10}, gameState, Asteroid.LARGE_ASTEROID_SIZE));

    }
    
    private void setupLevel(int levelNumber)
    {
        //save old level & score
        int oldLevel = gameState.getLevel();
        int oldScore = gameState.getCurrentScore();
        //player can't be null here
        int oldPlayerLives = gameState.getPlayerShip().getLives();
        
        
        gameState.resetToDefaults();
        gameState.addPlayerShip(new PlayerShip(new float[]{0, 0}, 0, new int[]{MenuGUI.WIDTH/2, MenuGUI.HEIGHT/2}, gameState, oldPlayerLives, 0, 3));
        addAsteroids(levelNumber);
        gameState.setLevel(oldLevel);
        gameState.addToCurrentScore(oldScore);
    }
    
    private void addAsteroids(int levelNumber)
    {
        int NumberOfAsteroids = Difficulty.spawnAsteroids(1);

        for (int i = 0; i < NumberOfAsteroids; i++) {
            float xVel = Difficulty.randomAsteroidVelocity(levelNumber);
            float yVel = Difficulty.randomAsteroidVelocity(levelNumber);
            float heading = Difficulty.randomHeading();
            int xCoord = Difficulty.randomXPos();
            int yCoord = Difficulty.randomYPos();
            int size = Difficulty.randomAsteroidSize();
            gameState.addAsteroid(new Asteroid(new float[]{xVel, yVel}, heading, new int[]{xCoord, yCoord}, gameState, size));
        }
    }

}
