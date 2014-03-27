package metagame;

import java.util.List;

/**
 * Created by the karatekidz on 24/03/14.
 */
public interface MiniGame {

    public boolean tick();

    public void addBoardListener(BoardListener bl);

}
