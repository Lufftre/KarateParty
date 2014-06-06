package karataparty.judojuking;

import karataparty.Position;

import java.util.List;

/**
 * Created by Ludvig on 2014-06-06.
 */
public class Enemy {
    private Position position;
    private boolean alive;
    private int size;
    private double speed;
    private double speedX, speedY;
    private double friction;
    private int target;
    private double targetAngle;

    public Enemy(Position position) {
        this.position = position;
        this.alive = true;
        this.speed = 1.5;
        this.speedX = 0;
        this.speedY = 0;
        this.friction = 1.04;
        this.target = 0;
        this.targetAngle = 0;
        this.size = 20;
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
        return size;
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
        double dist = -1;
        for (Player player : players) {
            if(player.getX()>getX()-player.getSize() && player.getX()<getX()+getSize()){
            if(player.getY()>getY()-player.getSize() && player.getY()<getY()+getSize()){
                player.setAlive(false);
                }
            }
            if(player.isAlive()) {
                if (getDistance(player) < dist || dist == -1) {
                    dist = getDistance(player);
                    target = player.getNumber();
                    targetAngle = getTargetAngle(player);
                }
            }
        }

        speedX += (Math.cos(targetAngle)*speed);
        speedY += (Math.sin(targetAngle)*speed);

        moveX(speedX);
        moveY(speedY);

        speedX /= friction;
        speedY /= friction;

    }
}
