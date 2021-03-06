package karataparty.sumoBird;

import karataparty.AbstractComponent;
import karataparty.AbstractMinigame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 29/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private Board board;

    private Action upPress;


    private Action wPress;

    public PaintComponent(AbstractMinigame board){
        this.board = (Board)board;
        createActions();
        createKeybinds();
    }

    private void createKeybinds(){
        //Up
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "pressedUp");
        this.getActionMap().put("pressedUp", this.upPress);
        //W
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "pressedW");
        this.getActionMap().put("pressedW", this.wPress);
    }

    private void createActions(){
        //Up
        this.upPress = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                board.upPress();
            }
        };
        //W
        this.wPress = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                board.wPress();
            }
        };
    }

    protected void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        for (Pipe pipe : board.getPipes()) {
            g2d.setColor(Color.GRAY);
            g2d.fillRect(board.getWidth() - pipe.getX(),0,pipe.getWidth(),pipe.getGapY());
            g2d.fillRect(board.getWidth() - pipe.getX(),pipe.getGapY()+pipe.getGapHeight(),pipe.getWidth(),board.getHeight()-pipe.getGapY());
        }


        for (Player player : board.getPlayers()) {

            if(player.isAlive()){
                g2d.setColor(playerColorMap.get(player.getNumber()));
            } else{
                g2d.setColor(Color.GRAY);
            }
            g2d.fillRect(100,player.getY(),player.getWidth(),player.getHeight());
            g2d.setColor(Color.BLACK);
            g2d.fillOval(115,player.getY()+10,5,5);
        }
    }
}
