package game;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The <code>Sound</code> class contains necessary codes for playing sound throughout the game.
 * It provides <tt>play</tt>, <tt>stop</tt>, and <tt>playLoop</tt> operations.
 * @author Meong Hee
 */
public class Sound {

    private AudioInputStream sound;
    private Clip clip;
    
    //  The string inputFile should contain the Path of the sound file.
    /**
     * Creates <i>Sound</i> using given inputFile. If the file is not found or the given file is not supported, it throws an exception.
     * @param inputFile name of the sound file
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Sound(String inputFile) throws UnsupportedAudioFileException, 
            IOException, LineUnavailableException {
        URL soundFile = Sound.class.getResource("sounds/" + inputFile);
        sound = AudioSystem.getAudioInputStream(soundFile);
        clip = AudioSystem.getClip();
        clip.open(sound);
    }
    
    /**
     * Plays the clip once.
     */
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
    
    /**
     * Stops the currently playing clip.
     */
    public void stop() {
        clip.stop();
    }
    
    /**
     * Plays the clip continously.
     */
    public void playLoop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}