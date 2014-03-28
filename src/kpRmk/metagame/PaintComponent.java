package kpRmk.metagame;

import kpRmk.AbstractComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private Board board;
    private Action space;

    public PaintComponent(Board b){
        this.board = b;
        createActions();
        createKeybinds();
    }

    private void createKeybinds(){
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spacePress");
        this.getActionMap().put("spacePress", this.space);
    }

    private void createActions(){
        this.space = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                board.spacePress();
            }
        };
    }


    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,800,800);
        //Draw board
        double radians = 2*Math.PI / board.getSize();
        int radiusBig = (int)((800/2) - (((800/2)*Math.PI)/board.getSize()));
        int radiusSmall = (int)((radiusBig*Math.PI)/board.getSize());
        for (int i = 0; i < board.getSize(); i++) {

            int x = (int)(Math.cos(radians*i) * radiusBig);
            int y = (int)(Math.sin(radians*i) * radiusBig);


            g2d.setColor(Color.WHITE);
            g2d.drawOval(x + ((800/2)-radiusSmall), y + ((800/2)-radiusSmall), radiusSmall*2, radiusSmall*2);

        }

        //Draw player stuff

        g2d.setFont(myFont);
        for (Player player : board.getPlayers()) {
            //Draw Player
            g2d.setColor(Color.GREEN);
            int x = (int)(Math.cos(radians*player.getSteps()) * radiusBig);
            int y = (int)(Math.sin(radians*player.getSteps()) * radiusBig);
            g2d.fillRect(x + ((800/2)-radiusSmall) + 10,y + ((800/2)-radiusSmall) + 10,20,20);
            //Draw Score
            g2d.setColor(Color.RED);
            g2d.drawString(String.valueOf(player.getKoins()),getWidth()-100 ,100*(1+player.getNumber()));

        }
        //Draw Die
        g2d.setColor(Color.WHITE);
        g2d.drawRect(40,10,50,50);
        g2d.drawString(String.valueOf(board.getDiceVal()), 50,50);

    }
}
