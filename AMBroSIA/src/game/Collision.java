/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Logic.LOG_LEVEL;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 *
 * @author Nikolaos
 */
public class Collision implements Runnable {

    final public static int ALIEN_SHIELD_DAMAGE = 1;
    final public static int LARGE_ASTEROID_SHIELD_DAMAGE = 3;
    final public static int MEDIUM_ASTEROID_SHIELD_DAMAGE = 2;
    final public static int SMALL_ASTEROID_SHIELD_DAMAGE = 1;
    final public static int PROJECTILE_SHIELD_DAMAGE = 1;
    
    private Physics physicsEngine;
    private GameState gameState;
    
    private final static Logger log = Logger.getLogger(Collision.class.getName());

    public Collision(GameState gs, Physics phys) {
        log.setLevel(LOG_LEVEL);
        physicsEngine = phys;
        gameState = gs;
    }

    @Override
    public void run() {
        log.debug("Collision start");
        ArrayList<MapObject> collisionList = physicsEngine.getCollisions();
        log.debug("SIZE OF THE COLLISION LIST = " + collisionList.size());
        log.debug("COLLISION LIST = " + Arrays.toString(collisionList.toArray()));
        if (!collisionList.isEmpty()) {
            for (int i = 0; i < collisionList.size(); i = i + 2) {
                MapObject collisionOne = collisionList.get(i);
                MapObject collisionTwo = collisionList.get(i + 1);

                if (collisionOne instanceof PlayerShip) {
                    if (collisionTwo instanceof AlienShip) {
                        // PlayerShip collided with AlienShip
                        if (collisionLogic((PlayerShip) collisionOne, (AlienShip) collisionTwo)) {
                            //break;
                        }
                    } else if (collisionTwo instanceof Asteroid) {
                        // PlayerShip collided with an Asteroid
                        if (collisionLogic((PlayerShip) collisionOne, (Asteroid) collisionTwo)) {
                            //break;
                        }
                    } else if (collisionTwo instanceof Projectile) {
                        // PlayerShip collided with a Projectile
                        if (collisionLogic((PlayerShip) collisionOne, (Projectile) collisionTwo)) {
                            //break;
                        }
                    } else if (collisionTwo instanceof BonusDrop) {
                        // PlayerShip collided with a BonusDrop
                        collisionLogic((PlayerShip) collisionOne, (BonusDrop) collisionTwo);
                    }
                } else if (collisionOne instanceof AlienShip) {
                    if (collisionTwo instanceof Asteroid) {
                        // AlienShip collided with an Asteroid

                        collisionLogic((AlienShip) collisionOne, (Asteroid) collisionTwo);
                    } else if (collisionTwo instanceof Projectile) {
                        // AlienShip collided with a Projectile
                        collisionLogic((AlienShip) collisionOne, (Projectile) collisionTwo);
                    }
                } else if (collisionOne instanceof Projectile) {
                    collisionLogic((Projectile) collisionOne, (Asteroid) collisionTwo);
                }
            }
        }
    }

    private boolean collisionLogic(PlayerShip playerShip, Asteroid asteroid) {
        log.debug("Collision between Player and Asteroid");
        if (asteroid.getSize() == Asteroid.LARGE_ASTEROID_SIZE) {
            if (playerShip.getShieldPoints() >= LARGE_ASTEROID_SHIELD_DAMAGE) {
                playerShip.setShieldPoints(playerShip.getShieldPoints() - LARGE_ASTEROID_SHIELD_DAMAGE);
            } else if (playerShip.getLives() == 1) {
                playerShip.destroy();
            } else {
                playerShip.destroy();
                playerShip.setLives(playerShip.getLives() - 1);
            }
        } else if (asteroid.getSize() == Asteroid.MEDIUM_ASTEROID_SIZE) {
            if (playerShip.getShieldPoints() >= MEDIUM_ASTEROID_SHIELD_DAMAGE) {
                playerShip.setShieldPoints(playerShip.getShieldPoints() - MEDIUM_ASTEROID_SHIELD_DAMAGE);
            } else if (playerShip.getLives() == 1) {
                playerShip.destroy();
            } else {
                playerShip.destroy();
                playerShip.setLives(playerShip.getLives() - 1);
                
            }
        } else if (asteroid.getSize() == Asteroid.SMALL_ASTEROID_SIZE) {
            if (playerShip.getShieldPoints() >= SMALL_ASTEROID_SHIELD_DAMAGE) {
                playerShip.setShieldPoints(playerShip.getShieldPoints() - SMALL_ASTEROID_SHIELD_DAMAGE);
            } else if (playerShip.getLives() == 1) {
                playerShip.destroy();
            } else {
                playerShip.destroy();
                playerShip.setLives(playerShip.getLives() - 1);
            }
        }

        asteroid.destroy(false);
        return gameState.getPlayerShip() == null;
    }

    private void collisionLogic(PlayerShip playerShip, BonusDrop bonusDrop) {
        log.debug("Collision between Player and Bonus");
        if (bonusDrop.getType() == BonusDrop.BOMB_BONUS_DROP) {
            playerShip.addBomb();
        } else if (bonusDrop.getType() == BonusDrop.LIFE_BONUS_DROP) {
            playerShip.setLives(playerShip.getLives() + 1);
        } else if (bonusDrop.getType() == BonusDrop.SHIELD_ONE_POINT_DROP) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 1);
        } else if (bonusDrop.getType() == BonusDrop.SHIELD_TWO_POINTS_DROP) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 2);
        } else if (bonusDrop.getType() == BonusDrop.SHIELD_THREE_POINTS_DROP) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() + 3);
        }

        bonusDrop.destroy();
    }

    private boolean collisionLogic(PlayerShip playerShip, AlienShip alienShip) {
        log.debug("Collision between Player and Alien");
        if (playerShip.getShieldPoints() >= ALIEN_SHIELD_DAMAGE) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() - ALIEN_SHIELD_DAMAGE);
        } else if (playerShip.getLives() == 1) {
            playerShip.destroy();
        } else {
            playerShip.setLives(playerShip.getLives() - 1);
        }

        alienShip.destroy();

        return gameState.getPlayerShip() == null;
    }

    private boolean collisionLogic(PlayerShip playerShip, Projectile projectile) {
        log.debug("Collision between Player and Projectile");
        if (playerShip.getShieldPoints() >= PROJECTILE_SHIELD_DAMAGE) {
            playerShip.setShieldPoints(playerShip.getShieldPoints() - PROJECTILE_SHIELD_DAMAGE);
        } else if (playerShip.getLives() == 1) {
            playerShip.destroy();
        } else {
            playerShip.setLives(playerShip.getLives() - 1);
        }

        projectile.destroy();

        return gameState.getPlayerShip() == null;
    }

    private void collisionLogic(AlienShip alienShip, Asteroid asteroid) {
        log.debug("Collision between Alien and Asteroid");
        alienShip.destroy();
        asteroid.destroy();
    }

    private void collisionLogic(AlienShip alienShip, Projectile projectile) {
        log.debug("Collision between Alien and Projectile");
        alienShip.destroy();
        projectile.destroy();
    }

    private void collisionLogic(Projectile projectile, Asteroid asteroid) {
        log.debug("Collision between Projectile and Asteroid");
        log.debug("DESTROYING PROJECTILE " + projectile.toString());
        projectile.destroy();
        log.debug("DESTROYING ASTEROID " + asteroid.toString());
        asteroid.destroy(false);
    }
}
