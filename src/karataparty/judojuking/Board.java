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

    private static final int HEIGHT = 790;
    private static final int WIDTH = 790;
    private int winner;
    private Random random;
    private List<Player> players;
    private List<Enemy> enemies;
    private Player player1,player2;
    private static final int SPAWN_DELAY = 500;
    private static final int FIRST_SPAWN_DELAY = 100;
    private int spawnCounter;
    private static final double BUMP_PUSH = 0.95;
    private static final double BUMP_SLOW = 0.25;


    private Board() {
        this.random = new Random();

        this.players = new ArrayList<>();
        this.player1 = new Player(0);
        this.player2 = new Player(1);
        players.add(player1);
        players.add(player2);

        this.enemies = new ArrayList<>();
        enemies.add(new Enemy(new Position(0,0)));
        this.spawnCounter = FIRST_SPAWN_DELAY;
        resetBoard();

    }

    private static final class BoardHolder {
        private static final Board SINGLETON_REF = new Board();
    }

    public static Board getBoardObject(){
        return BoardHolder.SINGLETON_REF;
    }

    private int randomPosX(){
        return random.nextInt(WIDTH /2)+ WIDTH /4;
    }
    private int randomPosY(){
        return random.nextInt(HEIGHT /2)+ HEIGHT /4;
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
        this.spawnCounter = FIRST_SPAWN_DELAY;
        this.winner = -1;
    }

    public int getHeight() {
	return HEIGHT;
    }
    public int getWidth() {
	return WIDTH;
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
            spawnCounter = SPAWN_DELAY;
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
        }else if(player.getX()> WIDTH -(player.getSize())){
            player.invertXspeed();
            player.setX(WIDTH -(player.getSize()));
        }

        if(player.getY()<0){
            player.invertYspeed();
            player.setY(0);
        }
        if(player.getY()> HEIGHT -(player.getSize())){
            player.invertYspeed();
            player.setY(HEIGHT -(player.getSize()));
        }
    }

    private void checkPlayerCollide(Player player){
        for (Player p : players) {
            if(p.getNumber()!=player.getNumber()){
                if(p.getX()>player.getX()-p.getSize() && p.getX()<player.getX()+player.getSize()){
                    if(p.getY()>player.getY()-p.getSize() && p.getY()<player.getY()+player.getSize()){
                        p.addSpeedX(player.getSpeedX()* BUMP_PUSH);
                        p.addSpeedY(player.getSpeedY()* BUMP_PUSH);
                        player.setSpeedX(player.getSpeedX()* BUMP_SLOW);
                        player.setSpeedY(player.getSpeedY()* BUMP_SLOW);

                    }
                }
            }
        }
    }
    public String toString(){
        return "Judo Juking";
    }
}
