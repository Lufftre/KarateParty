package kpRmk.metagame;

import java.awt.*;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Player {
    private int steps;
    private int koins;
    private int krystals;
    private int number;
    private String name;


    public Player(int playerNumber){

        this.steps = 0;
        this.koins = 0;
        this.krystals = 0;
        this.number = playerNumber;
        this.name = "Player" + String.valueOf(playerNumber);

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

    public int getNumber() {
        return number;
    }

    public void setSteps(int n) {
        this.steps = n;
    }

    public void setKoins(int n) {
        this.koins = n;
    }

    public void setKrystals(int n) {
        this.krystals = n;
    }

    public void addKoins(int n){
        this.koins += n;
    }

    public void removeKoins(int n){
        this.koins -= n;
    }

    public void addKrystal(){
        this.krystals++;
    }

    public void removeKrystals(int n){
        this.krystals -= n;
    }
    public void playerMove(){
        this.steps++;
    }

    public void reset(){
        this.steps = 0;
        this.koins = 0;
        this.krystals = 0;
    }
}
