
package UnitTests;

import game.Sound;
import game.GameAssets;
import game.GameState;
import MapObjects.PlayerShip;
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
    // Unforuntately the sound do not play. Please run other tests such as Collision.
    // You can hear the sound playing from there
    public void playSound(){
        GameAssets.theme.playLoop();
        GameAssets.theme.play();
    }
}
