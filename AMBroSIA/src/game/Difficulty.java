package game;

import gui.MenuGUI;
import java.util.Random;

/**
 *
 * @author Nikolaos Bukas, Anthony Chin
 */
public class Difficulty {
    private static int level;
    private static Random rand = new Random();
    
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    public static float randomHeading()
    {
        return randomFloat() * 360;
    }
    
    public static float randomAsteroidVelocity()
    {   
        int holder = rand.nextInt(10);
        while(holder == 0){
            holder = rand.nextInt(10);
        }
        return randomFloat() * holder + 0.03f; // Minimum: 0.02 , Maximum: 11
    }
    
    public static float randomAsteroidHeading(){   
        return 2 + rand.nextInt(2) + randomFloat(); // Minumum: 1.02, Maximum: 5
    }
    
    public static float randomAlienVelocity()
    {
        return randomFloat() * rand.nextInt(10);
    }
    
    public static int randomXPos()
    {
        return rand.nextInt(MenuGUI.WIDTH);
    }
    
    public static int randomYPos()
    {
        return rand.nextInt(MenuGUI.HEIGHT);
    }
    
    public String bonusDropRate()
    {
        return null;
    }
    
    public static float randExplosionVelocity()
    {
        return (rand.nextInt(5) + 1)*(rand.nextFloat() - rand.nextFloat());
    }
    
    private static float randomFloat()
    {
        float holder = rand.nextFloat();
        while (holder < 0.01){
            holder = rand.nextFloat();
        }
        return holder * 2 - 1;
    }
}
