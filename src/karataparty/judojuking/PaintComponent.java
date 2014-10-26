package karataparty.judojuking;

import karataparty.AbstractComponent;
import karataparty.AbstractMinigame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private Board board;

    private Action leftPress;
    private Action leftRelease;
    private Action rightPress;
    private Action rightRelease;
    private Action upPress;
    private Action upRelease;
    private Action downPress;
    private Action downRelease;

    private Action aPress;
    private Action aRelease;
    private Action dPress;
    private Action dRelease;
    private Action wPress;
    private Action wRelease;
    private Action sPress;
    private Action sRelease;


    public PaintComponent(AbstractMinigame board) {
        this.board = (Board)board;
        createActions();
        createKeybinds();
    }


    private void createKeybinds(){
	    //Left
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed LEFT"), "pressedLeft");
        this.getActionMap().put("pressedLeft", this.leftPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "releasedLeft");
        this.getActionMap().put("releasedLeft", this.leftRelease);
        //Right
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed RIGHT"), "pressedRight");
        this.getActionMap().put("pressedRight", this.rightPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "releasedRight");
        this.getActionMap().put("releasedRight", this.rightRelease);
        //Up
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed UP"), "pressedUp");
        this.getActionMap().put("pressedUp", this.upPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "releasedUp");
        this.getActionMap().put("releasedUp", this.upRelease);
        //Down
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed DOWN"), "pressedDown");
        this.getActionMap().put("pressedDown", this.downPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "releasedDown");
        this.getActionMap().put("releasedDown", this.downRelease);
        //A
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed A"), "pressedA");
        this.getActionMap().put("pressedA", this.aPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "releasedA");
        this.getActionMap().put("releasedA", this.aRelease);
        //D
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed D"), "pressedD");
        this.getActionMap().put("pressedD", this.dPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "releasedD");
        this.getActionMap().put("releasedD", this.dRelease);
        //W
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "pressedW");
        this.getActionMap().put("pressedW", this.wPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "releasedW");
        this.getActionMap().put("releasedW", this.wRelease);
        //S
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "pressedS");
        this.getActionMap().put("pressedS", this.sPress);
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "releasedS");
        this.getActionMap().put("releasedS", this.sRelease);
    }

    private void createActions(){
	    //LEFT
        this.leftPress = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
		board.leftPress();
            }
        };
	    this.leftRelease = new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
			board.leftRelease();
	            }
	        };
        //RIGHT
        this.rightPress = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
    board.rightPress();
         }
     };
        this.rightRelease = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
    board.rightRelease();
          }
      };
        //UP
        this.upPress = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
		board.upPress();
            }
        };
	    this.upRelease = new AbstractAction() {
	            public void actionPerformed(ActionEvent e) {
			board.upRelease();
	            }
	        };
        //DOWN
        this.downPress = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
    board.downPress();
         }
     };
        this.downRelease = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
    board.downRelease();
          }
      };
        //A
        this.aPress = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
    board.aPress();
         }
     };
        this.aRelease = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
    board.aRelease();
          }
      };
        //D
        this.dPress = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
    board.dPress();
         }
     };
        this.dRelease = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
    board.dRelease();
          }
      };
        //W
        this.wPress = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
    board.wPress();
         }
     };
        this.wRelease = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
    board.wRelease();
          }
      };
        //S
        this.sPress = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
    board.sPress();
         }
     };
        this.sRelease = new AbstractAction() {
          public void actionPerformed(ActionEvent e) {
    board.sRelease();
          }
      };
    }

    @Override
    public void paintComponent(Graphics g){
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,board.getWidth(),board.getHeight());

        for (Player player : board.getPlayers()) {
            if(player.isAlive()){
                g2d.setColor(playerColorMap.get(player.getNumber()));
            } else {
                g2d.setColor(Color.GRAY);
            }
            g2d.fillRect((int) player.getX(), (int) player.getY(), player.getSize(), player.getSize());
        }

        for (Enemy enemy : board.getEnemies()) {
            g2d.setColor(Color.RED);
            g2d.fillRect((int) enemy.getX(), (int) enemy.getY(), enemy.getSize(), enemy.getSize());
        }



    }

}
