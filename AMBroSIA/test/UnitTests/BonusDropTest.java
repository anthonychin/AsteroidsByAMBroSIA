package UnitTests;

import mapObjects.BonusDrop;
import game.GameAssets;
import game.GameState;
import java.util.Iterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Bonus Drop Tests
 * @author Anthony Chin
 */
public class BonusDropTest {
    private GameState gameState;
    private BonusDrop energyDrop;
    private BonusDrop energyDrop2;
    private BonusDrop energyDrop3;
    private BonusDrop energyDrop4;
    private BonusDrop energyDrop5;

    @Before
    public void setUp(){
        gameState = new GameState();
        energyDrop = new BonusDrop(new int[]{400,400}, gameState, 0);
        energyDrop2 = new BonusDrop(new int[]{400,400}, gameState, 1);
        energyDrop3 = new BonusDrop(new int[]{400,400}, gameState, 2);
        energyDrop4 = new BonusDrop(new int[]{400,400}, gameState, 3);
        energyDrop5 = new BonusDrop(new int[]{400,400}, gameState, 4);
        
    }
    
    @After
    public void tearDown(){
        gameState = null;
        energyDrop = null;
        energyDrop2 = null;
        energyDrop3 = null;
        energyDrop4 = null;
        energyDrop5 = null;
    }
    
    @Test
    public void getType(){
        gameState.addBonusDrop(energyDrop);
        gameState.addBonusDrop(energyDrop2);
        gameState.addBonusDrop(energyDrop3);
        gameState.addBonusDrop(energyDrop4);
        gameState.addBonusDrop(energyDrop5);
        
        Iterator<BonusDrop> itr = gameState.getBonusDrops().iterator();
        while(itr.hasNext()){
            BonusDrop bd = itr.next();
            switch(bd.getType()){
                case 0: assertEquals("bomb bonus drop", bd.getType(), 0);
                    break;
                case 1: assertEquals("life bonus drop", bd.getType(), 1);
                    break;
                case 2: assertEquals("3 shield points", bd.getType(), 2);
                    break;
                case 3: assertEquals("2 shield points", bd.getType(), 3);
                    break;
                case 4: assertEquals("1 shield point", bd.getType(), 4);
                    break;
                default: assertTrue("not a valid bonus type", bd.getType()<4);
            }
        }
    }
    
    @Test
    public void destroyBonusDrop(){
        gameState.addBonusDrop(energyDrop);
        energyDrop.destroy();
        
        assertEquals("bonus drop should be gone", gameState.getBonusDrops().size(), 0);
    }
    
    
}
