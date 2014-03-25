package metagame;

import java.awt.*;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class Player {

    private String name;
    private int steps;
    private int koins;
    private int krystals;
    private Color kolor;
    private int playerNumber;


    public Player(String name, Color kolor, int playerNumber, int cheat){
        this.name = name;
        this.steps = 0;
        this.koins = 0;
        this.krystals = 0;
        this.kolor = kolor;
        this.playerNumber = playerNumber;

        this.steps = cheat;
    }

    public String getName() {
        return name;
    }

    public int getSteps() {
        return steps;
    }

    public int getKoins() {
        return koins;
    }

    public int getKrystals() {
        return krystals;
    }

    public Color getKolor() {
        return kolor;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setKoins(int koins) {
        this.koins = koins;
    }

    public void setKrystals(int krystals) {
        this.krystals = krystals;
    }

    public void playerMove(){
        this.steps++;
    }
}
