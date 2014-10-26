package karataparty.metagame;

import karataparty.AbstractComponent;

import java.awt.*;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private Board board;
    private final int boardWidth, boardHeight;
    private final int centerPosX, centerPosY;
    private static final int PLAYER_SIZE = 20;
    private static final int KRYSTAL_WIDTH = 15;
    private static final int KRYSTAL_HEIGHT = 25;
    private static final int LEFT_COLUMN_ALIGN = 180;
    private static final int RIGHT_COLUMN_ALIGN = 80;
    private static final int ROW_SPACING = 100;
    private static final int HEADER_ALIGN = 40;
    private static final int DIE_X = 40;
    private static final int DIE_Y = 10;
    private static final int DIE_SIZE = 50;
    private static final int DIE_TEXT_POS = 50;
    private static final int N_ROUNDS_Y_ALIGN = 100;
    private Graphics2D g2d = null;


    public PaintComponent(Board board){
        this.board = board;
        this.boardWidth = 800;
        this.boardHeight = 800;

        this.centerPosX = boardWidth/2;
        this.centerPosY = boardHeight/2;

    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,boardWidth,boardHeight);
        //Draw board
        double radians = 2*Math.PI / board.getSize();
        int radiusBig = (int)((boardWidth/2) - (((boardWidth/2)*Math.PI)/board.getSize()));
        int radiusSmall = (int)((radiusBig*Math.PI)/board.getSize());
        for (int i = 0; i < board.getSize(); i++) {

            int x = (int)(Math.cos(radians*i) * radiusBig);
            int y = (int)(Math.sin(radians*i) * radiusBig);


            g2d.setColor(Color.WHITE);
            g2d.drawOval(x + ((boardWidth/2)-radiusSmall), y + ((boardHeight/2)-radiusSmall), radiusSmall*2, radiusSmall*2);

        }

        //Draw Krystal
        {
        int x = (int)(Math.cos(radians*board.getKrystal()) * radiusBig);
        int y = (int)(Math.sin(radians*board.getKrystal()) * radiusBig);
            g2d.setColor(Color.CYAN);
        g2d.fillOval(x + ((boardWidth/2)-radiusSmall) + PLAYER_SIZE /2,y + ((boardHeight/2)-radiusSmall) + PLAYER_SIZE /2, KRYSTAL_WIDTH, KRYSTAL_HEIGHT);
        }


        //Draw player stuff

        g2d.setFont(myFont);
        for (Player player : board.getPlayers()) {
            //Draw Player
            g2d.setColor(playerColorMap.get(player.getNumber()));
            int x = (int)(Math.cos(radians*player.getSteps()) * radiusBig);
            int y = (int)(Math.sin(radians*player.getSteps()) * radiusBig);
            g2d.fillRect(x + ((boardWidth/2)-radiusSmall) + PLAYER_SIZE /2,y + ((boardHeight/2)-radiusSmall) + PLAYER_SIZE /2, PLAYER_SIZE, PLAYER_SIZE);
            //Draw Score
            g2d.drawString(String.valueOf(player.getName()),getWidth()- LEFT_COLUMN_ALIGN, ROW_SPACING *(1+player.getNumber()) - HEADER_ALIGN);
            g2d.setColor(Color.RED);
            g2d.drawString(String.valueOf(player.getKoins()),getWidth()- LEFT_COLUMN_ALIGN, ROW_SPACING *(1+player.getNumber()));
            g2d.drawString(String.valueOf(player.getKrystals()),getWidth()- RIGHT_COLUMN_ALIGN, ROW_SPACING *(1+player.getNumber()));

        }
        //Draw Die
        g2d.setColor(Color.WHITE);
        g2d.drawRect(DIE_X, DIE_Y, DIE_SIZE, DIE_SIZE);
        g2d.drawString(String.valueOf(board.getDiceVal()), DIE_TEXT_POS, DIE_TEXT_POS);

        //Draw round number
        g2d.setColor(Color.RED);
        g2d.drawString(String.valueOf(board.getnRounds() - board.getCurrentRound()), centerPosX,centerPosY);
        g2d.setColor(Color.WHITE);
        g2d.drawString(board.getNextMiniGame().toString(),centerPosX,centerPosY+ N_ROUNDS_Y_ALIGN);

    }
}
