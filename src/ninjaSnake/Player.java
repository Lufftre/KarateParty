package ninjaSnake;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class Player {
    private int playerNumber;
    private int x,y;
    private double angle;
    private boolean alive;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.x = 50;
        this.y = 100;
        this.angle = 0;
        this.alive = true;
    }

    public int getPlayerNumber() {
        return playerNumber;
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

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
