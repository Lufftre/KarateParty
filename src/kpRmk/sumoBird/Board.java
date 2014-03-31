package kpRmk.sumoBird;

import kpRmk.AbstractComponent;
import kpRmk.AbstractMinigame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by the karatekidz on 29/03/14.
 */
public class Board extends AbstractMinigame {

    private ArrayList<Player> players;
    private ArrayList<Pipe> pipes;
    private Random random;
    private int pipeRate;
    private int pipeCounter;
    private int width,height;
    private int pipeSpeed;
    private int fallSpeed;
    private int winner;
    private Player player1,player2,player3,player4;

    public Board(){
        this.random =  new Random();
        this.players = new ArrayList<>();
        this.player1 = new Player(0);
        this.player2 = new Player(1);
        this.players.add(player1);
        this.players.add(player2);
        this.pipes = new ArrayList<>();
        this.pipeRate = 80;
        this.pipeCounter = 0;
        this.width = 800;
        this.height = 800;
        this.pipeSpeed = 5;
        this.fallSpeed = 1;
        this.winner = -1;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    };
    public ArrayList<Pipe> getPipes(){
        return pipes;
    };

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void upPress(){
        player1.flap();
    }
    public void wPress(){
        player2.flap();
    }

    public void upRelease(){}
    public void wRelease(){}

    public int getWinner() {
        return winner;
    }

    private boolean checkLanded(Player player){
        if(player.getY() > 800-player.getHeight())return true;
        return false;
    }

    private void ceiling(Player player){
        if(player.getY()<0){
            player.setY(0);
            player.setSpeed(0);
        }
    }

    private boolean checkCrash(Player player){
        if(!check_x(player) && !check_y(player)){
            return true;
        }

        return false;
    }
    private boolean check_x(Player player){
        Pipe pipe = pipes.get(0);
        if(width - pipe.getX() > 100 + player.getWidth() ||
           width - (pipe.getX() - pipe.getWidth()) < 100){

            return true;
        }
        return false;
    }

    private boolean check_y(Player player){
        Pipe pipe = pipes.get(0);
        if(pipe.getGapY() < player.getY() &&
           pipe.getGapY() + pipe.getGapHeight() > player.getY()+player.getHeight()){

            return true;
        }
        return false;
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

    public int tick(AbstractComponent component) {
        if(winner != -1){
            return winner;
        }
        pipeTick();
        Collections.shuffle(players);
        for (Player player : players) {
            playerTick(player);
            component.boardChanged();
        }
        return -1;
    }

    private void playerTick(Player player){
        if(!checkLanded(player)){
            player.moveY(player.getSpeed());
            player.changeSpeed(fallSpeed);
        }
        if(player.isAlive()){
            ceiling(player);
            if(checkCrash(player)){
                player.setAlive(false);
                checkWinner();
            }
        }
        if(checkLanded(player)){
            player.setAlive(false);
            player.setSpeed(0);
            player.setY(height-player.getHeight());
            checkWinner();
        };

    }

    private void pipeTick(){
        if(pipeCounter==0){
            pipes.add(new Pipe(random.nextInt(600)+100));
            pipeCounter = pipeRate;
        }
        for (Iterator<Pipe> it = pipes.iterator(); it.hasNext(); ) {
            Pipe pipe = it.next();
            pipe.moveX(pipeSpeed);
            if (pipe.getX()-pipe.getWidth() > width) {
                it.remove();
            }
        }
        pipeCounter--;

    }

    public void resetBoard(){
        for (Player player : players) {
            player.setY(0);
            player.setSpeed(0);
            player.setAlive(true);
        }
        this.winner = -1;
        this.pipeCounter = 0;
        this.pipes = new ArrayList<>();
    }

}
