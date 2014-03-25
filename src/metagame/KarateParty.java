package metagame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class KarateParty {
    private JFrame frame;
    private KarateKomponent komponent;

    private Board board;
    private KarateKlass karateKlass;
    private Timer timer;
    private Action tick;
    private Action space;

    public KarateParty() {
        createBoard();
        createFrame();
        createKarateKlass();
        createComponent();
        createActions();
        createTimer();
        createKeybinds();
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
        karateKlass.addBoardListener(komponent);
        frame.add(komponent);
        komponent.setPreferredSize(komponent.getPreferredSize());
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
                karateKlass.tick();
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
