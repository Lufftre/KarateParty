package karataparty.metagame;

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

    // --Commented out by Inspection (2014-05-16 13:55):private boolean roll;
    private int autoRoll;
    private int autoRollRate;
    private int currentPlayer;
    private int diceVal;

    private int krystal;
    private int krystalPrice;

    private int tickSpeed;
    private int tickCount;


    public Board(int nPlayers) {
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

    public List<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return size;
    }

// --Commented out by Inspection START (2014-05-16 13:56):
//    public int getCurrentPlayerNumber() {
//        return currentPlayer;
//    }
// --Commented out by Inspection STOP (2014-05-16 13:56)

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    public int getDiceVal() {
        return diceVal;
    }

    public int getKrystal() {
        return krystal;
    }

    /*public void spacePress(){
        roll = true;
    }
    */

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

    private boolean isWinner(){
        if(getCurrentPlayer().getKrystals()>=5){
            return true;
        }
        return false;
    }

    private void winning(){
        for (Player player : players) {
            player.reset();
        }
        autoRoll = autoRollRate;
        this.currentPlayer = 0;
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
                if (isWinner()) {
                    winning();
                }
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
