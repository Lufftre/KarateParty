/**
 * Abstract Player Class to be used in all Mini Games.
 * Contains a integer for playernumber.
 */
package karataparty;

/**
 * Created by the karatekidz on 26/03/14.
 */
@SuppressWarnings("AbstractClassWithoutAbstractMethods")
public abstract class AbstractPlayer {
    protected int number;
    public int getNumber() {
        return number;
    }
}
