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

    // --Commented out by Inspection (2014-05-16 13:53):protected int tileSize;
    protected Map<Integer, Color> playerColorMap = null;
    protected Font myFont;
    @SuppressWarnings({"InstanceVariableNamingConvention", "NonConstantFieldWithUpperCaseName"})
    protected int DIM_X,DIM_Y;

    protected AbstractComponent(){
        //this.tileSize = 80;
        createHashMap();
        this.setBackground(Color.BLACK);
        myFont = new Font("Arial", Font.BOLD,30);
        this.DIM_X = 1000;
        this.DIM_Y = 800;

    }

    protected void createHashMap(){
        playerColorMap = new HashMap<>();
        playerColorMap.put(0,Color.YELLOW);
        playerColorMap.put(1, Color.GREEN);
    }

    @SuppressWarnings("RefusedBequest")
    public Dimension getPreferredSize(){
        /*
        int xsize = i*tileSize + 100;
        int ysize = i*tileSize;
        return new Dimension(xsize,ysize);
        */
        return new Dimension(DIM_X, DIM_Y);
    }

    @SuppressWarnings("AbstractMethodOverridesConcreteMethod")
    protected abstract void paintComponent(Graphics g);

    public void boardChanged(){
        repaint();
    }

}