package kpRmk.ninjaSnake;

import kpRmk.AbstractComponent;
import kpRmk.Minigame;

import java.util.ArrayList;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class Board implements Minigame {

    private int height,width;
    private boolean[][] map;
    private ArrayList<Player> players;

    public Board() {
        this.height = 100;
        this.width = 100;
        this.map = new boolean[this.height][this.width];
        this.players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));

    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean tick(AbstractComponent component) {
        for (Player player : players) {
            playerTick(player);
        }
        component.boardChanged();

        return false;
    }

    private void playerTick(Player player){
        player.moveX((int)(Math.cos(player.getAngle())*10));
        player.moveY((int)(Math.sin(player.getAngle())*10));

    }
}
