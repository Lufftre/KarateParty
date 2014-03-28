package kpRmk.ninjaSnake;

import kpRmk.AbstractComponent;
import kpRmk.AbstractMinigame;
import kpRmk.Minigame;
import kpRmk.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Board extends AbstractMinigame
{

    private int height,width;
    private boolean[][] map;
    private ArrayList<Player> players;
    private int winner;

    public Board() {
        this.height = 1000;
        this.width = 1000;
        this.map = new boolean[this.height][this.width];
        this.players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
        this.winner = -1;

    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean[][] getMap() {
	return map;
    }

    public boolean getMapPoint(Position position){
        if((int)position.getX()>=0 && (int)position.getX()<width &&
           (int)position.getY()>=0 && (int)position.getY()<height){
	        return map[(int)position.getX()][(int)position.getY()];
        }
        return true;
    }

    public void leftPress(){
	players.get(0).setLeft(true);
    }
    public void leftRelease(){
	players.get(0).setLeft(false);
    }
    public void rightPress(){
	players.get(0).setRight(true);
    }
    public void rightRelease(){
	players.get(0).setRight(false);
    }

    public void aPress(){
	players.get(1).setLeft(true);
    }
    public void aRelease(){
	players.get(1).setLeft(false);
    }
    public void dPress(){
	players.get(1).setRight(true);
    }
    public void dRelease(){
	players.get(1).setRight(false);
    }

    private void setMapPoint(int x, int y){
	if(x>=0 && x<width && y>=0 && y<height){
		map[x][y] = true;
	}
    }

    private boolean checkCollide(Player player){
        //int x = (int)(player.getX() + xOffset);
        //int y = (int)(player.getY() + yOffset);
        double x = player.getX();
        double y = player.getY();

        if(x>=0 && x<width && y>=0 && y<height){
            //return getMapPoint(x,y);
            for (Position position : getCollidePoints(x, y, player.getAngle())) {
                if(getMapPoint(position)) return true;
            }
            return false;
	    }
	    return true;
    }

    private ArrayList<Position> getCollidePoints(double x, double y, double angle){
        ArrayList<Position> positions = new ArrayList<>();
        double a = Math.PI/39;
        for (int i = -20; i < 20; i++) {
            double xTemp = (Math.cos((a*i) + angle)*10) + x;
            double yTemp = (Math.sin((a * i) + angle)*10) + y;
            positions.add(new Position(xTemp,yTemp));
        }
        return positions;
    }

    private void killPlayer(Player player){
	player.setAlive(false);
    }
    private void checkWinner(){
        int n = 0;
        int winnerTemp = -1;
        for (Player player : players) {
            if(player.isAlive()){
                n++;
                winnerTemp = player.getNumber();
            }
        }
        if(n==1) winner = winnerTemp;
    }

    @Override
    public int tick(AbstractComponent component) {
	for (Player player : players) {
            playerTick(player);
        }
        component.boardChanged();

        return winner;
    }

    private void playerTick(Player player){
	if(player.isAlive()){
	    //Rotate Snake
	    if(player.isLeft()) player.rotateLeft();
	    if(player.isRight()) player.rotateRight();

	    //Leave trail
	    setMapPoint((int)player.getX(),(int)player.getY());

	    double x = (Math.cos(player.getAngle())*5);
	    double y = (Math.sin(player.getAngle())*5);
	    //Check collision
	    if(checkCollide(player)){
		    killPlayer(player);
            checkWinner();
	    } //else {
		//Move Snake
		player.moveX(x);
		player.moveY(y);
	    //}
	}

    }
}
