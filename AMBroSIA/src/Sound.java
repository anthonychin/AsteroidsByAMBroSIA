import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Meong Hee
 */
public class Sound {
    
    private File file;
    private AudioInputStream sound;
    private Clip clip;
    

    //  The string inputFile should contain the Path of the sound file.
    public Sound(String inputFile) throws UnsupportedAudioFileException, 
            IOException, LineUnavailableException {
        file = new File(inputFile);
        sound = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(sound);
    }
    
    //  Plays the clip only once.
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
    
    //  Stops the currently playing clip.
    public void stop() {
        clip.stop();
    }
    
    //  Plays the clip in continuously. e.g. BGM
    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}