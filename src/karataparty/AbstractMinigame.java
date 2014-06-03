/**
 * Abstract MiniGame Class
 * tick method returns -1 until it returns the winning players player number
 * resetBoard method to be called after minigame is over
 */
package karataparty;

/**
 * Created by ludno249 on 2014-03-27.
 */
public abstract class AbstractMinigame{
    public abstract int tick(AbstractComponent component);
    public abstract void resetBoard();

}
