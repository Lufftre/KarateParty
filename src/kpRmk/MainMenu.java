/**
 * Main menu panel.
 * Contains a JPanel with start and exit buttons.
 */
package kpRmk;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ludvig on 2014-04-10.
 */
public class MainMenu extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;
    protected JPanel panel;
    protected KarateParty karateParty;

    //Takes KarateParty object for startGame press.
    public MainMenu(KarateParty kp){
        panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(200,200));
        b1 = new JButton("Karate TIMe");
        b1.setActionCommand("start");
        b3 = new JButton("Quit");
        b3.setActionCommand("quit");
        b1.addActionListener(this);
        b3.addActionListener(this);
        panel.add(b1);
        panel.add(b3);
        add(panel);
        this.karateParty = kp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("start".equals(e.getActionCommand())) {
            karateParty.startGame();
        } else if("quit".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }
}
