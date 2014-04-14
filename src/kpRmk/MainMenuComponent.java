/**
 * Draws the background for the main menu.
 */
package kpRmk;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ludvig on 2014-04-10.
 */
public class MainMenuComponent extends AbstractComponent {
    private Image image;
    public MainMenuComponent(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

}
