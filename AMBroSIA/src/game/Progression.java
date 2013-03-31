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
    
    private void spawnAlien()
    {
        
//        gameState.addAlienShip(Difficulty.spawnAlien());
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
            //go to next level
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
    
    private void setupInitialLevel()
    {
        gameState.addPlayerShip(new PlayerShip(new float[]{0, 0}, 0, new int[]{MenuGUI.WIDTH/2, MenuGUI.HEIGHT/2}, gameState, 99, 0, 3));
        addAsteroids(1);
    }
    
    private void setupLevel(int levelNumber)
    {
        //save old level & score
        int oldLevel = gameState.getLevel();
        int oldScore = gameState.getHighScore();
        //player can't be null here
        int oldPlayerLives = gameState.getPlayerShip().getLives();
        
        
        gameState.resetToDefaults();
        gameState.addPlayerShip(new PlayerShip(new float[]{0, 0}, 0, new int[]{MenuGUI.WIDTH/2, MenuGUI.HEIGHT/2}, gameState, oldPlayerLives, 0, 3));
        addAsteroids(levelNumber);
        gameState.setLevel(oldLevel);
        gameState.addToHighScore(oldScore);
    }
    
    private void addAsteroids(int levelNumber)
    {
//        int NumberOfAsteroids = spawnAsteroids(1);
//
//        for (int i = 0; i < NumberOfAsteroids; i++) {
//            float xVel = randomAsteroidVelocity();
//            float yVel = randomAsteroidVelocity();
//            float heading = randomHeading();
//            int xCoord = randomXPos();
//            int yCoord = randomYPos();
//            int size = andomAsteroidSize();
//            gameState.addAsteroid(new Asteroid(new float[]{xVel, yVel}, heading, new int[]{xCoord, yCoord}, gameState, size));
//        }
    }

}
