/**
 * Abstract Component Class
 * Used for all component classes.
 * Default font, HashMap with all(both) player colors.
 * Tilesize currently not used(?)
 * PreferredSize dimension currently set to 1000x800
 */
package karataparty;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by the karatekidz on 26/03/14.
 */
public abstract class AbstractComponent extends JComponent{

    protected Map<Integer, Color> playerColorMap = null;
    protected Font myFont;
    protected int DIM_X = 1000;
    protected int DIM_Y = 800;

    protected AbstractComponent(){
        //this.tileSize = 80;
        createHashMap();
        this.setBackground(Color.BLACK);
        myFont = new Font("Arial", Font.BOLD,30);

    }

    protected void createHashMap(){
        playerColorMap = new HashMap<>();
        playerColorMap.put(0,Color.YELLOW);
        playerColorMap.put(1, Color.GREEN);
    }

    public Dimension getPreferredSize(){
        return new Dimension(DIM_X, DIM_Y);
    }

    protected abstract void paintComponent(Graphics g);

    public void boardChanged(){
        repaint();
    }

}