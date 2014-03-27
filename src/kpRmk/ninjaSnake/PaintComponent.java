package kpRmk.ninjaSnake;

import kpRmk.AbstractComponent;
import kpRmk.Minigame;

import java.awt.*;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private Minigame board;

    public PaintComponent(Minigame board) {
        this.board = board;
    }

    @Override
    public void paintComponent(Graphics g){
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        for (Player player : board.getPlayers()) {
            g2d.fillRect(player.getX(), player.getY(), 10, 10);
        }



    }

}
