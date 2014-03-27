package kpRmk;

import kpRmk.metagame.Board;
import kpRmk.metagame.PaintComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class KarateParty {
    private JFrame frame;

    private PaintComponent metaComponent;
    private AbstractComponent miniComponent;

    private Board metaBoard;
    private Minigame miniBoard;
    private Timer timer;
    private Action tick;
    private Action space;

    private boolean meta;

    public KarateParty() {
        createFrame();
        createBoard();
        createMetaComponent();
        createActions();
        createTimer();
        createKeybinds();

        this.meta = true;

    }

    private void createBoard(){
        this.metaBoard = new Board(2);
    }

    private void createFrame(){
        this.frame = new JFrame();
        frame.setVisible(true);
    }
    private void createMetaComponent(){
        metaComponent = new PaintComponent(this.metaBoard);
        frame.add(metaComponent);
        metaComponent.setPreferredSize(metaComponent.getPreferredSize(metaBoard.getSize()));

        frame.pack();
    }

    private void setMiniComponent(AbstractComponent component){
        this.miniComponent = component;
        frame.remove(metaComponent);
        frame.add(miniComponent);

        miniComponent.setPreferredSize(miniComponent.getPreferredSize(metaBoard.getSize()));
        frame.pack();
    }
    private void setMiniBoard(Minigame minigame){
        this.miniBoard = minigame;
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
                    if(metaBoard.tick(metaComponent)){
                        meta = false;
                        setMiniBoard(new kpRmk.ninjaSnake.Board());
                        setMiniComponent(new kpRmk.ninjaSnake.PaintComponent(miniBoard));
                    }
                } else {
                    if(miniBoard.tick(miniComponent)){
                        meta = true;
                    }
                }
            }
        };

        this.space = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                metaBoard.spacePress();
            }
        };
    }

    private void createKeybinds(){
        metaComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spacePress");
        metaComponent.getActionMap().put("spacePress", this.space);
    }

    public static void main(String[] args) {
        new KarateParty();
    }
}
