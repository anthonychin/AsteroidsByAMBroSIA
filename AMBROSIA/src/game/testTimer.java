/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gui.MenuGUI;

/**
 *
 * @author Michael
 */
public class testTimer implements Runnable{

    GraphicsEngine graphics;
    Physics physicsEngine;
    MenuGUI graphicalUserInterface;
    Runnable collide;
    timeToLive time;
    @Override
    public void run() {
        physicsEngine.run();
        graphics.run();
        graphicalUserInterface.run();
        collide.run();
        time.run();
    }
    
    public testTimer(GraphicsEngine ge, Physics pe, MenuGUI gui, Runnable collision, timeToLive ttl)
    {
        graphics = ge;
        physicsEngine = pe;
        graphicalUserInterface = gui;
        collide = collision;
        time = ttl;
    }
    
}
