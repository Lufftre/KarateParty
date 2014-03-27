package kpRmk.ninjaSnake;

import kpRmk.AbstractPlayer;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Player extends AbstractPlayer{
    private int x,y;
    private double angle;
    private boolean alive;

    public Player(int playerNumber) {
        this.number = playerNumber;
        this.x = 50;
        this.y = 100;
        this.angle = 0;
        this.alive = true;
    }

    public int getNumber() {
        return number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveX(int x){
        this.x += x;
    }

    public void moveY(int y){
        this.y += y;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
