package kpRmk.ninjaSnake;

import kpRmk.AbstractComponent;
import kpRmk.AbstractMinigame;
import kpRmk.Minigame;

import java.util.ArrayList;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Board extends AbstractMinigame
{

    private int height,width;
    private boolean[][] map;
    private ArrayList<Player> players;

    public Board() {
        this.height = 1000;
        this.width = 1000;
        this.map = new boolean[this.height][this.width];
        this.players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));

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

    public boolean getMapPoint(int x, int y){
	return map[x][y];
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

    private boolean checkCollide(int x, int y){
	if(x>=0 && x<width && y>=0 && y<height){
		return getMapPoint(x,y);
	}
	return true;
    }

    private void killPlayer(Player player){
	player.setAlive(false);
    }

    @Override
    public boolean tick(AbstractComponent component) {
	for (Player player : players) {
            playerTick(player);
        }
        component.boardChanged();

        return false;
    }

    private void playerTick(Player player){
	if(player.isAlive()){
	    //Rotate Snake
	    if(player.isLeft()) player.rotateLeft();
	    if(player.isRight()) player.rotateRight();

	    //Leave trail
	    setMapPoint(player.getX(),player.getY());

	    int x = (int)(Math.cos(player.getAngle())*5);
	    int y = (int)(Math.sin(player.getAngle())*5);
	    //Check collision
	    if(checkCollide(x + player.getX(),y + player.getY())){
		killPlayer(player);
	    } else {
		//Move Snake
		player.moveX(x);
		player.moveY(y);
	    }
	}

    }
}
