/**
 * Main menu panel.
 * Contains a JPanel with start and exit buttons.
 */
package karataparty.mainmenu;

import karataparty.KarateParty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ludvig on 2014-04-10.
 */
public class MainMenu extends JPanel implements ActionListener {
    protected JButton buttonStart, buttonQuit;
    protected JPanel panel;
    protected KarateParty karateParty;

    //Takes KarateParty object for startGame press.
    public MainMenu(KarateParty kp){
        panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(200,200));
        buttonStart = new JButton("Karate Time!");
        buttonStart.setActionCommand("start");
        buttonQuit = new JButton("Quit");
        buttonQuit.setActionCommand("quit");
        buttonStart.addActionListener(this);
        buttonQuit.addActionListener(this);
        panel.add(buttonStart);
        panel.add(buttonQuit);
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
