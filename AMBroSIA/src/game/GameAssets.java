/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 *
 * @author Nikolaos Bukas
 */
public class GameAssets {

    public static Sound victory;
    public static Sound gameOver;
    public static Sound noBombs;
    public static Sound bombUsed;
    public static Sound shields3;
    public static Sound shields2;
    public static Sound shields1;
    public static Sound shields0;
    public static Sound crash;
    public static Sound explosion;
    public static Sound playerFire;
    public static Sound alienFire;
    public static Sound theme;
    public static Sound missile;
    public static Sound alienSaucer;
    public static Sound alienDetected;
    public static Sound thrusters;
    public static Sound warp;
    
    public static Image spaceBackground;
    public static Image menuImage;
    public static Image tutorialImage;
    public static Image gameOverImage;

    public static void loadSounds() {
        try {
            victory = new Sound("GoalsComplete.wav");
            gameOver = new Sound("MissionFailed.wav");
            noBombs = new Sound("NoTorpedos.wav");
            bombUsed = new Sound("TorpedoFired.wav");
            shields3 = new Sound("Shields100.wav");
            shields2 = new Sound("Shields75.wav");
            shields1 = new Sound("Shields25.wav");
            shields0 = new Sound("ShieldsFailed.wav");
            crash = new Sound("crash.wav");
            explosion = new Sound("explosion.wav");
            playerFire = new Sound("Cardassian Cannon.wav");
            alienFire = new Sound("fire.wav");
            theme = new Sound("menu.wav");
            missile = new Sound("missle.wav");
            alienSaucer = new Sound("saucer.wav");
            alienDetected = new Sound("Incoming1.wav");
            thrusters = new Sound("engine1.wav");
            warp = new Sound("enter warp.wav");
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(GameAssets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameAssets.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(GameAssets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadImages() {
        spaceBackground = new ImageIcon(GameAssets.class.getResource("images/spaceBackground.jpg")).getImage();
        tutorialImage = new ImageIcon(GameAssets.class.getResource("images/keyboard.jpg")).getImage();
        gameOverImage = new ImageIcon(GameAssets.class.getResource("images/GameOver.jpg")).getImage();
        menuImage = new ImageIcon(GameAssets.class.getResource("images/asteroids.jpg")).getImage();
    }
}
