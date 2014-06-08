package karataparty.ninjasnake;

import karataparty.AbstractComponent;
import karataparty.AbstractMinigame;
import karataparty.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Board extends AbstractMinigame
{

    private int height,width;
    private boolean[][] map;
    private int winner;
    private Random random;
    private ArrayList<Player> players;
    private Player player1,player2,player3,player4;


    public Board() {
        this.random = new Random();
        this.height = 790;
        this.width = 790;

        this.map = new boolean[this.height][this.width];
        this.players = new ArrayList<>();
        this.player1 = new Player(0);
        this.player2 = new Player(1);
        players.add(player1);
        players.add(player2);
        resetBoard();

    }
    private int randomPosX(){
        return random.nextInt(width -400)+200;
    }
    private int randomPosY(){
        return random.nextInt(height -400)+200;
    }
    public void resetBoard(){
        for (Player player : players) {
            player.setX(randomPosX());
            player.setY(randomPosY());
            player.setAlive(true);
            player.setLeft(false);
            player.setRight(false);
        }
        this.winner = -1;
        this.map = new boolean[this.height][this.width];
        createOutside();
    }

    public int getHeight() {
	return height;
    }

    public int getWidth() {
	return width;
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

    public ArrayList<Player> getPlayers(){
        return players;
    };

    public void leftPress(){
	    player1.setLeft(true);
    }
    public void leftRelease(){
        player1.setLeft(false);
    }
    public void rightPress(){
        player1.setRight(true);
    }
    public void rightRelease(){
        player1.setRight(false);
    }

    public void aPress(){
	    player2.setLeft(true);
    }
    public void aRelease(){
	    player2.setLeft(false);
    }
    public void dPress(){
	    player2.setRight(true);
    }
    public void dRelease(){
	    player2.setRight(false);
    }

    private void setMapPoint(int x, int y){
	if(x>=0 && x<width && y>=0 && y<height){
		map[x][y] = true;
	}
    }

    //Creates outside border
    private void createOutside(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y += height-1) {
                setMapPoint(x,y);
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x+=width-1) {
                setMapPoint(x,y);
            }
        }
    }

    private boolean checkCollide(Player player){
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
            double xTemp = (Math.cos((a*i) + angle)*6) + x;
            double yTemp = (Math.sin((a * i) + angle)*6) + y;
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
        if(n==1) {
            winner = winnerTemp;
        }
    }

    @Override
    public int tick(AbstractComponent component) {
        if(winner != -1){
            return winner;
        }
        Collections.shuffle(players);
        for (Player player : players) {
            playerTick(player);
        }
        component.boardChanged();
        return -1;
    }

    private void playerTick(Player player){
        if(player.isAlive()){
            //Rotate Snake
            if(player.isLeft()) player.rotateLeft();
            if(player.isRight()) player.rotateRight();

            //Leave trail
            if(player.getGapCounter() < 0){
                player.setGapCounter(100);
            } else if(player.getGapCounter() < player.getGapLength()){
                player.gapCounterTick();
            } else {
                setMapPoint((int)player.getX(),(int)player.getY());
                player.gapCounterTick();
            }


            double x = (Math.cos(player.getAngle())*player.getSpeed());
            double y = (Math.sin(player.getAngle())*player.getSpeed());
            //Check collision
            if(checkCollide(player)){
                killPlayer(player);
                checkWinner();
            }
            player.moveX(x);
            player.moveY(y);
        }
    }
}
