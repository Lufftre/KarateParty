/**
 * Player Class for NinjaSnake
 *
 */
package karataparty.judojuking;

import karataparty.AbstractPlayer;
import karataparty.Position;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Player extends AbstractPlayer{
    private Position position;
    private boolean alive;
    private int size;
    private boolean left, right, up, down;
    private double speed;
    private double speedX, speedY;
    private double friction;

    public Player(int playerNumber) {
        this.number = playerNumber;
        this.position = new Position(0,0);
        this.alive = true;
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.speed = 2;
        this.speedX = 0;
        this.speedY = 0;
        this.friction = 1.2;
        this.size = 20;
    }



    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isLeft() {
	return left;
    }

    public boolean isRight() {
	return right;
    }

    public int getSize() {
        return size;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getFriction() {
        return friction;
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

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setFriction(int friction) {
        this.friction = friction;
    }

    public void moveX(double x){
        this.setX(this.position.getX() + x);
    }
    public void moveY(double y){
        this.setY(this.position.getY() + y);
    }

    public void addSpeedX(double x){this.setSpeedX(this.speedX + x);}
    public void addSpeedY(double y){this.setSpeedY(this.speedY + y);}

    public void invertXspeed(){speedX*=-1;}
    public void invertYspeed(){speedY*=-1;}

    public void tick(){
        if(this.left){speedX-=speed;}
        if(this.right){speedX+=speed;}
        if(this.up){speedY-=speed;}
        if(this.down){speedY+=speed;}
        moveX(speedX);
        moveY(speedY);

        speedX /= friction;
        speedY /= friction;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
