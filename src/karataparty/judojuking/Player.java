/**
 * Player Class for NinjaSnake
 *
 */
package karataparty.judojuking;

import karataparty.Position;
import karataparty.SuperPlayer;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Player extends SuperPlayer {
    private Position position;
    private boolean alive;
    private static final int SIZE = 20;
    private boolean left, right, up, down;
    private static final double SPEED = 2;
    private double speedX, speedY;
    private static final double FRICTION = 1.2;

    public Player(int playerNumber) {
        setNumber(playerNumber);
        this.position = new Position(0,0);
        this.alive = true;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.speedX = 0;
        this.speedY = 0;
    }



    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public boolean isAlive() {
        return alive;
    }

    public int getSize() {
        return SIZE;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setX(double x) {
        this.position.setX(x);
    }

    public void setY(double y) {
        this.position.setY(y);
    }

    public void setLeft(final boolean left) {
	this.left = left;
    }

    public void setRight(final boolean right) {
	this.right = right;
    }

    public void setUp(final boolean up) {
        this.up = up;
    }

    public void setDown(final boolean down) {
        this.down = down;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void moveX(double x){
        this.setX(this.position.getX() + x);
    }
    public void moveY(double y){
        this.setY(this.position.getY() + y);
    }

    public void addSpeedX(double x){
        this.speedX += x;}
    public void addSpeedY(double y){
        this.speedY += y;}

    public void invertXspeed(){speedX*=-1;}
    public void invertYspeed(){speedY*=-1;}

    public void tick(){
        if(this.left){speedX-= SPEED;}
        if(this.right){speedX+= SPEED;}
        if(this.up){speedY-= SPEED;}
        if(this.down){speedY+= SPEED;}
        moveX(speedX);
        moveY(speedY);

        speedX /= FRICTION;
        speedY /= FRICTION;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
