package karataparty.judojuking;

import karataparty.Position;

import java.util.List;

/**
 * Created by Ludvig on 2014-06-06.
 */
public class Enemy {
    private Position position;
    private static final int SIZE = 20;
    private static final double SPEED = 1.5;
    private double speedX, speedY;
    private static final double FRICTION = 1.04;
    private double targetAngle;

    public Enemy(Position position) {
        this.position = position;
        this.speedX = 0;
        this.speedY = 0;
        this.targetAngle = 0;
    }

    public double getX() {
        return position.getX();
    }
    public double getY() {
        return position.getY();
    }
    public void setX(double x) {
        this.position.setX(x);
    }
    public void setY(double y) {
        this.position.setY(y);
    }
    public int getSize() {
        return SIZE;
    }

    private double getDistance(Player player){
        return Math.sqrt(Math.pow(player.getX() - getX(),2) + Math.pow(player.getY() - getY(),2));
    }
    private double getTargetAngle(Player player){
        return Math.atan2(player.getY() - getY(), player.getX() - getX());
    }
    public void moveX(double x){
        this.setX(this.position.getX() + x);
    }
    public void moveY(double y){
        this.setY(this.position.getY() + y);
    }

    public void tick(List<Player> players){
        double dist = 0;
        for (Player player : players) {
            if(player.getX()>getX()-player.getSize() && player.getX()<getX()+ SIZE){
            if(player.getY()>getY()-player.getSize() && player.getY()<getY()+ SIZE){
                player.setAlive(false);
                }
            }
            if(player.isAlive()) {
                if (getDistance(player) < dist || dist == 0) {
                    dist = getDistance(player);
                    targetAngle = getTargetAngle(player);
                }
            }
        }

        speedX += (Math.cos(targetAngle)* SPEED);
        speedY += (Math.sin(targetAngle)* SPEED);

        moveX(speedX);
        moveY(speedY);

        speedX /= FRICTION;
        speedY /= FRICTION;

    }
}
