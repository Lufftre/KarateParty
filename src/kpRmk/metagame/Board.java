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
    private int autoRoll;
    private int autoRollRate;
    private int currentPlayer;
    private int diceVal;

    private int krystal;

    public Board(int nPlayers) {
        this.size = 50;
        this.random = new Random();
        players = new ArrayList<>();
        addPlayers(nPlayers);

        this.random = new Random();
        this.roll = false;
        this.autoRollRate = 30;
        this.autoRoll = autoRollRate;
        this.currentPlayer = 0;
        this.diceVal = 0;
        newKrystal();
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

    public int getKrystal() {
        return krystal;
    }

    public void spacePress(){
        roll = true;
    }

    private void newKrystal(){
        krystal = random.nextInt(getSize());
    }
    public void newRound(int nWinner){
        for (Player player : players) {
            if(player.getNumber() == nWinner){ player.addKoins(10);break;}
        }
        currentPlayer = 0;
    }

    public boolean tick(PaintComponent component){
        if(autoRoll!=0){
            diceVal = random.nextInt(12) + 1;
            component.boardChanged();
            autoRoll--;
        } else {
            getCurrentPlayer().playerMove();
            diceVal--;
            component.boardChanged();

            if(diceVal <= 0){
                //Try buy krystal
                if(getCurrentPlayer().getSteps()%size == krystal){
                    if(getCurrentPlayer().getKoins() >= 20){
                        getCurrentPlayer().addKrystal();
                        getCurrentPlayer().removeKoins(20);
                        newKrystal();
                        component.boardChanged();
                    }
                }
                currentPlayer++;
                autoRoll = autoRollRate;
                roll = false;
                if(currentPlayer == getPlayers().size()) return true;
            }
        }
        return false;
    }
}
