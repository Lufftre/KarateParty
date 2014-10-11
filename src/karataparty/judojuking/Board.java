package karataparty.judojuking;

import karataparty.AbstractComponent;
import karataparty.AbstractMinigame;
import karataparty.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by the karatekidz on 26/03/14.
 */

public class Board extends AbstractMinigame
{

    private int height,width;
    private int winner;
    private Random random;
    private List<Player> players;
    private List<Enemy> enemies;
    private Player player1,player2;
    private final int spawnDelay;
    private int spawnCounter;


    private Board() {
        this.random = new Random();
        this.height = 790;
        this.width = 790;

        this.players = new ArrayList<>();
        this.player1 = new Player(0);
        this.player2 = new Player(1);
        players.add(player1);
        players.add(player2);

        this.enemies = new ArrayList<>();
        enemies.add(new Enemy(new Position(0,0)));
        this.spawnDelay = 500;
        this.spawnCounter = 100;
        resetBoard();

    }

    public static Board getBoardObject(){
        if(ref == null)
            ref = new Board();
        return ref;
    }
    private static Board ref;

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
            player.setUp(false);
            player.setDown(false);
        }
        this.enemies = new ArrayList<>();
        this.spawnCounter = 100;
        this.winner = -1;
    }

    public int getHeight() {
	return height;
    }
    public int getWidth() {
	return width;
    }


    public Iterable<Player> getPlayers(){
        return players;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

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
    public void upPress(){
	    player1.setUp(true);
    }
    public void upRelease(){
        player1.setUp(false);
    }
    public void downPress(){
        player1.setDown(true);
    }
    public void downRelease(){
        player1.setDown(false);
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
    public void wPress(){
	    player2.setUp(true);
    }
    public void wRelease(){
        player2.setUp(false);
    }
    public void sPress(){
        player2.setDown(true);
    }
    public void sRelease(){
        player2.setDown(false);
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
        spawnCounter--;
        if(spawnCounter == 0){
            spawnCounter = spawnDelay;
            enemies.add(new Enemy(new Position(0,0)));
        }
        Collections.shuffle(players);
        for (Player player : players) {
            playerTick(player);
        }
        for (Enemy enemy : enemies) {
            enemy.tick(players);
            checkWinner();
        }
        component.boardChanged();
        return -1;
    }

    private void playerTick(Player player){
        if(player.isAlive()){
            checkOB(player);
            player.tick();
            checkPlayerCollide(player);

        }
    }

    private void checkOB(Player player){
        if(player.getX()<0){
            player.invertXspeed();
            player.setX(0);
        }else if(player.getX()>width-10){
            player.invertXspeed();
            player.setX(width-10);
        }

        if(player.getY()<0){
            player.invertYspeed();
            player.setY(0);
        }
        if(player.getY()>height-10){
            player.invertYspeed();
            player.setY(height-10);
        }
    }

    private void checkPlayerCollide(Player player){
        for (Player p : players) {
            if(p.getNumber()!=player.getNumber()){
                if(p.getX()>player.getX()-p.getSize() && p.getX()<player.getX()+player.getSize()){
                    if(p.getY()>player.getY()-p.getSize() && p.getY()<player.getY()+player.getSize()){
                        p.addSpeedX(player.getSpeedX()*0.95);
                        p.addSpeedY(player.getSpeedY()*0.95);
                        player.setSpeedX(player.getSpeedX()*0.25);
                        player.setSpeedY(player.getSpeedY()*0.25);

                    }
                }
            }
        }
    }
    public String toString(){
        return "Judo Juking";
    }
}
