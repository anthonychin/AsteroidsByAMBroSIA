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
    @Override
    public void run() {
        physicsEngine.run();
        graphics.run();
        graphicalUserInterface.run();
    }
    
    public testTimer(GraphicsEngine ge, Physics pe, MenuGUI gui)
    {
        graphics = ge;
        physicsEngine = pe;
        graphicalUserInterface = gui;
    }
    
}
