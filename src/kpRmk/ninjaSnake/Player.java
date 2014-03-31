package kpRmk.ninjaSnake;

import kpRmk.AbstractPlayer;
import kpRmk.Position;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Player extends AbstractPlayer{
    private Position position;
    private double angle;
    private boolean alive;
    private boolean left, right;
    private double rotateSpeed;
    private int gapLength;
    private int gapCounter;
    private int speed;

    public Player(int playerNumber) {
        this.number = playerNumber;
        this.position = new Position(0,0);
        this.angle = 0;
        this.alive = true;
        this.left = false;
        this.right = false;
        this.rotateSpeed = Math.PI/30;
        this.speed = 3;

        this.gapCounter = 0;
        this.gapLength = 4;

    }



    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public double getAngle() {
        return angle;
    }

    public int getGapLength() {
        return gapLength;
    }

    public int getGapCounter() {
        return gapCounter;
    }

    public int getSpeed() {
        return speed;
    }

    public void setGapCounter(int gapCounter) {
        this.gapCounter = gapCounter;
    }

    public void gapCounterTick(){
        gapCounter--;
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

    public void setX(double x) {
        this.position.setX(x);
    }

    public void setY(double y) {
        this.position.setY(y);
    }

    public void setAngle(double angle) {
	this.angle = angle;
    }

    public void setLeft(final boolean left) {
	this.left = left;
    }

    public void setRight(final boolean right) {
	this.right = right;
    }

    public void moveX(double x){
        this.setX(this.position.getX() + x);
    }

    public void moveY(double y){
        this.setY(this.position.getY() + y);
    }

    public void rotateLeft() {
        this.angle -= rotateSpeed;
        this.angle %= 2*Math.PI;
    }

    public void rotateRight() {
    	this.angle += rotateSpeed;
	    this.angle %= 2*Math.PI;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
