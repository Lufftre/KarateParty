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
    private Graphics2D g2d;
    private boolean doPrint;
    //private Action space;

    public PaintComponent(Board board){
        this.board = board;
        this.boardWidth = 800;
        this.boardHeight = 800;
        this.scoreboardWidth = 200;

        this.centerPosX = boardWidth/2;
        this.centerPosY = boardHeight/2;
        this.doPrint = false;

        //createActions();
       // createKeybinds();
    }
/*
    private void createKeybinds(){
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spacePress");
        this.getActionMap().put("spacePress", this.space);
    }
    */
/*
    private void createActions(){
        this.space = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                board.spacePress();
            }
        };
    }
    */

    public void announceMinigame(){
        doPrint = true;
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
        g2d.fillOval(x + ((800/2)-radiusSmall) + 10,y + ((800/2)-radiusSmall) + 10,15,25);
        }


        //Draw player stuff

        g2d.setFont(myFont);
        for (Player player : board.getPlayers()) {
            //Draw Player
            g2d.setColor(playerColorMap.get(player.getNumber()));
            int x = (int)(Math.cos(radians*player.getSteps()) * radiusBig);
            int y = (int)(Math.sin(radians*player.getSteps()) * radiusBig);
            g2d.fillRect(x + ((800/2)-radiusSmall) + 10,y + ((800/2)-radiusSmall) + 10,20,20);
            //Draw Score
            g2d.drawString(String.valueOf(player.getName()),getWidth()-180 ,100*(1+player.getNumber()) - 40);
            g2d.setColor(Color.RED);
            g2d.drawString(String.valueOf(player.getKoins()),getWidth()-180 ,100*(1+player.getNumber()));
            g2d.drawString(String.valueOf(player.getKrystals()),getWidth()-80 ,100*(1+player.getNumber()));

        }
        //Draw Die
        g2d.setColor(Color.WHITE);
        g2d.drawRect(40,10,50,50);
        g2d.drawString(String.valueOf(board.getDiceVal()), 50,50);

        //Draw round number
        g2d.setColor(Color.RED);
        g2d.drawString(String.valueOf(board.getnRounds() - board.getCurrentRound()), centerPosX,centerPosY);
        g2d.setColor(Color.WHITE);
        g2d.drawString(board.getNextMiniGame().toString(),centerPosX,centerPosY+100);

    }
}