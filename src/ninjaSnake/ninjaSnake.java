package ninjaSnake;

import metagame.BoardListener;
import metagame.KarateKlass;
import metagame.MiniGame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class NinjaSnake implements MiniGame {

    private int height,width;
    private boolean[][] map;
    private ArrayList<Player> players;

    private List<BoardListener> listeners;

    public NinjaSnake() {
        this.height = 100;
        this.width = 100;
        this.map = new boolean[this.height][this.width];
        this.players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));

        listeners = new ArrayList<>();


    }





    @Override
    public boolean tick() {
        for (Player player : players) {
            playerTick(player);

        }
        return false;
    }

    private void playerTick(Player player){
        player.setX((int)(Math.cos(player.getAngle()*5)));
        player.setY((int)(Math.sin(player.getAngle()*5)));
        notifyListeners();

    }

    public void addBoardListener(BoardListener bl){
        listeners.add(bl);
    }

    private void notifyListeners(){
        if (listeners != null){ //Safety first.
            for (BoardListener listener : listeners) {
                listener.boardChanged();
            }
        }
    }
}
