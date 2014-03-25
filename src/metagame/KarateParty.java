package metagame;

import main.SuperComponent;
import ninjaSnake.NinjaSnake;
import ninjaSnake.SnakeComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class KarateParty {
    private JFrame frame;
    private SuperComponent komponent;

    private Board board;
    private KarateKlass karateKlass;
    private Timer timer;
    private Action tick;
    private Action space;

    private boolean meta;

    private MiniGame minigame;
    private SuperComponent minigameComponent;

    public KarateParty() {
        createBoard();
        createFrame();
        createKarateKlass();
        createComponent();
        createActions();
        createTimer();
        createKeybinds();

        this.meta = true;

    }

    private void createBoard(){
        this.board = new Board();
    }

    private void createFrame(){
        this.frame = new JFrame();
        frame.setVisible(true);
    }

    private void createComponent(){
        this.komponent = new KarateKomponent(this.board, this.karateKlass);
        this.minigameComponent = new SnakeComponent();
        this.minigame = new NinjaSnake();

        karateKlass.addBoardListener(komponent);
        minigame.addBoardListener(minigameComponent);

        frame.add(minigameComponent);
        frame.add(komponent);

        komponent.setPreferredSize(komponent.getPreferredSize());
        minigameComponent.setPreferredSize(komponent.getPreferredSize());

        frame.pack();


    }

    private void createKarateKlass(){
        this.karateKlass = new KarateKlass(this.board);
    }

    private void createTimer(){
        this.timer = new Timer(200, this.tick);
        timer.setCoalesce(true);
        timer.start();
    }

    private void createActions(){
        this.tick = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if(meta){
                    if(karateKlass.tick()){
                        meta = false;
                        karateKlass.removeBoardListener(komponent);
                        karateKlass.addBoardListener(minigameComponent);

                    }
                } else {
                    if(minigame.tick()){meta = true;}
                }
            }
        };

        this.space = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                karateKlass.space();
            }
        };
    }

    private void createKeybinds(){
        komponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spacePress");
        komponent.getActionMap().put("spacePress", this.space);
    }

    public static void main(String[] args) {
        new KarateParty();
    }
}
