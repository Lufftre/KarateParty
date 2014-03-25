package main;

import metagame.*;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Created by the karatekidz on 25/03/14.
 */
public class SuperComponent extends JComponent implements BoardListener {
    protected int tileSize;
    protected EnumMap<SquareType, Color> colorMap;
    protected HashMap<Integer, Color> playerColorMap;
    protected Font myFont;

    public SuperComponent(){
        this.tileSize = 80;
        createEnumMap();
        createHashMap();
        this.setBackground(Color.BLACK);
        myFont = new Font("Arial", Font.BOLD,30);
    }

    protected void createEnumMap(){
        colorMap = new EnumMap<>(SquareType.class);
        colorMap.put(SquareType.GOOD, Color.BLUE);
        colorMap.put(SquareType.BAD, Color.RED);
        colorMap.put(SquareType.KRYSTAL, Color.CYAN);
        colorMap.put(SquareType.KARATEKID, Color.GREEN);
    }

    protected void createHashMap(){
        playerColorMap = new HashMap<>();
        playerColorMap.put(0,Color.YELLOW);
        playerColorMap.put(1, Color.GREEN);
    }

    @Override
    public void boardChanged() {
        validate();
        repaint();
    }
}
