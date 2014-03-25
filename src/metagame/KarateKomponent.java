package metagame;

import sun.security.krb5.KrbAsReqBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Created by the karatekidz on 24/03/14.
 */
public class KarateKomponent extends JComponent implements BoardListener{
    private Board board;
    private KarateKlass karateKlass;
    private final int tileSize;
    private EnumMap<SquareType, Color> colorMap;
    private HashMap<Integer, Color> playerColorMap;
    private final Font myFont;

    public KarateKomponent(Board board, KarateKlass karateKlass) {
        this.board = board;
        this.tileSize = 80;
        createEnumMap();
        createHashMap();
        this.setBackground(Color.BLACK);
        myFont = new Font("Arial", Font.BOLD,30);
        this.karateKlass = karateKlass;


    }

    public Dimension getPreferredSize(){
        int xsize = board.getSize()*tileSize + 100;
        int ysize = board.getSize()*tileSize;
        return new Dimension(xsize,ysize);
    }

    private void createEnumMap(){
        colorMap = new EnumMap<>(SquareType.class);
        colorMap.put(SquareType.GOOD, Color.BLUE);
        colorMap.put(SquareType.BAD, Color.RED);
        colorMap.put(SquareType.KRYSTAL, Color.CYAN);
        colorMap.put(SquareType.KARATEKID, Color.GREEN);
    }

    private void createHashMap(){
        playerColorMap = new HashMap<>();
        playerColorMap.put(0,Color.YELLOW);
        playerColorMap.put(1, Color.GREEN);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,tileSize*board.getSize(),tileSize*board.getSize());
        //Draw board
        double radians = 2*Math.PI / board.getSize();
        int radius = 100;
        for (int i = 0; i < board.getSize(); i++) {

            int x = (int)(Math.cos(radians*i) * radius*2);
            int y = (int)(Math.sin(radians*i) * radius*2);


            g2d.setColor(Color.WHITE);
            g2d.drawOval(((board.getSize()*tileSize-radius)/2)+ x, ((board.getSize()*tileSize-radius)/2) + y, radius, radius);

        }





        //Draw player stuff

        g2d.setFont(myFont);
        for (Player player : board.getPlayers()) {
            g2d.setColor(player.getKolor());

            int x = (int)(Math.cos(radians*player.getSteps()) * radius*2);
            int y = (int)(Math.sin(radians*player.getSteps()) * radius*2);
            g2d.fillRect(
                    ((board.getSize()*tileSize)/2)+ x,
                    ((board.getSize()*tileSize)/2) + y + 10*player.getPlayerNumber(),
                    20, 20);

            g2d.drawString(player.getName(), tileSize * board.getSize() - 75, 50);
        }

        g2d.setColor(playerColorMap.get(karateKlass.getCurrentPlayer()));
        g2d.drawRect(40,10,50,50);
        g2d.drawString(String.valueOf(karateKlass.getDice_val()), 50,50);



    }

    @Override
    public void boardChanged() {
        repaint();
    }
}
