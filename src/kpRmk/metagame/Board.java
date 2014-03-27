package kpRmk.metagame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Board {

    private ArrayList<Player> players;
    private int size;
    private Random random;

    private boolean roll;
    private int currentPlayer;
    private int diceVal;

    public Board(int nPlayers) {
        this.size = 12;
        this.random = new Random();
        players = new ArrayList<>();
        addPlayers(nPlayers);

        this.random = new Random();
        this.roll = false;
        this.currentPlayer = 0;
        this.diceVal = 0;


    }

    private void addPlayers(int n){
        for (int i = 0; i < n; i++) {
            players.add(new Player(i));
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return size;
    }

    public int getCurrentPlayerNumber() {
        return currentPlayer;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public int getDiceVal() {
        return diceVal;
    }

    public void spacePress(){
        roll = true;
    }

    public boolean tick(PaintComponent component){
        if(!roll){
            diceVal = random.nextInt(12);
            component.boardChanged();
        } else {
            getCurrentPlayer().playerMove();
            diceVal--;
            component.boardChanged();

            if(diceVal <= 0){
                currentPlayer++;
                roll = false;
                if(currentPlayer == getPlayers().size()) return true;
            }
        }
        return false;

    }
}
