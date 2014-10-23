package karataparty.metagame;

import karataparty.AbstractMinigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Board {

    private List<Player> players;
    private int size;
    private Random random;

    private int autoRoll;
    private int autoRollRate;
    private int currentPlayer;
    private int diceVal;

    private int krystal;
    private int krystalPrice;

    private int tickSpeed;
    private int tickCount;

    private final int nRounds;
    private int currentRound;

    private AbstractMinigame nextMiniGame;


    public Board(int nPlayers) {
        this.nRounds = 15;
        this.currentRound = 0;
        this.size = 50;
        this.random = new Random();
        players = new ArrayList<>();
        addPlayers(nPlayers);

        this.random = new Random();
        //this.roll = false;
        this.autoRollRate = 30;
        this.autoRoll = autoRollRate;
        this.currentPlayer = 0;
        this.diceVal = 0;
        newKrystal();

        this.krystalPrice = 50;
        this.tickSpeed = 8;
        this.tickCount = tickSpeed;
    }

    private void addPlayers(int n){
        for (int i = 0; i < n; i++) {
            players.add(new Player(i));
        }
    }

    public void setNextMiniGame(AbstractMinigame miniGame){
        nextMiniGame = miniGame;
    }
    public AbstractMinigame getNextMiniGame(){ return nextMiniGame; }

    public List<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return size;
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

    public int getnRounds(){ return nRounds; }

    public int getCurrentRound(){ return currentRound; }

    public void addRound(){ currentRound++;}

    private void newKrystal(){
        krystal = random.nextInt(size);
    }

    public void newRound(int nWinner){
        for (Player player : players) {
            if(player.getNumber() == nWinner){ player.addKoins(10);break;}
        }
        currentPlayer = 0;
    }

    private void tryBuyKrystal(){
        if(getCurrentPlayer().getSteps()%size == krystal){
            if(getCurrentPlayer().getKoins() >= krystalPrice){
                getCurrentPlayer().addKrystal();
                getCurrentPlayer().removeKoins(krystalPrice);
                newKrystal();
            }
        }
    }

    private boolean roundDone(){
        return currentPlayer == players.size();
    }

    private void preRoll(){
        diceVal = random.nextInt(12) + 1;
        autoRoll--;
    }

    private void moveTick(){
        getCurrentPlayer().playerMove();
        diceVal--;
        tryBuyKrystal();
    }

    private void postRoll(){
        currentPlayer++;
        autoRoll = autoRollRate;
        //roll = false;
    }


    public boolean tick(PaintComponent component){
        if (autoRoll != 0) {
            preRoll();
            component.boardChanged();
        } else {
            if (tickCount == 0){
                tickCount = tickSpeed;
                moveTick();

                component.boardChanged();
                if (diceVal <= 0) {
                    postRoll();
                    if (roundDone()) {
                        return true;
                    }
                }
            }
            tickCount--;
        }
        return false;
    }

}
