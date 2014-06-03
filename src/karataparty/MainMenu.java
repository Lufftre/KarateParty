/**
 * Main menu panel.
 * Contains a JPanel with start and exit buttons.
 */
package karataparty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ludvig on 2014-04-10.
 */
public class MainMenu extends JPanel implements ActionListener {
    protected JButton b1, b2;
    protected JPanel panel;
    protected KarateParty karateParty;

    //Takes KarateParty object for startGame press.
    public MainMenu(KarateParty kp){
        panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(200,200));
        b1 = new JButton("Karate TIMe");
        b1.setActionCommand("start");
        b2 = new JButton("Quit");
        b2.setActionCommand("quit");
        b1.addActionListener(this);
        b2.addActionListener(this);
        panel.add(b1);
        panel.add(b2);
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
