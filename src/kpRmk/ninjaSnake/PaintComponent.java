package kpRmk.ninjaSnake;

import kpRmk.AbstractComponent;
import kpRmk.AbstractMinigame;
import kpRmk.Minigame;
import kpRmk.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by the karatekidz on 26/03/14.
 */
public class PaintComponent extends AbstractComponent {
    private AbstractMinigame board;

    private Action leftPress;
    private Action leftRelease;
    private Action rightPress;
    private Action rightRelease;

    private Action aPress;
    private Action aRelease;
    private Action dPress;
    private Action dRelease;


    public PaintComponent(AbstractMinigame board) {
        this.board = board;
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
    }

    @Override
    public void paintComponent(Graphics g){
        final Graphics2D g2d = (Graphics2D) g;
        int size = 10;


        for (Player player : board.getPlayers()) {
	    g2d.setColor(playerColorMap.get(player.getNumber()));
            g2d.fillRect((int) player.getX(), (int) player.getY(), size, size);
        }

	g2d.setColor(Color.GRAY);
	for (int x = 0; x < board.getWidth(); x++) {
	    for (int y = 0; y < board.getHeight(); y++) {
		if(board.getMapPoint(new Position(x,y))){
		    g2d.fillRect(x, y, size, size);
		}
	    }
	      
	}



    }

}
