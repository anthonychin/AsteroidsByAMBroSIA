package game;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nikolaos
 */
import java.awt.Polygon;

public class MapObject {

    private float[] velocity;
    private int[] coordinates;
    private float heading;
    private float acceleration;
    private GameState gameState;
    private Polygon shape;

    public MapObject(float[] velocity, float heading, int[] coordinates, float acceleration, GameState gameState) {
        this.velocity = velocity;
        this.heading = heading;
        this.coordinates = coordinates;
        this.acceleration = acceleration;
        this.gameState = gameState;
        this.shape = new Polygon(new int[]{0}, new int[]{0}, 1);
    }

    public float[] getVelocity() {
        return this.velocity;
    }

    public void setVelocity(float[] velocity) {
        this.velocity = velocity;
    }

    public float getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getHeading() {
        return this.heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }

    public int[] getCoord() {
        return this.coordinates;
    }

    public void setCoord(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getX() {
        return coordinates[0];
    }

    public void setX(int x) {
        this.coordinates[0] = x;
    }

    public int getY() {
        return coordinates[1];
    }

    public void setY(int y) {
        this.coordinates[1] = y;
    }

    public Polygon getShape() {
        return this.shape;
    }

    public void setShape(Polygon shape) {
        this.shape = shape;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    //unimplemented.  All implementing classes should override.
    public void destroy() {
    }
}
