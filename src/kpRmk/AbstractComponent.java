package kpRmk;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by the karatekidz on 26/03/14.
 */
public abstract class AbstractComponent extends JComponent{

    protected int tileSize;
    protected HashMap<Integer, Color> playerColorMap;
    protected Font myFont;

    public AbstractComponent(){
        this.tileSize = 80;
        createHashMap();
        this.setBackground(Color.BLACK);
        myFont = new Font("Arial", Font.BOLD,30);
    }

    protected void createHashMap(){
        playerColorMap = new HashMap<>();
        playerColorMap.put(0,Color.YELLOW);
        playerColorMap.put(1, Color.GREEN);
    }

    public Dimension getPreferredSize(int i){
        int xsize = i*tileSize + 100;
        int ysize = i*tileSize;
        return new Dimension(xsize,ysize);
    }

    protected abstract void paintComponent(Graphics g);

    public void boardChanged(){
        repaint();
    };

}