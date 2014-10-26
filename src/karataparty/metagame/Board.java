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
    private static final int SIZE = 50;
    private Random random;

    private int autoRoll;
    private static final int AUTO_ROLL_RATE = 30;
    private int currentPlayer;
    private int diceVal;
    private static final int DICE_MAX = 12;

    private int krystal;
    private static final int KRYSTAL_PRICE = 50;

    private static final int TICK_SPEED = 8;
    private int tickCount;

    private static final int N_ROUNDS = 15;
    private int currentRound;

    private AbstractMinigame nextMiniGame = null;


    public Board(int nPlayers) {
        this.currentRound = 0;
        this.random = new Random();
        players = new ArrayList<>();
        addPlayers(nPlayers);

        this.random = new Random();
        this.autoRoll = AUTO_ROLL_RATE;
        this.currentPlayer = 0;
        this.diceVal = 0;
        newKrystal();

        this.tickCount = TICK_SPEED;
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
        return SIZE;
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

    public int getnRounds(){ return N_ROUNDS; }

    public int getCurrentRound(){ return currentRound; }

    public void addRound(){ currentRound++;}

    private void newKrystal(){
        krystal = random.nextInt(SIZE);
    }

    public void newRound(int nWinner){
        for (Player player : players) {
            if(player.getNumber() == nWinner){ player.addKoins(10);break;}
        }
        currentPlayer = 0;
    }

    private void tryBuyKrystal(){
        if(getCurrentPlayer().getSteps()% SIZE == krystal){
            if(getCurrentPlayer().getKoins() >= KRYSTAL_PRICE){
                getCurrentPlayer().addKrystal();
                getCurrentPlayer().removeKoins(KRYSTAL_PRICE);
                newKrystal();
            }
        }
    }

    private boolean roundDone(){
        return currentPlayer == players.size();
    }

    private void preRoll(){
        diceVal = random.nextInt(DICE_MAX) + 1;
        autoRoll--;
    }

    private void moveTick(){
        getCurrentPlayer().playerMove();
        diceVal--;
        tryBuyKrystal();
    }

    private void postRoll(){
        currentPlayer++;
        autoRoll = AUTO_ROLL_RATE;
        //roll = false;
    }


    public boolean tick(PaintComponent component){
        if (autoRoll != 0) {
            preRoll();
            component.boardChanged();
        } else {
            if (tickCount == 0){
                tickCount = TICK_SPEED;
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
