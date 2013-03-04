import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Myong
 */
public class Sound {
    
    private File file;
    private AudioInputStream sound;
    private Clip clip;
    
//    private AudioClip background;

    public Sound(String inputFile) {
        file = new File(inputFile);
        try {
            sound = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
//    //  This method will play the background music.
//    public void playBackgroundSound() throws Exception {
//        backgroundFile = new File("sound/background.wav");
//        background = AudioSystem.getAudioInputStream(backgroundFile);
////        background = getAudioClip(getClass().getResources(), "sounds/background.wav");
//    }
//    
//    //  This method will play the sound of the gun shooting.
//    public void playGunSound();
//    
//    //  This method will play the sound of the bomb exploding.
//    public void playBombSound();
//    
//    //  This method will play the main theme.
//    public void playTheme();
//    
//    //  This method will play the shield sound.
//    public void playShieldSound();
//    
//    //  This method will play the sound of the thruster firing.
//    public void playThruster();
//    
//    //  This method will play the sound of an explosion.
//    public void playExplosion();
//    
//    //  This method will play the victory sound.
//    public void playVictory();
//    
//    //  This will play the Game Over sound.
//    public void playDefeated();
//
}
