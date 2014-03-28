package kpRmk;

import kpRmk.ninjaSnake.Player;

import java.util.ArrayList;

/**
 * Created by ludno249 on 2014-03-27.
 */
public abstract class AbstractMinigame{
    public abstract int tick(AbstractComponent component);
    public abstract ArrayList<Player> getPlayers();

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract boolean[][] getMap();
    public abstract boolean getMapPoint(Position position);

    public abstract void leftPress();
    public abstract void leftRelease();
    public abstract void rightPress();
    public abstract void rightRelease();
    public abstract void aPress();
    public abstract void aRelease();
    public abstract void dPress();
    public abstract void dRelease();

}
