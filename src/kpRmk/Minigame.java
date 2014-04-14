/**
 * Was used earlier.
 * Not in use.
 *
 */
package kpRmk;

import kpRmk.ninjaSnake.Player;

import java.util.ArrayList;

/**
 * Created by the karatekidz on 26/03/14.
 */
public interface Minigame {

    public boolean tick(AbstractComponent component);
    public ArrayList<Player> getPlayers();

    public int getWidth();
    public int getHeight();
    public boolean[][] getMap();

    public void leftPress();
    public void leftRelease();
    public void rightPress();
    public void rightRelease();
    public void aPress();
    public void aRelease();
    public void dPress();
    public void dRelease();
}
