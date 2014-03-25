package metagame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class Board {
    private ArrayList<Player> players;
    private int size;
    private Random random;

    public Board() {
        players = new ArrayList<>();
        this.players.add(new Player("Tormen", Color.ORANGE, 0, 0));
        this.players.add(new Player("LedigaLudvig", Color.GREEN, 1, 0));
        this.size = 12;
        this.random = new Random();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getSize() {
        return size;
    }

}
