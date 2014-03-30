package kpRmk;

import kpRmk.ninjaSnake.Player;

import java.util.ArrayList;

/**
 * Created by ludno249 on 2014-03-27.
 */
public abstract class AbstractMinigame{
    public abstract int tick(AbstractComponent component);

    protected ArrayList<Player> players;
    public ArrayList<Player> getPlayers(){
        return players;
    };
}
