package karataparty.metagame;

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
        this.name = "Player" + playerNumber;

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

    public void addKoins(int n){
        this.koins += n;
    }

    public void removeKoins(int n){
        this.koins -= n;
    }

    public void addKrystal(){
        this.krystals++;
    }

    public void playerMove(){
        this.steps++;
    }

}
