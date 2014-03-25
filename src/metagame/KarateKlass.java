package metagame; /**
 * Created by the karatekidz on 24/03/14.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KarateKlass {

    private Random random;
    private Board board;

    private int phase;

    private boolean roll;
    private int dice_val;

    private int currentPlayer;

    private List<BoardListener> listeners;

    public KarateKlass(Board board) {
        this.random = new Random();
        this.board = board;
        this.phase = 1;
        this.roll = false;
        listeners = new ArrayList<>();
        this.currentPlayer = 0;
    }

    public int getPhase() {
        return phase;
    }

    public boolean isRoll() {
        return roll;
    }

    public int getDice_val() {
        return dice_val;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void space(){
        roll = true;
    }

    public void tick(){
        switch(phase){
            case 1:
                phase1();
            case 2:
                phase2();
        }
    }

    private void phase1(){
        if(!roll){
            dice_val = random.nextInt(12);
            notifyListeners();
        } else {
            board.getPlayers().get(currentPlayer).playerMove();
            dice_val--;
            notifyListeners();

            if(dice_val == 0){
                currentPlayer++;
                roll = false;
                if(currentPlayer == board.getPlayers().size()) phase++;
            }
        }

    }

    private void phase2(){

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
