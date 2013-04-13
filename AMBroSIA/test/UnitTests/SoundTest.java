
package UnitTests;

import game.Sound;
import game.GameAssets;
import game.GameState;
import game.PlayerShip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 * Sound Test Cases
 * @author Anthony Chin
 */
public class SoundTest {
    @Before
    public void setUp(){
        GameAssets.loadSounds();
    }
    
    @Test
    // Doesnt play. Do not know why it doesnt play
    public void playSound(){
        GameAssets.theme.playLoop();
        GameAssets.theme.play();
    }
}
