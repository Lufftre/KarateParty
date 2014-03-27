package kpRmk;

import kpRmk.ninjaSnake.Player;

import java.util.ArrayList;

/**
 * Created by the karatekidz on 26/03/14.
 */
public interface Minigame {

    public boolean tick(AbstractComponent component);
    public ArrayList<Player> getPlayers();

}
