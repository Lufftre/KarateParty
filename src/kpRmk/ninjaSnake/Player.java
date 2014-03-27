package kpRmk.ninjaSnake;

import kpRmk.AbstractPlayer;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Player extends AbstractPlayer{
    private int x,y;
    private double angle;
    private boolean alive;
    private boolean left, right;
    private double rotateSpeed;

    public Player(int playerNumber) {
        this.number = playerNumber;
        this.x = 50;
        this.y = 100 + 50*number;
        this.angle = 0;
        this.alive = true;
	this.left = false;
	this.right = false;
	this.rotateSpeed = Math.PI/15;
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

    public boolean isLeft() {
	return left;
    }

    public boolean isRight() {
	return right;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void moveX(int x){
        this.x += x;
    }

    public void moveY(int y){
        this.y += y;
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
