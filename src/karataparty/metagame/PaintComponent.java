package karataparty.metagame;

import karataparty.AbstractComponent;

import java.awt.*;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private Board board;
    private final int boardWidth, boardHeight;
    private final int scoreboardWidth;
    private final int centerPosX, centerPosY;
    private final int playerSize = 20;
    private final int krystalWidth = 15;
    private final int krystalHeight = 25;
    private final int leftColumnAlign = 180;
    private final int rightColumnAlign = 80;
    private final int rowSpacing = 100;
    private final int headerAlign = 40;
    private final int dieX = 10;
    private final int dieY = 40;
    private final int dieSize = 50;
    private final int dieTextPos = 50;
    private Graphics2D g2d;


    public PaintComponent(Board board){
        this.board = board;
        this.boardWidth = 800;
        this.boardHeight = 800;
        this.scoreboardWidth = 200;

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
        g2d.fillOval(x + ((boardWidth/2)-radiusSmall) + playerSize/2,y + ((boardHeight/2)-radiusSmall) + playerSize/2,krystalWidth,krystalHeight);
        }


        //Draw player stuff

        g2d.setFont(myFont);
        for (Player player : board.getPlayers()) {
            //Draw Player
            g2d.setColor(playerColorMap.get(player.getNumber()));
            int x = (int)(Math.cos(radians*player.getSteps()) * radiusBig);
            int y = (int)(Math.sin(radians*player.getSteps()) * radiusBig);
            g2d.fillRect(x + ((boardWidth/2)-radiusSmall) + playerSize/2,y + ((boardHeight/2)-radiusSmall) + playerSize/2,playerSize,playerSize);
            //Draw Score
            g2d.drawString(String.valueOf(player.getName()),getWidth()-leftColumnAlign ,rowSpacing*(1+player.getNumber()) - headerAlign);
            g2d.setColor(Color.RED);
            g2d.drawString(String.valueOf(player.getKoins()),getWidth()-leftColumnAlign ,rowSpacing*(1+player.getNumber()));
            g2d.drawString(String.valueOf(player.getKrystals()),getWidth()-rightColumnAlign ,rowSpacing*(1+player.getNumber()));

        }
        //Draw Die
        g2d.setColor(Color.WHITE);
        g2d.drawRect(dieX,dieY,dieSize,dieSize);
        g2d.drawString(String.valueOf(board.getDiceVal()), dieTextPos,dieTextPos);

        //Draw round number
        g2d.setColor(Color.RED);
        g2d.drawString(String.valueOf(board.getnRounds() - board.getCurrentRound()), centerPosX,centerPosY);
        g2d.setColor(Color.WHITE);
        g2d.drawString(board.getNextMiniGame().toString(),centerPosX,centerPosY+100);

    }
}
