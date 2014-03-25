package ninjaSnake;

import metagame.MiniGame;

import java.util.ArrayList;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class ninjaSnake implements MiniGame {

    private int height,width;
    private boolean[][] map;
    private ArrayList<Player> players;

    public ninjaSnake() {
        this.height = 100;
        this.width = 100;
        this.map = new boolean[this.height][this.width];
        this.players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
    }





    @Override
    public void tick() {
        for (Player player : players) {
            playerTick(player);
        }
    }

    private void playerTick(Player player){
        player.setX((int)(Math.cos(player.getAngle()*5)));
        player.setY((int)(Math.sin(player.getAngle()*5)));
    }
}
