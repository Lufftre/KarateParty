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
    protected static final int DIMX = 1000;
    protected static final int DIMY = 800;
    protected static final int FONTSIZE = 30;

    protected AbstractComponent(){
        createHashMap();
        this.setBackground(Color.BLACK);
        myFont = new Font("Arial", Font.BOLD, FONTSIZE);

    }

    protected void createHashMap(){
        playerColorMap = new HashMap<>();
        playerColorMap.put(0,Color.YELLOW);
        playerColorMap.put(1, Color.GREEN);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DIMX, DIMY);
    }

    public void boardChanged(){
        repaint();
    }

}