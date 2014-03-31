package kpRmk.sumoBird;

import kpRmk.AbstractPlayer;

/**
 * Created by the karatekidz on 29/03/14.
 */
public class Player extends AbstractPlayer {

    private int y;
    private int width,height;
    private int speed;
    private int maxSpeed;
    private boolean alive;

    public Player(int playerNumber){
        this.number = playerNumber;
        this.y = 0;
        this.width = 25;
        this.height = 25;
        this.speed = 0;
        this.alive = true;
        this.maxSpeed = 40;
    }

    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getSpeed() {
        return speed;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void moveY(int speed){
        this.y += speed;
    }
    public void changeSpeed(int speed){
        this.speed += speed;
        if(this.speed>maxSpeed)this.speed=maxSpeed;
    }
    public void flap(){
        if(alive){
            if(speed>0)speed=0;
            changeSpeed(-10);
        }
    }
}
